package com.tp.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController //以后经常做微服务异常捕获，要经常用到这个注解
//@Slf4j lombok+log4j型  加了这个注解就不需要创建静态变量
public class ErrorCatch {

    private static final Logger logger = LoggerFactory.getLogger(ErrorCatch.class);
    @RequestMapping("/getUser")
    public String getUser(int i ){
        int j = 1/i;
        logger.info("日志打印记录成功");
        return "返回"+j;
    }

}