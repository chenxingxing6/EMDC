package com.briup.environment.gui;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
/**
 * @Author: cxx
 * 扇形图报表，该项目中没用
 * @Date: 2018/6/12 19:48
 */
public class FreeChart {
    public FreeChart(ArrayList alist) {
        display(alist);

    }

    /**
     * 创建数据集
     * @param alist
     * @return饼图数据集
     */
    public DefaultPieDataset getDpd(ArrayList alist) {
        DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
        int count = alist.size();
        int menCount = 0;
        for (int i = 0; i < count; i++) {
            /*Student s = (Student) alist.get(i);
            if (s.getSex().equals("m") || s.getSex().equals("M")) {
                menCount = menCount + 1;
            }*/
        }
        count=100;
        menCount=20;
        double menPor = 100 * menCount / count;
        dpd.setValue("男", menPor); // 输入数据
        dpd.setValue("女", (100 - menPor));
        return dpd;
    }

    /**
     * 创建图形
     * @param alist
     * @return ChartPanel
     */
    public JPanel createChart(ArrayList alist) {
        JFreeChart chart = ChartFactory.createPieChart("查询结果中男女比例",
                getDpd(alist), true, true, false);
        // 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }

    public void display(ArrayList alist) {
        JFrame frame = new JFrame("男女比例");
        frame.add(createChart(alist));
        // chart要放在Java容器组件中，ChartFrame继承自java的Frame类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        frame.pack(); // 以合适的大小展现图形
        frame.setVisible(true);// 图形是否可见
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}
