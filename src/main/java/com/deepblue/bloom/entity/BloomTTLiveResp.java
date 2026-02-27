package com.deepblue.bloom.entity;

import lombok.Data;

@Data
public class BloomTTLiveResp extends BaseParam{

    private boolean existsFlag;

    private long time2Live;

}
