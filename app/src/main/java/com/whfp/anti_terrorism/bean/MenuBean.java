package com.whfp.anti_terrorism.bean;

/**
 * 主页九宫格菜单实体类
 * Created by 张明杨 on 2018-06-13-0013.
 */

public class MenuBean {
    //图片
    private int img;
    //文字
    private String text;


    public MenuBean(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
