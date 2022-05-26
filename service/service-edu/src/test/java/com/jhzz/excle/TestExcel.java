package com.jhzz.excle;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/26
 * \* Time: 22:16
 * \* Description:
 * \
 */
public class TestExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //设置写入文件夹地址 和 Excel文件名
//        String filename = "D:\\write.xlsx";
//        //2 调用EasyExcel里面的方法实现写操作
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表")
//                .doWrite(getList());
        //excel读操作
        String filename = "D:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();

    }
    private static List<DemoData> getList() {
        ArrayList<DemoData> demoData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSname("王-"+i);
            data.setSno(i+1);
            demoData.add(data);
        }
        return demoData;
    }
}
