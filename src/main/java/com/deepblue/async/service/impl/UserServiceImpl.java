package com.deepblue.async.service.impl;

import com.deepblue.async.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    public User getUserById(Long id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
        return new User(id, "张三");
    }
}