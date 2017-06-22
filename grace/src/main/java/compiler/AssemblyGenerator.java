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
    private String recentCode;

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

    public static boolean isParsable(String input) {
        boolean parsable = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            parsable = false;
        }
        return parsable;
    }


    //Help routines used to generate assembly code
    private void load(String R, String a)
    {
        Definition definition;

        if ( tempVarHashtable.containsKey(a) )
        {//it is a temp variable
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

            writeToFile("mov " + R + ", " + sizeType + " PTR [ebp - " + tmpVar.getBpOffset()*(-1) + "]");
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
                    writeToFile("mov esi, DWORD [ebp + " + variable.getBpOffset() + "]");
                    writeToFile("mov " + R + ", " + sizeType + " PTR [esi]");
                }
                else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable

                    if (variable.isAParameter())
                        writeToFile("mov " + R + ", " + sizeType + " PTR [ebp + " + variable.getBpOffset() + "]");
                    else//it is a local variable
                        writeToFile("mov " + R + ", " + sizeType + " PTR [ebp - " + variable.getBpOffset()*(-1) + "]");
                }
            }
            else//variable is not local (need access links, to get to the stack record were it IS local)
            {
                //need to implement getAR function
                writeToFile("getAR("+a+")");

                if (variable.isReference())//if it is a reference (it is a local parameter passed by reference)
                {
                    writeToFile("mov esi, DWORD PTR [esi + " + variable.getBpOffset() + "]");
                    writeToFile("mov " + R + ", " + sizeType + " PTR [esi]");
                }
                else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable
                    if (variable.isAParameter())
                        writeToFile("mov " + R + ", " + sizeType + " PTR [esi + " + variable.getBpOffset() + "]");
                    else//it is a local variable
                        writeToFile("mov " + R + ", " + sizeType + " PTR [esi - " + variable.getBpOffset() * (-1) + "]");

                }

            }
        }
        else//it is a constant (number or char)
        {
            if (a.startsWith("[")) {
                String var = a.substring(1, a.length() - 1);
                TempVar tmpVar = tempVarHashtable.get(var);
                int size = tmpVar.getSize();
                String sizeType;

                if (size == 4) {
                    sizeType = "DWORD";
                } else//size is 1
                {
                    sizeType = "BYTE";
                }

                load("edi", var);

                writeToFile("mov " + R + ", " + sizeType + " PTR [edi]");

            }
            else {
                if (isParsable(a)) {
                    int x = Integer.parseInt(a);
                    writeToFile("mov " + R + ", " + x);
                } else
                    writeToFile("mov " + R + ", " + a);
            }
        }
    }


    private void loadAddr(String R, String a) {
        Definition definition;

        if (tempVarHashtable.containsKey(a))
        {//it is a temp variable
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

            writeToFile("lea " + R + ", " + sizeType + " PTR [ebp - " + tmpVar.getBpOffset()*(-1) + "]");
        }
        else if ((definition = symbolTable.lookup(a)) != null) {//it is a variable (local or not local)
            Variable variable = (Variable) definition;
            String sizeType;

            if (variable.getType().equals("int")) {
                sizeType = "DWORD";
            } else//it is a char
            {
                sizeType = "BYTE";
            }

            //if the variable is local
            if (symbolTable.isLocal(variable)) {
                if (variable.isReference())//if it is a reference (it is a local parameter passed by reference)
                {
                    writeToFile("mov " + R + ", DWORD PTR [ebp + " + variable.getBpOffset() + "]");
                }
                else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable

                    if (variable.isAParameter())
                        writeToFile("lea " + R + ", " + sizeType + " PTR [ebp + " + variable.getBpOffset() + "]");
                    else
                        writeToFile("lea " + R + ", " + sizeType + " PTR [ebp - " + variable.getBpOffset()*(-1) + "]");
                }
            }
            else//variable is not local (need access links, to get to the stack record were it IS local)
            {
                //----------implement getAr()------------
                //need to implement getAR function
                writeToFile("getAR("+a+")");

                if (variable.isReference())//if it is a reference (it is a local parameter passed by reference)
                {
                    writeToFile("mov " + R + ", DWORD PTR [esi + " + variable.getBpOffset() + "]");
                }
                else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable

                    if (variable.isAParameter())
                        writeToFile("lea " + R + ", " + sizeType + " PTR [esi + " + variable.getBpOffset() + "]");
                    else
                        writeToFile("lea " + R + ", " + sizeType + " PTR [esi - " + variable.getBpOffset()*(-1) + "]");
                }
            }
        } else {
            if (a.startsWith("[")) {
                String var = a.substring(1, a.length() - 1);
                TempVar tmpVar = tempVarHashtable.get(var);

                load(R, var);

            }
        }
    }

    private void store(String R, String a) {
        Definition definition;
        String generatedCode = "";

        if (tempVarHashtable.containsKey(a))
        {//it is a temp variables
            TempVar tmpVar = tempVarHashtable.get(a);
            int size = tmpVar.getSize();
            String sizeType;

            if (size == 4) {
                sizeType = "DWORD";
            } else//size is 1
            {
                sizeType = "BYTE";
            }

            writeToFile("mov " + sizeType + " PTR [ebp - " + tmpVar.getBpOffset() * (-1) + "]" + "," + R);
        }
        else if ((definition = symbolTable.lookup(a)) != null) {//it is a variable (local or not local)
            Variable variable = (Variable) definition;
            String sizeType;

            if (variable.getType().equals("int")) {
                sizeType = "DWORD";
            } else//it is a char
            {
                sizeType = "BYTE";
            }

            //if the variable is local
            if (symbolTable.isLocal(variable)) {
                if (variable.isReference())//if it is a reference (it is a local parameter passed by reference)
                {
                    writeToFile("mov esi, DWORD PTR [ebp + " + variable.getBpOffset() + "]");
                    writeToFile("mov " + sizeType + " PTR [esi], " + R);
                } else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable

                    if (variable.isAParameter())
                        writeToFile("mov " + sizeType + " PTR [ebp + " + variable.getBpOffset() + "]," + R);
                    else//it is a local variable
                        writeToFile("mov " + sizeType + " PTR [ebp - " + variable.getBpOffset() * (-1) + "]," + R);
                }

            }
            else//variable is not local (need access links, to get to the stack record were it IS local)
            {
                //need to implement getAR function
                writeToFile("getAR("+a+")");

                if (variable.isReference())//if it is a reference (it is a local parameter passed by reference)
                {
                    writeToFile("mov esi, DWORD PTR [esi + " + variable.getBpOffset() + "]");
                    writeToFile("mov " + sizeType + " PTR [esi], " + R);
                }
                else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable
                    if (variable.isAParameter())
                        writeToFile("mov " + sizeType + " PTR [esi + " + variable.getBpOffset() + "], " + R);
                    else//it is a local variable
                        writeToFile("mov " + sizeType + " PTR [esi - " + variable.getBpOffset() * (-1) + "], " + R);

                }
            }
        } else//it is a constant (number or char)
        {
            if (a.startsWith("[")) {
                String var = a.substring(1, a.length() - 1);
                TempVar tmpVar = tempVarHashtable.get(var);
                int size = tmpVar.getSize();
                String sizeType;

                if (size == 4) {
                    sizeType = "DWORD";
                } else//size is 1
                {
                    sizeType = "BYTE";
                }

                load("edi", var);

                writeToFile("mov " + sizeType + " PTR [edi], " + R);

            }
        }
    }

    //generates assembly code from the most recent quads
    //we start from nextQuadToTransform at every generate call
    public void generate(int localVarSize)
    {
        int totalQuads = quads.size();

        //for all recently added quads
        for (int i=nextQuadToTranform; i<totalQuads; i++)
        {
            Quadruple quad = quads.get(i);
            System.out.println(quad);//print the quad
            //we will also print the assembly code generated for this quad
            String label = "L" + quad.getNum() + ":\n";
            //this block of assembly will have '@quadnum' as a label
            try
            {
                assemblyWriter.write(label);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            String quadOp = quad.getOp();
            this.recentCode = "";//reset recentCode (in each loop,it contains the assembly code generated)

            //transform them to assembly code according to each quad's op type
            if (quadOp.equals(":="))
                assemblyAssign(quad);

            else if (quadOp.equals("array"))
            {
                assemblyArray(quad);
            }
            else if (quadOp.equals("+") || quadOp.equals("-"))
                assemblyAddMinus(quad);
            else if (quadOp.equals("*"))
                assemblyMult(quad);
            else if (quadOp.equals("div") || quadOp.equals("mod"))
                assemblyDivMod(quad);

            else if (quadOp.equals("=") || quadOp.equals("<") || quadOp.equals("<=") || quadOp.equals(">") || quadOp.equals(">=") || quadOp.equals("#"))
                assemblyCond(quad);

            else if (quadOp.equals("jump"))
            {
                writeToFile("jmp " + "L" + quad.getResult());
            }
            else if (quadOp.equals("unit"))
                assemblyUnit(quad, localVarSize);
            else if (quadOp.equals("endu"))
                assemblyEndUnit(quad);

            else if (quadOp.equals("par"))
                assemblyParameterLoad(quad);

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

            System.out.println(recentCode);//print the assembly code generated for this quad
        }

        nextQuadToTranform = totalQuads;
    }

    public void assemblyUnit(Quadruple quad, int localVarSize) {
        String x = quad.getArg1();

        writeToFile(x + ":");
        writeToFile("push ebp");
        writeToFile("mov ebp esp");
        writeToFile("sub esp, " + localVarSize * (-1));
    }


    public void assemblyEndUnit(Quadruple quad) {
        String x = quad.getArg1();

        writeToFile("mov esp ebp");
        writeToFile("pop ebp");
        writeToFile("ret");
    }

    public void assemblyCall(Quadruple quad) {
        String x = quad.getResult();

        Definition def = symbolTable.lookup(x);
        Function f = (Function) def;

        if (def.getType().equals("nothing"))
            writeToFile("sub esp 4");

        //--------UpdateALL()-----------

        //------------------------------

        writeToFile("call " + x);

        writeToFile("add esp, " + f.getParameters().size() + 8);
    }


    public void assemblyAddMinus(Quadruple quad) {
        String quadOp = quad.getOp();
        String x = quad.getArg1();
        String y = quad.getArg2();
        String z = quad.getResult();

        load("eax", x);
        load("edx", y);

        if (quadOp.equals("+"))
            writeToFile("add eax, edx");
        else
            writeToFile("sub eax, edx");

        store("eax", z);
    }


    public void assemblyMult(Quadruple quad) {
        String x = quad.getArg1();
        String y = quad.getArg2();
        String z = quad.getResult();

        load("eax", x);
        load("ecx", y);

        writeToFile("imul ecx");

        store("ecx", z);
    }

    public void assemblyAssign(Quadruple quad) {
        String x = quad.getArg1();
        String z = quad.getResult();

        load("eax", x);
        store("eax", z);
    }

    public void assemblyDivMod(Quadruple quad) {
        String quadOp = quad.getOp();
        String x = quad.getArg1();
        String y = quad.getArg2();
        String z = quad.getResult();


        load("eax", x);
        writeToFile("cdq");

        load("ebx", y);

        writeToFile("idiv ebx");

        if (quadOp.equals("div"))
            store("eax", z);
        else if (quadOp.equals("mod"))
            store("edx", z);

    }

    public void assemblyCond(Quadruple quad) {
        String quadOp = quad.getOp();
        String x = quad.getArg1();
        String y = quad.getArg2();
        String z = quad.getResult();

        load("eax", x);
        load("edx", y);

        writeToFile("cmp eax, edx");
        String label = "L" + z;

        if (quadOp.equals("="))
            writeToFile("je " + label);

        else if (quadOp.equals("<"))
            writeToFile("jl " + label);

        else if (quadOp.equals("<="))
            writeToFile("jle " + label);

        else if (quadOp.equals(">"))
            writeToFile("jg " + label);

        else if (quadOp.equals(">="))
            writeToFile("jge " + label);

        else if (quadOp.equals("#"))
            writeToFile("jne " + label);


        else {
            System.out.println("Big mistake!!\nNo such condition operand. (AssemblyCond)");
            System.exit(-1);
        }
    }

    public void assemblyArray(Quadruple quad)
    {
        String x = quad.getArg1();
        String y = quad.getArg2();
        String z = quad.getResult();

        String arrayType = symbolTable.lookup(x).getType();//This should never throw null pointer exception
        //if the quad array is generated than x must be a defined lvalue in the symbol table!!

        load("eax", y);
        if (arrayType.equals("int"))
        {
            writeToFile("mov ecx, 4");
            writeToFile("imul ecx");
        }
        //if its char, no point multiplying by 1..

        loadAddr("ecx", x);
        writeToFile("add eax, ecx");
        store("eax", z);
    }

    public void assemblyParameterLoad(Quadruple quad) {
        String x = quad.getArg1();
        String passType = quad.getArg2();

        if (passType.equals("V")) {
            load("eax", x);
            writeToFile("push eax");
        }
        else if (passType.equals("R") || passType.equals("RET")) {

            loadAddr("esi", x);
            writeToFile("push esi");
        }
        else
        {
            System.out.println("Unknown 'par' pass type : " + passType);
            System.exit(-1);
        }
    }


    //assembly file functions
    private void writeToFile(String assemblyCode)
    {
        String indentedCode = "\t" + assemblyCode + "\n";
        this.recentCode += indentedCode;

        try
        {
            assemblyWriter.write(indentedCode);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
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
