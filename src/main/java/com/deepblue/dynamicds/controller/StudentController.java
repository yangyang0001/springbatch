package com.deepblue.dynamicds.controller;

import com.deepblue.dynamicds.entity.Student;
import com.deepblue.dynamicds.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/insertStudent")
    public Integer insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }

}
