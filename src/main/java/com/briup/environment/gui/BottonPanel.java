package com.briup.environment.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class BottonPanel extends JPanel{
    private static final long serialVersionUID = 1L;

    private JButton startButton;
    private JButton pauseButton;
    private JButton endButton;
    private JButton endButton0;
    private JButton mapItem0Button;
    private JButton mapItem1Button;
    private JButton mapItem2Button;
    private JButton mapItem3Button;
    private JButton getItem;
    private JButton getItem1;
    private JButton getItem4;
    //4.17
    private JButton rank;
    //4.22
    private JButton help;

    private JLabel scoreLabel;
    private JLabel lifeLabel;
    private JLabel scoreLabel0;
    private int x=510;
    public static int score;
    public BottonPanel () {


        setLayout(null);
        setBounds(0, 0, 800, 600);
        setOpaque(false);
        ImageIcon buttonIcon0 = new ImageIcon("src/imageIcon/Play.png");
        ImageIcon buttonIcon1 = new ImageIcon("src/imageIcon/Pause.png");
        ImageIcon buttonIcon2 = new ImageIcon("src/imageIcon/power.png");
        ImageIcon buttonIcon3 = new ImageIcon("src/imageIcon/Flag.png");
        ImageIcon buttonIcon4 = new ImageIcon("src/imageIcon/close.png");
        ImageIcon buttonIcon5 = new ImageIcon("src/imageIcon/set.png");
        ImageIcon buttonIcon7 = new ImageIcon("src/imageIcon/rise.png");
        ImageIcon buttonIcon8 = new ImageIcon("src/imageIcon/Help.png");

        startButton = new JButton("     开始游戏",buttonIcon0);
        startButton.setBounds(-20,50, 200, 30);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setForeground(Color.getHSBColor(205f, 15f, 53f));
        add(startButton);


        pauseButton = new JButton("     暂停游戏",buttonIcon1);
        pauseButton.setBounds(-20, 80, 200, 30);
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setForeground(Color.white);
        add(pauseButton);

        endButton = new JButton("     结束游戏",buttonIcon2);
        endButton.setBounds(-20,110, 200, 30);
        endButton.setBorderPainted(false);
        endButton.setContentAreaFilled(false);
        endButton.setForeground(Color.white);
        add(endButton);

        //4.17
        rank = new JButton("     排行榜",buttonIcon7);
        rank.setBounds(-27,150, 200, 30);
        rank.setBorderPainted(false);
        rank.setContentAreaFilled(false);
        rank.setForeground(Color.white);
        add(rank);
        //4.22
        help = new JButton("     游戏帮助",buttonIcon8);
        help.setBounds(-20,400, 200, 30);
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        help.setForeground(Color.white);
        add(help);

        mapItem0Button = new JButton("    模式",buttonIcon3);
        mapItem0Button.setBounds(-33,190, 200, 30);
        mapItem0Button.setBorderPainted(false);
        mapItem0Button.setContentAreaFilled(false);
        mapItem0Button.setForeground(Color.WHITE);
        add(mapItem0Button);

        mapItem1Button =new JButton("普通模式");
        mapItem1Button.setBounds(60,220 , 100, 25);
        mapItem1Button.setBorderPainted(false);
        mapItem1Button.setContentAreaFilled(false);
        mapItem1Button.setForeground(Color.WHITE);
        add(mapItem1Button);

        mapItem2Button =new JButton("对战模式");
        mapItem2Button.setBounds(60,240 , 100, 25);
        mapItem2Button.setBorderPainted(false);
        mapItem2Button.setContentAreaFilled(false);
        mapItem2Button.setForeground(Color.WHITE);
        add(mapItem2Button);

        mapItem3Button =new JButton("挑战模式");
        mapItem3Button.setBounds(60,260 , 100, 25);
        mapItem3Button.setBorderPainted(false);
        mapItem3Button.setContentAreaFilled(false);
        mapItem3Button.setForeground(Color.WHITE);
        add(mapItem3Button);

        getItem =new JButton("     设置",buttonIcon5);
        getItem.setBounds(-32,300 , 200, 30);
        getItem.setBorderPainted(false);
        getItem.setContentAreaFilled(false);
        getItem.setForeground(Color.WHITE);
        add(getItem);

        getItem1 =new JButton("背景颜色");
        getItem1.setBounds(15,330, 200, 30);
        getItem1.setBorderPainted(false);
        getItem1.setContentAreaFilled(false);
        getItem1.setForeground(Color.WHITE);
        add(getItem1);

        getItem4 =new JButton("蛇体颜色");
        getItem4.setBounds(15,360 , 200, 25);
        getItem4.setBorderPainted(false);
        getItem4.setContentAreaFilled(false);
        getItem4.setForeground(Color.WHITE);
        add(getItem4);


        scoreLabel = new JLabel("Snake");
        scoreLabel.setFont(new Font("Serif",Font.BOLD,30));
        scoreLabel.setBounds(30,10, 100, 30);
        scoreLabel.setForeground(Color.white );
        scoreLabel.setOpaque(false);
        add(scoreLabel);

        scoreLabel0 = new JLabel("分数：");
        scoreLabel0.setFont(new Font("Serif",Font.BOLD,18));
        scoreLabel0.setBounds(30,x, 100, 30);
        scoreLabel0.setForeground(Color.white );
        scoreLabel0.setOpaque(false);
        add(scoreLabel0);

        lifeLabel = new JLabel("生命：");
        lifeLabel.setFont(new Font("Serif",Font.BOLD,13));
        lifeLabel.setBounds(130,x, 100, 30);
        lifeLabel.setForeground(Color.white );
        lifeLabel.setOpaque(false);
        add(lifeLabel);

        endButton0 = new JButton(buttonIcon4);
        endButton0.setBounds(570,-20, 100, 100);
        endButton0.setBorderPainted(false);
        endButton0.setContentAreaFilled(false);
        add(endButton0);

        JLabel label1 = new JLabel("");
        label1.setBounds(600,530, 200, 15);
        label1.setText("V 2.1.6");
        label1.setForeground(Color.white);
        label1.setFont(new Font("黑体", Font.PLAIN, 14));
        add(label1);

        Color c= new Color(0, 250,154);
        this.setBackground(c);



        this.setFocusable(true);

    }





    public JButton getGetItem() {
        return getItem;
    }





    public void setGetItem(JButton getItem) {
        this.getItem = getItem;
    }



    public JButton getRank() {
        return rank;
    }



    public void setRank(JButton rank) {
        this.rank = rank;
    }


    //4.22

    public JButton getHelp() {
        return help;
    }



    public void setHelp(JButton help) {
        this.help = help;
    }



    public JButton getStartButton() {
        return startButton;
    }


    public JButton getPauseButton() {
        return pauseButton;
    }


    public JButton getEndButton() {
        return endButton;
    }

    public JButton getEndButton0() {
        return endButton0;
    }


    public JButton getMapItem3Button() {
        return mapItem3Button;
    }


    public JButton getMapItem2Button() {
        return mapItem2Button;
    }


    public JButton getMapItem1Button() {
        return mapItem1Button;
    }

    public JButton getMapItem0Button() {
        return mapItem0Button;
    }


    public JButton getGetItem1() {
        return getItem1;
    }


    public JButton getGetItem4() {
        return getItem4;
    }
    //4.17
    public JButton getRankButton() {
        return rank;
    }

    //4.22
    public JButton getHelpButton() {
        return help;
    }

    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        BottonPanel.score = score;
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.white);
        g.setFont(new Font("Serif",Font.BOLD,50));
        g.drawString(score+"", 80, x+20);
    }
}
