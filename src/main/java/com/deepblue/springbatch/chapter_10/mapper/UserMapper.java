package com.deepblue.springbatch.chapter_10.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepblue.springbatch.chapter_10.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAll();
}
