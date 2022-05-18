package com.jhzz.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jhzz.commonutils.CommonResult;
import com.jhzz.eduservice.entity.EduTeacher;
import com.jhzz.eduservice.query.TeacherQuery;
import com.jhzz.eduservice.service.EduTeacherService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/18
 * \* Time: 14:31
 * \* Description: 讲师控制器
 * \
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(tags = "讲师管理")
public class EduTeacherController {

    @Resource
    private EduTeacherService teacherService;

    @GetMapping("getAll")
    @ApiOperation("获取全部讲师信息")
    public CommonResult getEduTeacherList() {
        List<EduTeacher> list = teacherService.list();
        return CommonResult.ok().data("list", list);
    }

    @DeleteMapping("del/{id}")
    @ApiOperation("根据id删除讲师")
    @ApiImplicitParam(name = "id", value = "讲师id")
    public CommonResult deleteEduTeacher(@PathVariable String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    @PostMapping("pageTeachers/{pageNum}/{pageSize}")
    @ApiOperation("分页查询讲师信息")
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "pageNum", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面数量", defaultValue = "5")})
    public CommonResult pageTeacher(@PathVariable Long pageNum,
                                    @PathVariable Long pageSize,
                                    @RequestBody(required = false) TeacherQuery teacherQuery) {

        Page<EduTeacher> pageTeachers = teacherService.findPageTeachers(pageNum, pageSize, teacherQuery);
        return CommonResult.ok().data("list", pageTeachers.getRecords()).data("total", pageTeachers.getTotal());
    }

    @PostMapping("addTeacher")
    @ApiOperation("添加讲师")
    @ApiImplicitParam(name = "teacher", value = "讲师对象", dataType = "EduTeacher")
    public CommonResult addTeacher(@RequestBody EduTeacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    @PostMapping("updateTeacher/{id}")
    @ApiOperation("修改讲师信息")
    @ApiImplicitParam(name = "teacher", value = "讲师对象", dataType = "EduTeacher")
    public CommonResult updateTeacher(@ApiParam(value = "修改的id", required = true) @PathVariable String id, @RequestBody EduTeacher teacher) {
        teacher.setId(id);
        teacherService.updateById(teacher);

        return CommonResult.ok().message("修改成功");
    }

    @GetMapping("getTeacher/{id}")
    @ApiOperation("根据id获取讲师信息")
    public CommonResult findTeacher(@PathVariable String id) {
        EduTeacher teacher = teacherService.getById(id);
        return CommonResult.ok().data("teacher",teacher);
    }


}
