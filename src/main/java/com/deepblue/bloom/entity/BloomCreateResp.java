package com.deepblue.bloom.entity;

import lombok.Data;

@Data
public class BloomCreateResp extends BaseParam{

    private boolean existsFlag;

    private boolean firstIFlag;

    private boolean expireFlag;

    private boolean addFlag;

}
