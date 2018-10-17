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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.adapter.GridImageAdapter;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.FullyGridLayoutManager;
import com.whfp.anti_terrorism.utils.PictureSelectorUtils;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 散装汽油加油报告
 * Created by 张明杨 on 2018-06-14-0014.
 */
@ContentView(R.layout.activity_szqyjybg)
public class SZQYJYBGActivity extends BasicActivity implements EasyPermissions.PermissionCallbacks {

    //派出所证明照片
    @ViewInject(R.id.iv_pcszm)
    private ImageView iv_pcszm;

    //身份证正面照片
    @ViewInject(R.id.iv_id_card_z)
    private ImageView iv_id_card_z;

    //身份证反面照片
    @ViewInject(R.id.iv_id_card_f)
    private ImageView iv_id_card_f;

    //现场照片列表
    @ViewInject(R.id.rv_image_site)
    private RecyclerView rv_image_site;

    //加油量
    @ViewInject(R.id.et_jyl)
    private EditText et_jyl;

    //加油用途
    @ViewInject(R.id.et_jyyt)
    private EditText et_jyyt;

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
    private final static int REQUEST_SELECT_PHOTO_PCSZM = 102;//选择派出所证明照片的请求码
    private final static int REQUEST_SELECT_PHOTO_SFZZM = 103;//选择身份证正面照片的请求码
    private final static int REQUEST_SELECT_PHOTO_SFZFM = 104;//选择身份证反面照片的请求码
    private final static int REQUEST_SELECT_PHOTO_XCZP = 105;//选择现场照片的请求码

    //选择照片返回的地址
    private String pathPCSZM;//派出所证明照片路径
    private String pathSFZZM;//身份证正面照片路径
    private String pathSFZFM;//身份证反面照片路径
    private List<String> pathXCZP;//现场照片列表路径集合
    private List<LocalMedia> list_data = new ArrayList<>();//现场照片列表原始路径集合


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
        setTitleText("散装汽油加油报告");
        //初始化适配器
        adapter = new GridImageAdapter(SZQYJYBGActivity.this, onAddPicClickListener);
        //设置数据
        adapter.setList(list_data);
        //设置最多可选择几张
        adapter.setSelectMax(SELECTOR_MAX);
        //设置布局管理器
        FullyGridLayoutManager manager = new FullyGridLayoutManager(SZQYJYBGActivity.this, 3, GridLayoutManager.VERTICAL, false);
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
                            PictureSelector.create(SZQYJYBGActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, list_data);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(SZQYJYBGActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(SZQYJYBGActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    @Event(value = {R.id.iv_pcszm, R.id.iv_id_card_z, R.id.iv_id_card_f, R.id.btn_submit})
    private void OnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_pcszm://选择派出所证明照片
                if (getPermission())
                    PictureSelectorUtils.startPictureSelector(SZQYJYBGActivity.this, REQUEST_SELECT_PHOTO_PCSZM);
                break;
            case R.id.iv_id_card_z://选择身份证正面照片
                if (getPermission())
                    PictureSelectorUtils.startPictureSelector(SZQYJYBGActivity.this, REQUEST_SELECT_PHOTO_SFZZM);
                break;
            case R.id.iv_id_card_f://选择身份证反面照片
                if (getPermission())
                    PictureSelectorUtils.startPictureSelector(SZQYJYBGActivity.this, REQUEST_SELECT_PHOTO_SFZFM);
                break;
            case R.id.btn_submit://上报
                showAlertDialog("提示", "上报成功", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                RxLogTool.i(list_data.toString());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清除缓存，包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
        PictureFileUtils.deleteCacheDirFile(SZQYJYBGActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String path = "";
            if (requestCode != REQUEST_SELECT_PHOTO_XCZP) {
                LocalMedia localMediaPath = PictureSelector.obtainMultipleResult(data).get(0);
                path = localMediaPath.getCompressPath();
                logI("获取到的图片Uri地址是：" + path);
            } else {//现场照片
                list_data = PictureSelector.obtainMultipleResult(data);
                adapter.setList(list_data);
                adapter.notifyDataSetChanged();
            }
            switch (requestCode) {
                case REQUEST_SELECT_PHOTO_PCSZM://派出所证明
                    pathPCSZM = path;
                    Glide.with(SZQYJYBGActivity.this).load(pathPCSZM).into(iv_pcszm);
                    break;
                case REQUEST_SELECT_PHOTO_SFZZM://身份证正面
                    pathSFZZM = path;
                    Glide.with(SZQYJYBGActivity.this).load(pathSFZZM).into(iv_id_card_z);
                    break;
                case REQUEST_SELECT_PHOTO_SFZFM://身份证反面
                    pathSFZFM = path;
                    Glide.with(SZQYJYBGActivity.this).load(pathSFZFM).into(iv_id_card_f);
                    break;
            }
        } else {
            RxLogTool.e("SZQYJYBGActivity：请求数据失败或者用户取消");
        }
    }

    /**
     * 多选图片的添加点击事件
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            if (getPermission())
                PictureSelectorUtils.startPictureSelector(SZQYJYBGActivity.this, SELECTOR_MAX, list_data, REQUEST_SELECT_PHOTO_XCZP);
        }
    };


    /**
     * 权限申请回调
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
}
