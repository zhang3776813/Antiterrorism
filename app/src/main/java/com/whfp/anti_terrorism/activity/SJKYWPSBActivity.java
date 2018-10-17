package com.whfp.anti_terrorism.activity;

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
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.adapter.GridImageAdapter;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.FullyGridLayoutManager;
import com.whfp.anti_terrorism.utils.PermissionUtils;
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
 * 收寄可疑物品上报
 * Created by 张明杨 on 2018-06-19-0019.
 */
@ContentView(R.layout.activity_sjkywpsb)
public class SJKYWPSBActivity extends BasicActivity implements EasyPermissions.PermissionCallbacks {

    //收寄人姓名
    @ViewInject(R.id.et_sjrxm)
    private EditText et_sjrxm;

    //收寄人电话
    @ViewInject(R.id.et_sjrdh)
    private EditText et_sjrdh;

    //收寄人地址
    @ViewInject(R.id.et_sjrdz)
    private EditText et_sjrdz;

    //收寄数量
    @ViewInject(R.id.et_sjsl)
    private EditText et_sjsl;

    //已选择的照片九宫格列表
    @ViewInject(R.id.rv_recycler)
    private RecyclerView rv_recycler;

    //上报按钮
    @ViewInject(R.id.btn_submit)
    private Button btn_submit;

    //展示已选择图片九宫格的适配器
    private GridImageAdapter adapter;

    //最多可选择几张
    private int SELECTOR_MAX = 6;

    //选择照片返回的地址
    private List<String> path_data;//现场照片列表路径集合
    private List<LocalMedia> list_data = new ArrayList<>();//现场照片列表原始路径集合

    //请求码
    private final static int PER_PHOTO_SELETRO = 101;//申请权限的请求码
    private final static int REQUEST_SELECT_PHOTO_KYWP = 102;//选择可疑物品照片的请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式状态栏
        StatusBarUtils.with(this)
                .init();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("收寄可疑物品上报");
        initGridImageAdapter();
    }


    /**
     * 初始化可增删图片的九宫格图片展示适配器
     */
    private void initGridImageAdapter() {
        //初始化适配器
        adapter = new GridImageAdapter(SJKYWPSBActivity.this, onAddPicClickListener);
        //设置数据
        adapter.setList(list_data);
        //设置最多可选择几张
        adapter.setSelectMax(SELECTOR_MAX);
        //设置布局管理器
        FullyGridLayoutManager manager = new FullyGridLayoutManager(SJKYWPSBActivity.this, 3, GridLayoutManager.VERTICAL, false);
        rv_recycler.setLayoutManager(manager);
        //绑定适配器
        rv_recycler.setAdapter(adapter);
        //设置Item点击事件
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
                            PictureSelector.create(SJKYWPSBActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, list_data);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(SJKYWPSBActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(SJKYWPSBActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }


    /**
     * 多选按钮的添加事件
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            if (PermissionUtils.getPermissionCameraAndExternalStorage(SJKYWPSBActivity.this, PER_PHOTO_SELETRO))
                PictureSelectorUtils.startPictureSelector(SJKYWPSBActivity.this, SELECTOR_MAX, list_data, REQUEST_SELECT_PHOTO_KYWP);
        }
    };


    @Event(value = R.id.btn_submit)
    private void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit://上报
                showAlertDialog("提示", "上报成功", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_SELECT_PHOTO_KYWP://可疑物品照片
                    list_data = PictureSelector.obtainMultipleResult(data);
                    adapter.setList(list_data);
                    adapter.notifyDataSetChanged();
                    break;
            }
        } else {
            RxLogTool.e("SZQYJYBGActivity：请求数据失败或者用户取消");
        }
    }

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
