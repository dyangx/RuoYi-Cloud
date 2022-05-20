package com.ruoyi.system.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "YhFeign", url = "https://pre-yhcrm.lianlianlvyou.com/")
public interface YhFeign {

    @RequestMapping(value = "yhcrm/settledContract/query/list",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject go(@RequestBody JSONObject json, @RequestHeader("authorization") String token);
}