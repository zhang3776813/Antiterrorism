package com.whfp.anti_terrorism.bean;

/**
 * Created by 张明杨 on 2018-05-03-0003.
 */

public class LoginBean {


    /**
     * dept : {"deptAddress":"","deptDesc":"","deptFax":"","deptName":"武汉丰普科技有限公司","deptPhone":"","deptSort":1,"id":"297ef8ea5aa66c88015aa67bac930003","leadUid":"","superId":"0"}
     * message : 登录成功
     * statusCode : 200
     * user : {"deptId":"297ef8ea5aa66c88015aa67bac930003","errorCount":0,"id":"402881f73e1c4ba4013e1c4c08470001","lastLoginIp":"127.0.0.1","lastLoginTime":1540193937132,"mobilePhoneNumber":"18615200092","orgId":"402881f73e1c5181013e1c56da3c0002","registerTime":1366274344000,"registerUid":"0","trueName":"超级管理员","type":"0","userDesc":"拥有系统最高权限!","userName":"fpadmin","userPassword":"H1AF2G39C90F59F00H5DHA574BA4EE3H","userSex":1,"userStatus":1}
     */

    private DeptBean dept;
    private String message;
    private int statusCode;
    private UserBean user;

    public DeptBean getDept() {
        return dept;
    }

    public void setDept(DeptBean dept) {
        this.dept = dept;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class DeptBean {
        /**
         * deptAddress :
         * deptDesc :
         * deptFax :
         * deptName : 武汉丰普科技有限公司
         * deptPhone :
         * deptSort : 1
         * id : 297ef8ea5aa66c88015aa67bac930003
         * leadUid :
         * superId : 0
         */

        private String deptAddress;
        private String deptDesc;
        private String deptFax;
        private String deptName;
        private String deptPhone;
        private int deptSort;
        private String id;
        private String leadUid;
        private String superId;

        public String getDeptAddress() {
            return deptAddress;
        }

        public void setDeptAddress(String deptAddress) {
            this.deptAddress = deptAddress;
        }

        public String getDeptDesc() {
            return deptDesc;
        }

        public void setDeptDesc(String deptDesc) {
            this.deptDesc = deptDesc;
        }

        public String getDeptFax() {
            return deptFax;
        }

        public void setDeptFax(String deptFax) {
            this.deptFax = deptFax;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getDeptPhone() {
            return deptPhone;
        }

        public void setDeptPhone(String deptPhone) {
            this.deptPhone = deptPhone;
        }

        public int getDeptSort() {
            return deptSort;
        }

        public void setDeptSort(int deptSort) {
            this.deptSort = deptSort;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLeadUid() {
            return leadUid;
        }

        public void setLeadUid(String leadUid) {
            this.leadUid = leadUid;
        }

        public String getSuperId() {
            return superId;
        }

        public void setSuperId(String superId) {
            this.superId = superId;
        }
    }

    public static class UserBean {
        /**
         * deptId : 297ef8ea5aa66c88015aa67bac930003
         * errorCount : 0
         * id : 402881f73e1c4ba4013e1c4c08470001
         * lastLoginIp : 127.0.0.1
         * lastLoginTime : 1540193937132
         * mobilePhoneNumber : 18615200092
         * orgId : 402881f73e1c5181013e1c56da3c0002
         * registerTime : 1366274344000
         * registerUid : 0
         * trueName : 超级管理员
         * type : 0
         * userDesc : 拥有系统最高权限!
         * userName : fpadmin
         * userPassword : H1AF2G39C90F59F00H5DHA574BA4EE3H
         * userSex : 1
         * userStatus : 1
         */

        private String deptId;
        private int errorCount;
        private String id;
        private String lastLoginIp;
        private long lastLoginTime;
        private String mobilePhoneNumber;
        private String orgId;
        private long registerTime;
        private String registerUid;
        private String trueName;
        private String type;
        private String userDesc;
        private String userName;
        private String userPassword;
        private int userSex;
        private int userStatus;

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public int getErrorCount() {
            return errorCount;
        }

        public void setErrorCount(int errorCount) {
            this.errorCount = errorCount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLastLoginIp() {
            return lastLoginIp;
        }

        public void setLastLoginIp(String lastLoginIp) {
            this.lastLoginIp = lastLoginIp;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getMobilePhoneNumber() {
            return mobilePhoneNumber;
        }

        public void setMobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public long getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(long registerTime) {
            this.registerTime = registerTime;
        }

        public String getRegisterUid() {
            return registerUid;
        }

        public void setRegisterUid(String registerUid) {
            this.registerUid = registerUid;
        }

        public String getTrueName() {
            return trueName;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserDesc() {
            return userDesc;
        }

        public void setUserDesc(String userDesc) {
            this.userDesc = userDesc;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public int getUserSex() {
            return userSex;
        }

        public void setUserSex(int userSex) {
            this.userSex = userSex;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }
    }
}
