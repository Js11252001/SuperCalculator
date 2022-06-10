package com.company;

import javax.swing.*;
import java.util.Queue;

public class Operator {
    double num1=0,num2=0,result=0;
    char operator;

    Operator(double num1, double num2, double result, char operator, JTextArea area, Queue<String> queue){
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
        this.operator = operator;
        f(area, queue);
    }

    public void f(JTextArea area, Queue<String> queue){

        while (queue.size() > 0) {
            String element = queue.poll();
            area.append(element);
        }
        switch(operator) {
            case'+':
                result=num1+num2;
                area.append("" + num2);
                break;
            case'-':
                result=num1-num2;
                area.append("" + num2);
                break;
            case'*':
                result=num1*num2;
                area.append("" + num2);
                break;
            case'/':
                result=num1/num2;
                area.append("" + num2);
                break;}
    }
}
