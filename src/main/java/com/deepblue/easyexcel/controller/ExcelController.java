package com.deepblue.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.deepblue.easyexcel.entity.ExcelData;
import com.deepblue.easyexcel.listener.ExcelListener;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
@Tag(name = "Excel 接口", description = "导入导出功能")
public class ExcelController {

    // 导出
    @GetMapping("/download")
    public void exportExcel(HttpServletResponse response) throws Exception {
        List<ExcelData> dataList = new ArrayList<>();
        dataList.add(new ExcelData() {{
            setId(1L);
            setUsername("张三");
            setPassword("zhangsan@example.com");
        }});
        dataList.add(new ExcelData() {{
            setId(2L);
            setUsername("李四");
            setPassword("lisi@example.com");
        }});

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setCharacterEncoding("utf-8");

        String fileName = URLEncoder.encode("用户信息.xlsx", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        EasyExcel.write(response.getOutputStream(), ExcelData.class)
                .sheet("用户信息")
                .doWrite(dataList);
    }

    // 导入
    @PostMapping("/upload")
    public String importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        ExcelListener listener = new ExcelListener();

        EasyExcel.read(file.getInputStream(), ExcelData.class, listener)
                .headRowNumber(1)
                .sheet()
                .doRead();

        List<ExcelData> userList = listener.getDataList();
        return "导入成功，记录数：" + userList.size();
    }
}

