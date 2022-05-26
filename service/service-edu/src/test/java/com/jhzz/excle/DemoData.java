package com.jhzz.excle;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/26
 * \* Time: 22:15
 * \* Description:
 * \
 */
@Data
public class DemoData {

    //设置表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private int sno;

    //设置表头名称
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
