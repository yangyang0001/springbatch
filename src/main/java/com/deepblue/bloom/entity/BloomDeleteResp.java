package com.deepblue.bloom.entity;

import lombok.Data;

@Data
public class BloomDeleteResp extends BaseParam{

    private boolean existsFlag;

    private boolean deleteFlag;

}
