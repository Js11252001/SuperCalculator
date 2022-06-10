package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Calculator implements ActionListener{
    OperandUI operandUI =new OperandUI();
    Number num = new Number();
    HistoryUI history = new HistoryUI();
    JFrame frame;
    JTextField textField;
    JPanel panel;
    Queue<String> queue;
    boolean isFirst;


    Font myFont = new Font("宋体",Font.BOLD,30);

    double num1, num2, result;
    char operator;

    Calculator(){
        isFirst = true;
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        queue = new PriorityQueue();

        textField = new JTextField();
        textField.setBounds(50, 45, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        history.hisButton.addActionListener(this);


        for(int i =0;i<9;i++) {
            operandUI.functionButtons[i].addActionListener(this);
            operandUI.functionButtons[i].setFont(myFont);
            operandUI.functionButtons[i].setFocusable(false);
        }

        for(int i =0;i<10;i++) {
            num.numberButtons[i] = new JButton(String.valueOf(i));
            num.numberButtons[i].addActionListener(this);
            num.numberButtons[i].setFont(myFont);
            num.numberButtons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4,4,10,10));

        operandUI.AddToPanel(panel,num);

        frame.add(panel);
        frame.add(history.toolBar);
        operandUI.AddToFrame(frame);
        frame.add(textField);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window has been opened.
             *
             * @param e
             */
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                File file = new File("./src/data.txt");
                try {
                    Scanner inputFile = new Scanner(file);
                    while (inputFile.hasNextLine()) {
                        history.area.append(inputFile.nextLine() + "\n");
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }

            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                File file = new File("./src/data.txt");
                String arr = history.area.getText();
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(arr);
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<10;i++) {
            if(e.getSource() == num.numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource()== operandUI.decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if(e.getSource()== operandUI.addButton) {
            if (isFirst) {
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                queue.add(num1 + " + ");
                textField.setText("");
                isFirst = false;
            }
            else {
                num2 = Double.parseDouble(textField.getText());
                num1 = switchOperator(num1, num2, operator);
                operator = '+';
                queue.add(num2 + " + ");
                textField.setText("");
            }
        }
        if(e.getSource()== operandUI.subButton) {
            if (isFirst) {
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                queue.add(num1 + " - ");
                textField.setText("");
                isFirst = false;
            }
            else {
                num2 = Double.parseDouble(textField.getText());
                num1 = switchOperator(num1, num2, operator);
                operator = '-';
                queue.add(num2 + " - ");
                textField.setText("");
            }
        }
        if(e.getSource()== operandUI.mulButton) {
            if (isFirst) {
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                queue.add(num1 + " * ");
                textField.setText("");
                isFirst = false;
            }
            else {
                num2 = Double.parseDouble(textField.getText());
                num1 = switchOperator(num1, num2, operator);
                operator = '*';
                queue.add(num2 + " * ");
                textField.setText("");
            }
        }
        if(e.getSource()== operandUI.divButton) {
            if (isFirst) {
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                queue.add(num1 + " / ");
                textField.setText("");
                isFirst = false;
            }
            else {
                num2 = Double.parseDouble(textField.getText());
                num1 = switchOperator(num1, num2, operator);
                operator = '/';
                queue.add(num2 + " / ");
                textField.setText("");
            }
        }
        if(e.getSource()== operandUI.equButton) {
            num2=Double.parseDouble(textField.getText());
            Operator op = new Operator(num1, num2, result, operator, history.area, queue);
            textField.setText(String.valueOf(op.result));
            history.area.append(" = " + op.result + "\n");
            num1 = 0;
            num2 = 0;
            result = 0;
            isFirst = true;
        }
        if(e.getSource()== operandUI.clrButton) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            isFirst = true;
        }
        if(e.getSource()== operandUI.delButton) {
            String string = textField.getText();
            textField.setText("");
            for(int i=0;i<string.length()-1;i++) {
                textField.setText(textField.getText()+string.charAt(i));
            }
        }
        if(e.getSource()== operandUI.negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp*=-1;
            textField.setText(String.valueOf(temp));
        }
        history.CheckAction(e);
    }

    public double switchOperator(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return 0;
    }
}