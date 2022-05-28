package com.jhzz.eduservice.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.chapter.ChapterVo;
import com.jhzz.eduservice.service.EduChapterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/27
 * \* Time: 19:15
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Resource
    private EduChapterService eduChapterService;

    /**
     * 返回课程大纲,根据课程id查
     */
    @GetMapping("findChapterVideo/{courseId}")
    public CommonResult findChapterVideo(@PathVariable String courseId) {
       List<ChapterVo> list = eduChapterService.findChapterVideoByCourseId(courseId);
        return CommonResult.ok().data("list",list);
    }
}
