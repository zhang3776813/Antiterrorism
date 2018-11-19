package com.whfp.anti_terrorism.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 校车列表实体类
 * Created by 张明杨 on 2018-10-16-0016.
 */

public class SchoolBusListBean implements Parcelable{


    /**
     * device : [{"carlicence":"鄂J77677","channelcount":4,"cname":"","deviceid":"008B002AA0","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J77707","channelcount":4,"cname":"","deviceid":"008B002989","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J76867","channelcount":4,"cname":"","deviceid":"008B002891","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73316","channelcount":4,"cname":"","deviceid":"008B00292D","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73319","channelcount":4,"cname":"","deviceid":"008B0029BF","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73018","channelcount":4,"cname":"","deviceid":"008B002994","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73205","channelcount":4,"cname":"","deviceid":"008B0029B0","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73201","channelcount":4,"cname":"","deviceid":"008B00291D","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73207","channelcount":4,"cname":"","deviceid":"008B0029A2","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73203","channelcount":4,"cname":"","deviceid":"008B0029B3","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73202","channelcount":4,"cname":"","deviceid":"008B002870","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73062","channelcount":4,"cname":"","deviceid":"008B002AA3","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72095","channelcount":4,"cname":"","deviceid":"008B002886","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72105","channelcount":4,"cname":"","deviceid":"008B002810","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73318","channelcount":4,"cname":"","deviceid":"008B00284E","devicepassword":"admin","devicetype":"4","deviceusername":"admin","en":15,"groupid":3,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73176","channelcount":4,"cname":"","deviceid":"008B002E2B","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72608","channelcount":4,"cname":"","deviceid":"008B002E8B","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73110","channelcount":4,"cname":"","deviceid":"008B00308E","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72606","channelcount":4,"cname":"","deviceid":"008B003056","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72916","channelcount":4,"cname":"","deviceid":"008B002DFB","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73187","channelcount":4,"cname":"","deviceid":"008B002D27","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72535","channelcount":4,"cname":"","deviceid":"008B002DE8","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73023","channelcount":4,"cname":"","deviceid":"008B002E75","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J77977","channelcount":4,"cname":"","deviceid":"008B003006","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72667","channelcount":4,"cname":"","deviceid":"008B002D38","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J71658","channelcount":4,"cname":"","deviceid":"008B002E97","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73000","channelcount":4,"cname":"","deviceid":"008B002E05","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72387","channelcount":4,"cname":"","deviceid":"008B002DB0","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72036","channelcount":4,"cname":"","deviceid":"008B002D95","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73113","channelcount":4,"cname":"","deviceid":"008B002E48","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72972","channelcount":4,"cname":"","deviceid":"008B002CF1","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73132","channelcount":4,"cname":"","deviceid":"008B002D1E","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73032","channelcount":4,"cname":"","deviceid":"008B002CE4","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73166","channelcount":4,"cname":"","deviceid":"008B003039","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73012","channelcount":4,"cname":"","deviceid":"008B001FC6","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72828","channelcount":4,"cname":"","deviceid":"008B0031CA","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J77778","channelcount":4,"cname":"","deviceid":"008B002FD6","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73168","channelcount":4,"cname":"","deviceid":"008B002E6B","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72697","channelcount":4,"cname":"","deviceid":"008B002DDC","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73179","channelcount":4,"cname":"","deviceid":"008B002DD7","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73123","channelcount":4,"cname":"","deviceid":"008B0031D8","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J78797","channelcount":4,"cname":"","deviceid":"008B003048","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J78799","channelcount":4,"cname":"","deviceid":"008B002DD9","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J77758","channelcount":4,"cname":"","deviceid":"008B002D59","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":7,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72988","channelcount":4,"cname":"","deviceid":"006002360D","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J79780","channelcount":4,"cname":"","deviceid":"0060023785","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72317","channelcount":4,"cname":"","deviceid":"00600236D5","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J78517","channelcount":4,"cname":"","deviceid":"006002347E","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J78232","channelcount":4,"cname":"","deviceid":"00600233C5","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72556","channelcount":4,"cname":"","deviceid":"006002366A","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72197","channelcount":4,"cname":"","deviceid":"00600279B4","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72827","channelcount":4,"cname":"","deviceid":"006002788B","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73218","channelcount":4,"cname":"","deviceid":"006002787C","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72362","channelcount":4,"cname":"","deviceid":"0060027998","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂JNF181","channelcount":4,"cname":"","deviceid":"0060027843","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72118","channelcount":4,"cname":"","deviceid":"00600279A1","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73463","channelcount":4,"cname":"","deviceid":"0060027B59","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":10,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J77771","channelcount":4,"cname":"","deviceid":"008B004BAA","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J77986","channelcount":4,"cname":"","deviceid":"008B004A77","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J78768","channelcount":4,"cname":"","deviceid":"008B004BB3","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72863","channelcount":4,"cname":"","deviceid":"008B0049D5","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J70608","channelcount":4,"cname":"","deviceid":"008B004985","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J77706","channelcount":4,"cname":"","deviceid":"008B004A0C","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J77768","channelcount":4,"cname":"","deviceid":"008B004988","devicepassword":"","devicetype":"4","deviceusername":"","en":-1,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72665","channelcount":4,"cname":"","deviceid":"008B0049C5","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J72860","channelcount":4,"cname":"","deviceid":"008B004A65","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J73866","channelcount":4,"cname":"","deviceid":"008B004A60","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J77770","channelcount":4,"cname":"","deviceid":"008B00496C","devicepassword":"","devicetype":"4","deviceusername":"","en":-1,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891},{"carlicence":"鄂J70639","channelcount":4,"cname":"","deviceid":"008B004996","devicepassword":"","devicetype":"4","deviceusername":"","en":15,"groupid":12,"linktype":"124","registerip":"119.96.239.107","registerport":5556,"remark":"","transmitip":"119.96.239.107","transmitport":17891}]
     * errorcode : 0
     * group : [{"groupfatherid":0,"groupid":1,"groupname":"中心","remark":""},{"groupfatherid":1,"groupid":10,"groupname":"宸新建材","remark":""},{"groupfatherid":1,"groupid":2,"groupname":"罗田校车","remark":""},{"groupfatherid":2,"groupid":12,"groupname":"大崎校车公司","remark":""},{"groupfatherid":2,"groupid":7,"groupname":"凤山校车公司","remark":""},{"groupfatherid":2,"groupid":3,"groupname":"九资河校车公司","remark":""}]
     * response : null
     */

    private int errorcode;
    private Object response;
    private List<DeviceBean> device;
    private List<GroupBean> group;

    protected SchoolBusListBean(Parcel in) {
        errorcode = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(errorcode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SchoolBusListBean> CREATOR = new Creator<SchoolBusListBean>() {
        @Override
        public SchoolBusListBean createFromParcel(Parcel in) {
            return new SchoolBusListBean(in);
        }

        @Override
        public SchoolBusListBean[] newArray(int size) {
            return new SchoolBusListBean[size];
        }
    };

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public List<DeviceBean> getDevice() {
        return device;
    }

    public void setDevice(List<DeviceBean> device) {
        this.device = device;
    }

    public List<GroupBean> getGroup() {
        return group;
    }

    public void setGroup(List<GroupBean> group) {
        this.group = group;
    }

    public static class DeviceBean implements Parcelable{
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

        protected DeviceBean(Parcel in) {
            carlicence = in.readString();
            channelcount = in.readInt();
            cname = in.readString();
            deviceid = in.readString();
            devicepassword = in.readString();
            devicetype = in.readString();
            deviceusername = in.readString();
            en = in.readInt();
            groupid = in.readInt();
            linktype = in.readString();
            registerip = in.readString();
            registerport = in.readInt();
            remark = in.readString();
            transmitip = in.readString();
            transmitport = in.readInt();
            status = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(carlicence);
            dest.writeInt(channelcount);
            dest.writeString(cname);
            dest.writeString(deviceid);
            dest.writeString(devicepassword);
            dest.writeString(devicetype);
            dest.writeString(deviceusername);
            dest.writeInt(en);
            dest.writeInt(groupid);
            dest.writeString(linktype);
            dest.writeString(registerip);
            dest.writeInt(registerport);
            dest.writeString(remark);
            dest.writeString(transmitip);
            dest.writeInt(transmitport);
            dest.writeInt(status);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<DeviceBean> CREATOR = new Creator<DeviceBean>() {
            @Override
            public DeviceBean createFromParcel(Parcel in) {
                return new DeviceBean(in);
            }

            @Override
            public DeviceBean[] newArray(int size) {
                return new DeviceBean[size];
            }
        };

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

    public static class GroupBean implements Parcelable{
        /**
         * groupfatherid : 0
         * groupid : 1
         * groupname : 中心
         * remark :
         */

        private int groupfatherid;
        private int groupid;
        private String groupname;
        private String remark;

        protected GroupBean(Parcel in) {
            groupfatherid = in.readInt();
            groupid = in.readInt();
            groupname = in.readString();
            remark = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(groupfatherid);
            dest.writeInt(groupid);
            dest.writeString(groupname);
            dest.writeString(remark);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<GroupBean> CREATOR = new Creator<GroupBean>() {
            @Override
            public GroupBean createFromParcel(Parcel in) {
                return new GroupBean(in);
            }

            @Override
            public GroupBean[] newArray(int size) {
                return new GroupBean[size];
            }
        };

        public int getGroupfatherid() {
            return groupfatherid;
        }

        public void setGroupfatherid(int groupfatherid) {
            this.groupfatherid = groupfatherid;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
