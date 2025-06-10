package com.deepblue.reader;

import com.alibaba.fastjson.JSON;
import com.deepblue.entity.MineJobParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
@StepScope
@Slf4j
public class MineItemReader implements ItemStreamReader<String> {

    private List<String> dataList;

    private Iterator<String> iterator;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        // 获取 JobParameters
        StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
        JobParameters jobParameters = stepExecution.getJobParameters();
        JobParameter<List> param = (JobParameter<List>) jobParameters.getParameter("dataList");
        dataList = param.getValue();
        iterator = dataList.iterator();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        System.out.println("list = " + JSON.toJSONString(list));

        long size = list.size();
//        for (int i = 0; i < size; i++) {
//            System.out.println(list.size());
//            String remove = list.remove(0);
//            System.out.println("remove = " + remove);
//        }

        MineJobParam param = new MineJobParam();
        param.setDataList(list);

        System.out.println("param :" + JSON.toJSONString(param));

    }
}


