package com.whfp.anti_terrorism.bean;

import java.util.List;

/**
 * 校车列表实体类
 * Created by 张明杨 on 2018-10-16-0016.
 */

public class SchoolBusListBean {

    private List<DeviceBean> device;

    public List<DeviceBean> getDevice() {
        return device;
    }

    public void setDevice(List<DeviceBean> device) {
        this.device = device;
    }

    public static class DeviceBean {
        /**
         * carlicence : 鄂J77677
         * channelcount : 4
         * cname :
         * deviceid : 008B002AA0
         * devicepassword : admin
         * devicetype : 4
         * deviceusername : admin
         * en : 15
         * groupid : 3
         * linktype : 124
         * registerip : 119.96.239.107
         * registerport : 5556
         * remark :
         * transmitip : 119.96.239.107
         * transmitport : 17891
         */

        private String carlicence;
        private int channelcount;
        private String cname;
        private String deviceid;
        private String devicepassword;
        private String devicetype;
        private String deviceusername;
        private int en;
        private int groupid;
        private String linktype;
        private String registerip;
        private int registerport;
        private String remark;
        private String transmitip;
        private int transmitport;

        //设备在线状态  1为在线   0为离线
        private int status = 0;

        public String getCarlicence() {
            return carlicence;
        }

        public void setCarlicence(String carlicence) {
            this.carlicence = carlicence;
        }

        public int getChannelcount() {
            return channelcount;
        }

        public void setChannelcount(int channelcount) {
            this.channelcount = channelcount;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getDevicepassword() {
            return devicepassword;
        }

        public void setDevicepassword(String devicepassword) {
            this.devicepassword = devicepassword;
        }

        public String getDevicetype() {
            return devicetype;
        }

        public void setDevicetype(String devicetype) {
            this.devicetype = devicetype;
        }

        public String getDeviceusername() {
            return deviceusername;
        }

        public void setDeviceusername(String deviceusername) {
            this.deviceusername = deviceusername;
        }

        public int getEn() {
            return en;
        }

        public void setEn(int en) {
            this.en = en;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public String getLinktype() {
            return linktype;
        }

        public void setLinktype(String linktype) {
            this.linktype = linktype;
        }

        public String getRegisterip() {
            return registerip;
        }

        public void setRegisterip(String registerip) {
            this.registerip = registerip;
        }

        public int getRegisterport() {
            return registerport;
        }

        public void setRegisterport(int registerport) {
            this.registerport = registerport;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTransmitip() {
            return transmitip;
        }

        public void setTransmitip(String transmitip) {
            this.transmitip = transmitip;
        }

        public int getTransmitport() {
            return transmitport;
        }

        public void setTransmitport(int transmitport) {
            this.transmitport = transmitport;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
