package com.jhzz.eduservice.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/27
 * \* Time: 13:31
 * \* Description:
 * \
 */
@Data
public class SubjectListVo {

    private String id;
    private String parentId;
    private String label;
    private List<SubjectListVo> children;
}
