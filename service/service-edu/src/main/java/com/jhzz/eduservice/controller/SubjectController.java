package com.jhzz.eduservice.controller;

import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.service.SubjectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/26
 * \* Time: 23:38
 * \* Description:
 * \
 */
@RestController
@RequestMapping("eduservice/subject")
public class SubjectController {
    @Resource
    private SubjectService subjectService;

    /**
     * 添加课程分类
     */
    @PostMapping("addSubject")
    public CommonResult addSubject(MultipartFile file) {
        //上传过来的excel文件
        return subjectService.addSubject(file,subjectService);
    }
}
