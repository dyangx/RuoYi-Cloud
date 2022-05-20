package com.ruoyi.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.feign.YhFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private YhFeign yhFeign;

    @RequestMapping("go")
    public Object go(){
        JSONObject ob = new JSONObject();
        ob.put("pageNum",1);
        ob.put("pageSize",10);
        ob.put("keyWord","20220517");
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2Jhc2VfaWQiOiIyNTk0MHwxNjUyOTI2Mzk0NDc3In0.0mLnhE67BgHWGuLMRhwEtzX6GzzNrsOjInmM9_AAQJoJTbdU9_L7uUbkEiV7R8UpSPwS3Mof6anr-QZa4bSdlg";
        return yhFeign.go(ob,token);
    }
}
