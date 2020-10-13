package com.esr.test;

import com.esr.gui.widgets.VFlowLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class VFlowLayoutTest {
    public static void main(String[] args) {
        System.out.println("Test my vertical flow layout...");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 300, 500);
        JPanel contentPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        contentPanel.setBorder(padding);
        contentPanel.setBackground(Color.LIGHT_GRAY);
        VFlowLayout myVFlowLayout = new VFlowLayout(1, 0, 5, 5, 20, 40, true, false);
        contentPanel.setLayout(myVFlowLayout);
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(scrollPane);

        for (int i = 0; i < 7; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            button.setPreferredSize(new Dimension(50, 30));
            contentPanel.add(button);
        }

        JButton specButton = new JButton("spec");
        specButton.setPreferredSize(new Dimension(100, 50));
        contentPanel.add(specButton);
        specButton.setVisible(true);

        for (int i = 0; i < 20; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            button.setPreferredSize(new Dimension(90, 30));
            contentPanel.add(button);
        }

        frame.setVisible(true);
    }


}
