package com.jhzz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduCourse;
import com.jhzz.eduservice.entity.EduCourseDescription;
import com.jhzz.eduservice.entity.vo.CourseInfoVo;
import com.jhzz.eduservice.service.EduCourseDescriptionService;
import com.jhzz.eduservice.service.EduCourseService;
import com.jhzz.eduservice.mapper.EduCourseMapper;
import com.jhzz.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * @author Huanzhi
 * @description 针对表【edu_course(课程)】的数据库操作Service实现
 * @createDate 2022-05-27 19:08:42
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
        implements EduCourseService {

    @Resource
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public CommonResult addCourseInfo(CourseInfoVo courseInfoVo) {
        System.out.println("courseInfoVo = " + courseInfoVo);
        if (
                StringUtils.isBlank(courseInfoVo.getTitle())
                        || StringUtils.isBlank(courseInfoVo.getSubjectId())
                        || StringUtils.isBlank(courseInfoVo.getTeacherId())
        ) {
            throw new GuliException(20001, "参数不正确");
        }
        //1. 向课程表中添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        boolean save = this.save(eduCourse);
        if (!save) {
            throw new GuliException(20001, "添加课程基本信息失败");
        }

        //2.向课程简介表中添加
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(eduCourse.getId());
        boolean add = courseDescriptionService.save(courseDescription);
        if (add) {
            return CommonResult.ok().data("courseId", courseDescription.getId());
        }
        return CommonResult.error();
    }


}




