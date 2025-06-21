package com.deepblue.limiter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
@TableName("limit_rule")
public class LimitRule {

    private Long id;

    private String rateKey;

    private String rateMode;

    private Long rateRate;

    private Long rateInterval;

    private String rateUnit;

}
