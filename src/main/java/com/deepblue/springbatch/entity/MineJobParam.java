package com.deepblue.springbatch.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

@Data
@Accessors(chain = true)
public class MineJobParam<T> extends Param<T> {

    private List<T> dataList;
}
