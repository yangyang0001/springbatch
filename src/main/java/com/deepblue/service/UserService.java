package com.deepblue.service;

import com.deepblue.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUserById(Long id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
        return new User(id, "张三");
    }
}