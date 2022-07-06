package com.ruoyi.common.redis.util;

import com.ruoyi.common.core.utils.SpringUtils;

import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PKUtil {

    public static RedisTemplate<String,Long> redisTemplate;

    static {
        PKUtil.redisTemplate = SpringUtils.getBean("redisTemplate");
    }

    @SuppressWarnings("ConstantConditions")
    public static long generateID(){
        String prefix = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        long serialNo = redisTemplate.opsForValue().increment(prefix,1L);
        redisTemplate.expire(prefix,10, TimeUnit.SECONDS);
        String format = String.format("%03d", serialNo);
        return Long.parseLong(prefix + format);
    }
}
