package com.jhzz.eduservice.client;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/2
 * \* Time: 14:20
 * \* Description:
 * \
 */
@FeignClient(value = "service-vod",fallback = VodClientImpl.class)
@Component
public interface VodClient {

    /**
     * 删除视频
     * @param videoId 视频id
     * @return
     */
    @DeleteMapping(value = "/eduvod/video/deleteVideo/{videoId}")
    CommonResult deleteVideo(@PathVariable("videoId") String videoId);

    /**
     * 根据list删除多个sp
     * @param ids 视频id集合
     * @return
     */
    @DeleteMapping("/eduvod/video/deleteMoreVideo")
    CommonResult deleteMoreVideo(@RequestParam("ids") List<String> ids);
}
