package com.deepblue.service.impl;

import com.deepblue.service.CommonService;
import org.springframework.stereotype.Service;

@Service
public class YouServiceImpl implements CommonService {
    @Override
    public String mine() {
        return "YouServiceImpl";
    }
}
