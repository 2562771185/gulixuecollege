package com.jhzz.eduservice.service;

import com.jhzz.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jhzz.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
* @author Huanzhi
* @description 针对表【edu_chapter(课程)】的数据库操作Service
* @createDate 2022-05-27 19:12:16
*/
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> findChapterVideoByCourseId(String courseId);
}
