package com.jhzz.eduservice.service;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jhzz.eduservice.entity.vo.CourseInfoVo;
import com.jhzz.eduservice.query.CourseQuery;

/**
* @author Huanzhi
* @description 针对表【edu_course(课程)】的数据库操作Service
* @createDate 2022-05-27 19:08:42
*/
public interface EduCourseService extends IService<EduCourse> {

    CommonResult addCourseInfo(CourseInfoVo courseInfoVo);

    CommonResult getInfoById(String id);

    CommonResult getCoursePage(Long pageNum, Long pageSize, CourseQuery courseQuery);

    CommonResult publishCourse(String courseId);

    CommonResult deleteCourse(String courseId);

    CommonResult soldCourse(String courseId);
}
