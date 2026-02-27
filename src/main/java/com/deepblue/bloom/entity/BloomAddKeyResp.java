package com.deepblue.bloom.entity;

import lombok.Data;

@Data
public class BloomAddKeyResp extends BaseParam{

    private boolean existsFlag;

    private boolean addFlag;
}
