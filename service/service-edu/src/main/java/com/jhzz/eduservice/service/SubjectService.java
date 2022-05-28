package com.jhzz.eduservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.Subject;
import org.springframework.web.multipart.MultipartFile;

/**
* @author Huanzhi
* @description 针对表【edu_subject(课程科目)】的数据库操作Service
* @createDate 2022-05-26 23:36:18
*/
public interface SubjectService extends IService<Subject> {

    CommonResult addSubject(MultipartFile file,SubjectService subjectService);

    CommonResult getSubjectList();
}
