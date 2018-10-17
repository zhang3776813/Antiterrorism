package com.whfp.anti_terrorism.bean;

import java.util.List;

/**
 * Created by 张明杨 on 2018-05-03-0003.
 */

public class LoginBean {


    /**
     * data : {"userInfo":{"area":"402881ec3f74d2d5013f74dc42ac1c66","deptId":"40288101632013c801632412816c0000","errorCount":0,"factoryName":"武汉丰普科技有限公司","id":"40288101632013c80163241281aa0001","idCard":"420525199308130834","lastLoginIp":"192.168.18.153","lastLoginTime":1525328090776,"mobilePhoneNumber":"17671462708","registerTime":1525318582000,"registerUid":"402881f73e1c4ba4013e1c4c08470001","trueName":"张明杨","userName":"zhang3776813","userPassword":"7G130917AF30BE5E203D7157AF0AACGA","userSex":1,"userStatus":1},"userRoles":["serviceAgent"]}
     * message : 请求成功！
     * statusCode : 300
     */

    private DataBean data;
    private String message;
    private String statusCode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public static class DataBean {
        /**
         * userInfo : {"area":"402881ec3f74d2d5013f74dc42ac1c66","deptId":"40288101632013c801632412816c0000","errorCount":0,"factoryName":"武汉丰普科技有限公司","id":"40288101632013c80163241281aa0001","idCard":"420525199308130834","lastLoginIp":"192.168.18.153","lastLoginTime":1525328090776,"mobilePhoneNumber":"17671462708","registerTime":1525318582000,"registerUid":"402881f73e1c4ba4013e1c4c08470001","trueName":"张明杨","userName":"zhang3776813","userPassword":"7G130917AF30BE5E203D7157AF0AACGA","userSex":1,"userStatus":1}
         * userRoles : ["serviceAgent"]
         */

        private UserInfoBean userInfo;
        private List<String> userRoles;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<String> getUserRoles() {
            return userRoles;
        }

        public void setUserRoles(List<String> userRoles) {
            this.userRoles = userRoles;
        }

        public static class UserInfoBean {
            /**
             * area : 402881ec3f74d2d5013f74dc42ac1c66
             * deptId : 40288101632013c801632412816c0000
             * errorCount : 0
             * factoryName : 武汉丰普科技有限公司
             * id : 40288101632013c80163241281aa0001
             * idCard : 420525199308130834
             * lastLoginIp : 192.168.18.153
             * lastLoginTime : 1525328090776
             * mobilePhoneNumber : 17671462708
             * registerTime : 1525318582000
             * registerUid : 402881f73e1c4ba4013e1c4c08470001
             * trueName : 张明杨
             * userName : zhang3776813
             * userPassword : 7G130917AF30BE5E203D7157AF0AACGA
             * userSex : 1
             * userStatus : 1
             */

            private String area;
            private String deptId;
            private int errorCount;
            private String factoryName;
            private String id;
            private String idCard;
            private String lastLoginIp;
            private long lastLoginTime;
            private String mobilePhoneNumber;
            private long registerTime;
            private String registerUid;
            private String trueName;
            private String userName;
            private String userPassword;
            private int userSex;
            private int userStatus;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

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

            public String getFactoryName() {
                return factoryName;
            }

            public void setFactoryName(String factoryName) {
                this.factoryName = factoryName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
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
}
