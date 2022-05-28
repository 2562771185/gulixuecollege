package com.jhzz.eduservice.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.vo.CourseInfoVo;
import com.jhzz.eduservice.service.EduCourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class EduCourseController {
    @Resource
    private EduCourseService courseService;

    @PostMapping("addCourseInfo")
    public CommonResult addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        return courseService.addCourseInfo(courseInfoVo);
    }


}
