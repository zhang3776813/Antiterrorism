package com.whfp.anti_terrorism.bean;

import java.util.List;

/**
 * 获取校车的GPS信息实体类
 * Created by 张明杨 on 2018-11-02-0002.
 */

public class SchoolBusGpsBean {

    /**
     * data : [{"terid":"008B002891","gpstime":"2018-11-02 06:32:59","altitude":0,"direction":152,"gpslat":"31.125240","gpslng":"115.690591","speed":12,"recordspeed":0,"state":0,"time":"2018-11-02 06:32:48"},{"terid":"008B002891","gpstime":"2018-11-02 06:33:02","altitude":0,"direction":140,"gpslat":"31.125105","gpslng":"115.690723","speed":19,"recordspeed":0,"state":0,"time":"2018-11-02 06:32:51"},{"terid":"008B002891","gpstime":"2018-11-02 06:33:09","altitude":0,"direction":153,"gpslat":"31.124899","gpslng":"115.690913","speed":15,"recordspeed":0,"state":0,"time":"2018-11-02 06:32:58"},{"terid":"008B002891","gpstime":"2018-11-02 06:33:19","altitude":0,"direction":310,"gpslat":"31.124687","gpslng":"115.690790","speed":9,"recordspeed":0,"state":0,"time":"2018-11-02 06:33:08"},{"terid":"008B002891","gpstime":"2018-11-02 06:33:29","altitude":0,"direction":291,"gpslat":"31.124831","gpslng":"115.690512","speed":13,"recordspeed":0,"state":0,"time":"2018-11-02 06:33:18"},{"terid":"008B002891","gpstime":"2018-11-02 06:33:30","altitude":0,"direction":290,"gpslat":"31.124842","gpslng":"115.690473","speed":15,"recordspeed":0,"state":0,"time":"2018-11-02 06:33:19"},{"terid":"008B002891","gpstime":"2018-11-02 06:33:31","altitude":0,"direction":290,"gpslat":"31.124842","gpslng":"115.690473","speed":15,"recordspeed":0,"state":0,"time":"2018-11-02 06:33:20"},{"terid":"008B002891","gpstime":"2018-11-02 06:33:39","altitude":0,"direction":263,"gpslat":"31.124852","gpslng":"115.689988","speed":21,"recordspeed":0,"state":0,"time":"2018-11-02 06:33:28"},{"terid":"008B002891","gpstime":"2018-11-02 06:33:49","altitude":0,"direction":260,"gpslat":"31.124764","gpslng":"115.689348","speed":31,"recordspeed":0,"state":0,"time":"2018-11-02 06:33:38"},{"terid":"008B002891","gpstime":"2018-11-02 06:33:59","altitude":0,"direction":259,"gpslat":"31.124577","gpslng":"115.688338","speed":37,"recordspeed":0,"state":0,"time":"2018-11-02 06:33:48"},{"terid":"008B002891","gpstime":"2018-11-02 06:34:09","altitude":0,"direction":258,"gpslat":"31.124380","gpslng":"115.687100","speed":40,"recordspeed":0,"state":0,"time":"2018-11-02 06:33:58"},{"terid":"008B002891","gpstime":"2018-11-02 06:34:19","altitude":0,"direction":261,"gpslat":"31.124214","gpslng":"115.685980","speed":37,"recordspeed":0,"state":0,"time":"2018-11-02 06:34:08"},{"terid":"008B002891","gpstime":"2018-11-02 06:34:29","altitude":0,"direction":263,"gpslat":"31.124055","gpslng":"115.685016","speed":37,"recordspeed":0,"state":0,"time":"2018-11-02 06:34:18"},{"terid":"008B002891","gpstime":"2018-11-02 06:34:39","altitude":0,"direction":298,"gpslat":"31.124319","gpslng":"115.683912","speed":42,"recordspeed":0,"state":0,"time":"2018-11-02 06:34:28"},{"terid":"008B002891","gpstime":"2018-11-02 06:34:49","altitude":0,"direction":304,"gpslat":"31.124978","gpslng":"115.682720","speed":45,"recordspeed":0,"state":0,"time":"2018-11-02 06:34:38"},{"terid":"008B002891","gpstime":"2018-11-02 06:34:59","altitude":0,"direction":282,"gpslat":"31.125473","gpslng":"115.681711","speed":39,"recordspeed":0,"state":0,"time":"2018-11-02 06:34:48"}]
     * errorcode : 200
     */

    private int errorcode;
    private List<DataBean> data;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * terid : 008B002891
         * gpstime : 2018-11-02 06:32:59
         * altitude : 0
         * direction : 152
         * gpslat : 31.125240
         * gpslng : 115.690591
         * speed : 12
         * recordspeed : 0
         * state : 0
         * time : 2018-11-02 06:32:48
         */

        private String terid;
        private String gpstime;
        private int altitude;
        private int direction;
        private String gpslat;
        private String gpslng;
        private int speed;
        private int recordspeed;
        private int state;
        private String time;

        public String getTerid() {
            return terid;
        }

        public void setTerid(String terid) {
            this.terid = terid;
        }

        public String getGpstime() {
            return gpstime;
        }

        public void setGpstime(String gpstime) {
            this.gpstime = gpstime;
        }

        public int getAltitude() {
            return altitude;
        }

        public void setAltitude(int altitude) {
            this.altitude = altitude;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String getGpslat() {
            return gpslat;
        }

        public void setGpslat(String gpslat) {
            this.gpslat = gpslat;
        }

        public String getGpslng() {
            return gpslng;
        }

        public void setGpslng(String gpslng) {
            this.gpslng = gpslng;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getRecordspeed() {
            return recordspeed;
        }

        public void setRecordspeed(int recordspeed) {
            this.recordspeed = recordspeed;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
