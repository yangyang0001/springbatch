package com.deepblue.redis_lua;

import jakarta.annotation.Resource;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedissonClient redissonClient;


    /**
     * 批量设置值 并 批量返回 实现真正的 pipeline
     * @param maps
     * @param seconds
     * @return
     */
//    public List<Object> batchSetAndExpirePipeline(Map<String, String> maps, long seconds) {
//        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//        return redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
//            maps.forEach((key, val) -> {
//                connection.set(serializer.serialize(key), serializer.serialize(val), Expiration.seconds(seconds), RedisStringCommands.SetOption.SET_IF_ABSENT);
//            });
//            return null;
//        });
//    }

    public List<Object> batchSetAndExpirePipeline(Map<String, String> maps, long seconds) {
        return redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            maps.forEach((key, val) -> {
                connection.set(
                        serializer.serialize(key),
                        serializer.serialize(val),
                        Expiration.seconds(seconds),
                        RedisStringCommands.SetOption.SET_IF_ABSENT
                );
            });
            return null;
        });
    }


    public List<Object> batchSetOrExpirePipeline(Map<String, String> map, Long seconds) {
        return redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                map.forEach((key, val) -> {
                    // 收集每个 set 操作的返回值
                    Boolean setFlag = operations.opsForValue().setIfAbsent(key, val, seconds, TimeUnit.SECONDS);
                });
                return null;
            }
        });
    }
}
