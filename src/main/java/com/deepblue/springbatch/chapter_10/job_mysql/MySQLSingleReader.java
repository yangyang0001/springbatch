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
//
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//
//@Slf4j
//@Component
//@StepScope
//public class MySQLSingleReader implements ItemReader<User> {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    private int pageSize = 5;      // 每页多少条
//
//    private int pageNo = 1;        // 当前页
//
//    private Iterator<User> iterator = Collections.emptyIterator();
//
//    @Override
//    public User read() {
//        // 如果上一次查到的还有数据，直接取
//        if (iterator.hasNext()) {
//            return iterator.next();
//        }
//
//        // 查询下一页
//        PageHelper.startPage(pageNo, pageSize);
//        List<User> users = userMapper.selectList(null);
//        log.info("MySQLSingleReader ------------------------------------------------------------------------------ users.size: {}, pageNo:{}", users.size(), pageNo);
//
//        PageHelper.clearPage();
//
//        // 如果没有数据，表示结束
//        if (users.isEmpty()) {
//            return null;
//        }
//
//        // 更新迭代器
//        iterator = users.iterator();
//        pageNo++;
//
//        // 如果当前迭代器还有元素，就直接返回
//        return iterator.next();
//
//    }
//}
