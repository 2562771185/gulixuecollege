package com.jhzz.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduCourse;
import com.jhzz.eduservice.entity.EduTeacher;
import com.jhzz.eduservice.service.EduCourseService;
import com.jhzz.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/5
 * \* Time: 16:20
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/eduservice/index")
public class IndexController {
    @Resource
    private EduCourseService courseService;
    @Resource
    private EduTeacherService teacherService;

    /**
     * 查询前8条热门课程，查询前4条名师
     */
    @GetMapping("getIndex")
    public CommonResult index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.orderByDesc("buy_count");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("sort");
        wrapperTeacher.orderByDesc("level");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return CommonResult.ok().data("eduList", eduList).data("teacherList", teacherList);
    }
}
