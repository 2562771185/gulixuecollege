package com.jhzz.servicecms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.servicecms.entity.CrmBanner;
import com.jhzz.servicecms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/educms/banneradmin")
@Api(tags = "Banner增删改查")
@CrossOrigin
public class BannerAdminController {

    @Resource
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取banner分页")
    @GetMapping("getPageBanner/{pageNum}/{pageSize}")
    public CommonResult getPageBanner(@PathVariable Long pageNum, @PathVariable Long pageSize) {
        Page<CrmBanner> page = new Page<>(pageNum, pageSize);
        Page<CrmBanner> bannerPage = bannerService.page(page, null);
        return CommonResult.ok().data("list",bannerPage.getRecords()).data("total",bannerPage.getTotal());
    }
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public CommonResult get(@PathVariable String id) {
        CrmBanner banner = bannerService.getBannerById(id);
        return CommonResult.ok().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public CommonResult save(@RequestBody CrmBanner banner) {
        bannerService.saveBanner(banner);
        return CommonResult.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public CommonResult updateById(@RequestBody CrmBanner banner) {
        bannerService.updateBannerById(banner);
        return CommonResult.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public CommonResult remove(@PathVariable String id) {
        bannerService.removeBannerById(id);
        return CommonResult.ok();
    }
}
