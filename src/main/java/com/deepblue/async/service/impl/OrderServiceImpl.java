package com.deepblue.async.service.impl;

import com.deepblue.async.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl {
    public List<Order> getOrdersByUserId(Long userId) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {

        }
        return List.of(new Order(1L, userId, "iPhone"), new Order(2L, userId, "MacBook"));
    }
}
