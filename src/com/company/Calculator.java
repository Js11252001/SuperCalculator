package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Calculator implements ActionListener{
    Operand operand =new Operand();
    Number num = new Number();
    History history = new History();
    JFrame frame;
    JTextField textField;
    JPanel panel;


    Font myFont = new Font("宋体",Font.BOLD,30);

    double num1=0,num2=0,result=0;
    char operator;

    Calculator(){

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 45, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        history.hisButton.addActionListener(this);


        for(int i =0;i<9;i++) {
            operand.functionButtons[i].addActionListener(this);
            operand.functionButtons[i].setFont(myFont);
            operand.functionButtons[i].setFocusable(false);
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

        operand.AddToPanel(panel,num);

        frame.add(panel);
        frame.add(history.toolBar);
        operand.AddToFrame(frame);
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
        if(e.getSource()==operand.decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if(e.getSource()==operand.addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator ='+';
            textField.setText("");
        }
        if(e.getSource()==operand.subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator ='-';
            textField.setText("");
        }
        if(e.getSource()==operand.mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator ='*';
            textField.setText("");
        }
        if(e.getSource()==operand.divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator ='/';
            textField.setText("");
        }
        if(e.getSource()==operand.equButton) {
            num2=Double.parseDouble(textField.getText());

            Operator op = new Operator(num1,num2,result,operator, history.area);
            num1= op.num1;
            num2= op.num2;
            result= op.result;


            textField.setText(String.valueOf(result));
            history.area.append(" = " + result + "\n");
            num1=result;
        }
        if(e.getSource()==operand.clrButton) {
            textField.setText("");
        }
        if(e.getSource()==operand.delButton) {
            String string = textField.getText();
            textField.setText("");
            for(int i=0;i<string.length()-1;i++) {
                textField.setText(textField.getText()+string.charAt(i));
            }
        }
        if(e.getSource()==operand.negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp*=-1;
            textField.setText(String.valueOf(temp));
        }
        history.CheckAction(e);
    }
}
