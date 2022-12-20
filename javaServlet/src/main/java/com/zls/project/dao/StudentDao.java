package com.zls.project.dao;

import com.zls.project.entity.Student;

public interface StudentDao {
    Student getStudentById(String id);
    Student getStudentByName(String name);
    String getStudentNameById(String id);
    String getStudentIdByName(String id);
}
