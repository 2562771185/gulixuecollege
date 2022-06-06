package com.jhzz.eduservice.client.impl;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/2
 * \* Time: 20:39
 * \* Description:
 * \
 */
@Component
public class VodClientImpl implements VodClient {
    /**
     * 删除视频
     *
     * @param videoId 视频id
     * @return
     */
    @Override
    public CommonResult deleteVideo(String videoId) {
        return CommonResult.error("删除单个视频出错");
    }

    /**
     * 根据list删除多个sp
     *
     * @param ids 视频id集合
     * @return
     */
    @Override
    public CommonResult deleteMoreVideo(List<String> ids) {
        return CommonResult.error("删除多个视频出错");
    }
}
