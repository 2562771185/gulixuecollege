package com.jhzz.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.Subject;
import com.jhzz.eduservice.entity.excel.SubjectData;
import com.jhzz.eduservice.entity.vo.SubjectListVo;
import com.jhzz.eduservice.listener.SubjectExcelListener;
import com.jhzz.eduservice.service.SubjectService;
import com.jhzz.eduservice.mapper.SubjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Huanzhi
 * @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
 * @createDate 2022-05-26 23:36:18
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
        implements SubjectService {


    @Override
    public CommonResult addSubject(MultipartFile file, SubjectService subjectService) {
        if (file == null) {
            System.out.println("文件为空 ");
            return CommonResult.error().data("msg", "文件为空");
        }
        try {
            //文件输入流
            InputStream stream = file.getInputStream();
            EasyExcel.read(stream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.ok();
    }

    @Override
    public CommonResult getSubjectList() {
        //1.查询一级分类
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        List<Subject> oneList = this.list(wrapper);
        List<SubjectListVo> listVos1 = copyList(oneList);
        //2.查询二级分类,将二级分类加入一级分类的子类中 todo 递归查询 效率可能很低
        for (SubjectListVo listVo : listVos1) {
            listVo.setChildren(findChildren(listVo));
        }
        return CommonResult.ok().data("list", listVos1);
    }

    private List<SubjectListVo> findChildren(SubjectListVo listVo) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",listVo.getId());
        List<Subject> list = this.list(wrapper);
        List<SubjectListVo> vos = copyList(list);
        //再一次进行子类查询 三级或者死机
        for (SubjectListVo vo : vos) {
            vo.setChildren(findChildren(vo));
        }
        return vos;
    }

    private SubjectListVo copyOne(Subject subject) {
        SubjectListVo vo = new SubjectListVo();
        vo.setId(subject.getId());
        vo.setLabel(subject.getTitle());
        vo.setParentId(subject.getParentId());
        return vo;
    }

    private List<SubjectListVo> copyList(List<Subject> subjectList) {
        List<SubjectListVo> voList = new ArrayList<>();
        for (Subject s : subjectList) {
            voList.add(copyOne(s));
        }
        return voList;
    }
}




