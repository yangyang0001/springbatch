package com.deepblue.extend.controller;


import com.deepblue.extend.entity.MineObject;
import com.deepblue.extend.handler.OtheHandler;
import com.deepblue.extend.handler.YourHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecuteController {

    @Autowired
    private YourHandler yourHandler;

    @Autowired
    private OtheHandler otheHandler;


    @RequestMapping("/yourExecute")
    public Object yourExecute(HttpServletRequest request) {
        return yourHandler.execute(new MineObject());
    }

    @RequestMapping("/otheExecute")
    public Object otheExecute(HttpServletRequest request) {
        return otheHandler.execute(new MineObject());
    }
}
