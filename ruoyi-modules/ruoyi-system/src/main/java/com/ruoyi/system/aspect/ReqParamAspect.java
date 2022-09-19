package com.ruoyi.system.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@Order(1)
public class ReqParamAspect {
    @Value("${lianlian.log.maxLength:1024}")
    private int maxLogLength;

    @Pointcut("bean(*Controller)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        if("initBinder".equals(methodName)){
            return joinPoint.proceed();
        }
        boolean isGetMethod = StringUtils.startsWithAny(method.getName(),"select","get","query","find","list");
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        if(annotation != null && StringUtils.isNotBlank(annotation.value())) {
            methodName = annotation.value();
        }
        if (args != null) {
            log.info("方法名称:{},请求参数：{}", methodName, JSON.toJSONString(args, SerializerFeature.IgnoreNonFieldGetter));
        }
        Object proceed = joinPoint.proceed();
        log.info("方法名称:{},响应时长{} 毫秒,响应参数:{}", methodName,System.currentTimeMillis() - startTime,
                isGetMethod?StringUtils.abbreviate(JSON.toJSONString(proceed, SerializerFeature.IgnoreNonFieldGetter),maxLogLength):
                        JSON.toJSONString(proceed, SerializerFeature.IgnoreNonFieldGetter));
        return proceed;
    }
}
