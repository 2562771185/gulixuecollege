package com.jhzz.eduservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 课程科目
 * @TableName edu_subject
 */
@TableName(value ="edu_subject")
@Data
public class Subject implements Serializable {
    /**
     * 课程类别ID
     */
    @TableId
    @ApiModelProperty(value = "课程类别ID")
    private String id;

    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称")
    private String title;

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    private String parentId;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}