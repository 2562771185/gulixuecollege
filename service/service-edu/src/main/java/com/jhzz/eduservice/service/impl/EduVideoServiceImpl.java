package com.jhzz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.client.VodClient;
import com.jhzz.eduservice.entity.EduChapter;
import com.jhzz.eduservice.entity.EduVideo;
import com.jhzz.eduservice.query.TeacherQuery;
import com.jhzz.eduservice.service.EduVideoService;
import com.jhzz.eduservice.mapper.EduVideoMapper;
import com.jhzz.servicebase.exceptionhandler.GuliException;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Huanzhi
 * @description 针对表【edu_video(课程视频)】的数据库操作Service实现
 * @createDate 2022-05-27 19:11:55
 */
@Service
@Slf4j
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo>
        implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public boolean removeVideo(String courseId) {
        long count = this.count(new QueryWrapper<EduVideo>().eq("course_id", courseId));
        boolean remove = false;
        if (count > 0) {
            remove = this.remove(new QueryWrapper<EduVideo>().eq("course_id", courseId));
        }
        return remove;
    }

    @Override
    public CommonResult removeVideoAndOss(String videoId) {
        //先查出视频id，先删除视频 在删除小节信息
        EduVideo video = this.getById(videoId);
        String videoSourceId = video.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)) {
            vodClient.deleteVideo(videoSourceId);
        }
        this.removeById(videoId);
        return CommonResult.ok();
    }

    /**
     * 删除课程的所有小节及其视频
     *
     * @param courseId 课程id
     */
    @Override
    public CommonResult removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> videos = this.list(queryWrapper);
        log.info("videos:{}", videos);
        List<String> ids = videos.stream()
                .map(EduVideo::getVideoSourceId)
                .filter(videoSourceId -> !StringUtils.isEmpty(videoSourceId))
                .collect(Collectors.toList());
        log.info("ids:{}", ids);
        //删除课程所有视频
        if (ids.size() > 0) {
            CommonResult result = vodClient.deleteMoreVideo(ids);
            if (!result.getSuccess()){
                throw new GuliException(20001,"熔断器执行.........");
            }
        }
        //删除小节信息
        QueryWrapper<EduVideo> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("course_id", courseId);
        this.remove(deleteWrapper);
        return CommonResult.ok();
    }
}




