package com.deepblue.extend.handler;

import com.deepblue.extend.entity.MineObject;
import org.springframework.stereotype.Component;

@Component
public abstract class ParentHandler implements ExecuteHandler<MineObject> {

    @Override
    public MineObject execute(MineObject param) {
        param.setId("parent");
        param.setName("ParentHandler");
        return param;
    }
}
