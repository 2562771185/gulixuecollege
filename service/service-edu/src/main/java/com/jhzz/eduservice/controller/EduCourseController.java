package com.jhzz.eduservice.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.vo.CourseInfoVo;
import com.jhzz.eduservice.query.CourseQuery;
import com.jhzz.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/27
 * \* Time: 19:15
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/eduservice/course")
@Api(tags = "课程管理")
public class EduCourseController {
    @Resource
    private EduCourseService courseService;

    @PostMapping("addCourseInfo")
    public CommonResult addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        return courseService.addCourseInfo(courseInfoVo);
    }
    @GetMapping("getInfoById/{id}")
    public CommonResult getInfoById(@PathVariable String id){
        return courseService.getInfoById(id);
    }

    @PostMapping("updateCourseInfo")
    public CommonResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        return courseService.addCourseInfo(courseInfoVo);
    }
    @PostMapping("getCourseList/{pageNum}/{pageSize}")
    public CommonResult getCoursePage(@RequestBody(required = false) CourseQuery courseQuery, @PathVariable Long pageNum, @PathVariable Long pageSize){
        return courseService.getCoursePage(pageNum,pageSize,courseQuery);
    }
    @GetMapping("publishCourse/{courseId}")
    public CommonResult publishCourse(@PathVariable String courseId){
        return courseService.publishCourse(courseId);
    }
}
