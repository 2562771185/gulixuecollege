package com.jhzz.servicemsm.controller;

import cn.hutool.core.util.RandomUtil;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.servicemsm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/5
 * \* Time: 23:23
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/api/msm")
@CrossOrigin
public class MsmApiController {
    @Resource
    private MsmService msmService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{phone}")
    public CommonResult code(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return CommonResult.ok().data("code", code);
        }
        code = RandomUtil.randomNumbers(4);
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        return CommonResult.ok().data("code", code);
        //todo 未实现发送短信，此处采用随机验证码方式
//        boolean isSend = msmService.send(phone, "SMS_154950909", code);
//        if (isSend) {

//        } else {
//            return CommonResult.error().message("发送短信失败");
//        }
    }

    public static void main(String[] args) {
        String string = RandomUtil.randomNumbers(4);
        System.out.println("string = " + string);
    }
}
