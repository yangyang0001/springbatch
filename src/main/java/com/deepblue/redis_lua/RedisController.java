package com.deepblue.redis_lua;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("redis")
public class RedisController {


    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("batchSetAndExpirePipeline")
    @ResponseBody
    public List<Object> batchSetAndExpirePipeline(@RequestBody Map<String, String> maps, @RequestParam(defaultValue = "120") long seconds) {
        List<Object> objects = redisUtil.batchSetAndExpirePipeline(maps, seconds);
        System.out.println("objects -------------------------------------------------:" + JSON.toJSONString(objects));
        return objects;
    }

    @RequestMapping("batchSetOrExpirePipeline")
    @ResponseBody
    public List<Object> batchSetOrExpirePipeline(@RequestBody Map<String, String> maps, @RequestParam(defaultValue = "240") long seconds) {
        List<Object> objects = redisUtil.batchSetOrExpirePipeline(maps, seconds);
        System.out.println("objects -------------------------------------------------:" + JSON.toJSONString(objects));
        return objects;
    }


    public static void main(String[] args) {
        Map<String, String> maps = new HashMap<>();
        maps.put("1111", "aaaa");
        maps.put("2222", "bbbb");
        maps.put("3333", "cccc");
        maps.put("4444", "dddd");
        maps.put("5555", "eeee");

        System.out.println(JSON.toJSONString(maps));
    }
}
