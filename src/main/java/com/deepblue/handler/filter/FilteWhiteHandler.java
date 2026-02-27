package com.deepblue.handler.filter;

import com.alibaba.fastjson.JSON;
import com.deepblue.test.entity.Mine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FilteWhiteHandler extends FilteHandler<List<Mine>> {

    public static List<String> idList = List.of("3333");


    @Override
    public List<Mine> doFilter(List<Mine> mines) {
        log.info("FilteBlackHandler invoke doFilter, mines = " + JSON.toJSONString(mines));
        return mines.stream().filter(item -> !idList.contains(item.getId())).toList();
    }
}
