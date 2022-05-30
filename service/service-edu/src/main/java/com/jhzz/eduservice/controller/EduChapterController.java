package com.jhzz.eduservice.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduChapter;
import com.jhzz.eduservice.entity.vo.ChapterVo;
import com.jhzz.eduservice.service.EduChapterService;
import com.jhzz.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "章节管理")
@Slf4j
public class EduChapterController {
    @Resource
    private EduChapterService eduChapterService;

    /**
     * 返回课程大纲,根据课程id查
     */
    @GetMapping("findChapterVideo/{courseId}")
    public CommonResult findChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = eduChapterService.findChapterVideoByCourseId(courseId);
        return CommonResult.ok().data("list", list);
    }

    /**
     * 添加章节信息
     */
    @PostMapping("addChapter")
    public CommonResult addChapter(@RequestBody EduChapter chapter) {
        log.info("chapter:{}",chapter);
        boolean save = eduChapterService.save(chapter);
        if (save) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    /**
     * 根据id查询章节
     */
    @GetMapping("findChapterInfo/{chapterId}")
    public CommonResult findChapterInfo(@PathVariable String chapterId) {
        EduChapter chapter = eduChapterService.getById(chapterId);
        return CommonResult.ok().data("chapter", chapter);
    }

    /**
     * 修改章节
     */
    @PostMapping("updateChapter")
    public CommonResult updateChapter(@RequestBody EduChapter chapter) {
        if (chapter == null) {
            throw new GuliException(20001, "参数不正确");
        }
        boolean update = eduChapterService.updateById(chapter);
        if (update) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    /**
     * 根据id删除章节
     */
    @DeleteMapping("{chapterId}")
    public CommonResult deleteChapter(@PathVariable String chapterId) {
       return eduChapterService.deleteChapter(chapterId);
    }

}
