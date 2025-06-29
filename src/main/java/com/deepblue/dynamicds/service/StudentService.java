package com.deepblue.dynamicds.service;

import com.deepblue.dynamicds.entity.Student;

import java.util.List;

public interface StudentService {

    Integer insertStudent(Student student);

    Integer updateStudent(Student student);

    Integer deleteStudent(Integer id);

    Student selectStudentById(Integer id);

    List<Student> selectPageList(Student student, Long pageNo, Long pageSize);

}
