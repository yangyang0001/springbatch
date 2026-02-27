package com.deepblue.extend.handler;

import com.deepblue.extend.entity.MineObject;
import org.springframework.stereotype.Component;

@Component
public class YourHandler extends ParentHandler {

    @Override
    public MineObject execute(MineObject param) {
        param.setId("your");
        param.setName("YourHandler");
        return param;
    }
}
