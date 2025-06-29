package com.deepblue.dynamicds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepblue.dynamicds.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
