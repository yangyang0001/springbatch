package com.deepblue.handler.controller;

import com.alibaba.fastjson.JSON;
import com.deepblue.handler.annotation.FilteBlack;
import com.deepblue.handler.annotation.FilteBoth;
import com.deepblue.handler.annotation.FilteWhite;
import com.deepblue.test.entity.Mine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("test-filte")
public class FilteController {

    @PostMapping("filter")
    @ResponseBody
    @FilteWhite
    @FilteBlack
    public List<Mine> filter(@RequestBody List<Mine> mineList) {

        System.out.println("mineList: " + mineList);

        return mineList;
    }


    public static void main(String[] args) {
        List<Mine> mineList = new ArrayList<>();

        Mine mine1 = new Mine("1111", "1111");
        Mine mine2 = new Mine("2222", "2222");
        Mine mine3 = new Mine("3333", "3333");
        Mine mine4 = new Mine("4444", "4444");
        Mine mine5 = new Mine("5555", "5555");
        Mine mine6 = new Mine("6666", "6666");

        mineList.add(mine1);
        mineList.add(mine2);
        mineList.add(mine3);
        mineList.add(mine4);
        mineList.add(mine5);
        mineList.add(mine6);

        System.out.println(JSON.toJSONString(mineList));
    }
}
