package com.company;

import javax.swing.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 作为操作数据的类
 * @author 贾山
 */
public class Operator {
    double num1=0,num2=0,result=0;
    char operator;

    Operator(double num1, double num2, double result, char operator, JTextArea area, Deque<String> queue){
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
        this.operator = operator;
        func(area, queue);
    }

    /**
     * 将表达式的负号‘-’用@符号代替
     * @param oper
     *      运算表达式
     * @return
     *      替换后的表达式
     */
    private static String changeMinus(String oper) {
        //将表达式中的负数符号使用特殊符号替代，获得没有负数字符串
        for(int i=0;i<oper.length();i++){
            char c = oper.charAt(i);
            if(c=='-'){
                //判断第一个字符是否是负数
                if(i==0){
                    oper = "@"+oper.substring(1);
                }else{
                    //判断前一个字符是否是+-*/中的一个
                    char cprev = oper.charAt(i-1);
                    if(cprev=='+'||cprev=='-'||cprev=='*'||cprev=='/'){
                        oper = oper.substring(0, i)+"@"+oper.substring(i+1);
                    }
                }
            }
        }
        return oper;
    }

    /**
     * 从表达式中获得所有参与运算的数字
     * @param oper
     *      运算表达式
     * @return
     *      数字的List集合
     */
    private static List<Double> Oper2cha(String oper) {
        oper = changeMinus(oper);
        //将字符串按照运算符切割，得到字符串数组
        Pattern pt = Pattern.compile("\\+|\\-|\\*|\\/");
        String[] split = pt.split(oper);

        //遍历数组，判断每个元素，将特殊符号转换成负数符号,并转换成double类型放在list集合中
        List<Double> list = new ArrayList<>();
        for(int i=0;i<split.length;i++){
            String single = split[i];
            if(single.charAt(0)=='@'){
                single = "-"+single.substring(1);
            }
            list.add(Double.parseDouble(single));
        }
        return list;
    }

    /**
     * 得到表达式中所有的运算符
     * @param oper
     *      运算表达式
     * @return
     *      符号的list集合
     */
    private static List<Character> Oper2op(String oper) {
        //将表达式中的符号替换为@符号
        oper = changeMinus(oper);
        //将字符串按照正则表达式分组
        String regex = "@[0-9]|[0-9]";
        Pattern pt = Pattern.compile(regex);
        String[] split = pt.split(oper);
        //将数组元素放在集合中
        List<Character> list = new ArrayList<>();
        for(int i=0;i<split.length;i++){
            String temp = split[i].trim();
            if(temp.equals("+")||temp.equals("-")||temp.equals("*")||temp.equals("/")){
                list.add(temp.trim().charAt(0));
            }
        }
        return list;
    }

    /**
     *该方法完成表达式的计算（不带括弧）
     * @param oper
     *      计算表达式
     * @return
     *      返回运算结果
     */
    public static  String calc(String oper){
        //1.将运算表达式分离
        //1.1得到所有参与运算的运算符
        List<Character> op = Oper2op(oper);
        //1.2得到所有的参与运算的数字
        List<Double> cha = Oper2cha(oper);

        //遍历运算符容器，完成乘除运算
        for(int i=0;i<op.size();i++){
            Character c = op.get(i);
            if(c=='*'||c=='/'){
                //取得运算符---->把存储的运算符从符号容器中拿出来
                op.remove(i);//是乘除符号则将其从集合中移除出来
                //取得运算数字--->从数字容器中获得对应索引的字符
                Double opl = cha.remove(i);//拿到运算符左侧数字
                Double opr = cha.remove(i);//拿到运算符右侧数字
                //将运算结果放回--->将运算后的数放在数字容器索引出
                if(c=='*'){
                    cha.add(i, opl*opr);//将运算后的数字添加在i的位置，当前i位置的数向后瞬移
                }else
                    cha.add(i,opl/opr);
                i--;//运算符容器的指针回到原来的位置,防止跳过下一个运算符
            }
        }
        //遍历运算符容器，完成加减运算，当运算符容器为空时，运算结束
        while(!op.isEmpty()){
            //获取运算符
            Character o = op.remove(0);
            //获取左右运算数字
            Double chal = cha.remove(0);
            Double chara = cha.remove(0);
            if(o=='-'){
                chal = chal-chara;
            }
            if(o=='+'){
                chal = chal+chara;
            }
            //将运算结果放回到数字容器中
            cha.add(0,chal);
        }
        return cha.get(0).toString();
    }

    /**
     * 向string中追加数据
     * @param area 历史记录文字的显示板
     * @param queue 用于存储数据的队列
     */
    public void func(JTextArea area, Queue<String> queue){

        StringBuilder arr = new StringBuilder("");
        while (queue.size() > 0) {
            String element = queue.poll();
            arr.append(element);
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
        arr.append(num2);
        this.result = Double.parseDouble(calc(arr.toString()));
    }
}
