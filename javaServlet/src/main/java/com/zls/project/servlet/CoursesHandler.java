package com.zls.project.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zls.project.dao.CookieDao;
import com.zls.project.dao.impl.CookieDaoImpl;
import com.zls.project.db.DatabaseAccess;
import com.zls.project.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CoursesHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.getWriter().write("{\"status\":\"302\",\"url\":\"/login\",\"courses\":[]}");
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
                        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
                        //获取课程列表
                        response.setHeader("Content-Type", "text/html;charset=utf-8");
                        response.setStatus(200);
                        List<Course> courses = DatabaseAccess.getCourse();
                        ObjectMapper mapper = new ObjectMapper();
                        String json = mapper.writeValueAsString(courses);
                        PrintWriter out = response.getWriter();
                        json = "{\"status\":\"200\",\"url\":\"isnull\",\"courses\":" + json +"}";
                        out.write(json);
                        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
                        flag = true;
                    }
                }
            }
            if (!flag) {
                response.getWriter().write("{\"status\":\"302\",\"url\":\"/login\",\"courses\":[]}");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
