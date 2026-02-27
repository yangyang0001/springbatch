package com.deepblue.limiter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepblue.limiter.entity.LimitRule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LimitRuleMapper extends BaseMapper<LimitRule> {

}
