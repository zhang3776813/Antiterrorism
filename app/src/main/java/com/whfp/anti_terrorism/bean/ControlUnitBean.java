package com.whfp.anti_terrorism.bean;

import java.util.List;

/**
 * Created by 张明杨 on 2018-10-25-0025.
 */

public class ControlUnitBean {

    /**
     * code : 200
     * data : [{"createTime":1529989521098,"indexCode":"42112304002160708266","name":"罗田县环保局","parentIndexCode":"0","parentTree":"0","unitLevel":1,"unitType":2,"updateTime":1540434106159},{"createTime":1530065961053,"indexCode":"42112304002160943881","name":"物价局","parentIndexCode":"42112304","parentTree":"42112304","unitLevel":5,"unitType":2,"updateTime":1540434106159},{"createTime":1530065061047,"indexCode":"42","name":"湖北省","parentIndexCode":"0","parentTree":"0","unitLevel":1,"unitType":1,"updateTime":1540434106159},{"createTime":1530065061048,"indexCode":"4211","name":"黄冈市","parentIndexCode":"42","parentTree":"42","unitLevel":2,"unitType":1,"updateTime":1540434106159},{"createTime":1530065061049,"indexCode":"421123","name":"罗田县","parentIndexCode":"4211","parentTree":"4211","unitLevel":3,"unitType":1,"updateTime":1540434106159},{"createTime":1530065061050,"indexCode":"42112304","name":"大型单位","parentIndexCode":"421123","parentTree":"421123","unitLevel":4,"unitType":1,"updateTime":1540434106159},{"createTime":1531361601057,"indexCode":"42112304002160593603","name":"123","parentIndexCode":"0","parentTree":"0","unitLevel":1,"unitType":2,"updateTime":1540434106159},{"createTime":1379320437000,"description":"系统初始化时创建","indexCode":"0","name":"罗田公安局内保反恐项目","parentIndexCode":"-1","parentTree":"-1","unitLevel":0,"unitType":1,"updateTime":1540434106159}]
     * msg : 成功
     * page : {"page":0,"size":50,"total":8}
     */

    private String code;
    private String msg;
    private PageBean page;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * page : 0
         * size : 50
         * total : 8
         */

        private int page;
        private int size;
        private int total;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class DataBean {
        /**
         * createTime : 1529989521098
         * indexCode : 42112304002160708266
         * name : 罗田县环保局
         * parentIndexCode : 0
         * parentTree : 0
         * unitLevel : 1
         * unitType : 2
         * updateTime : 1540434106159
         * description : 系统初始化时创建
         */

        private long createTime;
        private String indexCode;
        private String name;
        private String parentIndexCode;
        private String parentTree;
        private int unitLevel;
        private int unitType;
        private long updateTime;
        private String description;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getIndexCode() {
            return indexCode;
        }

        public void setIndexCode(String indexCode) {
            this.indexCode = indexCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentIndexCode() {
            return parentIndexCode;
        }

        public void setParentIndexCode(String parentIndexCode) {
            this.parentIndexCode = parentIndexCode;
        }

        public String getParentTree() {
            return parentTree;
        }

        public void setParentTree(String parentTree) {
            this.parentTree = parentTree;
        }

        public int getUnitLevel() {
            return unitLevel;
        }

        public void setUnitLevel(int unitLevel) {
            this.unitLevel = unitLevel;
        }

        public int getUnitType() {
            return unitType;
        }

        public void setUnitType(int unitType) {
            this.unitType = unitType;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
