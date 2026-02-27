package com.deepblue.springbatch.chapter_10.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("性别")
    private Integer gender;

    @ExcelProperty("删除标识")
    private Integer delFlag = 0;
}
