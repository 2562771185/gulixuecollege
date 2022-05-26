package com.jhzz.serviceoss.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.serviceoss.service.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/26
 * \* Time: 14:59
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/eduoss")
@CrossOrigin
public class OSSController {
    @Resource
    private OssService ossService;

    @PostMapping("/upload")
    public CommonResult uploadFile(MultipartFile file) {
        String url = ossService.uploadFile(file);
        return CommonResult.ok().data("url",url);
    }
    @PostMapping("/delete")
    public CommonResult deleteFile(@RequestParam("filename") String filename) {
        ossService.deleteFile(filename);
        return CommonResult.ok().data("msg","未保存，删除图片");
    }

}
