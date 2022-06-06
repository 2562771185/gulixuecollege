package com.jhzz.serviceucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/6
 * \* Time: 15:47
 * \* Description:
 * \
 */
@Data
public class LoginInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String avatar;
    private String nickname;
    private String mobile;

    /**
     * 性别 1 女，2 男
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

}
