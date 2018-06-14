package com.briup.environment.gui;
import com.briup.environment.bean.MaxMinAvg;
import com.briup.environment.dao.Api;
import com.briup.environment.dao.ApiImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;

/**
 * @Author: cxx
 * 实时图表生成
 * @Date: 2018/6/13 13:59
 */
public class RealTimeChart  extends ChartPanel implements Runnable {
    private static TimeSeries timeSeries;
    private long value=0;
    private static boolean f=true;
    public  void setFlag(boolean flag) {
        RealTimeChart.f = flag;
    }
    private int day;
    private int type;

    /**
     * @param chartContent
     * @param title  标题
     * @param yaxisName
     */
    public RealTimeChart(String chartContent,String title,String yaxisName,int day,int type)
    {
        super(createChart(chartContent,title,yaxisName));
        this.day=day;
        this.type=type;
    }

    private static JFreeChart createChart(String chartContent,String title,String yaxisName){
        //创建时序图对象
        timeSeries = new TimeSeries(chartContent,Millisecond.class);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,"时间(秒)",yaxisName,timeseriescollection,true,true,false);
        XYPlot xyplot = jfreechart.getXYPlot();
        xyplot.setBackgroundPaint(Color.WHITE);
        xyplot.setRangeGridlinePaint(Color.BLUE);//纵坐标格线颜色
        xyplot.setDomainGridlinePaint(Color.BLACK);//横坐标格线颜色
        xyplot.setDomainGridlinesVisible(true);//显示横坐标格线
        xyplot.setRangeGridlinesVisible(true);//显示纵坐标格线

        //纵坐标设定
        ValueAxis valueaxis = xyplot.getDomainAxis();
        //设定图表数据显示部分背景色
        xyplot.setBackgroundPaint(Color.white);
        //设置网格竖线颜色
        xyplot.setDomainGridlinePaint(Color.red);
        //设置网格横线颜色
        xyplot.setRangeGridlinePaint(Color.red);
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(true);


        //自动设置数据轴数据范围
        valueaxis.setAutoRange(true);
        //数据轴固定数据范围 30s
        valueaxis.setFixedAutoRange(30000D);
        valueaxis = xyplot.getRangeAxis();
        //valueaxis.setRange(0.0D,200D);
        return jfreechart;
    }

    public void run()
    {
        Api api = new ApiImpl();
        boolean flag=false;
        while(true)
        {
            try
            {
                //判断该线程是否终止
                if (f) {
                    List<MaxMinAvg> value = api.getMaxMinAvg(day, type);
                    double t = (double) value.get(0).getAvg();//list.get(0).getAvg();
                    BigDecimal b = new BigDecimal(t);
                    t = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    if (!flag) {
                        t = 0;
                        flag = true;
                    }
                    //自己改变一下数据
                  /*  double bb=0;
                    if ((bb=random())>50){
                        if (bb>90) t=t*0.9;
                        else if (bb>80) t=t*0.8;
                        else if (bb>70) t=t*0.7;
                        else t=t*0.6;
                    }*/
                    System.out.println("**********实时数据展示值："+t);
                    timeSeries.add(new Millisecond(),t);
                    Thread.sleep(300);

                }else {
                    break;
                }
            }
            catch (Exception e)  {
                e.printStackTrace();
            }
        }
    }

    //测试
    private long randomNum()
    {
        return (long)(Math.random()*20+80);
    }

    private double random(){
        return Math.random()*100;
    }

    public static void main(String[] args) {
        //System.out.println(Math.random()*100);
    }
}
