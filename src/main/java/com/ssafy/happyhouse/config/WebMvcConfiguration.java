package com.ssafy.happyhouse.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
public class WebMvcConfiguration  {


}
