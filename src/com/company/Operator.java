package com.company;

import javax.swing.*;

public class Operator {
    double num1=0,num2=0,result=0;
    char operator;

    Operator(double num1, double num2, double result, char operator, JTextArea area){
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
        this.operator = operator;
        f(area);
    }

    public void f(JTextArea area){
        switch(operator) {
            case'+':
                result=num1+num2;
                area.append( num1 + " + " + num2);
                break;
            case'-':
                result=num1-num2;
                area.append(num1 + " - " + num2);
                break;
            case'*':
                result=num1*num2;
                area.append(num1 + " * " + num2);
                break;
            case'/':
                result=num1/num2;
                area.append(num1 + " / " + num2);
                break;}
    }
}
