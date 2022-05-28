package com.jhzz.eduservice.mapper;

import com.jhzz.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Huanzhi
* @description 针对表【edu_course(课程)】的数据库操作Mapper
* @createDate 2022-05-27 19:08:42
* @Entity com.jhzz.eduservice.entity.EduCourse
*/
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

}




