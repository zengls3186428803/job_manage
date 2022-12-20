package com.zls.project.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Random;

import com.zls.project.dao.CookieDao;
import com.zls.project.dao.impl.CookieDaoImpl;
import com.zls.project.db.DatabaseAccess;

public class LoginHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> paramNames = request.getParameterNames();
        String account = null, password = null;
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if ("account".equals(paramName)) {
                account = paramValues[0];
            } else if ("password".equals(paramName)) {
                password = paramValues[0];
            } else {
                System.out.println(paramName + ":" + paramValues[0]);
            }
        }
        int flag = DatabaseAccess.check_account_password(account, password);
        if (flag == 0) {
            Random random = new Random();
            long x1 = random.nextLong();
            long x2 = random.nextLong();
            if(x1 < 0) x1 = -x1;
            if(x2 < 0) x2 = -x2;
            String CookieId = String.valueOf(x1) + String.valueOf(x2);
            Cookie cookie = new Cookie("CookieId", CookieId);
            cookie.setMaxAge(60 * 10);
            CookieDao cookieDao = new CookieDaoImpl();
            cookieDao.addCookie(CookieId, account);
            response.addCookie(cookie);
            response.getWriter().write("/courses");
        } else {
            response.setHeader("Content-Type","text/html;charset=utf-8");
            response.getWriter().write("1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
