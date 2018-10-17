package com.whfp.anti_terrorism.bean;

/**
 * 历史监控录像列表
 * Created by 张明杨 on 2018-06-14-0014.
 */

public class MonitorHistoryBean {
    //录像名称
    private String videoName;
    //录像时间
    private String time;
    //录像预览图
    private int videoPreviewImage;

    public MonitorHistoryBean(String videoName, String time, int videoPreviewImage) {
        this.videoName = videoName;
        this.time = time;
        this.videoPreviewImage = videoPreviewImage;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getVideoPreviewImage() {
        return videoPreviewImage;
    }

    public void setVideoPreviewImage(int videoPreviewImage) {
        this.videoPreviewImage = videoPreviewImage;
    }
}
