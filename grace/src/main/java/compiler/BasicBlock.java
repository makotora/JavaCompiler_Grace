package compiler;

import java.util.ArrayList;
import java.util.HashSet;
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

    public void delete()
    {
        //replace every quad with noop(no assembly code will be generated)
        for (Quadruple quad : quads)
        {
            deleteQuad(quad);
        }
    }

    public void deleteQuad(Quadruple quad)
    {
        quad.setOp("noop");
        quad.setArg1("-");
        quad.setArg2("-");
        quad.setResult("-");
    }


    //if x := a
    // other_code
    // y := x
    // then y := a ! (BUT! other_code cannot assign x a different value)
    public void copyPropagation()
    {
        int total = quads.size();
        for (int i=0; i<total; i++)
        {
            Quadruple quad = quads.get(i);

            if (quad.getOp().equals(":="))
            {// var := value
                String var = quad.getResult();//the variable
                String value = quad.getArg1();//the value assigned to the variable

                //for all quads after this one, propagate (if you can) the right value of the left
                for (int j=i+1; j<total; j++)
                {
                    Quadruple innerQuad = quads.get(j);

                    if (innerQuad.getOp().equals(":=") && innerQuad.getResult().equals(var))//if var is being assigned a different value
                    {//we cant propagate anymore. the value is changed so we cant switch 'var' with 'value' below here
                        break;
                    }
                    else //if 'var' is not being assigned a different value
                    {// if 'var' shows up ANYWHERE (arg1 or arg2) we can replace it with 'value'
                        String arg1 = innerQuad.getArg1();
                        String arg2 = innerQuad.getArg2();

                        if (arg1 != null && arg1.equals(var))
                            innerQuad.setArg1(value);

                        if (arg2 != null && arg2.equals(var))
                            innerQuad.setArg2(value);
                    }
                }
            }
        }
    }


    public void subexpressions()
    {
        int total = quads.size();
        for (int i=0; i<total; i++)
        {
            Quadruple quad = quads.get(i);
            String op = quad.getOp();

            if ( isMathOp(op) )
            {// op,x,y,result
                String result = quad.getResult();
                String x = quad.getArg1();
                String y = quad.getArg2();

                //for all quads after this one, propagate (if you can) the right value of the left
                for (int j=i+1; j<total; j++)
                {
                    Quadruple innerQuad = quads.get(j);
                    String innerOp = innerQuad.getOp();
                    String quadResult = innerQuad.getResult();

                    if ( (innerOp.equals(":=") || isMathOp(innerOp)) && (quadResult.equals(result) || quadResult.equals(x) || quadResult.equals(y)))//if var is being assigned a different value
                    {//we cant propagate anymore. the value is changed so we cant switch 'var' with 'value' below here
                        break;
                    }
                    else if (op.equals(innerOp))//if 'var' is not being assigned a different value
                    {// if 'var' shows up ANYWHERE (arg1 or arg2) we can replace it with 'value'
                        String x2 = innerQuad.getArg1();
                        String y2 = innerQuad.getArg2();

                        if (op.equals("+") || op.equals("*"))
                        {
                            if ( (x.equals(x2) && y.equals(y2)) || (x.equals(y2) && y.equals(x2)))
                            {
                                innerQuad.setOp(":=");
                                innerQuad.setArg1(result);
                                innerQuad.setArg2("-");
                            }
                        }
                        else
                        {
                            if ( x.equals(x2) && y.equals(y2) )
                            {
                                innerQuad.setOp(":=");
                                innerQuad.setArg1(result);
                                innerQuad.setArg2("-");
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isInSingleAssignmentForm()
    {
        HashSet assignments = new HashSet();

        for (Quadruple quad : quads) {
            //if it is an assignment
            String leftPart;
            if (quad.getOp().equals(":="))
            {
                leftPart = quad.getResult();

                //if its not the first time we are assigning something to this result
                if (assignments.contains(leftPart))
                    return false;//its not in single assignment form
                else
                    assignments.add(leftPart);//note that we have assigned something to this result
            }

        }

        return true;//if we dont return false inside, it is in single assignment form!
    }

    public void constantFolding()
    {
        for (Quadruple quad : quads)
        {
            String op = quad.getOp();

            if (op.equals(":="))//if it is an assignment
            {
                // x = x, doesnt need to exist!
                if (quad.getArg1().equals(quad.getArg2()))//if left part and right part of assignment is the same
                {
                    deleteQuad(quad);//this quad doesnt have to exist
                }
            }
            //if it is a comparison quad
            else if (isCompareOp(op))
            {
                String arg1 = quad.getArg1();
                String arg2 = quad.getArg2();

                if (isParsable(arg1) && isParsable(arg2))//if they are both constants
                {
                    boolean evaluation;
                    int num1 = Integer.parseInt(arg1);
                    int num2 = Integer.parseInt(arg2);

                    if (op.equals("="))
                    {
                        evaluation = (num1 == num2);
                    }
                    else if (op.equals("#"))
                    {
                        evaluation = (num1 != num2);
                    }
                    else if (op.equals(">"))
                    {
                        evaluation = (num1 > num2);
                    }
                    else if (op.equals(">="))
                    {
                        evaluation = (num1 >= num2);
                    }
                    else if (op.equals("<"))
                    {
                        evaluation = (num1 < num2);
                    }
                    else// <=
                    {
                        evaluation = (num1 <= num2);
                    }

                    if (evaluation == true)//if it is always evaluated to true
                    {//just make the jump! (always)
                        quad.setOp("jump");
                        quad.setArg1("-");
                        quad.setArg2("-");
                    }
                    else//"erase" this quad, it can never jump! make it a noop quad
                    {
                        deleteQuad(quad);
                    }
                }
            }
            //if it is a math quad
            else if (isMathOp(op))
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

    private boolean isCompareOp(String op)
    {
        if (op.equals(">") || op.equals(">=") || op.equals("<") || op.equals("<=") || op.equals("=") || op.equals("#"))
            return true;
        else
            return false;
    }


    private static boolean isParsable(String input) {
        boolean parsable = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            parsable = false;
        }
        return parsable;
    }


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
}
