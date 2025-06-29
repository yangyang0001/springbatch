package com.deepblue.dynamicds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepblue.dynamicds.entity.Student;
import com.deepblue.dynamicds.mapper.StudentMapper;
import com.deepblue.dynamicds.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
//    @DS("new")
    @DS("tidb")
    public Integer insertStudent(Student student) {
        // TODO 经过 DEBUG, 插入 tidb 数据源的 student 表时, 能拿到 AUTO_RANDOM 的主键 pkId, 自增列 id 拿不到
        // TODO 经过 DEBUG, 插入 new  数据源的 student 表时, 能拿到 AUTO_INCREMENT 的主键 pkId
        int insert = studentMapper.insert(student);
        return insert;
    }

    @Override
    public Integer updateStudent(Student student) {
        return 0;
    }

    @Override
    public Integer deleteStudent(Long id) {
        return 0;
    }

    @DS("tidb")
    @Override
    public Student selectStudentById(Long id) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Student::getId, id);
        wrapper.last("limit 1");
        return studentMapper.selectOne(wrapper);
    }

    @Override
    public List<Student> selectPageList(Student student, Long pageNo, Long pageSize) {
        return List.of();
    }
}
