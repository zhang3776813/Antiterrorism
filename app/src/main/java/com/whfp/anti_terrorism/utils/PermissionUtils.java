package com.whfp.anti_terrorism.utils;

import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 权限申请工具类，需引入implementation 'pub.devrel:easypermissions:1.2.0'库才可正常使用
 * Created by 张明杨 on 2018-06-19-0019.
 */

public class PermissionUtils {

    //相机+SD卡读写权限
    public static String[] PERMS_CAMERA_AND_EXTERNAL_STORAGE = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    /**
     * 获取相机+SD卡读写权限
     *
     * @param host        Activity
     * @param requestCode 请求码
     * @return 是否已申请成功
     */
    public static boolean getPermissionCameraAndExternalStorage(@NonNull Activity host, int requestCode) {
        if (EasyPermissions.hasPermissions(host, PERMS_CAMERA_AND_EXTERNAL_STORAGE)) {
            //已经申请过权限
            return true;
        } else {
            //没有申请过权限，现在去申请
            EasyPermissions.requestPermissions(host, "选择照片需要SD卡读写权限，拍照需要用到相机权限，拒绝将会导致无法正常使用，请允许",
                    requestCode, PERMS_CAMERA_AND_EXTERNAL_STORAGE);
            return false;
        }
    }

    /**
     * 获取相机+SD卡读写权限
     *
     * @param host        Fragment
     * @param requestCode 请求码
     * @return 是否已申请成功
     */
    public static boolean getPermissionCameraAndExternalStorage(@NonNull Fragment host, int requestCode) {
        if (EasyPermissions.hasPermissions(host.getActivity(), PERMS_CAMERA_AND_EXTERNAL_STORAGE)) {
            //已经申请过权限
            return true;
        } else {
            //没有申请过权限，现在去申请
            EasyPermissions.requestPermissions(host, "选择照片需要SD卡读写权限，拍照需要用到相机权限，拒绝将会导致无法正常使用，请允许",
                    requestCode, PERMS_CAMERA_AND_EXTERNAL_STORAGE);
            return false;
        }
    }


    /**
     * 结果回调处理
     * 需要Activity或Fragment实现 EasyPermissions.PermissionCallbacks
     */

//    /**
//     * 权限申请回调中调用 EasyPermissions.PermissionCallbacks的实现
//     * @param requestCode
//     * @param permissions
//     * @param grantResults
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }

//    @Override
//    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        switch (requestCode) {
//            case PER_PHOTO_SELETRO:
//                RxToast.success("权限申请成功！");
//                break;
//        }
//    }

//    @Override
//    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
//        //处理权限名字字符串
//        StringBuffer sb = new StringBuffer();
//        for (String str : perms) {
//            sb.append(str);
//            sb.append("\n");
//        }
//        sb.replace(sb.length() - 2, sb.length(), "");
//
//        switch (requestCode) {
//            case PER_PHOTO_SELETRO:
//                RxToast.error("已拒绝权限" + sb.toString(), Toast.LENGTH_LONG);
//                break;
//        }
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show();
//            new AppSettingsDialog
//                    .Builder(this)
//                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
//                    .setPositiveButton("是")
//                    .setNegativeButton("否")
//                    .build()
//                    .show();
//        } else {
//            // 请求失败，执行相应操作
//            RxToast.error("权限申请失败");
//            finish();
//        }
//    }
}
