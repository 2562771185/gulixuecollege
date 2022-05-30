package com.jhzz.eduservice.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduVideo;
import com.jhzz.eduservice.entity.vo.VideoVo;
import com.jhzz.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/27
 * \* Time: 19:16
 * \* Description:
 * \
 */
@RestController
@Api(tags = "小节管理")
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Resource
    private EduVideoService videoService;

    /**
     * 添加小节
     */
    @PostMapping("addVideo")
    public CommonResult addVideo(@RequestBody EduVideo video){
        boolean save = videoService.save(video);
        if (save){
            return CommonResult.ok();
        }
        return CommonResult.error();
    }
    /**
     * 删除小节
     */
    @DeleteMapping("deleteVideo/{videoId}")
    public CommonResult deleteVideo(@PathVariable String videoId){
        boolean delete = videoService.removeById(videoId);
        if (delete){
            return CommonResult.ok();
        }
        return CommonResult.error();
    }
    /**
     * 修改小节
     */
    @PostMapping("updateVideo")
    public CommonResult updateVideo(@RequestBody EduVideo video){
        boolean update = videoService.updateById(video);
        if (update){
            return CommonResult.ok().data("video",video);
        }
        return CommonResult.error();
    }
    /**
     * 根据id查询小节
     */
    @GetMapping("getVideo/{videoId}")
    public CommonResult getVideo(@PathVariable String videoId){
        EduVideo video = videoService.getById(videoId);
        return CommonResult.ok().data("video",video);
    }
}
