package compiler;

import compiler.SymbolTable.SymbolTable;
import compiler.Definition.*;
import compiler.Others.*;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;
import java.lang.String;
import java.lang.Object;
import java.util.*;

/**
 * Created by mt on 6/10/17.
 */
public class AssemblyGenerator {

    //information we need
    private SymbolTable symbolTable;
    private Hashtable<String, TempVar> tempVarHashtable;
    private List<Quadruple> quads;
    private int nextQuadToTranform;//keep track of how many quads we have transformed so far,to continue from there
    private BufferedWriter assemblyWriter;

    public AssemblyGenerator(SymbolTable symbolTable, Hashtable<String, TempVar> tempVarHashtable, List<Quadruple> quads, String filename) {
        this.symbolTable = symbolTable;
        this.tempVarHashtable = tempVarHashtable;
        this.quads = quads;
        nextQuadToTranform = 0;

        String assemblyFileName = filename + ".s";
        try
        {
            FileWriter fw = new FileWriter(assemblyFileName);
            assemblyWriter = new BufferedWriter(fw);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Help routines used to generate assembly code
    private String load(String R, String a)
    {
        Definition definition;
        String generatedCode = "";

        if ( tempVarHashtable.containsKey(a) )
        {//it is a temp variables
            TempVar tmpVar = tempVarHashtable.get(a);
            int size = tmpVar.getSize();
            String sizeType;

            if (size == 4)
            {
                sizeType = "DWORD";
            }
            else//size is 1
            {
                sizeType = "BYTE";
            }

            generatedCode = "\tmov " + R + "," + sizeType + " PTR [ebp - " + tmpVar.getBpOffset()*(-1) + "]\n";
            return generatedCode;
        }
        else if ( (definition = symbolTable.lookup(a)) != null )
        {//it is a variable (local or not local)
            Variable variable = (Variable) definition;
            String sizeType;

            if (variable.getType().equals("int"))
            {
                sizeType = "DWORD";
            }
            else//it is a char
            {
                sizeType = "BYTE";
            }

            //if the variable is local
            if (symbolTable.isLocal(variable))
            {
                if (variable.isReference())//if it is a reference (it is a local parameter passed by reference)
                {
                    generatedCode += "\tmov esi, DWORD PTR [ebp + " + variable.getBpOffset() + "]\n";
                    generatedCode += "\tmov " + R + ", " + sizeType + " PTR [esi]\n";
                }
                else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable

                    if (variable.isAParameter())
                        generatedCode = "\tmov " + R + "," + sizeType + " PTR [ebp + " + variable.getBpOffset() + "]\n";
                    else//it is a local variable
                        generatedCode = "\tmov " + R + "," + sizeType + " PTR [ebp - " + variable.getBpOffset()*(-1) + "]\n";

                    return generatedCode;
                }
            }
            else//variable is not local (need access links, to get to the stack record were it IS local)
            {
                //need to implement getAR function
                generatedCode += "\tgetAR(a)\n";

                if (variable.isReference())//if it is a reference (it is a local parameter passed by reference)
                {
                    generatedCode += "\tmov esi, DWORD PTR [esi + " + variable.getBpOffset() + "]\n";
                    generatedCode += "\tmov " + R + ", " + sizeType + " PTR [esi]\n";
                }
                else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable
                    if (variable.isAParameter())
                        generatedCode = "\tmov " + R + "," + sizeType + " PTR [ebp + " + variable.getBpOffset() + "]\n";
                    else//it is a local variable
                        generatedCode = "\tmov " + R + "," + sizeType + " PTR [ebp - " + variable.getBpOffset()*(-1) + "]\n";

                    return generatedCode;
                }

            }
        }
        else//it is a constant (number or char)
        {

        }

        return "";
    }

    //generates assembly code from the most recent quads
    //we start from nextQuadToTransform at every generate call
    public void generate()
    {
        int totalQuads = quads.size();

        //for all recently added quads
        for (int i=nextQuadToTranform; i<totalQuads; i++)
        {
            Quadruple quad = quads.get(i);
            System.out.println(quad);//print the quad
            //we will also print the assembly code generated for this quad
            String label = "@" + quad.getNum() + "\n";
            String quadOp = quad.getOp();

            //this block of assembly will have '@quadnum' as a label
            try
            {
                assemblyWriter.write(label);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            String assemblyCode = "";

            //transform them to assembly code according to each quad's op type
            if (quadOp.equals(":="))
            {

            }
            else if (quadOp.equals("array"))
            {

            }
            else if (quadOp.equals("+") || quadOp.equals("-"))
            {
                String x = quad.getArg1();
                String y = quad.getArg2();
                assemblyCode += load("eax", x);
                assemblyCode += load("edx", y);

                if (quadOp.equals("+"))
                    assemblyCode += "\t" + "add eax, edx\n";
                else
                    assemblyCode += "\t" + "sub eax, edx\n";

            }
            else if (quadOp.equals("*"))
            {

            }
            else if (quadOp.equals("div") || quadOp.equals("mod"))
            {

            }
            else if (quadOp.equals("=") || quadOp.equals("<") || quadOp.equals("<=") || quadOp.equals(">") || quadOp.equals(">=") || quadOp.equals("#"))
            {

            }
            else if (quadOp.equals("jump"))
            {

            }
            else if (quadOp.equals("unit"))
            {

            }
            else if (quadOp.equals("endu"))
            {

            }
            else if (quadOp.equals("par"))
            {
                String passType = quad.getArg2();

                if (passType.equals("V"))
                {

                }
                else if (passType.equals("R") || passType.equals("RET"))
                {

                }
                else
                {
                    System.out.println("Unknown 'par' pass type : " + passType);
                    System.exit(-1);
                }
            }
            else if (quadOp.equals("call"))
            {

            }
            else if (quadOp.equals("ret"))
            {

            }
            else
            {
                System.out.println("Unknown quadOp :" + quadOp);
                System.exit(-1);
            }

            System.out.println(assemblyCode);//print the assembly code generated for this quad
            try {
                assemblyWriter.write(assemblyCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        nextQuadToTranform = totalQuads;
    }

    public void closeFile()
    {
        try {
            assemblyWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
