package com.whfp.anti_terrorism.bean;

import java.util.List;

/**
 * 获取校车最后一条GPS的实体类
 * Created by 张明杨 on 2018-11-04-0004.
 */

public class SchoolBusGpsLastBean {

    /**
     * data : [{"terid":"AE99873120","gpstime":"2017-06-19 00:00:00","altitude":600,"direction":45,"gpslat":"23.654123","gpslng":"108.432143","speed":80,"recordspeed":0,"state":0,"time":"2017-06-19 00:00:00"}]
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
         * terid : AE99873120
         * gpstime : 2017-06-19 00:00:00
         * altitude : 600
         * direction : 45
         * gpslat : 23.654123
         * gpslng : 108.432143
         * speed : 80
         * recordspeed : 0
         * state : 0
         * time : 2017-06-19 00:00:00
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
