package com.jhzz.servicebase.exceptionhandler;

import com.jhzz.commonutils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/18
 * \* Time: 23:45
 * \* Description:统一异常处理类
 * \
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult error(Exception e){
        e.printStackTrace();
        return CommonResult.error().message("出错了(#^.^#)请联系管理员");
    }
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public CommonResult error(GuliException e){
        log.error(e.getMessage());
        return CommonResult.error().message(e.getMsg()).code(e.getCode());
    }

}
