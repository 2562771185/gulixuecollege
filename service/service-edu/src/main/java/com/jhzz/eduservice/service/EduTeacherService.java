package com.jhzz.eduservice.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduTeacher;
import com.jhzz.eduservice.query.TeacherQuery;

/**
* @author Huanzhi
* @description 针对表【edu_teacher(讲师)】的数据库操作Service
* @createDate 2022-05-18 14:18:06
*/
public interface EduTeacherService extends IService<EduTeacher> {
    Page<EduTeacher> findPageTeachers(Long pageNum, Long pageSize, TeacherQuery teacherQuery);

    CommonResult getNameList();
}
