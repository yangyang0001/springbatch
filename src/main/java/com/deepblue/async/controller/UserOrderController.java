package com.deepblue.async.controller;

import com.deepblue.async.service.CommonService;
import com.deepblue.async.service.impl.OrderServiceImpl;
import com.deepblue.async.service.impl.UserServiceImpl;
import com.deepblue.async.entity.Order;
import com.deepblue.async.entity.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 测试 Future 合并任务!
 */
@RestController
@RequestMapping("/api")
public class UserOrderController {

    @Resource
    private List<CommonService> services;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/user-orders/{id}")
    public Object getUserAndOrders(@PathVariable Long id) {
        CompletableFuture<User> userFuture1 = CompletableFuture.supplyAsync(() -> userService.getUserById(id));
        CompletableFuture<List<Order>> orderFuture = CompletableFuture.supplyAsync(() -> orderService.getOrdersByUserId(id));

        CompletableFuture<User> userFuture2 = CompletableFuture.supplyAsync(() -> userService.getUserById(200L));
        List<CompletableFuture<User>> list = List.of(
                CompletableFuture.supplyAsync(() -> new User(2L, "wangwu")),
                CompletableFuture.supplyAsync(() -> new User(3L, "zhaoliu")),
                CompletableFuture.supplyAsync(() -> new User(4L, "xiaoming"))
        );

        // 等待两个异步任务都完成
        CompletableFuture<Void> all = CompletableFuture.allOf(userFuture1, orderFuture);
        CompletableFuture<Void> alls = CompletableFuture.allOf((list.toArray(new CompletableFuture[0])));

        return alls.thenApply(v -> {
            list.stream().forEach(CompletableFuture::join);
            Map<Object, Object> result = new HashMap<>();
            list.stream().forEach(item -> {
                result.put(item.join().getId(), item.join().getName());
            });
            return result;
        }).join();

//        return all.thenApply(v -> {
//            Map<String, Object> result = new HashMap<>();
//            result.put("user", userFuture1.join());
//            result.put("orders", orderFuture.join());
//            return ResponseEntity.ok(result);
//        }).join();


    }
}
