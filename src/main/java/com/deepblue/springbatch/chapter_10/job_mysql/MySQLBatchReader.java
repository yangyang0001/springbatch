//package com.deepblue.springbatch.chapter_10.job_mysql;
//
//import com.deepblue.springbatch.chapter_10.entity.User;
//import com.deepblue.springbatch.chapter_10.mapper.UserMapper;
//import com.github.pagehelper.PageHelper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//
//@Slf4j
//@Component
//@StepScope
//public class MySQLBatchReader implements ItemReader<List<User>> {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    private int pageSize = 5;      // 每页多少条
//
//    private int pageNo = 1;        // 当前页
//
//    @Override
//    public List<User> read() {
//
//        // 查询下一页
//        PageHelper.startPage(pageNo, pageSize);
//        List<User> users = userMapper.selectList(null);
//        PageHelper.clearPage();
//        log.info("MySQLBatchReader ------------------------------------------------------------------------------ users.size: {}, pageNo:{}", users.size(), pageNo);
//
//        pageNo++;
//
//        // 如果没有数据，表示结束
//        if (users.isEmpty()) {
//            return null;
//        } else {
//            return users;
//        }
//    }
//}
