package com.jhzz.eduservice.mapper;

import com.jhzz.eduservice.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Huanzhi
* @description 针对表【edu_subject(课程科目)】的数据库操作Mapper
* @createDate 2022-05-26 23:36:18
* @Entity com.jhzz.eduservice.entity.Subject
*/
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

}




