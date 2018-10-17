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
 * 校园走失情况上报
 * Created by wantao on 2018/6/14.
 */
@ContentView(R.layout.activity_xyzssb)
public class XYZSSBActivity extends BasicActivity implements EasyPermissions.PermissionCallbacks, GridImageAdapter.OnItemClickListener {

    //走失人姓名
    @ViewInject(R.id.et_zsrxm)
    private EditText et_zsrxm;

    //走失时间
    @ViewInject(R.id.et_zssj)
    private EditText et_zssj;

    //走失人职位
    @ViewInject(R.id.et_zsrzw)
    private EditText et_zsrzw;

    //上报按钮
    @ViewInject(R.id.btn_submit)
    private Button btn_submit;


    //用户已选中的照片列表
    @ViewInject(R.id.rv_recycler)
    private RecyclerView rv_recycler;

    //需要申请的权限
    String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    //请求码
    private final static int PER_PHOTO_SELETRO = 11;//申请权限的请求码
    private final static int REQUEST_SELECT_PHOTO_ZSZP = 12;//走失照片的请求码

    private List<LocalMedia> list_data = new ArrayList<>();//走失人照片列表原始路径集合
    private GridImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("校园走失情况上报");
        //初始化图片选择GridRecycler
        initGridRecycler();
    }

    /**
     * GridRecycler
     */
    private void initGridRecycler() {
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(list_data);
        adapter.setSelectMax(3);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
        rv_recycler.setLayoutManager(manager);
        rv_recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }


    /**
     * 多选图片的添加点击事件
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            if (getPermission())
                PictureSelectorUtils.startPictureSelector(XYZSSBActivity.this, 3, list_data, REQUEST_SELECT_PHOTO_ZSZP);
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

    /**
     * 已选图片的点击事件(即GridRecycler Item的点击事件)
     */
    @Override
    public void onItemClick(int position, View v) {
        if (list_data.size() > 0) {
            LocalMedia media = list_data.get(position);
            String pictureType = media.getPictureType();
            int mediaType = PictureMimeType.pictureToVideo(pictureType);
            switch (mediaType) {
                case 1:
                    // 预览图片 可自定长按保存路径
                    PictureSelector.create(XYZSSBActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, list_data);
                    break;
                case 2:
                    // 预览视频
                    PictureSelector.create(XYZSSBActivity.this).externalPictureVideo(media.getPath());
                    break;
                case 3:
                    // 预览音频
                    PictureSelector.create(XYZSSBActivity.this).externalPictureAudio(media.getPath());
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_SELECT_PHOTO_ZSZP://走失人照片
                    list_data = PictureSelector.obtainMultipleResult(data);
                    adapter.setList(list_data);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    /**
     * 请求拍照权限
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case PER_PHOTO_SELETRO:
                RxToast.success("权限申请成功！请继续您的操作！");
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
