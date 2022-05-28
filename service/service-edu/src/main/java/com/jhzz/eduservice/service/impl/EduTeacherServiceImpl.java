package com.jhzz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduTeacher;
import com.jhzz.eduservice.entity.vo.TeacherListVo;
import com.jhzz.eduservice.query.TeacherQuery;
import com.jhzz.eduservice.service.EduTeacherService;

import com.jhzz.eduservice.mapper.EduTeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huanzhi
 * @description 针对表【edu_teacher(讲师)】的数据库操作Service实现
 * @createDate 2022-05-18 14:18:06
 */
@Service
@Slf4j
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
        implements EduTeacherService {

    @Override
    public Page<EduTeacher> findPageTeachers(Long pageNum, Long pageSize, TeacherQuery teacherQuery) {
        Page<EduTeacher> page = new Page(pageNum, pageSize);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        //根据sort字段升序
        queryWrapper.orderByDesc("sort");
        if (teacherQuery == null) {
            Page<EduTeacher> teacherPage = this.page(page);
            return teacherPage;
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        return this.page(page,queryWrapper);
    }

    @Override
    public CommonResult getNameList() {
        List<EduTeacher> list = this.list();
        List<TeacherListVo> vos = copyList(list);
        return CommonResult.ok().data("list",vos);
    }

    private List<TeacherListVo> copyList(List<EduTeacher> list) {
        List<TeacherListVo> vos = new ArrayList<>();
        for (EduTeacher eduTeacher : list) {
            vos.add(copyOne(eduTeacher));
        }
        return vos;
    }

    private TeacherListVo copyOne(EduTeacher eduTeacher) {
        TeacherListVo vo = new TeacherListVo();
        BeanUtils.copyProperties(eduTeacher, vo);
        return vo;
    }


}




