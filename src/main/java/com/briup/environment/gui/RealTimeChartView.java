package com.briup.environment.gui;

import com.briup.environment.bean.MaxMinAvg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Timer;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static oracle.net.aso.C12.e;

/**
 * @Author: cxx
 * 实时数据展示
 * @Date: 2018/6/13 15:10
 */
public class RealTimeChartView {
    private Thread thread=null;
    //构造函数
    public RealTimeChartView(int day, int type){
        String msg="";
        switch (type){
            case 1:msg="温度";break;
            case 2:msg="湿度";break;
            case 3:msg="光照强度";break;
            default:msg="二氧化碳";break;
        }
        JFrame frame=new JFrame("实时数据展示");
        RealTimeChart rtcp=new RealTimeChart(msg,day+"号平均"+msg+"实时监控","数值",day,type);
        //标记线程为开启
        rtcp.setFlag(true);
        frame.getContentPane().add(rtcp,new BorderLayout().CENTER);
        frame.pack();
        frame.setVisible(true);
        thread=new Thread(rtcp);
        thread.start();
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowevent)
            {
                //关闭线程，否则会影响数据的展示
                rtcp.setFlag(false);
            }

        });
    }
}
