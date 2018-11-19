package com.whfp.anti_terrorism.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.adapter.GridImageAdapter;
import com.whfp.anti_terrorism.basic.BaseCallback;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.SJSBBean;
import com.whfp.anti_terrorism.utils.FullyGridLayoutManager;
import com.whfp.anti_terrorism.utils.PictureSelectorUtils;
import com.whfp.anti_terrorism.utils.PreferencesUtils;
import com.whfp.anti_terrorism.utils.StatusBarUtils;
import com.whfp.anti_terrorism.validation.Mobile;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 通用事件上报
 * Created by 张明杨 on 2018-06-14-0014.
 */
@ContentView(R.layout.activity_tysjsb)
public class SJSBActivity extends BasicActivity implements EasyPermissions.PermissionCallbacks, Validator.ValidationListener {

    //上报人职务
    @ViewInject(R.id.et_sbrzw)
    @NotEmpty(message = "上报人职务不可为空")
    @Order(1)
    private EditText et_sbrzw;

    //上报人电话
    @ViewInject(R.id.et_sbrdh)
    @Mobile
    @Order(2)
    private EditText et_sbrdh;

    //上报地址
    @ViewInject(R.id.et_sbdz)
    @NotEmpty(message = "上报人地址不可为空")
    @Order(3)
    private EditText et_sbdz;

    //事件描述
    @ViewInject(R.id.et_sjms)
    @NotEmpty(message = "上报人姓名不可为空")
    @Order(4)
    private EditText et_sjms;

    //照片列表
    @ViewInject(R.id.rv_image_site)
    private RecyclerView rv_image_site;

    //上报按钮
    @ViewInject(R.id.btn_submit)
    private Button btn_submit;

    //展示已选择图片九宫格的适配器
    private GridImageAdapter adapter;

    //最多可选择几张
    private int SELECTOR_MAX = 6;

    //需要申请的权限
    String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    //请求码
    private final static int PER_PHOTO_SELETRO = 101;//申请权限的请求码
    private final static int REQUEST_SELECT_PHOTO_XCZP = 105;//选择现场照片的请求码

    //选择照片返回的地址
    private List<String> pathXCZP;//现场照片列表路径集合
    private List<LocalMedia> list_data = new ArrayList<>();//现场照片列表原始路径集合

    //传递过来的事件类型
    private String eventType;

    //表单验证工具对象
    private Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式状态栏
        StatusBarUtils.with(this)
                .init();
    }

    /**
     * 判断权限是否已获取
     *
     * @return
     */
    private boolean getPermission() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            //已经申请过权限
            return true;
        } else {
            //没有申请过权限，现在去申请
            EasyPermissions.requestPermissions(this, "选择照片需要SD卡读写权限，拍照需要用到相机权限，拒绝将会导致无法正常使用，请允许",
                    PER_PHOTO_SELETRO, perms);
            return false;
        }
    }

    @Override
    protected void init() {
        super.init();
        validator = new Validator(this);
        validator.setValidationListener(this);
        //注册手机号验证注解类
        Validator.registerAnnotation(Mobile.class);

        eventType = getIntent().getStringExtra(Constants.SJSB_TYPE);
        setTitleText(getIntent().getStringExtra(Constants.SJSB_TITLE));

        //初始化适配器
        adapter = new GridImageAdapter(SJSBActivity.this, onAddPicClickListener);
        //设置数据
        adapter.setList(list_data);
        //设置最多可选择几张
        adapter.setSelectMax(SELECTOR_MAX);
        //设置布局管理器
        FullyGridLayoutManager manager = new FullyGridLayoutManager(SJSBActivity.this, 3, GridLayoutManager.VERTICAL, false);
        rv_image_site.setLayoutManager(manager);
        //绑定适配器
        rv_image_site.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (list_data.size() > 0) {
                    LocalMedia media = list_data.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(SJSBActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, list_data);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(SJSBActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(SJSBActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    private void httpReport() {
        startRxLodingDialog();
        httpUtils.doEVENT_REPORT(eventType, PreferencesUtils.getUserId(context), et_sbrzw.getText().toString().trim(),
                et_sbrdh.getText().toString().trim(), et_sbdz.getText().toString().trim(), et_sjms.getText().toString().trim(), list_data,
                new BaseCallback(context, Constants.SJSB) {
                    @Override
                    public void showDatas(Object obj) {
                        super.showDatas(obj);
                        stopRxLodingDialog();
                        SJSBBean sjsbBean = (SJSBBean) obj;
                        if (sjsbBean != null && sjsbBean.getCode().equals("200"))
                            showAlertDialog("提示", sjsbBean.getMsg(), "确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    finish();
                                }
                            });
                        else
                            RxToast.error(sjsbBean.getMsg());
                    }
                });
    }

    @Event(value = R.id.btn_submit)
    private void OnClick(View v) {
        switch (v.getId()) {

            case R.id.btn_submit://上报
                RxLogTool.i(list_data.toString());
                validator.validate();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清除缓存，包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
        PictureFileUtils.deleteCacheDirFile(SJSBActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_SELECT_PHOTO_XCZP) {//现场照片
            list_data = PictureSelector.obtainMultipleResult(data);
            adapter.setList(list_data);
            adapter.notifyDataSetChanged();
        } else {
            RxLogTool.e("SJSBActivity：请求数据失败或者用户取消");
        }
    }

    /**
     * 多选图片的添加点击事件
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            if (getPermission())
                PictureSelectorUtils.startPictureSelector(SJSBActivity.this, SELECTOR_MAX, list_data, REQUEST_SELECT_PHOTO_XCZP);
        }
    };


    /**
     * 权限申请回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case PER_PHOTO_SELETRO:
                RxToast.success("权限申请成功！");
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //处理权限名字字符串
        StringBuffer sb = new StringBuffer();
        for (String str : perms) {
            sb.append(str);
            sb.append("\n");
        }
        sb.replace(sb.length() - 2, sb.length(), "");

        switch (requestCode) {
            case PER_PHOTO_SELETRO:
                RxToast.error("已拒绝权限" + sb.toString(), Toast.LENGTH_LONG);
                break;
        }
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show();
            new AppSettingsDialog
                    .Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("是")
                    .setNegativeButton("否")
                    .build()
                    .show();
        } else {
            // 请求失败，执行相应操作
            RxToast.error("权限申请失败");
            finish();
        }
    }

    /**
     * 验证成功
     */
    @Override
    public void onValidationSucceeded() {
        httpReport();
    }

    /**
     * 验证失败
     *
     * @param errors
     */
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }
        }
    }
}
