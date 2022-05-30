package com.jhzz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduCourse;
import com.jhzz.eduservice.entity.EduCourseDescription;
import com.jhzz.eduservice.entity.EduTeacher;
import com.jhzz.eduservice.entity.vo.CourseInfoVo;
import com.jhzz.eduservice.query.CourseQuery;
import com.jhzz.eduservice.service.EduCourseDescriptionService;
import com.jhzz.eduservice.service.EduCourseService;
import com.jhzz.eduservice.mapper.EduCourseMapper;
import com.jhzz.eduservice.service.EduTeacherService;
import com.jhzz.eduservice.service.SubjectService;
import com.jhzz.servicebase.exceptionhandler.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Huanzhi
 * @description 针对表【edu_course(课程)】的数据库操作Service实现
 * @createDate 2022-05-27 19:08:42
 */
@Service
@Slf4j
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
        implements EduCourseService {

    @Resource
    private EduCourseDescriptionService courseDescriptionService;
    @Resource
    private EduTeacherService teacherService;
    @Resource
    private SubjectService subjectService;
    public static final String COURSE_STATUS = "Normal";

    @Override
    public CommonResult addCourseInfo(CourseInfoVo courseInfoVo) {
        boolean isEdit = false;
        if (courseInfoVo.getId() != null) {
            isEdit = true;
        }
        log.info("courseInfoVo = " + courseInfoVo);
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
        if (isEdit) {
            eduCourse.setId(courseInfoVo.getId());
            boolean update = this.updateById(eduCourse);
            if (!update) {
                throw new GuliException(20001, "更新课程基本信息失败");
            }
        } else {
            boolean save = this.save(eduCourse);
            if (!save) {
                throw new GuliException(20001, "添加课程基本信息失败");
            }
        }

        //2.向课程简介表中添加
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(eduCourse.getId());
        if (isEdit) {
            boolean update = courseDescriptionService.updateById(courseDescription);
            if (update) {
                return CommonResult.ok().data("courseId", courseDescription.getId());
            }
        } else {
            boolean add = courseDescriptionService.save(courseDescription);
            if (add) {
                return CommonResult.ok().data("courseId", courseDescription.getId());
            }
        }
        return CommonResult.error();
    }

    @Override
    public CommonResult getInfoById(String id) {
        EduCourse eduCourse = this.getById(id);
        CourseInfoVo vo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, vo);
        EduCourseDescription description = courseDescriptionService.getById(eduCourse.getId());
        vo.setDescription(description.getDescription());
        String titleParent = subjectService.getById(eduCourse.getSubjectParentId()).getTitle();
        String titleSon = subjectService.getById(eduCourse.getSubjectId()).getTitle();
        EduTeacher teacher = teacherService.getById(eduCourse.getTeacherId());
        vo.setTeacherName(teacher.getName());
        vo.setSubjectName(titleSon);
        vo.setSubjectParentName(titleParent);
        return CommonResult.ok().data("course", vo);
    }

    @Override
    public CommonResult getCoursePage(Long pageNum, Long pageSize, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        queryWrapper.eq("status", COURSE_STATUS);
        Page<EduCourse> page = new Page<>(pageNum, pageSize);
        Page<EduCourse> coursePage = null;
        long total = 0;
        List<EduCourse> list = null;
        if (courseQuery == null) {
            coursePage = this.page(page, queryWrapper);
            list = coursePage.getRecords();
            total = coursePage.getTotal();
            return CommonResult.ok().data("total", total).data("list", list);
        }
        if (StringUtils.isNotBlank(courseQuery.getTitle())) {
            queryWrapper.like("title", courseQuery.getTitle());
        }
        if (StringUtils.isNotBlank(courseQuery.getTeacherId())) {
            queryWrapper.eq("teacher_id", courseQuery.getTeacherId());
        }
        if (StringUtils.isNotBlank(courseQuery.getSubjectId())) {
            queryWrapper.eq("subject_id", courseQuery.getSubjectId());
        }
        if (StringUtils.isNotBlank(courseQuery.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
        }
        coursePage = this.page(page, queryWrapper);
        list = coursePage.getRecords();
        total = coursePage.getTotal();
        return CommonResult.ok().data("total", total).data("list", list);
    }

    @Override
    public CommonResult publishCourse(String courseId) {
        UpdateWrapper<EduCourse> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", courseId);
        updateWrapper.set("status", COURSE_STATUS);
        boolean update = this.update(updateWrapper);
        if (update) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }


}




