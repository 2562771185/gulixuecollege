package com.jhzz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhzz.eduservice.entity.EduChapter;
import com.jhzz.eduservice.entity.EduVideo;
import com.jhzz.eduservice.entity.chapter.ChapterVo;
import com.jhzz.eduservice.entity.chapter.VideoVo;
import com.jhzz.eduservice.service.EduChapterService;
import com.jhzz.eduservice.mapper.EduChapterMapper;
import com.jhzz.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Huanzhi
 * @description 针对表【edu_chapter(课程)】的数据库操作Service实现
 * @createDate 2022-05-27 19:12:16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter>
        implements EduChapterService {
    @Resource
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> findChapterVideoByCourseId(String courseId) {

        //1. 根据课程id查询课程里面的所有章节
        QueryWrapper<EduChapter> wrapperChapters = new QueryWrapper<>();
        wrapperChapters.eq("course_id", courseId);
        List<EduChapter> eduChapters = this.list(wrapperChapters);
        //2. 根据课程id查询课程里面的所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideos = videoService.list(wrapperVideo);
        //3. 封装集合
        List<ChapterVo> voList = copyList(eduChapters);
        //4. 遍历出章节里面的小节
        for (ChapterVo vo : voList) {
            List<VideoVo> videoList = findVideoList(eduVideos, vo.getId());
            vo.setVideoList(videoList);
        }

        return voList;
    }

    private List<VideoVo> findVideoList(List<EduVideo> eduVideos, String id) {
        List<VideoVo> result = new ArrayList<>();
        for (EduVideo video : eduVideos) {
            if (video.getChapterId().equals(id)) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video, videoVo);
                result.add(videoVo);
            }
        }
        return result;
    }


    private List<ChapterVo> copyList(List<EduChapter> list) {
        List<ChapterVo> vos = new ArrayList<>();
        for (EduChapter chapter : list) {
            vos.add(copy(chapter));
        }
        return vos;
    }

    private ChapterVo copy(EduChapter chapter) {
        ChapterVo vo = new ChapterVo();
        BeanUtils.copyProperties(chapter, vo);
        return vo;
    }
}




