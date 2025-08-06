package com.deepblue.limiter.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepblue.limiter.entity.LimitRule;
import com.deepblue.limiter.mapper.LimitRuleMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
public class RateLimiterCacheConfig {

    private LoadingCache<String, RRateLimiter> limiterCache;

    @Autowired
    private LimitRuleMapper limitRuleMapper;

    @Resource
    private RedissonClient redissonClient;

    @PostConstruct
    public void init() {




        limiterCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, RRateLimiter>() {
                    @Override
                    public RRateLimiter load(String key) throws Exception {
                        QueryWrapper<LimitRule> wrapper = new QueryWrapper<>();
                        wrapper.lambda().orderByAsc(LimitRule::getId);
                        List<LimitRule> ruleList = new ArrayList<>();
                        try {
                            ruleList = limitRuleMapper.selectList(wrapper);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if(!CollectionUtils.isEmpty(ruleList)) {
                            LimitRule limitRule = ruleList.stream().filter(item -> key.equals(item.getRateKey())).findFirst().orElse(null);
                            if(limitRule != null) {
                                RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
                                RateType mode = RateType.valueOf(limitRule.getRateMode());
                                Long rate = limitRule.getRateRate();
                                Long interval = limitRule.getRateInterval();
                                RateIntervalUnit unit = RateIntervalUnit.valueOf(limitRule.getRateUnit());
                                if(StringUtils.isNotBlank(key) && mode != null && rate != 0 && interval != 0 && unit != null) {
                                    rateLimiter.setRate(mode, rate,interval, unit);
                                }
                                return rateLimiter;
                            }
                        }
                        return null;
                    }
                });

        limiterCache.invalidateAll();
    }

    public RRateLimiter getRateLimiter(String key) {
        try {
            return limiterCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
