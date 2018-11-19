package com.whfp.anti_terrorism.bean;

/**
 * 获取校车WEb平台的key实体类
 * Created by 张明杨 on 2018-11-02-0002.
 */

public class SchoolKeyBean {

    /**
     * data : {"key":"zT908g2j9niUElD%2FoItNSkcrqHq74dTI%2BmUpGocefXU%3D"}
     * errorcode : 200
     */

    private DataBean data;
    private int errorcode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public static class DataBean {
        /**
         * key : zT908g2j9niUElD%2FoItNSkcrqHq74dTI%2BmUpGocefXU%3D
         */

        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
