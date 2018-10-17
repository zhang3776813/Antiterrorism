package com.whfp.anti_terrorism.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * 照片选择器工具类，需配合implementation 'com.github.LuckSiege.PictureSelector:picture_library:v2.2.3'库才可使用
 * Created by 张明杨 on 2018-06-15-0015.
 */
public class PictureSelectorUtils {

    /**
     * 启动图片选择器（单选）
     *
     * @param activity
     * @param requestCode
     */
    public static void startPictureSelector(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
//                        .theme()//不设置样式则为默认的黑色样式
                .maxSelectNum(1)//最大图片选择数量
                .selectionMode(PictureConfig.SINGLE)//单选或多选
                .isCamera(true)//是否显示拍照图片
                .enableCrop(false)//是否裁剪
                .imageSpanCount(3)//照片显示列数
                .sizeMultiplier(0.7f) //glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                        .glideOverride(150, 150)//图片加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .compress(true)//是否压缩
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(200)// 小于200kb的图片不压缩
                .forResult(requestCode);//结果回调onActivityResult code
    }


    /**
     * 启动图片选择器（单选）
     *
     * @param fragment
     * @param requestCode
     */
    public static void startPictureSelector(Fragment fragment, int requestCode) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
//                        .theme()//不设置样式则为默认的黑色样式
                .maxSelectNum(1)//最大图片选择数量
                .selectionMode(PictureConfig.SINGLE)//单选或多选
                .isCamera(true)//是否显示拍照图片
                .enableCrop(false)//是否裁剪
                .imageSpanCount(3)//照片显示列数
                .sizeMultiplier(0.7f) //glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                        .glideOverride(150, 150)//图片加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .compress(true)//是否压缩
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(200)// 小于200kb的图片不压缩
                .forResult(requestCode);//结果回调onActivityResult code
    }

    /**
     * 启动图片选择器（多选）
     *
     * @param activity
     * @param maxSelectNum 最大图片选择数量
     * @param requestCode
     */
    public static void startPictureSelector(Activity activity, int maxSelectNum, List<LocalMedia> list_data, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
//                        .theme()//不设置样式则为默认的黑色样式
                .maxSelectNum(maxSelectNum)//最大图片选择数量
                .selectionMode(PictureConfig.MULTIPLE)//单选或多选
                .isCamera(true)//是否显示拍照图片
                .enableCrop(false)//是否裁剪
                .imageSpanCount(3)//照片显示列数
                .sizeMultiplier(0.7f) //glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                        .glideOverride(150, 150)//图片加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .compress(true)//是否压缩
                .selectionMedia(list_data)// 是否传入已选图片
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(200)// 小于200kb的图片不压缩
                .forResult(requestCode);//结果回调onActivityResult code
    }

    /**
     * 启动图片选择器（多选）
     *
     * @param fragment
     * @param maxSelectNum 最大图片选择数量
     * @param requestCode
     */
    public static void startPictureSelector(Fragment fragment, int maxSelectNum, List<LocalMedia> list_data, int requestCode) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
//                        .theme()//不设置样式则为默认的黑色样式
                .maxSelectNum(maxSelectNum)//最大图片选择数量
                .selectionMode(PictureConfig.MULTIPLE)//单选或多选
                .isCamera(true)//是否显示拍照图片
                .enableCrop(false)//是否裁剪
                .imageSpanCount(3)//照片显示列数
                .sizeMultiplier(0.7f) //glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                        .glideOverride(150, 150)//图片加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .compress(true)//是否压缩
                .selectionMedia(list_data)// 是否传入已选图片
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(200)// 小于200kb的图片不压缩
                .forResult(requestCode);//结果回调onActivityResult code
    }


    // 进入相册 以下是例子：用不到的api可以不写
    private void lz(Activity activity) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(true)// 是否可预览视频 true or false
                .enablePreviewAudio(true) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .glideOverride(150, 150)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(16, 9)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .compressSavePath("")//压缩图片保存地址
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(true)// 是否开启点击声音 true or false
                .selectionMedia(new ArrayList<LocalMedia>())// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .cropWH(16, 9)// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .videoQuality(1)// 视频录制质量 0 or 1 int
                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                .recordVideoSecond(60)//视频秒数录制 默认60s int
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


//    结果回调
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case PictureConfig.CHOOSE_REQUEST:
//                    // 图片、视频、音频选择结果回调
//                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
//                    // 例如 LocalMedia 里面返回三种path
//                    // 1.media.getPath(); 为原图path
//                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
//                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
//                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    adapter.setList(selectList);
//                    adapter.notifyDataSetChanged();
//                    break;
//            }
//        }
//    }


//    常用功能
//            启动相册并拍照
//
// PictureSelector.create(MainActivity.this)
//            .openGallery(PictureMimeType.ofImage())
//            .forResult(PictureConfig.CHOOSE_REQUEST);
//
//    单独启动拍照或视频 根据PictureMimeType自动识别
//
//  PictureSelector.create(MainActivity.this)
//            .openCamera(PictureMimeType.ofImage())
//            .forResult(PictureConfig.CHOOSE_REQUEST);
//    预览图片
//
//// 预览图片 可自定长按保存路径
//*注意 .themeStyle(themeId)；不可少，否则闪退...
//
//            PictureSelector.create(MainActivity.this).themeStyle(themeId).openExternalPreview(position, "/custom_file", selectList);
//PictureSelector.create(MainActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
//    预览视频
//PictureSelector.create(MainActivity.this).externalPictureVideo(video_path);

}
