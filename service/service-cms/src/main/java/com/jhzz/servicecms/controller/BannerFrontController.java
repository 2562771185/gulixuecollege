package com.jhzz.servicecms.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.servicecms.entity.CrmBanner;
import com.jhzz.servicecms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/5
 * \* Time: 12:53
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/educms/bannerfront")
@Api(tags = "网站首页Banner列表")
@CrossOrigin
public class BannerFrontController {

    @Resource
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public CommonResult index() {
        List<CrmBanner> list = bannerService.selectIndexList();
        return CommonResult.ok().data("bannerList", list);
    }
}
