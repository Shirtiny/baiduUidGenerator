package com.baidu.fsg.uid.service;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UidGenerateService{

    @Resource
    private UidGenerator uidGenerator;

    public long generateUid() {
        return uidGenerator.getUID();
    }
}
