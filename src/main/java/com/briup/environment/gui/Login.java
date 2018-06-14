package com.briup.environment.gui;
import com.briup.environment.util.SystemUtil;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * @Author: cxx
 * @Date: 2018/6/11 22:09
 */
public class Login extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    public static String userflag = null;
    String userName,password;
    boolean flag = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //创建frame
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        String path = null;
        path = SystemUtil.imgPath+"background2.jpg";
        ImageIcon background = new ImageIcon(path);
        JLabel label = new JLabel(background);
        label.setBounds(0, -1, this.getWidth(), this.getHeight());
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JButton btnNewButton = new JButton("注册账号");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Register frame = new Register();
                frame.setVisible(true);
                System.out.println("注册账号");
                dispose();
            }
        });

        btnNewButton.setBorderPainted(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setForeground(Color.lightGray);
        btnNewButton.setFont(new Font("微软雅黑",Font.PLAIN,12));
        btnNewButton.setBounds(300, 83, 90, 23);
        btnNewButton.addMouseMotionListener(new MouseMotionAdapter() {


        });
        contentPane.setLayout(null);
        contentPane.add(btnNewButton);
        textField = new JTextField();
        textField.setBounds(154, 80, 143, 21);
        contentPane.add(textField);
        textField.setColumns(10);
        textField.setBorder(null);
        textField_1 = new JPasswordField();
        textField_1.setBounds(154, 123, 143, 21);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        textField_1.setBorder(null);
        JLabel lblNewLabel = new JLabel("用户名:");
        lblNewLabel.setBounds(82, 83, 54, 15);
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel.setForeground(Color.WHITE);
        contentPane.add(lblNewLabel);
        JLabel lblNewLabel_1 = new JLabel("密码:");
        lblNewLabel_1.setBounds(82, 126, 54, 15);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        contentPane.add(lblNewLabel_1);
        final JLabel label1 = new JLabel("");
        label1.setBounds(160, 155, 200, 15);
        contentPane.add(label1);
        final JButton btnNewButton_1 = new JButton("登陆");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                userName = textField.getText();
                password = textField_1.getText();
                User user= new UserImpl();
                boolean flag=user.login(userName,password);
                contentPane.requestFocus(true);
                btnNewButton_1.setEnabled(true);
                if(flag == true){
                    userflag = userName;
                    MainWindow window = new MainWindow();
                    //初始化一下数据
                    window.selectDataInfo();
                    dispose();
                }
                else{
                    label1.setText("用户名或密码不正确!");
                    label1.setForeground(Color.red);
                    contentPane.add(label1);
                    label1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                }
            }
        });

        btnNewButton_1.setBounds(120, 182, 200, 30);
        btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setForeground(Color.white);
        btnNewButton_1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        contentPane.add(btnNewButton_1);

        JButton button = new JButton("更改密码");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChangeKey frame = new ChangeKey();
                frame.setVisible(true);
                dispose();
            }
        });
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.lightGray);
        button.setFont(new Font("微软雅黑",Font.PLAIN,12));
        button.setBounds(299, 127, 93, 23);
        contentPane.add(button);
    }
}
