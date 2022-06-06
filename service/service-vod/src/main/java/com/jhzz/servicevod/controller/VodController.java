package com.jhzz.servicevod.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.servicevod.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/1
 * \* Time: 17:14
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
@Slf4j
public class VodController {
    @Resource
    private VideoService videoService;

    @PostMapping("uploadVideo")
    public CommonResult uploadVideo(@RequestBody MultipartFile file){
        String videoId = videoService.uploadVideo(file);
        return CommonResult.ok().data("videoId",videoId);
    }
    @DeleteMapping("deleteVideo/{videoId}")
    public CommonResult deleteVideo(@PathVariable String videoId){
        videoService.removeVideo(videoId);
        return CommonResult.ok("删除视频成功");
    }
    @GetMapping("getVideoInfo/{videoId}")
    public CommonResult getVideoInfo(@PathVariable String videoId){
        log.info("传入的id{}",videoId);
        return videoService.getVideoInfo(videoId);
    }
    @DeleteMapping("deleteMoreVideo")
    public CommonResult deleteMoreVideo(@RequestParam("ids") List<String> ids){
        return videoService.deleteMoreVideo(ids);
    }
}
