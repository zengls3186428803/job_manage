package com.zls.project.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zls.project.dao.CookieDao;
import com.zls.project.dao.impl.CookieDaoImpl;
import com.zls.project.db.DatabaseAccess;
import com.zls.project.entity.Course;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobsHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.getWriter().write("{\"status\":\"302\",\"url\":\"/login\",\"id\":\"isnull\",\"jobs\":[]}");
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
                        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
                        //获取作业列表
                        response.setHeader("Content-Type", "text/html;charset=utf-8");
                        Enumeration<String> parameterNames = request.getParameterNames();
                        while (parameterNames.hasMoreElements()) {
                            String parameterName = parameterNames.nextElement();
                            if (!"id".equals(parameterName)) {
                                continue;
                            }
                            String[] parameterValues = request.getParameterValues(parameterName);
                            String id = parameterValues[0];
                            File file = new File("/home/zengls/IdeaProjects/javaWeb/javaServlet/web/jobs/index.html");

//                          File file = new File("/home/zengls/IdeaProjects/javaWeb/javaServlet/web/details/index.html");
                            FileInputStream io = new FileInputStream(file);
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            byte[] buf = new byte[10240];
                            int len;
                            while ((len = io.read(buf)) > 0) {
                                os.write(buf, 0, len);
                            }
                            io.close();
//                          System.out.println(os.toString());
                            // 按指定模式在字符串查找
                            String line = os.toString();
                            os.close();
                            String pattern = "let target_url =.*";
                            // 创建 Pattern 对象
                            Pattern r = Pattern.compile(pattern);
                            // 现在创建 matcher 对象
                            Matcher m = r.matcher(line);
                            if (m.find()) {
                                System.out.println("Found value: " + m.group(0));
                            } else {
                                System.out.println("NO MATCH");
                            }
                            String html = m.replaceAll("let target_url=\"/jobContentHandler?id=" + id.toString() + "\";");
                            PrintWriter printWriter = response.getWriter();
                            printWriter.write(html);
                        }
                        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
                        flag = true;
                    }
                }
            }
            if (!flag) {
                response.getWriter().write("{\"status\":\"302\",\"url\":\"/login\",\"id:\":\"isnull\",\"jobs\":[]}");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
