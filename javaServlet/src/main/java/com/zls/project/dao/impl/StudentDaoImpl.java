package com.zls.project.dao.impl;

import com.zls.project.dao.StudentDao;
import com.zls.project.entity.Cookie;
import com.zls.project.entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.zls.project.db.DatabaseAccess.*;
import static com.zls.project.db.DatabaseAccess.PASS;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Student getStudentById(String id) {
        return null;
    }

    @Override
    public Student getStudentByName(String name) {
        return null;
    }

    @Override
    public String getStudentNameById(String id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        List<Student> result_list = new ArrayList<>();
        String sql = "select Sid, Sname from Student where Sid=" + id;
        try {
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                Student student = new Student();
                student.setId(resultset.getString("Sid"));
                student.setName(resultset.getString("Sname"));
                result_list.add(student);
            }
            resultset.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        if(result_list.isEmpty()) {
            return "";
        }
        return result_list.get(0).getName();
    }

    @Override
    public String getStudentIdByName(String id) {
        return null;
    }
}
