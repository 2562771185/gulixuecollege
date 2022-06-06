package com.jhzz.serviceucenter.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.commonutils.jwt.JwtUtils;
import com.jhzz.servicebase.exceptionhandler.GuliException;
import com.jhzz.serviceucenter.entity.vo.LoginInfoVo;
import com.jhzz.serviceucenter.entity.vo.LoginVo;
import com.jhzz.serviceucenter.entity.vo.RegisterVo;
import com.jhzz.serviceucenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/6
 * \* Time: 15:22
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/ucenterservice/apimember")
@CrossOrigin
public class MemberApiController {
    @Resource
    private UcenterMemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public CommonResult login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return CommonResult.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public CommonResult register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return CommonResult.ok("注册成功");
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public CommonResult getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
            return CommonResult.ok().data("item", loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }


}
