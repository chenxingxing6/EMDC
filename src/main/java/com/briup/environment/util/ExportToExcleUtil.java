package com.briup.environment.util;

import java.io.File;
import java.util.ArrayList;

import com.briup.environment.bean.Environment;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/**
 * @Author: cxx
 * 导出Excel表
 * @Date: 2018/6/12 19:18
 */
public class ExportToExcleUtil {
    public static void exportEnvInfo(ArrayList list) {
        try {
            WritableWorkbook book = Workbook.createWorkbook(new File("D:\\environment.xls"));
            // 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet(" 第一页 ", 0);
            String[] title = {"名称","发送端id", "树莓派id", "传感器地址", "传感器个数","指令标号","数据","状态","采集时间"};
            for (int i = 0; i < title.length; i++) {
                Label lb = new Label(i, 0, title[i]);
                sheet.addCell(lb);
            }
            int size = list.size();
            for (int j = 1; j <= size; j++) {
                Environment e = (Environment) list.get(j - 1);
                Label lbName = new Label(0, j, e.getName());
                Label lbSrcId = new Label(1, j, e.getSrcId());
                Label lbDesId = new Label(2, j, e.getDesId());
                Label lbSersorAddress = new Label(3, j, e.getSersorAddress());
                jxl.write.Number lbCount = new jxl.write.Number(4, j,e.getCount());
                Label lbCmd = new Label(5, j, e.getDesId());
                Label lbData = new Label(6, j,String.valueOf(e.getData()));
                Label lbStatu = new Label(7, j, String.valueOf(e.getStatus()));
                Label lbDate = new Label(8, j, e.getGather_date().toString());
                sheet.addCell(lbName);
                sheet.addCell(lbSrcId);
                sheet.addCell(lbDesId);
                sheet.addCell(lbSersorAddress);
                sheet.addCell(lbCount);
                sheet.addCell(lbCmd);
                sheet.addCell(lbData);
                sheet.addCell(lbStatu);
                sheet.addCell(lbDate);
            }
            // 写入数据并关闭文件
            book.write();
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
