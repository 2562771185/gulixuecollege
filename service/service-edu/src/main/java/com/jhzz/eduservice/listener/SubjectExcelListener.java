package com.jhzz.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhzz.eduservice.entity.Subject;
import com.jhzz.eduservice.entity.excel.SubjectData;
import com.jhzz.eduservice.service.SubjectService;
import com.jhzz.servicebase.exceptionhandler.GuliException;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/27
 * \* Time: 0:14
 * \* Description: 因为 SubjectExcelListener 不能交给spring进行管理，需要自己new
 *                  不能注入其他对象
 * \
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    public SubjectService subjectService;

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectExcelListener() {
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001,"文件数据为空");
        }
        //一行一行的读取，每次读取两个值，第一个值一级分类。第二个值二级分类
        //不能重复添加
        Subject oneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (oneSubject == null){
            //如果为空，则新建一个一级分类
            oneSubject = new Subject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(oneSubject);
        }
        //获取一级分类的值
        String pid = oneSubject.getId();

//        添加二级分类
        Subject towSubject = this.existTowSubject(subjectService, subjectData.getTowSubjectName(), pid);
        if (towSubject == null){
            //如果为空，则新建一个一级分类
            towSubject = new Subject();
            towSubject.setParentId(pid);
            towSubject.setTitle(subjectData.getTowSubjectName());
            subjectService.save(towSubject);
        }


    }

    /**
     * 判断一级分类是否存在，不能重复添加
     * @param subjectService
     * @param name
     * @return
     */
    private Subject existOneSubject(SubjectService subjectService,String name){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Subject serviceOne = subjectService.getOne(wrapper);
        return serviceOne;
    }

    /**
     * 判断二级分类是否存在，不能重复添加
     * @param subjectService
     * @param name
     * @return
     */
    private Subject existTowSubject(SubjectService subjectService,String name,String pid){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Subject serviceTwo = subjectService.getOne(wrapper);
        return serviceTwo;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
