package com.jhzz.eduservice.mapper;

import com.jhzz.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Huanzhi
* @description 针对表【edu_comment(评论)】的数据库操作Mapper
* @createDate 2022-05-27 19:12:12
* @Entity com.jhzz.eduservice.entity.EduComment
*/
@Mapper
public interface EduCommentMapper extends BaseMapper<EduComment> {

}




