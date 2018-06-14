package com.briup.environment.gui;

import com.briup.environment.bean.MaxMinAvg;
import javafx.scene.chart.BarChart;

import javax.swing.*;
import java.util.List;

/**
 * @Author: cxx
 * @Date: 2018/6/13 8:58
 */
public class DemoFrame extends JFrame{
    /**
     * @param list  List<MaxMinAvg>集合数据
     * @param day   哪一天
     */
    public DemoFrame(List<MaxMinAvg> list,int day){
        //----------------------设置窗体大小
        this.setSize(800,600);
        //---------------------------将报表面板添加到窗体中
        this.add(new BarChartServlet(list,day).getChartPanel());
        //----------------------设置窗体大小不可变
        this.setResizable(false);
        //----------------------设置窗体相对于屏幕居中
        this.setLocationRelativeTo(null);
        //------------------------设置窗体可见
        this.setVisible(true);
    }
}
