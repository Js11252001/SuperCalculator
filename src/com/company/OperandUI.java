package com.company;

import javax.swing.*;

/** 操作数的UI类
 * @author 贾山
 */
public class OperandUI {
        JButton addButton,subButton,mulButton,divButton;
        JButton decButton, equButton, delButton, clrButton, negButton;
        JButton[] functionButtons = new JButton[9];

        OperandUI(){
            addButton = new JButton("+");
            subButton = new JButton("-");
            mulButton = new JButton("*");
            divButton = new JButton("/");
            decButton = new JButton(".");
            equButton = new JButton("=");
            delButton = new JButton("Del");
            clrButton = new JButton("Clr");
            negButton = new JButton("(-)");

            functionButtons[0] = addButton;
            functionButtons[1] = subButton;
            functionButtons[2] = mulButton;
            functionButtons[3] = divButton;
            functionButtons[4] = decButton;
            functionButtons[5] = equButton;
            functionButtons[6] = delButton;
            functionButtons[7] = clrButton;
            functionButtons[8] = negButton;

            negButton.setBounds(50,430,100,50);
            delButton.setBounds(150,430,100,50);
            clrButton.setBounds(250,430,100,50);

        }

    /**
     * 将num的按钮添加到面板中
     * @param panel 要添加的面板
     * @param num 要添加的num按钮
     */
    public void AddToPanel(JPanel panel, Number num){
            panel.add(num.numberButtons[1]);
            panel.add(num.numberButtons[2]);
            panel.add(num.numberButtons[3]);
            panel.add(addButton);
            panel.add(num.numberButtons[4]);
            panel.add(num.numberButtons[5]);
            panel.add(num.numberButtons[6]);
            panel.add(subButton);
            panel.add(num.numberButtons[7]);
            panel.add(num.numberButtons[8]);
            panel.add(num.numberButtons[9]);
            panel.add(mulButton);
            panel.add(decButton);
            panel.add(num.numberButtons[0]);
            panel.add(equButton);
            panel.add(divButton);
        }

    /**
     * 添加按钮到frame中
      * @param frame 要添加的frame
     */
    public void AddToFrame(JFrame frame){

        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);

    }
}


