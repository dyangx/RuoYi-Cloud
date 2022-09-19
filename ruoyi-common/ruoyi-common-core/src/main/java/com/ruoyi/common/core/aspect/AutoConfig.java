package com.ruoyi.common.core.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ReqParamAspect.class)
public class AutoConfig {
}
