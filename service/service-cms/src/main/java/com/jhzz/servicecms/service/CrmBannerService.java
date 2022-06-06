package com.jhzz.servicecms.service;

import com.jhzz.servicecms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Huanzhi
* @description 针对表【crm_banner(首页banner表)】的数据库操作Service
* @createDate 2022-06-05 12:52:36
*/
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectIndexList();

    CrmBanner getBannerById(String id);

    void saveBanner(CrmBanner banner);

    void updateBannerById(CrmBanner banner);

    void removeBannerById(String id);
}
