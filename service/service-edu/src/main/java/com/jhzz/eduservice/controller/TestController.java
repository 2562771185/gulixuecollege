package com.jhzz.eduservice.controller;

import com.jhzz.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/18
 * \* Time: 14:22
 * \* Description:
 * \
 */
@RestController
@Api(tags = "测试")
public class TestController {

    @RequestMapping("hello")
    public String hello() {
        try {
            int i = 1 / 0;
        }catch (Exception e){
            throw new GuliException(5001,"自定义异常");
        }
        return "aaaaaaaaaaa";
    }
}
