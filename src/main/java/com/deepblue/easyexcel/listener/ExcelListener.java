package com.deepblue.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import com.deepblue.easyexcel.entity.ExcelData;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<ExcelData> {

    private final List<ExcelData> dataList = new ArrayList<>();

    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        System.out.println("excelData is :" + JSON.toJSONString(excelData));
        dataList.add(excelData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 可将 dataList 写入数据库
        System.out.println("解析完成，共读取：" + dataList.size() + " 条");
    }

    public List<ExcelData> getDataList() {
        return dataList;
    }
}


