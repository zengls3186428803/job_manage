package com.zls.project.db;


import com.zls.project.entity.Course;
import com.zls.project.entity.Job;
import com.zls.project.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseAccess {
    private static final long serialVersionUID = 1L;
    // JDBC 驱动名及数据库 URL
    public static  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/nenu";

    // 数据库的用户名与密码
    public static final String USER = "root";
    public static final String PASS = "123456";

//    public static List<?> queryBySql(String sql) {
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultset = null;
//        List<?> result_list = new ArrayList<>();
//        try {
//            Class.forName(JDBC_DRIVER);
//            // 打开一个连接
//            connection = DriverManager.getConnection(DB_URL, USER, PASS);
//            // 执行 SQL 查询
//            statement = connection.createStatement();
//            resultset = statement.executeQuery(sql);
//            while (resultset.next()) {
//                Objects
//                course.setId(resultset.getString("Cid"));
//                course.setName(resultset.getString("Cname"));
//                System.out.println(course.getId() + " " + course.getName());
//                result_list.add(result)
//            }
//            resultset.close();
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return courses;
//    }

    public static int check_account_password(String account, String password) {
        try {
            Class.forName(JDBC_DRIVER);

            // 打开一个连接
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行 SQL 查询
            Statement statement = connection.createStatement();
            String sql = "SELECT Sid, Spw FROM Student where Sid=" + account + "";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                // 通过字段检索
                String pw = rs.getString("Spw");
                if (pw.equals(password)) {
                    return 0;
                } else {
                    return 1;
                }
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 2;
    }

    public static List<Student> getStudents() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        List<Student> students = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            statement = connection.createStatement();
            resultset = statement.executeQuery("select * from Student");
            while (resultset.next()) {
                Student student = new Student();
                student.setId(resultset.getString("Sid"));
                student.setName(resultset.getString("Sname"));
                System.out.println(student.getId() + " " + student.getName());
                students.add(student);
            }
            resultset.close();
            statement.close();
            connection.close();
            return students;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return students;
    }
    public static List<Course> getCourse() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        List<Course> courses = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            statement = connection.createStatement();
            resultset = statement.executeQuery("select * from Course");
            while (resultset.next()) {
                Course course = new Course();
                course.setId(resultset.getString("Cid"));
                course.setName(resultset.getString("Cname"));
                courses.add(course);
            }
            resultset.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return courses;
    }
    public static List<Job> getJobCourse(String cid) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        List<Job> jobs = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            statement = connection.createStatement();
            resultset = statement.executeQuery("select Jid, Jcontent from Job where Cid=" + cid);
            while (resultset.next()) {
                Job job = new Job();
                job.setId(resultset.getString("Jid"));
                job.setContent(resultset.getString("Jcontent"));
                jobs.add(job);
            }
            resultset.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return jobs;
    }
    public static String getCourseName(String cid) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        List<String> course_names = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            statement = connection.createStatement();
            resultset = statement.executeQuery("select Cname from Course where Cid=" + cid);
            while (resultset.next()) {
                String name = new String();
                name = resultset.getString("Cname");
                course_names.add(name);
            }
            resultset.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return course_names.get(0);
    }
}
