package com.jhzz.serviceoss.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.serviceoss.service.OssService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OSSController {
    @Resource
    private OssService ossService;

    @PostMapping("/upload")
    public CommonResult uploadFile(MultipartFile file, @RequestParam("host") String host) {
        log.info("file:{}",file.getOriginalFilename());
        String url = ossService.uploadFile(file,host);
        return CommonResult.ok().data("url",url);
    }
    @PostMapping("/delete")
    public CommonResult deleteFile(@RequestParam("filename") String filename) {
        log.info("filename:{}",filename);
        ossService.deleteFile(filename);
        return CommonResult.ok().data("msg","未保存，删除图片");
    }

}
