package com.zls.project.dao;

import com.zls.project.entity.Cookie;

import java.util.List;

public interface CookieDao {
    Cookie getCookieById(String CookieId);
    boolean existById(String CookieId);
    List<Cookie> getCookiesBy(String Pid);

    void addCookie(String CookieId, String Pid);
    void delCookieById(String CookieId);
    void delCookieByPid(String Pid);
}
