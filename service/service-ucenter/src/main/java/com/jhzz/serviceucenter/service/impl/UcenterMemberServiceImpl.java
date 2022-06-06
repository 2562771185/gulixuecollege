package com.jhzz.serviceucenter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhzz.commonutils.MD5.MD5;
import com.jhzz.commonutils.jwt.JwtUtils;
import com.jhzz.servicebase.exceptionhandler.GuliException;
import com.jhzz.serviceucenter.entity.UcenterMember;
import com.jhzz.serviceucenter.entity.vo.LoginInfoVo;
import com.jhzz.serviceucenter.entity.vo.LoginVo;
import com.jhzz.serviceucenter.entity.vo.RegisterVo;
import com.jhzz.serviceucenter.service.UcenterMemberService;
import com.jhzz.serviceucenter.mapper.UcenterMemberMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author Huanzhi
 * @description 针对表【ucenter_member(会员表)】的数据库操作Service实现
 * @createDate 2022-06-06 15:21:56
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember>
        implements UcenterMemberService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //校验参数
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "非法参数");
        }
        //获取会员
        UcenterMember member = this.getOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (null == member) {
            throw new GuliException(20001, "用户不存在");
        }
        //校验密码
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(20001, "error");
        }

        //校验是否被禁用
        if (member.getIsDisabled() == 1) {
            throw new GuliException(20001, "用户被禁用了");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //校验参数
        if(StrUtil.hasBlank(mobile,nickname,password,code)) {
            throw new GuliException(20001,"error");
        }
        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode =String.valueOf(redisTemplate.opsForValue().get(mobile));
        if(!code.equals(mobleCode)) {
            throw new GuliException(20001,"验证码错误");
        }
        //查询数据库中是否存在相同的手机号码
        Long count = this.count(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(count.intValue() > 0) {
            throw new GuliException(20001,"已存在手机号码");
        }
        //添加注册信息到数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(0);
        member.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        this.save(member);
    }

    @Override
    public LoginInfoVo getLoginInfo(String memberId) {
        UcenterMember member = this.getById(memberId);
        if (member == null) {
            return null;
        }
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtils.copyProperties(member, loginInfoVo);
        return loginInfoVo;
    }
}




