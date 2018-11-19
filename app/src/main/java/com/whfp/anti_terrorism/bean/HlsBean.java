package com.whfp.anti_terrorism.bean;

/**
 * HLS实体类
 * Created by 张明杨 on 2018-10-26-0026.
 */

public class HlsBean {


    /**
     * msg : Success
     * code : 0
     * data : http://119.96.239.239:83/pag/223.75.122.15/7302/1253955761310011638/0/MAIN/TCP/live.m3u8&checkinfo=ewogICAidGltZSIgOiAiMjAxODExMDdUMTY0MDU2WiIsCiAgICJ1cmwiIDogImh0dHA6Ly8xMTkuOTYuMjM5LjIzOTo4My9wYWcvMjIzLjc1LjEyMi4xNS83MzAyLzEyNTM5NTU3NjEzMTAwMTE2MzgvMC9NQUlOL1RDUC9saXZlLm0zdTgiCn0K&idinfo=EAAAAAAQAABOCKlp0poA6rdVxU2dULG0G8r+jIYukdUM+9Hj9GOKLAw1cEupZxz7PGvaQdN6EpM=
     */

    private String msg;
    private String code;
    private String playrealUrl;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlayrealUrl() {
        return playrealUrl;
    }

    public void setPlayrealUrl(String data) {
        this.playrealUrl = data;
    }
}
