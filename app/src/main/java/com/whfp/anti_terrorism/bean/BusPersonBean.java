package com.whfp.anti_terrorism.bean;

/**
 * 乘车人员 Bean
 * Created by wantao on 2018/6/15.
 */

public class BusPersonBean {
    private String Name; //名字
    private String identity; //身份

    public BusPersonBean() {
    }

    public BusPersonBean(String name, String identity) {
        Name = name;
        this.identity = identity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
