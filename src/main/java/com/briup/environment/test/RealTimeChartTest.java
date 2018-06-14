package com.briup.environment.test;
import com.briup.environment.gui.RealTimeChart;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 * @Author: cxx
 * JFreeChart实现实时曲线图
 * @Date: 2018/6/13 14:00
 */
public class RealTimeChartTest {
    public static void main(String[] args)
    {
        JFrame frame=new JFrame("实时数据测试");
        RealTimeChart rtcp=new RealTimeChart("温度","1号温度实时监控","数值",1,1);
        frame.getContentPane().add(rtcp,new BorderLayout().CENTER);
        frame.pack();
        frame.setVisible(true);
        (new Thread(rtcp)).start();
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowevent)
            {
                System.exit(0);
            }

        });
    }
}
