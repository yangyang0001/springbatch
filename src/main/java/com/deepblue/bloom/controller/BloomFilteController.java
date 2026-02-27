package com.deepblue.bloom.controller;

import com.alibaba.fastjson.JSON;
import com.deepblue.bloom.entity.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/bloom")
public class BloomFilteController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    @Value("${redis.bloom.expectedNum:100000}")
    private long expectedNum;

    @Value("${redis.bloom.tolerance:0.001}")
    private double tolerance;

    @RequestMapping(value = "/createBloomFilte", method = RequestMethod.POST)
    @ResponseBody
    public BloomCreateResp createBloomFilte(@RequestBody BloomReq bloomReq) {
//        log.info("BloomFilteController createBloomFilte, bloomReq: {}", JSON.toJSONString(bloomReq));
//        BloomCreateResp createResp = new BloomCreateResp();
//        BeanUtils.copyProperties(bloomReq, createResp);
//
//        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(bloomReq.getFilterName());
//        createResp.setExistsFlag(bloomFilter.isExists());
//
//        if (!createResp.isExistsFlag()) {
//            boolean firstIFlag = bloomFilter.tryInit(expectedNum, tolerance);
//            // 强制创建bitmap
//            boolean addFlag = bloomFilter.add("test_aaa");
//            boolean expireFlag = bloomFilter.expire(bloomReq.getExpired(), TimeUnit.SECONDS);
//            createResp.setFirstIFlag(firstIFlag);
//            createResp.setExpireFlag(expireFlag);
//            createResp.setAddFlag(addFlag);
//        } else {
//            createResp.setExistsFlag(true);
//        }
//
//        return createResp;


        log.info("BloomFilteController createBloomFilte, bloomReq: {}", JSON.toJSONString(bloomReq));

        BloomCreateResp createResp = new BloomCreateResp();
        BeanUtils.copyProperties(bloomReq, createResp);

        String filterName = bloomReq.getFilterName();
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(filterName);

        // ---------- 快速路径 ----------
        if (bloomFilter.isExists()) {
            createResp.setExistsFlag(true);
            return createResp;
        }

        // ---------- 初始化竞争标记 key ----------
        String initKey = "bloom:init:" + filterName;

        Boolean first = redisTemplate.opsForValue().setIfAbsent(initKey, "1", 2, TimeUnit.SECONDS);

        // ---------- 只有一个线程能进入 ----------
        if (Boolean.TRUE.equals(first)) {
            try {
                // double check 防止极端并发
                if (!bloomFilter.isExists()) {
                    boolean init = bloomFilter.tryInit(expectedNum, tolerance);
                    boolean addFlag = bloomFilter.add(initKey);
                    boolean expire = bloomFilter.expire(bloomReq.getExpired(), TimeUnit.SECONDS);
                    createResp.setFirstIFlag(init);
                    createResp.setExpireFlag(expire);
                    createResp.setExistsFlag(true);
                    createResp.setAddFlag(addFlag);
                }
            } catch (Exception e) {
                // 初始化失败必须删除标记
                redisTemplate.delete(initKey);
                throw e;
            }
        }

        return createResp;
    }


    @RequestMapping(value = "/existsBloomFilte", method = RequestMethod.POST)
    @ResponseBody
    public BloomExistsResp existsBloomFilte(@RequestBody BloomReq bloomReq) {
        log.info("BloomFilteController existsBloomFilte, bloomReq: {}", JSON.toJSONString(bloomReq));
        BloomExistsResp existsResp = new BloomExistsResp();
        BeanUtils.copyProperties(bloomReq, existsResp);

        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(bloomReq.getFilterName());
        existsResp.setExistsFlag(bloomFilter.isExists());
        return existsResp;
    }

    @RequestMapping(value = "/addBloomFilteKey", method = RequestMethod.POST)
    @ResponseBody
    public BloomAddKeyResp addBloomFilteKey(@RequestBody BloomReq bloomReq) {
        log.info("BloomFilteController addBloomFilteKey, bloomReq: {}", JSON.toJSONString(bloomReq));
        BloomAddKeyResp addKeyResp = new BloomAddKeyResp();
        BeanUtils.copyProperties(bloomReq, addKeyResp);

        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(bloomReq.getFilterName());
        boolean existsFlag = bloomFilter.isExists();
        addKeyResp.setExistsFlag(existsFlag);

        if (addKeyResp.isExistsFlag()) {
            boolean addFlag = bloomFilter.add(bloomReq.getKey());
            addKeyResp.setAddFlag(addFlag);
        }

        return addKeyResp;
    }


    @RequestMapping(value = "/containBloomFilte", method = RequestMethod.POST)
    @ResponseBody
    public BloomContaiResp containBloomFilte(@RequestBody BloomReq bloomReq) {
        log.info("BloomFilteController containBloomFilte, bloomReq: {}", JSON.toJSONString(bloomReq));
        BloomContaiResp contaiResp = new BloomContaiResp();
        BeanUtils.copyProperties(bloomReq, contaiResp);

        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(bloomReq.getFilterName());
        contaiResp.setExistsFlag(bloomFilter.isExists());

        if (contaiResp.isExistsFlag()) {
            boolean contaiFlag = bloomFilter.contains(bloomReq.getKey());
            contaiResp.setContaiFlag(contaiFlag);
        } else {
            contaiResp.setExistsFlag(false);
            contaiResp.setContaiFlag(false);
        }

        return contaiResp;
    }

    @RequestMapping(value = "/getBloomFilteTTL", method = RequestMethod.POST)
    @ResponseBody
    public BloomTTLiveResp getBloomFilteTTL(@RequestBody BloomReq bloomReq) {
        log.info("BloomFilteController getBloomFilteTTL, bloomReq: {}", JSON.toJSONString(bloomReq));
        BloomTTLiveResp ttLiveResp = new BloomTTLiveResp();
        BeanUtils.copyProperties(bloomReq, ttLiveResp);

        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(bloomReq.getFilterName());
        ttLiveResp.setExistsFlag(bloomFilter.isExists());

        if (ttLiveResp.isExistsFlag()) {
            long ttl = bloomFilter.remainTimeToLive();
            ttLiveResp.setTime2Live(ttl);
        } else {
            ttLiveResp.setExistsFlag(false);
        }

        return ttLiveResp;
    }

    public static void main(String[] args) {
        BloomReq bloomReq = new BloomReq();
        bloomReq.setFilterName("message:websocket:open:1000");
        bloomReq.setKey("bingoplus0001");
        bloomReq.setExpired(120);

        System.out.println(JSON.toJSONString(bloomReq));
    }

}
