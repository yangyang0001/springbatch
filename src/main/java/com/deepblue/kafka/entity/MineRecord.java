package com.deepblue.kafka.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MineRecord {

    private Long id;

    private String name;

    private String pass;

    private Integer age;
}
