package com.deepblue.dynamicds.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("student")
public class Student {

    @TableId(type = IdType.AUTO)
    private Long pkId;

    private Long id;

    private String name;

    private Integer age;

    private Integer gender;

    private Integer delFlag = 0;
}
