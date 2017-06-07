package compiler;

import javax.print.DocFlavor;

/**
 * Created by valyo95 on 12/5/2017.
 */
public class Quadruple {
    int num;
    String op;
    String arg1;
    String arg2;
    String result;
    public Quadruple(int num, String op, String arg1, String arg2, String result) {
        this.num = num;
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
//        System.out.println(this );
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        String arg1Str,arg2Str,resultStr;
        if (arg1 == null)
            arg1Str = "-";
        else
            arg1Str = arg1;

        if (arg2 == null)
            arg2Str = "-";
        else
            arg2Str = arg2;

        if (result == null)
            resultStr = "-";
        else
            resultStr = result;

        return num + ": " + op + "," + arg1Str + "," + arg2Str + "," + resultStr;
    }

//    @Override
//    public String toString() {
//        return "Quadruple{" +
//                "num=" + num +
//                ", op='" + op + '\'' +
//                ", arg1='" + arg1 + '\'' +
//                ", arg2='" + arg2 + '\'' +
//                ", result='" + result + '\'' +
//                '}';
//    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

