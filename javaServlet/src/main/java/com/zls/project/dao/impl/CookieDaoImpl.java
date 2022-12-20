package com.zls.project.dao.impl;

import com.zls.project.dao.CookieDao;
import com.zls.project.db.DatabaseAccess;
import com.zls.project.entity.Cookie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.zls.project.db.DatabaseAccess.*;


public class CookieDaoImpl implements CookieDao {
    @Override
    public Cookie getCookieById(String CookieId) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        List<Cookie> result_list = new ArrayList<>();
        String sql = "select CookieId, Pid from Cookie where CookieId=" + CookieId;
        try {
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                Cookie cookie = new Cookie();
                cookie.setCookieId(resultset.getString("CookieId"));
                cookie.setPid(resultset.getString("Pid"));
                result_list.add(cookie);
            }
            resultset.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        if(result_list.isEmpty()) {
            return null;
        }
        return result_list.get(0);
    }

    @Override
    public boolean existById(String CookieId) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        List<Cookie> result_list = new ArrayList<>();
        String sql = "select CookieId, Pid from Cookie where CookieId=" + CookieId;
        try {
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                Cookie cookie = new Cookie();
                cookie.setCookieId(resultset.getString("CookieId"));
                cookie.setPid(resultset.getString("Pid"));
                result_list.add(cookie);
            }
            resultset.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return !result_list.isEmpty();
    }

    @Override
    public List<Cookie> getCookiesBy(String Pid) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        List<Cookie> result_list = new ArrayList<>();
        String sql = "select CookieId, Pid from Cookie where Pid=" + Pid;
        try {
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                Cookie cookie = new Cookie();
                cookie.setCookieId(resultset.getString("CookieId"));
                cookie.setPid(resultset.getString("Pid"));
                result_list.add(cookie);
            }
            resultset.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result_list;
    }

    @Override
    public void addCookie(String CookieId, String Pid) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        List<Cookie> result_list = new ArrayList<>();
        String sql = "insert Cookie(CookieId,Pid) values(" + CookieId + "," + Pid + ")";
        try {
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            resultset.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void delCookieById(String CookieId) {

    }

    @Override
    public void delCookieByPid(String Pid) {

    }
}
