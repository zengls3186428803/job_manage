package com.zls.project.entity;

public class Cookie {
    String CookieId;//cookie的uid
    String Pid;//用户id

    public String getCookieId() {
        return CookieId;
    }

    public String getPid() {
        return Pid;
    }

    public void setCookieId(String cookieId) {
        CookieId = cookieId;
    }

    public void setPid(String pid) {
        Pid = pid;
    }
}
