package com.ruoyi.a.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {


    @RequestMapping("test")
    public Object test(){
        return "hello you !";
    }
}
