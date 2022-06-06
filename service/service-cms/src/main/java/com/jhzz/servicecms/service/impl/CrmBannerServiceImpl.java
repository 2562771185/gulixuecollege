package com.jhzz.servicecms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhzz.servicecms.entity.CrmBanner;
import com.jhzz.servicecms.service.CrmBannerService;
import com.jhzz.servicecms.mapper.CrmBannerMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huanzhi
 * @description 针对表【crm_banner(首页banner表)】的数据库操作Service实现
 * @createDate 2022-06-05 12:52:36
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner>
        implements CrmBannerService {

    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectIndexList() {
        List<CrmBanner> banners = this.list(new QueryWrapper<CrmBanner>().orderByDesc("sort"));
        return banners;
    }

    @Override
    public CrmBanner getBannerById(String id) {
        return this.getById(id);
    }

    @Override
    public void saveBanner(CrmBanner banner) {
        this.save(banner);
    }

    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void updateBannerById(CrmBanner banner) {
        this.updateById(banner);
    }

    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void removeBannerById(String id) {
        this.removeById(id);
    }
}




