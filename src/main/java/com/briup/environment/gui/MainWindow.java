package com.briup.environment.gui;
import com.briup.environment.dao.Api;
import com.briup.environment.dao.ApiImpl;
import com.briup.environment.bean.Environment;
import com.briup.environment.bean.MaxMinAvg;
import com.briup.environment.util.ExportToExcleUtil;
import com.briup.environment.util.SystemUtil;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class MainWindow extends JFrame {
    private Image image=new ImageIcon(SystemUtil.imgPath+"icon.png").getImage();
    private JTabbedPane leftPagePane = new JTabbedPane();
    private JTabbedPane rightPagePane = new JTabbedPane();
    private JPanel selectPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private Api api =null;
    private DefaultTableModel model;
    private JTable table;
    private JComboBox comboBox=new JComboBox();
    private JComboBox comboBox1=new JComboBox();
    private ButtonGroup bgp = new ButtonGroup();
    private ArrayList alist;

    /**
     * 构造函数，初始化主界面
     */
    public MainWindow() {
        this.setSize(1200, 600);
        initLeftPagePane();
        initRightPagePane();
        //创建菜单栏
        JMenuBar  br=new  JMenuBar() ;
        JMenu other=new JMenu("其他操作") ;
        JMenuItem o1=new JMenuItem("寻求帮助");
        JMenuItem o2=new JMenuItem("退出登录");
        other.add(o1);
        other.add(o2);
        br.add(other);
        this.setJMenuBar(br); //为窗体设置 菜单工具栏
        //JSplitPane 用于分隔两个（只能两个）Component。
        JSplitPane splitePane = new JSplitPane();
        splitePane.setLeftComponent(leftPagePane);
        splitePane.setRightComponent(rightPagePane);
        splitePane.setEnabled(false);
        JLabel label = new JLabel("<html><h1>物联网监控后台管理中心</h1></html>");
        northPanel.add(label);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(splitePane);
        this.setTitle("物联网监控后台管理中心");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setIconImage(image);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //帮助事件
        o1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Helps h = new Helps();
                h.setVisible(true);
            }
        });

        //退出登录
        o2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });

        //数据类型(温度，湿度...)下拉列表事件监听
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDataInfo();
            }
        });
        //day下拉列表监听事件
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDataInfo();
            }
        });
    }

    // 左边部分初始化
    public void initLeftPagePane() {
        JPanel leftPanel = new JPanel();
        initselectPanel();
        JPanel addPanel = addPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        leftPanel.add(selectPanel);
        leftPanel.add(addPanel);
        int w = this.getWidth();
        int h = this.getHeight();
        leftPanel.setPreferredSize(new Dimension(w / 5, h));
        leftPagePane.add("基本操作", leftPanel);
    }

    // 初始化查找信息部分
    public void initselectPanel() {
        JLabel lbSid = new JLabel("日期：");
        JLabel lbName = new JLabel("类型：");
        JButton btSelect = new JButton("开始查找");
        selectPanel.setLayout(null);
        lbSid.setBounds(25, 68, 70, 30);
        lbName.setBounds(25, 125, 70, 30);
        //下拉列表日期选择
        comboBox1.setBounds(70, 70, 130, 28);
        //获取有效天数
        api = new ApiImpl();
        List<Integer> day = api.getDay();
        for (Integer i : day) {
            comboBox1.addItem(i);
        }
        selectPanel.add(comboBox1);
        //下拉列表类型选择
        comboBox.setBounds(70, 130, 130, 28);
        comboBox.addItem("所有数据");
        comboBox.addItem("温度");
        comboBox.addItem("湿度");
        comboBox.addItem("光照强度");
        comboBox.addItem("二氧化碳");
        selectPanel.add(comboBox);
        selectPanel.add(lbSid);
        selectPanel.add(lbName);
        selectPanel.setBorder(BorderFactory.createTitledBorder(null, "信息查询"));


        //按enter来查询
        /*InputMap inputMapSelect = btSelect.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMapSelect = btSelect.getActionMap();
        inputMapSelect.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"enter");
        actionMapSelect.put("enter", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                selectDataInfo();
            }
        });*/
    }

    // 左边增加信息部分,算是内部类，所以变量要为final类型
    public JPanel addPanel() {
        JPanel addPanel = new JPanel();
        JButton btCount = new JButton("统计条数");
        JButton btAddMsg = new JButton("生成报表");
        JButton btRealData = new JButton("实时数据");
        addPanel.setLayout(null);
        btCount.setBounds(75, 65, 100, 30);
        btAddMsg.setBounds(75, 105, 100, 30);
        btRealData.setBounds(75, 145, 100, 30);
        addPanel.add(btCount);
        addPanel.add(btAddMsg);
        addPanel.add(btRealData);
        addPanel.setBorder(BorderFactory.createTitledBorder(null, "信息统计"));
        //事件监听
        btAddMsg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int day = comboBox1.getSelectedIndex();
                int type = comboBox.getSelectedIndex();
                System.out.println("生成报表");
                api = new ApiImpl();
                //根据下标获取day
                day = api.getDay().get(day);
                countDBData(day,type);
                try {
                    new DemoFrame(api.getMaxMinAvg(day,type),day);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btRealData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int day = comboBox1.getSelectedIndex();
                int type = comboBox.getSelectedIndex();
                if (type==0){
                    SystemUtil.alert("请更换数据类型，不支持所有数据!",0);
                    return;
                }
                System.out.println("实时数据分析");
                api = new ApiImpl();
                //根据下标获取day
                day = api.getDay().get(day);
                try {
                    new RealTimeChartView(day,type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //统计记录条数事件
        btCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemUtil.alert("共有"+table.getModel().getRowCount()+"条记录!",1);
            }
        });

        //jdk1.8的写法
        /*btCount.addActionListener(e -> {});*/
        return addPanel;
    }

    // 右边部分初始化
    public void initRightPagePane() {
        JPanel selectRSPanel = new JPanel();
        JPanel southPanel = rightPageSouthPane();
        String[] columnNames = {"","名称","发送端id", "树莓派id", "传感器地址", "传感器个数","指令标号","数据","状态","采集时间"};
        String[][] data = {};
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        TableColumn tc = table.getTableHeader().getColumnModel().getColumn(0);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setWidth(0);
        tc.setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cr);
        JScrollPane centerPanel = new JScrollPane(table);
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.blue));

        selectRSPanel.setLayout(new BorderLayout());
        selectRSPanel.add(centerPanel, BorderLayout.CENTER);
        selectRSPanel.add(southPanel, BorderLayout.SOUTH);
        rightPagePane.add("结果信息展现与操作", selectRSPanel);
    }

    // 初始化右边部分的页脚
    public JPanel rightPageSouthPane() {
        JPanel southPanel = new JPanel();
        JButton btExportMsg = new JButton("导出信息到excel");
        JButton btDelMsg = new JButton("删除信息");
        JButton btFreeChart = new JButton("图形报表");
        JButton btClearData = new JButton("清空数据");
        JButton btRefrashData = new JButton("刷新数据");
        btExportMsg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ArrayList<Environment> tableList = getTableList();
                if (tableList != null && tableList.size() != 0) {
                    ExportToExcleUtil.exportEnvInfo(tableList);
                    SystemUtil.alert("已经将信息导出到xls文件中！",1);
                } else {
                    SystemUtil.alert("列表为空，没有信息到导出！",0);
                }
            }
        });

        //删除按钮
        btDelMsg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               delectInfo();
            }
        });

        //图形报表
        btFreeChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               ArrayList<Environment> tableList = getTableList();
                if (tableList != null && tableList.size() != 0) {
                    //new FreeChart(tableList);
                    int day = comboBox1.getSelectedIndex();
                    int type = comboBox.getSelectedIndex();
                    System.out.println("生成报表");
                    api = new ApiImpl();
                    //根据下标获取day
                    day = api.getDay().get(day);
                    countDBData(day,type);
                    try {
                        new DemoFrame(api.getMaxMinAvg(day,type),day);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    SystemUtil.alert("列表为空，没有图表报表的数据！",0);
                }
            }
        });

        //刷新数据
        btRefrashData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               //根据当前选择信息，刷新数据
                selectDataInfo();
            }
        });

        //清空数据
        btClearData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                model.setRowCount(0);

            }
        });
        southPanel.setLayout(new GridLayout(1, 5));
        southPanel.add(btExportMsg);
        southPanel.add(btDelMsg);
        southPanel.add(btFreeChart);
        southPanel.add(btClearData);
        southPanel.add(btRefrashData);
        southPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        return southPanel;
    }

    //设置表单数据
    public void setTable(Collection<Environment> env){
        model.setRowCount(0);
        if (env.size()>0) {
            try {
                int id=0;
                for (Environment o : env) {
                    id++;
                    Object[] data = {
                            id,
                            o.getName(),
                            o.getSrcId(),
                            o.getDesId(),
                            o.getSersorAddress(),
                            o.getCount(),
                            o.getCmd(),
                            o.getData(),
                            o.getStatus(),
                            SystemUtil.formateTimestamp(o.getGather_date())
                    };
                    model.addRow(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            SystemUtil.alert("没有记录...",1);
        }
    }

    //获取table上的数据
    public ArrayList getTableList(){
        try{
        ArrayList<Environment> list = new ArrayList<Environment>();
        int size = table.getModel().getRowCount();
        for (int i = 0; i < size; i++) {
            Environment e = new Environment();
            e.setName(table.getModel().getValueAt(i, 1).toString());
            e.setSrcId(table.getModel().getValueAt(i, 2).toString());
            e.setDesId(table.getModel().getValueAt(i, 3).toString());
            e.setSersorAddress(table.getModel().getValueAt(i, 4).toString());
            e.setCount((int)table.getModel().getValueAt(i, 5));
            e.setCmd(table.getModel().getValueAt(i, 6).toString());
            e.setData((float)table.getModel().getValueAt(i, 7));
            e.setStatus((int)table.getModel().getValueAt(i, 8));
            e.setGather_date(SystemUtil.dateToStamp(table.getModel().getValueAt(i, 9).toString()));
            list.add(e);
        }
        return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除数据，这里没调用sql
     */
    public void delectInfo() {
        int len = table.getSelectedRows().length;
        if (len > 0) {
            int id;
            int type = SystemUtil.alertConfirm("删除后数据将不可恢复,真的要删除吗？");
            if (type == JOptionPane.YES_OPTION) {
               for (int j = len; j > 0; j--) // 注意这个地方要从后往前删
                {
                    System.out.println(table.getSelectedRow());
                    id = Integer.parseInt(table.getValueAt(
                     table.getSelectedRow(), 0).toString());
                    System.out.println("删除数据id:"+id);
                    model.removeRow(table.getSelectedRow());
                }
            }
        } else {
            SystemUtil.alert("没有选中任何行！",0);
        }
    }


    /**
     * 按条件获取Collection数据
     * 天数，温度，湿度，光照强度，二氧化碳
     */
    public void selectDataInfo() {
        //获取下拉列表索引
        int day = comboBox1.getSelectedIndex();
        int type = comboBox.getSelectedIndex();
        api = new ApiImpl();
        //获取到几号
        day=api.getDay().get(day);
        try {
            //按条件从数据库查询数据
            setTable(api.getData(day,type));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试用，将数据打印在控制台
    public void countDBData(int day,int type){
        api = new ApiImpl();
        if (type==0){
            try {
                System.out.println("所有数据");
                List<MaxMinAvg> data = api.getMaxMinAvg(day, type);
                for (MaxMinAvg d : data) {
                    System.out.println(d.toString());
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else {
            try {
                System.out.println("数据种类："+type);
                List<MaxMinAvg> data = api.getMaxMinAvg(day, type);
                System.out.println(data.get(0).toString());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow stuMis = new MainWindow();
                    stuMis.setVisible(true);
                    stuMis.selectDataInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
