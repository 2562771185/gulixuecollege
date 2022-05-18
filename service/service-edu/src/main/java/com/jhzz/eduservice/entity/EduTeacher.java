package com.jhzz.eduservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 讲师
* @author Huanzhi
 * @TableName edu_teacher
*/
@TableName(value ="edu_teacher")
@Data
public class EduTeacher implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
    * 讲师ID
    */
    @NotNull(message="[讲师ID]不能为空")
    @ApiModelProperty("讲师ID")
    private String id;
    /**
    * 讲师姓名
    */
    @NotBlank(message="[讲师姓名]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("讲师姓名")
    @Length(max= 20,message="编码长度不能超过20")
    private String name;
    /**
    * 讲师简介
    */
    @NotBlank(message="[讲师简介]不能为空")
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("讲师简介")
    @Length(max= 500,message="编码长度不能超过500")
    private String intro;
    /**
    * 讲师资历,一句话说明讲师
    */
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("讲师资历,一句话说明讲师")
    @Length(max= 500,message="编码长度不能超过500")
    private String career;
    /**
    * 头衔 1高级讲师 2首席讲师
    */
    @NotNull(message="[头衔 1高级讲师 2首席讲师]不能为空")
    @ApiModelProperty("头衔 1高级讲师 2首席讲师")
    private Integer level;
    /**
    * 讲师头像
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("讲师头像")
    @Length(max= 255,message="编码长度不能超过255")
    private String avatar;
    /**
    * 排序
    */
    @NotNull(message="[排序]不能为空")
    @ApiModelProperty("排序")
    private Integer sort;
    /**
    * 逻辑删除 1（true）已删除， 0（false）未删除
    */
    @TableLogic
    @NotNull(message="[逻辑删除 1（true）已删除， 0（false）未删除]不能为空")
    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
    * 更新时间
    */
    @NotNull(message="[更新时间]不能为空")
    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
