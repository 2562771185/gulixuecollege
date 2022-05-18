package com.jhzz.eduservice.controller;

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
public class TestController {

    @RequestMapping("hello")
    public String hello() {
        return "aaaaaaaaaaa";
    }
}
