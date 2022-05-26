package com.jhzz.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.Subject;
import com.jhzz.eduservice.entity.excel.SubjectData;
import com.jhzz.eduservice.listener.SubjectExcelListener;
import com.jhzz.eduservice.service.SubjectService;
import com.jhzz.eduservice.mapper.SubjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
* @author Huanzhi
* @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
* @createDate 2022-05-26 23:36:18
*/
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
    implements SubjectService{

    @Override
    public CommonResult addSubject(MultipartFile file,SubjectService subjectService) {
        try {
            //文件输入流
            InputStream stream = file.getInputStream();
            EasyExcel.read(stream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.ok();
    }
}




