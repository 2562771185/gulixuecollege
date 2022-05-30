package com.jhzz.eduservice.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.param.LoginParam;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/25
 * \* Time: 13:04
 * \* Description:
 * \
 */
@RestController
@RequestMapping("eduservice/user")
@CrossOrigin
@Api(tags = "登录管理")
public class LoginController {

    /**
     * todo 根据用户名密码登录
     * @param loginParam
     * @return
     */
    @PostMapping("login")
    public CommonResult login(@RequestBody LoginParam loginParam){
        System.out.println(loginParam.getUsername());
        System.out.println(loginParam.getPassword());
        return CommonResult.ok().data("token","admin-token-jhzz");
    }
    /**
     * http://localhost:8001/eduservice/user/info?token=admin-token
     * todo 根据登录用户的token查询用户信息
     */
    @GetMapping("info")
    public CommonResult findUserInfoByToken(@RequestParam("token")String token){
        System.out.println("token = " + token);
        return CommonResult.ok().data("name","超级管理员").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
    @PostMapping("logout")
    public CommonResult logout(){
        return CommonResult.ok().data("token","");
    }
}
