package com.jhzz.serviceucenter.service;

import com.jhzz.serviceucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jhzz.serviceucenter.entity.vo.LoginInfoVo;
import com.jhzz.serviceucenter.entity.vo.LoginVo;
import com.jhzz.serviceucenter.entity.vo.RegisterVo;

/**
* @author Huanzhi
* @description 针对表【ucenter_member(会员表)】的数据库操作Service
* @createDate 2022-06-06 15:21:56
*/
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    LoginInfoVo getLoginInfo(String memberId);
}
