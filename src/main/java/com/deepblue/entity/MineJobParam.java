package com.deepblue.entity;

import com.deepblue.common.Param;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

@Data
@Accessors(chain = true)
public class MineJobParam<String> extends Param<String> {

    private List<String> dataList;
}
