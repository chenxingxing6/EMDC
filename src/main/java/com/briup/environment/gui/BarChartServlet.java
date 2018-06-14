package com.briup.environment.gui;
import java.awt.*;
import java.util.List;

import com.briup.environment.bean.MaxMinAvg;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 * @Author: cxx
 * 生成报表类
 * @Date: 2018/6/13 8:56
 */
public class BarChartServlet {
    //构造函数，初始化面板
    public BarChartServlet(List<MaxMinAvg> list,int day){
        //获取数据
        CategoryDataset dataset = getDataset(list);
        String title="";
        if (list.size()==1) {
            title=day+"号数据统计";
        }else {
            title=day+"号所有数据统计";
        }
        //创建图形实体对象
        JFreeChart chart=ChartFactory.createBarChart3D(//工厂模式
                title, //图形的标题
                "", //目录轴的显示标签(X轴)
                "值",  //数据轴的显示标签(Y轴)
                dataset, //数据集
                PlotOrientation.VERTICAL, //垂直显示图形
                true,  //是否生成图样
                true, //是否生成提示工具
                false);//是否生成URL链接
        CategoryPlot plot=chart.getCategoryPlot();//获取图形区域对象
        BarRenderer3D renderer = new BarRenderer3D();//3D属性修改
        //设置网格竖线颜色
        plot.setDomainGridlinePaint(Color.blue);
        plot.setDomainGridlinesVisible(true);
        //设置网格横线颜色
        plot.setRangeGridlinePaint(Color.blue);
        plot.setRangeGridlinesVisible(true);
        //图片背景色
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setOutlineVisible(true);
        //图边框颜色
        plot.setOutlinePaint(Color.magenta);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelPaint(Color.black);//设置数值颜色，默认黑色
        renderer.setBaseItemLabelFont(new Font("微软雅黑",Font.PLAIN,13));
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER));
        renderer.setItemLabelAnchorOffset(15);
        plot.setRenderer(renderer);
        //------------------------------------------获取X轴
        CategoryAxis domainAxis=plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));//设置X轴的标题的字体
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,15));//设置X轴坐标上的字体
        domainAxis.setTickLabelsVisible(true);//X轴的标题文字是否显示
        //-----------------------------------------获取Y轴
        ValueAxis rangeAxis=plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));//设置Y轴坐标上的标题字体
        //设置图样的文字样式
        chart.getLegend().setItemFont(new Font("黑体",Font.BOLD ,15));
        //设置图形的标题
        chart.getTitle().setFont(new Font("宋体",Font.BOLD ,20));
        frame1 =new ChartPanel(chart,true);//将已经画好的图形报表存放到面板中
    }

    //用于获取数据
    private static CategoryDataset getDataset(List<MaxMinAvg> list){
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();//创建数据集对象
        //往数据集对象中添加数据(实际应用中是从数据库，Excel文件或者文本文件中获取数据，这里为了方便起见将数据直接给出)
        System.out.println("============"+list.size());
        String msg="";
        if (list.size()==1){
            switch (list.get(0).getType()){
                case 1:msg="温度";break;
                case 2:msg="湿度";break;
                case 3:msg="光照强度";break;
                default:msg="二氧化碳";break;
            }
            dataset.addValue(list.get(0).getMin(),"最小值",msg);//数据值，X轴，Y轴
            dataset.addValue(list.get(0).getMax(),"最大值",msg);
            dataset.addValue(list.get(0).getAvg(),"平均值",msg);
        }else if (list.size()==4){
            for (MaxMinAvg value : list) {
                switch (value.getType()){
                    case 1:msg="温度";break;
                    case 2:msg="湿度";break;
                    case 3:msg="光照强度";break;
                    default:msg="二氧化碳";break;
                }
                dataset.addValue(value.getMin(),"最小值",msg);
                dataset.addValue(value.getMax(),"最大值",msg);
                dataset.addValue(value.getAvg(),"平均值",msg);
            }
        }
        return dataset;
    }


    //构建容器面板，用于存放已经画好的图形报表
    private ChartPanel frame1;
    //在构造方法中将图形报表初始化
    //构建一个方法，用于获取存放了图形的面板(封装：隐藏具体实现)
    public ChartPanel getChartPanel(){
        return frame1;
    }
}
