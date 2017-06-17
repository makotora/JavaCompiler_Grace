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
                    generatedCode += "\tmov esi, " + sizeType + " [ebp + " + variable.getBpOffset() + "]\n";
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
            if (isParsable(a)) {
                int x = Integer.parseInt(a);
                generatedCode += "\tmov " + R + ", " + x + " \n";
            } else
                generatedCode += "\tmov " + R + ", ASCII(" + a.replace("'", "") + ")\n";

            return generatedCode;
        }

        return "";
    }

    private String loadAddr(String R, String a) {
        Definition definition;
        String generatedCode = "";
        if (tempVarHashtable.containsKey(a)) {
            System.out.println("No loadAddr for tmpVarin DIAFANIES. Must be an error!\nExiting\n");
            System.exit(-1);
            return generatedCode;
        } else if ((definition = symbolTable.lookup(a)) != null) {//it is a variable (local or not local)
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
                    //-----------Den 3erw akoma an prepei na kanoyme kati edwwww------
            /*        generatedCode += "\tmov esi, " + sizeType +" [ebp + " + variable.getBpOffset() + "]\n";
                    generatedCode += "\tmov " + R + ", " + sizeType + " PTR [esi]\n";
            */
                } else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable

                    if (variable.isAParameter())
                        generatedCode = "\tlea " + R + "," + sizeType + " PTR [ebp + " + variable.getBpOffset() + "]\n";

                    return generatedCode;
                }
            } else//variable is not local (need access links, to get to the stack record were it IS local)
            {
                //need to implement getAR function
                //Need to implement genika!
            }
        }

        return "";
    }

    private String store(String R, String a) {
        Definition definition;
        String generatedCode = "";

        if (tempVarHashtable.containsKey(a)) {//it is a temp variables
            TempVar tmpVar = tempVarHashtable.get(a);
            int size = tmpVar.getSize();
            String sizeType;

            if (size == 4) {
                sizeType = "DWORD";
            } else//size is 1
            {
                sizeType = "BYTE";
            }

            generatedCode = "\tmov " + sizeType + " PTR [ebp - " + tmpVar.getBpOffset() * (-1) + "]" + "," + R + "\n";
            return generatedCode;
        } else if ((definition = symbolTable.lookup(a)) != null) {//it is a variable (local or not local)
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
                    generatedCode += "\tmov esi, " + sizeType + "PTR [ebp + " + variable.getBpOffset() + "]\n";
                    generatedCode += "\tmov " + sizeType + " PTR [esi], " + R + "\n";
                } else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable

                    if (variable.isAParameter())
                        generatedCode = "\tmov " + sizeType + " PTR [ebp + " + variable.getBpOffset() + "]," + R + "\n";
                    else//it is a local variable
                        generatedCode = "\tmov " + sizeType + " PTR [ebp - " + variable.getBpOffset() * (-1) + "]," + R + "\n";
                }
                return generatedCode;

            } else//variable is not local (need access links, to get to the stack record were it IS local)
            {
                //need to implement getAR function
                generatedCode += "\tgetAR(a)\n";

                if (variable.isReference())//if it is a reference (it is a local parameter passed by reference)
                {
                    generatedCode += "\tmov esi, DWORD PTR [esi + " + variable.getBpOffset() + "]\n";
                    generatedCode += "\tmov " + sizeType + " PTR [esi], " + R + " \n";
                } else//it is not a parameter passed by reference
                {//so it is a parameter by value,or simply a local variable
                    if (variable.isAParameter())
                        generatedCode = "\tmov " + sizeType + " PTR [ebp + " + variable.getBpOffset() + "], " + R + "\n";
                    else//it is a local variable
                        generatedCode = "\tmov " + sizeType + " PTR [ebp - " + variable.getBpOffset() + "], " + R + "\n";

                }
                return generatedCode;
            }
        } else//it is a constant (number or char)
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
                assemblyAssign(quad, assemblyCode);

            else if (quadOp.equals("array"))
            {

            }
            else if (quadOp.equals("+") || quadOp.equals("-"))
                assemblyAddMinus(quad, assemblyCode);
            else if (quadOp.equals("*"))
                assemblyMult(quad, assemblyCode);
            else if (quadOp.equals("div") || quadOp.equals("mod"))
                assemblyDivMod(quad, assemblyCode);

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
                assemblyParameterLoad(quad, assemblyCode);

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

    public void assemblyAddMinus(Quadruple quad, String code) {
        String quadOp = quad.getOp();
        String x = quad.getArg1();
        String y = quad.getArg2();
        String z = quad.getResult();
        code += load("eax", x);
        code += load("edx", y);

        if (quadOp.equals("+"))
            code += "\t" + "add eax, edx\n";
        else
            code += "\t" + "sub eax, edx\n";

        code += store("eax", z);
        System.out.println("here\n\n" + code + "\n\n\n\n");
    }

    public void assemblyMult(Quadruple quad, String code) {
        String x = quad.getArg1();
        String y = quad.getArg2();
        String z = quad.getResult();

        code += load("eax", x);
        code += load("ecx", y);

        code += "\timul ecx \n";

        code += store("ecx", z);
        System.out.println("here\n\n" + code + "\n\n\n\n");

    }

    public void assemblyAssign(Quadruple quad, String code) {
        String x = quad.getArg1();
        String z = quad.getResult();

        code += load("eax", x);
        code += store("eax", z);

        System.out.println("here\n\n" + code + "\n\n\n\n");

    }

    public void assemblyDivMod(Quadruple quad, String code) {
        String quadOp = quad.getOp();
        String x = quad.getArg1();
        String y = quad.getArg2();
        String z = quad.getResult();

        code += load("eax", x);
        code += "\tcdq\n";

        code += load("ebx", y);

        code += "\tidiv ebx\n";

        if (quadOp.equals("div"))
            code += store("eax", z);
        else if (quadOp.equals("mod"))
            code += store("edx", z);

        System.out.println("here\n\n" + code + "\n\n\n\n");

    }

    public void assemblyParameterLoad(Quadruple quad, String code) {
        String x = quad.getArg1();
        String passType = quad.getArg2();

        if (passType.equals("V")) {
            code += load("eax", x);
            code += "\tpush eax\n";
        } else if (passType.equals("R") || passType.equals("RET")) {

        } else {
            System.out.println("Unknown 'par' pass type : " + passType);
            System.exit(-1);
        }

        System.out.println("here\n\n" + code + "\n\n\n\n");

    }
}
