package com.deepblue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Order {
    private Long id;
    private Long userId;
    private String product;
}