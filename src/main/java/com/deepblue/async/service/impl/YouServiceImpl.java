package com.deepblue.async.service.impl;

import com.deepblue.async.service.CommonService;
import org.springframework.stereotype.Service;

@Service
public class YouServiceImpl implements CommonService {
    @Override
    public String mine() {
        return "YouServiceImpl";
    }
}
