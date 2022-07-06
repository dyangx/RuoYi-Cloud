package com.ruoyi.a.controller;

import com.ruoyi.a.service.TAService;
import com.ruoyi.common.redis.util.PKUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private TAService taService;


    @RequestMapping("test")
    public Object test(){
        return "hello you !";
    }

    @RequestMapping("ta/list")
    public Object list(){
        List<Long> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            long s = PKUtil.generateID();
            list.add(s);
        }
        return list;
    }
}
