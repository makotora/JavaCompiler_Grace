package compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mt on 6/24/17.
 */
public class BasicBlock
{
    List<Quadruple> quads;

    public BasicBlock(List<Quadruple> allQuads, int start, int end)
    {
        this.quads = new ArrayList<Quadruple>();
//        System.out.println("\n\nNew basic block! start: " + start + " end: " + end);

        for (int i=start; i<=end; i++)
        {
            int quad_index = i-1;
            //we -1 because the list starts from 0
//            System.out.println(allQuads.get(quad_index));
            this.quads.add(allQuads.get(quad_index));
        }
    }

    public void printQuads()
    {
        for (Quadruple quad : quads) {
            System.out.println(quad);
        }
    }
    public int firstQuadNum() { return quads.get(0).getNum(); }
    public int lastQuadNum() { return quads.get(quads.size()-1).getNum(); }

    public ArrayList<Integer> getJumpQuads()
    {
        ArrayList<Integer> list = new ArrayList<Integer>();//a list of the quads we "jump" to.It can be either 1 or 2 quads

        Quadruple lastQuad = quads.get(quads.size()-1);
        String lastOp = lastQuad.getOp();

        if (lastOp.equals("jump"))//we will only jump at one quad.that of the "jump" quads result
        {
            list.add(Integer.parseInt(lastQuad.getResult()));
        }
        else if (lastOp.equals("<") || lastOp.equals("<=") || lastOp.equals(">") || lastOp.equals(">=") || lastOp.equals("=") || lastOp.equals("#"))
        {//there are 2 quads we can jump to (depending on the result of the condition)
            int jump_quad = Integer.parseInt(lastQuad.getResult());
            int next_quad = lastQuad.getNum() + 1;

            list.add(jump_quad);//if the condition is true

            if (jump_quad != next_quad)//if they are not the same
                list.add(next_quad);
        }
        else//no jumps at all, just continue to the next quad
        {
            list.add(lastQuad.getNum() + 1);
        }

        return list;
    }

    public void constantFolding()
    {
        for (Quadruple quad : quads)
        {
            String op = quad.getOp();

            //if it is a math quad
            if (isMathOp(op))
            {
                //first see if we can do the math compile time
                String arg1 = quad.getArg1();
                String arg2 = quad.getArg2();

                if (isParsable(arg1) && isParsable(arg2))//if they are both constants
                {//do the math yourself and just do an assignment to the quad's result
                    int num1 = Integer.parseInt(arg1);
                    int num2 = Integer.parseInt(arg2);
                    int mathResult;
                    if (op.equals("+"))
                    {
                        mathResult = num1 + num2;
                    }
                    else if (op.equals("-"))
                    {
                        mathResult = num1 - num2;
                    }
                    else if (op.equals("*"))
                    {
                        mathResult = num1 * num2;
                    }
                    else if (op.equals("div"))
                    {
                        mathResult = num1 / num2;
                    }
                    else //mod
                    {
                        mathResult = num1 % num2;
                    }

                    //replace quad with an assignment
                    quad.setOp(":=");
                    quad.setArg1(Integer.toString(mathResult));
                    quad.setArg2("-");
                    //result temp var remains the same
                }
                else//if one of them is a constant, see if we can make something better (mult by 0, add 0 etc)
                {
                    if (isParsable(arg1))
                    {
                        int num1 = Integer.parseInt(arg1);

                        if (op.equals("+") && num1 == 0)//0 + x = x
                        {
                            //replace quad with an assignment
                            quad.setOp(":=");
                            quad.setArg1(quad.getArg2());
                            quad.setArg2("-");
                        }
                        else if (op.equals("*"))
                        {
                            if (num1 == 1)//1 * x = x
                            {
                                quad.setOp(":=");
                                quad.setArg1(quad.getArg2());
                                quad.setArg2("-");
                            }
                            else if (num1 == 0)// 0 * x = 0
                            {
                                quad.setOp(":=");
                                quad.setArg1("0");
                                quad.setArg2("-");
                            }
                            else if (op.equals("div"))
                            {
                                if (num1 == 0)//0 div x = 0
                                {
                                    quad.setOp(":=");
                                    quad.setArg1("0");
                                    quad.setArg2("-");
                                }
                            }
                            else //mod
                            {
                                if (num1 == 0)//0 mod x = 0
                                {
                                    quad.setOp(":=");
                                    quad.setArg1("0");
                                    quad.setArg2("-");
                                }
                            }
                        }
                    }
                    else if (isParsable(arg2))
                    {
                        int num2 = Integer.parseInt(arg2);

                        if (op.equals("+") && num2 == 0)//x + 0 = x
                        {
                            //replace quad with an assignment
                            quad.setOp(":=");
                            quad.setArg2("-");
                        }
                        if (op.equals("+") && num2 == 0)//x - 0 = x
                        {
                            quad.setOp(":=");
                            quad.setArg2("-");
                        }
                        else if (op.equals("*"))
                        {
                            if (num2 == 1)//x * 1 = x
                            {
                                quad.setOp(":=");
                                quad.setArg2("-");
                            }
                            else if (num2 == 0)//x * 0 = 0
                            {
                                quad.setOp(":=");
                                quad.setArg1("0");
                                quad.setArg2("-");
                            }
                        }
                    }
                    else//none of them are constants
                    {//maybe there is something we can do..
                        if (op.equals("+"))
                        {
                            if (arg1.equals(arg2))//x + x = 2x
                            {
                                quad.setOp("*");
                                quad.setArg1("2");
                                quad.setArg2("x");
                            }
                        }
                        else if (op.equals("-"))
                        {
                            if (arg1.equals(arg2))//x - x = 0
                            {
                                quad.setOp(":=");
                                quad.setArg1("0");
                                quad.setArg2("-");
                            }
                        }
                        else if (op.equals("div"))
                        {
                            if (arg1.equals(arg2))//x div x = 1
                            {
                                quad.setOp(":=");
                                quad.setArg1("1");
                                quad.setArg2("-");
                            }
                        }
                        else if (op.equals("mod"))
                        {
                            if (arg1.equals(arg2))//x mod x = 0
                            {
                                quad.setOp(":=");
                                quad.setArg1("1");
                                quad.setArg2("-");
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isMathOp(String op)
    {
        if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("div") || op.equals("mod"))
            return true;
        else
            return false;
    }

    public static boolean isParsable(String input) {
        boolean parsable = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            parsable = false;
        }
        return parsable;
    }
}
