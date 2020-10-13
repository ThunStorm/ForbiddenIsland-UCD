package com.esr.test;
//
//import javax.swing.*;
//import java.awt.*;
//
//
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
public class Learning {

//    public static void main(String[] args) {
//        JFrame jFrame = new JFrame("Java第四个GUI程序");    //创建Frame窗口
//        JPanel jPanel = new JPanel();    //创建面板
//        JButton btn1 = new JButton("1");    //创建按钮
//        JButton btn2 = new JButton("2");
//        JButton btn3 = new JButton("3");
//        JButton btn4 = new JButton("4");
//        JButton btn5 = new JButton("5");
//        JButton btn6 = new JButton("6");
//        JButton btn7 = new JButton("7");
//        JButton btn8 = new JButton("8");
//        JButton btn9 = new JButton("9");
//        jPanel.add(btn1);    //面板中添加按钮
//        jPanel.add(btn2);
//        jPanel.add(btn3);
//        jPanel.add(btn4);
//        jPanel.add(btn5);
//        jPanel.add(btn6);
//        jPanel.add(btn7);
//        jPanel.add(btn8);
//        jPanel.add(btn9);
//        //向JPanel添加FlowLayout布局管理器，将组件间的横向和纵向间隙都设置为20像素
//        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
//        jPanel.setBackground(Color.gray);    //设置背景色
//        jPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//        jFrame.add(jPanel);    //添加面板到容器
//        jFrame.setBounds(300, 200, 100, 500);    //设置容器的大小
//        jFrame.setVisible(true);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // BoxLayout Code
//        public static void main(String[] args)
//        {
//            JFrame frame=new JFrame("Java示例程序");
//            Box b1=Box.createHorizontalBox();    //创建横向Box容器
//            Box b2=Box.createVerticalBox();    //创建纵向Box容器
//            frame.add(b1);    //将外层横向Box添加进窗体
//            b1.add(Box.createVerticalStrut(200));    //添加高度为200的垂直框架
//            b1.add(new JButton("西"));    //添加按钮1
//            b1.add(Box.createHorizontalStrut(140));    //添加长度为140的水平框架
//            b1.add(new JButton("东"));    //添加按钮2
//            b1.add(Box.createHorizontalGlue());    //添加水平胶水
//            b1.add(b2);    //添加嵌套的纵向Box容器
//            //添加宽度为100，高度为20的固定区域
//            b2.add(Box.createRigidArea(new Dimension(100,20)));
//            b2.add(new JButton("北"));    //添加按钮3
//            b2.add(Box.createVerticalGlue());    //添加垂直组件
//            b2.add(new JButton("南"));    //添加按钮4
//            b2.add(Box.createVerticalStrut(40));    //添加长度为40的垂直框架
//            //设置窗口的关闭动作、标题、大小位置以及可见性等
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setBounds(100,100,400,200);
//            frame.setVisible(true);
//        }
//    }


    //向JFrame中添加JButton按钮
    public static void makeButton(String title,JFrame frame,GridBagLayout gridBagLayout,GridBagConstraints constraints)
    {
        JButton button=new JButton(title);    //创建Button对象
        gridBagLayout.setConstraints(button,constraints);
        frame.add(button);
    }

    // gridLayout
//    public static void main(String[] args)
//    {
//        JFrame frame=new JFrame("GridLayou布局计算器");
//        JPanel panel=new JPanel();    //创建面板
//        //指定面板的布局为GridLayout，4行4列，间隙为5
//        panel.setLayout(new GridLayout(4,4,5,5));
//        panel.add(new JButton("7"));    //添加按钮
//        panel.add(new JButton("8"));
//        panel.add(new JButton("9"));
//        panel.add(new JButton("/"));
//        panel.add(new JButton("4"));
//        panel.add(new JButton("5"));
//        panel.add(new JButton("6"));
//        panel.add(new JButton("*"));
//        panel.add(new JButton("1"));
//        panel.add(new JButton("2"));
//        panel.add(new JButton("3"));
//        panel.add(new JButton("-"));
//        panel.add(new JButton("0"));
//        panel.add(new JButton("."));
//        panel.add(new JButton("="));
//        panel.add(new JButton("+"));
//        frame.add(panel);    //添加面板到容器
//        frame.setBounds(300,200,200,150);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
    // GridBagLayout Code
//    public static void main(String[] agrs)
//    {
//        JFrame frame=new JFrame("拨号盘");
//        GridBagLayout gbaglayout=new GridBagLayout();    //创建GridBagLayout布局管理器
//        GridBagConstraints constraints=new GridBagConstraints();
//        frame.setLayout(gbaglayout);    //使用GridBagLayout布局管理器
//        constraints.fill=GridBagConstraints.BOTH;    //组件填充显示区域
//        constraints.weightx=0.0;    //恢复默认值
//        constraints.gridwidth = GridBagConstraints.REMAINDER;    //结束行
//        JTextField tf=new JTextField("13612345678");
//        gbaglayout.setConstraints(tf, constraints);
//        frame.add(tf);
//        constraints.weightx=0.5;    // 指定组件的分配区域
//        constraints.weighty=0.2;
//        constraints.gridwidth=1;
//        makeButton("7",frame,gbaglayout,constraints);    //调用方法，添加按钮组件
//        makeButton("8",frame,gbaglayout,constraints);
//        constraints.gridwidth=GridBagConstraints.REMAINDER;    //结束行
//        makeButton("9",frame,gbaglayout,constraints);
//        constraints.gridwidth=1;    //重新设置gridwidth的值
//
//        makeButton("4",frame,gbaglayout,constraints);
//        makeButton("5",frame,gbaglayout,constraints);
//        constraints.gridwidth=GridBagConstraints.REMAINDER;
//        makeButton("6",frame,gbaglayout,constraints);
//        constraints.gridwidth=1;
//
//        makeButton("1",frame,gbaglayout,constraints);
//        makeButton("2",frame,gbaglayout,constraints);
//        constraints.gridwidth=GridBagConstraints.REMAINDER;
//        makeButton("3",frame,gbaglayout,constraints);
//        constraints.gridwidth=1;
//
//        makeButton("返回",frame,gbaglayout,constraints);
//        constraints.gridwidth=GridBagConstraints.REMAINDER;
//        makeButton("拨号",frame,gbaglayout,constraints);
//        constraints.gridwidth=1;
//        frame.setBounds(400,400,400,400);    //设置容器大小
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}
