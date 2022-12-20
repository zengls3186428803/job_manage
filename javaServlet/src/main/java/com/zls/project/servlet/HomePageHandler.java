package com.zls.project.servlet;

import com.zls.project.dao.CookieDao;
import com.zls.project.dao.impl.CookieDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePageHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(302);
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            response.setHeader("Location", "/login");
        } else {
            boolean flag = false;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("CookieId")) {
                    String CookieId = cookie.getValue();
                    if (CookieId == null || CookieId.length() == 0) {
                        continue;
                    }
                    CookieDao cookieDao = new CookieDaoImpl();
                    if (cookieDao.existById(CookieId)) {
                        response.setHeader("Location", "/courses");
                        flag = true;
                    }
                }
            }
            if(!flag) {
                response.setHeader("Location", "/login");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
