package com.deepblue.springbatch.chapter_other.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

@Data
@Accessors(chain = true)
public class MineJobParam<T> extends Param<T> {

    private List<T> dataList;
}
