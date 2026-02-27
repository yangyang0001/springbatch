package com.deepblue.easyexcel.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

// @Accessors(chain = true) // TODO 这个点要记住, 这块影响导入, 不能加这个注解
@Data
public class ExcelData {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("密码")
    private String password;
}

