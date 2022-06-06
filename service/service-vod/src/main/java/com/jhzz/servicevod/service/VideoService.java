package com.jhzz.servicevod.service;

import com.jhzz.commonutils.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/1
 * \* Time: 15:46
 * \* Description:
 * \
 */
public interface VideoService {
    /**
     * 上传视频
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 根据视频id删除视频
     * @param videoId
     */
    void removeVideo(String videoId);


    CommonResult getVideoInfo(String videoId);

    CommonResult deleteMoreVideo(List<String> ids);
}
