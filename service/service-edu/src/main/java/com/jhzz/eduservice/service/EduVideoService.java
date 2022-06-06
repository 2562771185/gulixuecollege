package com.jhzz.eduservice.service;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Huanzhi
* @description 针对表【edu_video(课程视频)】的数据库操作Service
* @createDate 2022-05-27 19:11:55
*/
public interface EduVideoService extends IService<EduVideo> {

    boolean removeVideo(String courseId);

    CommonResult removeVideoAndOss(String videoId);

    CommonResult removeVideoByCourseId(String courseId);
}
