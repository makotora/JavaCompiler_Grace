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
    private Hashtable<String, String> stringLabels;
    private List<Quadruple> quads;
    private int nextQuadToTranform;//keep track of how many quads we have transformed so far,to continue from there
    private BufferedWriter assemblyWriter;
    private String recentCode;
    private int np;
    private String current;

    public AssemblyGenerator(SymbolTable symbolTable, Hashtable<String, TempVar> tempVarHashtable, Hashtable<String, String> stringLabels, List<Quadruple> quads, String filename) {
        this.symbolTable = symbolTable;
        this.tempVarHashtable = tempVarHashtable;
        this.stringLabels = stringLabels;
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

        //write assembly file header
        try
        {
            assemblyWriter.write(".intel_syntax noprefix\n.text\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        writeToFile(".global main");
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

    private String endof(String unitName)
    {
        return unitName + "_end";
    }

    //Help routines used to generate assembly code
    private void updateAL(int nx)
    {
        if (np < nx)
            writeToFile("push ebp");
        else if (np == nx)
            writeToFile("push DWORD PTR [ebp + 8]");
        else
        {
            writeToFile("mov esi, DWORD PTR [ebp + 8]");

            int nDiff = np - nx;
            for (int i=0; i<nDiff; i++)
                writeToFile("mov esi, DWORD PTR [esi + 8]");

            writeToFile("push DWORD PTR [esi + 8]");
        }
    }

    private void getAR(int nx)
    {
        writeToFile("mov esi, DWORD PTR [ebp + 8]");

        int nDiff = np - nx;
        for (int i=0; i<nDiff-1; i++)
            writeToFile("mov esi, DWORD PTR [esi + 8]");
    }

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
                    writeToFile("mov esi, DWORD PTR [ebp + " + variable.getBpOffset() + "]");
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
                getAR(variable.getScopeNumber());

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
                if (stringLabels.containsKey(a))
                {
                    System.out.println("ERROR! LOADS DOESNT KNOW WHAT TO DO WITH STRING LITERALS!");
                    System.exit(-1);
                }

                if (isParsable(a)) {
                    int x = Integer.parseInt(a);
                    writeToFile("mov " + R + ", " + x);
                }
                else
                {
                    if (a.equals("'\\0'"))
                    {
                        writeToFile("mov " + R + ", " + "0");

                    }
                    else
                        writeToFile("mov " + R + ", " + a);
                }
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

            System.out.println("loolo: " + variable);
            if (variable.getType().equals("int") || (variable.getDimensions() != null && variable.getDimensions().size() != 0)) {
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
                getAR(variable.getScopeNumber());

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
        }
        else {
            if (a.startsWith("[")) {
                String var = a.substring(1, a.length() - 1);
                TempVar tmpVar = tempVarHashtable.get(var);

                load(R, var);

            }
            else if (stringLabels.containsKey(a))
            {
                String stringLabel = stringLabels.get(a);
                writeToFile("mov " + R + ", OFFSET FLAT:" + stringLabel);
            }
            else
            {
                System.out.println(stringLabels);
                System.out.println("Error! Dont know how to loadAddr: " + a);
                System.exit(-1);
            }
        }
    }

    private void store(String R, String a)
    {
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

            writeToFile("mov " + sizeType + " PTR [ebp - " + tmpVar.getBpOffset() * (-1) + "]" + ", " + R);
        }
        else if ((definition = symbolTable.lookup(a)) != null)
        {//it is a variable (local or not local)
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
                        writeToFile("mov " + sizeType + " PTR [ebp + " + variable.getBpOffset() + "], " + R);
                    else//it is a local variable
                        writeToFile("mov " + sizeType + " PTR [ebp - " + variable.getBpOffset() * (-1) + "], " + R);
                }

            }
            else//variable is not local (need access links, to get to the stack record were it IS local)
            {
                getAR(variable.getScopeNumber());

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
        }
        else//it is a constant (number or char)
        {
            if (a.startsWith("[")) {
                String var = a.substring(1, a.length() - 1);
                TempVar tmpVar = tempVarHashtable.get(var);
                System.out.println("hahahahahhahaha: " + tmpVar);
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
            else if (a.equals("$$"))//this needs to be stored in the result's address (bp+12)
            {
                writeToFile("mov esi, DWORD PTR [ebp + 12]");

                //get type of current unit (what it returns)
                String fName;
                //the name of the function in the unit quad contains "_scopeNumber" added at the end
                //so that units with same names (but in different scopes) have different labels!
                int indexOfLast = current.lastIndexOf("_");//see where that part starts to 'cut' it out
                fName = current.substring(0, indexOfLast);

                Function f = (Function) symbolTable.lookup(fName);
                String sizeType;

                if (f.getType().equals("int"))
                    sizeType = "DWORD";
                else //it returns char
                    sizeType = "BYTE";

                writeToFile("mov " + sizeType + " PTR [esi], " + R);
            }
            else
            {
                System.out.println("Store unknown case error!!: " + a);
                System.exit(-1);
            }
        }
    }

    //generates assembly code from the most recent quads
    //we start from nextQuadToTransform at every generate call
    public void generate(int localVarSize, int np)
    {
        this.np = np;//save the function's scope number so that getAR, and updateAL can see it
        int totalQuads = quads.size();

        //for all recently added quads
        for (int i=nextQuadToTranform; i<totalQuads; i++)
        {
            Quadruple quad = quads.get(i);
            System.out.println(quad);//print the quad
            //we will also print the assembly code generated for this quad
            String label = "L" + quad.getNum() + ":\n";
            //this block of assembly will have 'Lquadnum' as a label
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
                assemblyCall(quad);
            }
            else if (quadOp.equals("ret"))
            {
                writeToFile("jmp " + endof(current));
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
        this.current = x;
        String unit_label;

        if (current.equals("main_1"))//main needs to have the same name in the assembly label
            unit_label = "main:";
        else
            unit_label = x + ":";

        try
        {
            recentCode += unit_label + "\n";
            assemblyWriter.write(unit_label + "\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        writeToFile("push ebp");
        writeToFile("mov ebp, esp");
        writeToFile("sub esp, " + localVarSize * (-1));
    }


    public void assemblyEndUnit(Quadruple quad) {
        String x = quad.getArg1();

        try
        {
            recentCode += endof(x) + ":\n";
            assemblyWriter.write(endof(x) + ":\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        writeToFile("mov esp, ebp");
        writeToFile("pop ebp");
        writeToFile("ret");
    }

    public void assemblyCall(Quadruple quad) {
        String x = quad.getResult();
        String fName;//the name of the function/unit in the quads is not the same as the name at the symbolTable
        //the name of the function in the unit quad contains "_scopeNumber" added at the end
        //so that units with same names (but in different scopes) have different labels!
        int indexOfLast = x.lastIndexOf("_");//see where that part starts to 'cut' it out
        fName = x.substring(0, indexOfLast);

        Function f = (Function) symbolTable.lookup(fName);

        if (f.getType().equals("nothing"))
            writeToFile("sub esp, 4");

        updateAL(f.getScopeNumber());

        writeToFile("call " + x);

        //we push for every parameter, regardless of its type
        //that means that we 'allocate' 4 bytes for each parameter
        int paramsSize = f.getParameters().size()*4;//size is: 'number_of_params'*4
        writeToFile("add esp, " + (paramsSize + 8));
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

        //x is either an idLvalue (var) or a string literal

        Variable var = (Variable) symbolTable.lookup(x);//if this returns null it means that x is a string

        load("eax", y);
        if (var != null)//if var exists, see its type
        {
            String arrayType = var.getType();
            if (arrayType.equals("int")) {
                writeToFile("mov ecx, 4");
                writeToFile("imul ecx");
            }
        }
        //if its a string or its char, no point multiplying by 1..
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
        //write library functions
        assemblyPrintFunctions();

        //write all string literals in the .data section
        try
        {
            assemblyWriter.write("\n.data\n");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        int stringCounter = stringLabels.size();


        Set<String> strings = stringLabels.keySet();
        Iterator<String> itr = strings.iterator();

        while (itr.hasNext())
        {
            String string = itr.next();
            String label = stringLabels.get(string);

            writeToFile(label + ": .ASCIZ " + string);
        };

        writeToFile("printInt: .ASCIZ \"%d\"" );
        writeToFile("printChar: .ASCIZ \"%c\"" );
        writeToFile("printStr: .ASCIZ \"%s\"" );

        try {
            assemblyWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void assemblyPrintFunctions()
    {
        writeToFile("puts_1:");
        writeToFile("push ebp");
        writeToFile("mov ebp, esp");

        writeToFile("mov eax, DWORD PTR [ebp + 16]");
        writeToFile("push eax");

        writeToFile("mov eax, OFFSET FLAT:printStr");
        writeToFile("push eax");

        writeToFile("call printf");
        writeToFile("add esp, 8");

        writeToFile("mov esp, ebp");
        writeToFile("pop ebp");
        writeToFile("ret");
    }
}
