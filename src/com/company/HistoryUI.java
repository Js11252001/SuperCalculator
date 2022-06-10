package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HistoryUI {
    JToolBar toolBar;
    JTextArea area;
    JFrame r;
    JScrollPane scrollPane;
    JButton hisButton;

    HistoryUI() {
        toolBar = new JToolBar();
        area = new JTextArea();
        hisButton = new JButton("历史记录");
        hisButton.setToolTipText("查看历史记录");
        toolBar.add(hisButton);
        toolBar.setBounds(0,0,80,40);
    }

    public void CheckAction(ActionEvent e) {
        if(e.getSource()==hisButton) {
            if (r == null) {
                r = new JFrame("历史记录");
                r.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                r.setBounds(300, 100, 400, 200);
                area.setBounds(23,217,350,180);
                area.setBackground(Color.LIGHT_GRAY);
                area.setEditable(false);
                scrollPane = new JScrollPane();
                scrollPane.setBounds(23,217,350,180);
                scrollPane.setViewportView(area);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                r.add(scrollPane);
                r.setVisible(true);
            }
            else {
                r.setVisible(true);
            }
        }
    }
}
