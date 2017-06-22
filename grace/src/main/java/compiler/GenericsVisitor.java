package compiler;

import com.sun.org.apache.xpath.internal.SourceTree;
import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import compiler.SymbolTable.SymbolTable;
import compiler.Definition.*;
import compiler.Others.*;

import java.awt.*;
import java.sql.Array;
import java.util.List;
import java.lang.String;
import java.lang.Object;
import java.util.*;


public class GenericsVisitor extends DepthFirstAdapter {

    //used to keep track of what type is every node
    //recursively each node:
    // (a)gets the types of its children,and check if they are valid
    // (b)places its one type in the var (for the parent to see)
    //this field is also used to keep track of the temporaryVariable name ($num) in which the result of expressions was put in
    private Type type;

    //used to keep track of returns in functions
    //and check if they return the correct thing
    private Stack<Type> returnTypes;
    private Stack<Boolean> returnFound;

    //used to keep the bpOffset of the next local variable across all function definitions
    //the top of the stack will always contain the offset of the next local var in the 'current' function
    private Stack<Integer> functionVarsBpOffset;

    //keep information per scope (name,type,bpoffset etc) about the variables and functions (definitions,declaration)
    private SymbolTable symbolTable;

    //keep information (name,type,bpoffset) for temporary variables (all of them, not per scope)
    private int tempVarsCounter;
    private Hashtable<String, TempVar> tempVarsHashTable;

    //quadaples generated from grace code are kept in this list
    private List<Quadruple> quads;

    //FINALLY...for assembly code:
    //we use the above information (symboltable, tempvarhash, quads)
    //as help, so as to generate assembly code by transforming our quadaples
    private AssemblyGenerator assemblyGenerator;

    public GenericsVisitor(String filename)
    {
        type = null;
        returnTypes = new Stack<Type>();
        returnFound = new Stack<Boolean>();
        functionVarsBpOffset = new Stack<Integer>();
        symbolTable = new SymbolTable();
        tempVarsHashTable = new Hashtable<String, TempVar>();
        quads = new ArrayList<Quadruple>();

        assemblyGenerator = new AssemblyGenerator(symbolTable, tempVarsHashTable, quads, filename);
    }


    public void visit(Node node)
    {
        node.apply(this);
    }

    private Type getTypeEvaluation(Node node)
    {
        visit(node);
        Type typeEvaluation = this.type;
        this.type = null;
        return typeEvaluation;
    }


    @Override
    public void inStart(Start node)
    {
        tempVarsCounter = 1;
        //create the first scope (for or main func to be defined in
        symbolTable.enter();

        //check if main (the first function defined) meets the specifications
        //that is : (1)it has no args (2)returns nothing
        AFuncDef main = (AFuncDef) node.getPFuncDef();

        if (main.getPar().size() != 0)
        {
            System.out.println("Error! Program's main function is not allowed to take parameters!");
            System.exit(-1);
        }

        if (!main.getRetType().toString().trim().equals("nothing"))
        {
            System.out.println("Error! Program's main function must return 'nothing'!");
            System.exit(-1);
        }

        //define grace's standard functions
        List<Variable> params;
        List<Integer> dimensions;

        //puti
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("n", "int", dimensions, 1, true, false, 8));
        symbolTable.insertAFunction("puti", "nothing", params, true);

        //putc
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("c", "char", dimensions, 1, true, false, 8));
        symbolTable.insertAFunction("putc", "nothing", params, true);

        //puts
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s", "char", dimensions, 1, true, true, 8));
        symbolTable.insertAFunction("puts", "nothing", params, true);

        //geti
        params = new ArrayList();
        symbolTable.insertAFunction("geti", "int", params, true);

        //getc
        params = new ArrayList();
        symbolTable.insertAFunction("getc", "char", params, true);

        //gets
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("n", "int", dimensions, 1, true, false, 8));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s", "char", dimensions, 1, true, true, 12));
        symbolTable.insertAFunction("gets", "nothing", params, true);

        //abs
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("n", "int", dimensions, 1, true, false, 8));
        symbolTable.insertAFunction("abs", "int", params, true);

        //ord
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("c", "char", dimensions, 1, true, false, 8));
        symbolTable.insertAFunction("ord", "int", params, true);

        //chr
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("n", "int", dimensions, 1, true, false, 8));
        symbolTable.insertAFunction("chr", "char", params, true);

        //strlen
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s", "char", dimensions, 1, true, true, 8));
        symbolTable.insertAFunction("strlen", "int", params, true);

        //strcmp
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s1", "char", dimensions, 1, true, true, 8));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s2", "char", dimensions, 1, true, true, 12));
        symbolTable.insertAFunction("strcmp", "int", params, true);

        //strcpy
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("trg", "char", dimensions, 1, true, true, 8));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("src", "char", dimensions, 1, true, true, 12));
        symbolTable.insertAFunction("strcpy", "nothing", params, true);

        //strcat
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("trg", "char", dimensions, 1, true, true, 8));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("src", "char", dimensions, 1, true, true, 12));
        symbolTable.insertAFunction("strcat", "nothing", params, true);

    }

    @Override
    public void outStart(Start node)
    {
        System.out.println("\n------Printing Quadruples------");
        for (Quadruple quad : quads) {
            System.out.println(quad);
        }

        symbolTable.exit();
        assemblyGenerator.closeFile();
    }



    /*HANDLE FUNCTION DEFINITIONS/DECLARATIONS AND VAR DEFINITIONS*/
    /*Save them (scopes as well) at the symbol table*/
    @Override
    public void caseAFuncDef(AFuncDef node)
    {
        //Push the bp offset of the first local variable (if there is one) for this function
        functionVarsBpOffset.push(0);

        LinkedList<PPar> pars = node.getPar();

        APar tmpParameter;
        List<Variable> variableList = new ArrayList();


        List<Integer> parameterSizes = new ArrayList();
        for (PPar pPar : pars)
        {
             tmpParameter = (APar) pPar;
             String type = tmpParameter.getType().toString().trim();
             boolean isReference;

             if (tmpParameter.getRef() != null)
                 isReference = true;
             else
                 isReference = false;

             List<Integer> dimensionList = new ArrayList();

             if (tmpParameter.getLbrack() != null)
             {
                 dimensionList.add(0);
             }

            for (TNumber tNumber : tmpParameter.getNumber()) {
                dimensionList.add(Integer.parseInt(tNumber.toString().trim()));
            }

            //next we need to calculate how much space this parameter type
            //will take on the stack
            int paramSize;

            //if param is passed by reference ALWAYS keep 4 bytes in mem for the address
            if (isReference)
            {
                paramSize = 4;
            }
            else
            {
                //if it is not an array
                if (dimensionList.isEmpty())
                {
                    //size of param if there are no dimensions (not an array)
                    if ( type.equals("int") )
                        paramSize = 4;
                    else //char
                        paramSize = 1;

                }
                //its an array
                else//All arrays in grace are passed by reference! So:
                {//we will keep 4 bytes (for the address) for this param
                    paramSize = 4;
                }
            }

            //for each name of this parameter type
            for (TId tId : tmpParameter.getId())
            {
                parameterSizes.add(paramSize);//We save each parameter's size in order to adjust their bpOffsets afterwards
                //(We want the last one to be the one having offset + 16)
                variableList.add(new Variable(tId.toString(), type, dimensionList, symbolTable.getSize(), true, isReference, 0));
                //we will adjust bpOffsets afterwards, just put 0 for now
            }
        }

        //use the parameters' sizes saved above, so as to adjust bpOffsets
        //again: the last parameter of the function will be the one having +16 offset!
        int totalParams = variableList.size();
        int nextParamBpOffset = 16;

        for (int i=totalParams-1; i>=0; i--)//for all parameters starting from the last one
        {
            Variable param = variableList.get(i);//get that parameter
            int paramSize = parameterSizes.get(i);//get its size

            param.setBpOffset(nextParamBpOffset);//assign the next available offset to this parameter
            nextParamBpOffset += paramSize;//next parameter (if there is one) will be placed after this one
        }

        //define function
        symbolTable.insertAFunction(node.getId().toString(), node.getRetType().toString(), variableList, true);
        symbolTable.enter();
        //define the functions parameters inside the scope of the function
        int i;
        for (i=0; i<variableList.size(); i++)
        {
            Variable var = variableList.get(i);
            symbolTable.insertAParameter(var.getId(), var.getType(), var.getDimensions(), var.isReference(), var.getBpOffset());
        }

        //visit all local defines of that function

        ArrayList copy = new ArrayList(node.getLocalDef());
        Iterator var3 = copy.iterator();

        while(var3.hasNext()) {
            PLocalDef e = (PLocalDef)var3.next();
            e.apply(this);
        }


        //create unit quadaple
        quads.add(new Quadruple(quads.size() + 1,"unit", node.getId().getText() + (symbolTable.getSize() - 1), null, null));
        returnTypes.push(new Type(node.getRetType().toString().trim()));//push return type to be checked when we find 'return' in the block
        returnFound.push(new Boolean(false));
        //visit the function's block
        if(node.getBlock() != null) {
            node.getBlock().apply(this);
        }

        //unit ends after the functions block
        returnTypes.pop();
        Boolean returnWasFound = returnFound.pop();

        //after all local defines of the function are visited there will be no more local variables to set and get bpOffsets
        //after the function's block is visited there will be not more tempVariables to set and get bpOffsets
        //we dont need to keep track of the bpOffset for this function anymore
        functionVarsBpOffset.pop();//pop 'next' local variable offset since we won't need it

        //if this functions is supposed to return something (int/char) and it didnt.Error!
        if (!node.getRetType().toString().trim().equals("nothing") && returnWasFound.equals(false))
        {
            System.out.println("No return in function '" + node.getId().getText() +
                    "' returning '" + node.getRetType().toString().trim() + "'.");
            System.exit(-1);
        }
        quads.add(new Quadruple(quads.size() + 1,"endu", node.getId().getText() + (symbolTable.getSize() - 1), null, null));

        assemblyGenerator.generate();
        symbolTable.exit();
    }

    @Override
    public void inAFuncDecl(AFuncDecl node)
    {
        LinkedList<PPar> pars = node.getPar();

        APar tmpParameter;
        List<Variable> variableList = new ArrayList();
        List<Integer> parameterSizes = new ArrayList();

        for (PPar pPar : pars)
        {
            tmpParameter = (APar) pPar;
            String type = tmpParameter.getType().toString().trim();
            boolean isReference;

            if (tmpParameter.getRef() != null)
                isReference = true;
            else
                isReference = false;

            List<Integer> dimensionList = new ArrayList();

            if (tmpParameter.getLbrack() != null)
            {
                dimensionList.add(0);
            }

            for (TNumber tNumber : tmpParameter.getNumber()) {
                dimensionList.add(Integer.parseInt(tNumber.toString().trim()));
            }

            //next we need to calculate how much space this parameter type
            //will take on the stack
            int paramSize;

            //if param is passed by reference ALWAYS keep 4 bytes in mem for the address
            if (isReference)
            {
                paramSize = 4;
            }
            else
            {
                //if it is not an array
                if (dimensionList.isEmpty())
                {
                    //size of param if there are no dimensions (not an array)
                    if ( type.equals("int") )
                        paramSize = 4;
                    else //char
                        paramSize = 1;

                }
                //its an array
                else//All arrays in grace are passed by reference! So:
                {//we will keep 4 bytes (for the address) for this param
                    paramSize = 4;
                }
            }

            //for each name of this parameter type
            for (TId tId : tmpParameter.getId()) {
                parameterSizes.add(paramSize);
                //like in the func_def case, we save the sizes in order to adjust the correct offsets afterwards (last param: +16 offset)
                variableList.add(new Variable(tId.toString(), type, dimensionList, symbolTable.getSize(), true, isReference, 0));
            }

        }

        //use the parameters' sizes saved above, so as to adjust bpOffsets
        //again: the last parameter of the function will be the one having +16 offset!
        int totalParams = variableList.size();
        int nextParamBpOffset = 16;

        for (int i=totalParams-1; i>=0; i--)//for all parameters starting from the last one
        {
            Variable param = variableList.get(i);//get that parameter
            int paramSize = parameterSizes.get(i);//get its size

            param.setBpOffset(nextParamBpOffset);//assign the next available offset to this parameter
            nextParamBpOffset += paramSize;//next parameter (if there is one) will be placed after this one
        }

        symbolTable.insertAFunction(node.getId().toString(), node.getRetType().toString(), variableList, false);
    }

    public void inAVarDef(AVarDef node) {

        String type = node.getType().toString().trim();

        List<Integer> dimensionList = new ArrayList();

        for (TNumber tNumber : node.getNumber()) {
            dimensionList.add(Integer.parseInt(tNumber.toString().trim()));
        }

        //calculate the size of that local variable in memory
        int varSize;
        //size of var if there are no dimensions
        if ( type.equals("int") )
            varSize = 4;
        else
            varSize = 1;


        int totalElements = 1;
        //if there are dimensions, multiply varSize by the total number of single elements in the array
        for (Integer integer : dimensionList) {
            totalElements *= integer;
        }

        varSize *= totalElements;

        for (TId tId : node.getId()) {
            //set bpOffset for this local variable depending on its size
            int nextBpOffset = functionVarsBpOffset.pop() - varSize;
            functionVarsBpOffset.push(nextBpOffset);
            symbolTable.insertAVariable(tId.toString(), type, dimensionList, nextBpOffset);
        }

    }


    /*DEFINITION CHECKING! WHEN A FUNCTION CALL OCCURS ,OR A VARIABLE (the id of an lvalue) IS USED*/
    /*Check if the function is defined/declared and if its being called with the right NUMBER of parameters*/
    /*Check if the variable is defined and if the right number of dimensions is applied*/
    //AND
    /*TYPE CHECKING!THIS INCLUDES LOTS OF STUFF LIKE :*/
    /*checking if a function is called with the right TYPE of parameters*/
    /*checking if a variables dimensions used are ints!*/
    /*checking if an expression (e.g plus) is applied to the right type of constants/variables*/
    /*checking if a condition is valid*/


    /*Start with the basic productions*/
    /*By basic we mean that they are used for the rest expressions as operands*/
    /*These are: function_calls and variables aka lvalues*/
    /*we know the type of functions and variables thanks to the symbol table*/

    @Override
    public void caseAFuncCall(AFuncCall node)
    {
        //check if the function is defined and called with the right number of parameters*/
        Definition definition = symbolTable.lookup(node.getId().getText());
        Function function = null;

        if (definition != null)
        {
            if (definition instanceof Function)
            {
                function = (Function) definition;

                Integer paramsNum = function.getParameters().size();
                Integer givenParams = node.getExpr().size();

                if (!paramsNum.equals(givenParams))
                {
                    System.out.println("Error. Function '" + node.getId().getText() + "' was defined with " + paramsNum + " parameters." +
                            givenParams + " given.");
                    System.exit(-1);
                }
            }
            else
            {
                System.out.println("Error. '" + node.getId().getText() + "' was defined as a variable.It's not a function.");
                System.exit(-1);
            }
        }
        else
        {
            System.out.println("Error. Undefined function '" + node.getId().getText() + "'");
            System.exit(-1);
        }

        if(node.getId() != null)
        {
            node.getId().apply(this);
        }

        List<Variable> definedParams = function.getParameters();

        {
            //visit every argument given to this function call
            //check if each one of them is of the correct type depending on this functions definition
            int i = 0;
            List<PExpr> copy = new ArrayList<PExpr>(node.getExpr());

            for(PExpr e : copy)
            {
                Type givenParamType = getTypeEvaluation(e);
                Variable definedParam = definedParams.get(i);//get respective type of parameter from function definition
                String definedType;
                if (definedParam.isReference())
                    definedType = "ref " + definedParam.getType();
                else
                    definedType = definedParam.getType();

                if (!givenParamType.matchesParameter(definedParam))
                {
                    System.out.println("Invalid function call of '" + node.getId().getText() +
                            "'.Expecting '" + definedType + definedParam.getDimensions() + "' as parameter in position: " + (i+1) );
                    System.out.println("'" + givenParamType.makeReadable() + "' was given.");
                    System.exit(-1);
                }
                //DETAIL! If function is expecting a reference.we need to make sure that the passed parameter is a variable
                //make sure that the caller passes an lvalue
                if ( definedParam.isReference()) {
                    if (!(e instanceof ALvalueExpr)) {
                        System.out.println("Invalid function call of '" + node.getId().getText() +
                                "'.Expecting '" + definedType + "' as parameter in position: " + (i + 1));
                        System.out.println("Ref expects an lvalue,not an expression! ('" + e.toString().trim() + "')");
                        System.exit(-1);
                    }
                }

                //if function is not expecting an array
                if (definedParam.getDimensions().size() == 0)
                {
                    String par;
                    if (definedParam.isReference())
                    {
                        if (givenParamType.isArray()) //if we already have and address (due to array quadaple)
                        {
                            par = givenParamType.getTempVar();
                        }
                        else //if we have a value, we need the address {} of that variable
                        {
                            par = "{" + givenParamType.getTempVar() + "}";
                        }

                        quads.add(new Quadruple(quads.size() + 1, "par", par, "R", null));
                    }
                    else
                    {

                        if (givenParamType.isArray()) //if we have an address we need the content
                        {
                            par = "[" + givenParamType.getTempVar() + "]";
                        }
                        else //if we already have a "value"
                        {
                            par = givenParamType.getTempVar();
                        }
                        quads.add(new Quadruple(quads.size() + 1, "par", par, "V", null));
                    }
                }
                else //if function is expecting an array ALWAYS pass by reference
                {
                    String par = givenParamType.getTempVar();

                    quads.add(new Quadruple(quads.size() + 1, "par", par, "R", null));
                }

                i++;
            }

        }

        //if we are here,the function was called in a correct way
        //make a temp var for function to return result too (if it returns something)
        if (!function.getType().equals("nothing")) {
            String tempVarName= newTempVariable(function.getType());//temp variable keeping the return value,has the same type as the function..

            quads.add(new Quadruple(quads.size() + 1, "par", tempVarName, "RET", null));
            this.type = new Type(function.getType(), tempVarName);//type of function call (result) is the type of the function
        }
        else
        {
            this.type = new Type(function.getType());
        }
        //make the call
        quads.add(new Quadruple(quads.size() + 1, "call", null, null, definition.getId() + definition.getScopeNumber()));

    }

    public void caseAIdLvalue(AIdLvalue node)
    {
        //first check if the variable/lvalue is defined and called with a valid number of dimensions
        Definition definition = symbolTable.lookup(node.getId().getText());
        Variable var = null;
        Integer dimensionNum = 0;
        Integer givenDimensions = 0;
/*

        System.out.println("idLvalue");
        System.out.println(node.getId());
        System.out.println(node.getExpr());
*/
        if (definition != null)
        {
            if (definition instanceof Variable)
            {
                var = (Variable) definition;

                dimensionNum = var.getDimensions().size();
                givenDimensions = node.getExpr().size();

                if (dimensionNum < givenDimensions)
                {
                    System.out.println("Error. Var '" + node.getId().getText() + "' was defined with " + dimensionNum + " dimensions." +
                            givenDimensions + " given.");
                    System.exit(-1);
                }
            }
            else
            {
                System.out.println("Error. '" + node.getId().getText() + "' was defined as a function.It's not a variable.");
                System.exit(-1);
            }
        }
        else
        {
            System.out.println("Error. Undefined variable '" + node.getId().getText() + "'");
            System.exit(-1);
        }

        //variable is defined and called with the right number of dimensions (not more than those needed)
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        String id = node.getId().getText();
        String lvalueTempVar;//this will contain the final tmpVarname (were to access it from) for this lvalue


        if (givenDimensions == 0)
        {//nothing else to do, its just a plain var not an array
            lvalueTempVar = id;//access this lvalue by its name
        }
        else //givenDimensions > 0
        {//access this lvalue by the address of the element it refers to
            //calculate this address (first calculate the offset derived from the dimensions given)

            if (dimensionNum == 1)//array has just one dimension, no need for temp vars, just evaluate the expression given for first dimension
            {//calculating the offset is easy because the first dimension's evaluation is enough..
                List<PExpr> copy = new ArrayList<PExpr>(node.getExpr());
                PExpr e = copy.get(0);
                Type type = getTypeEvaluation(e);

                if (!type.isInt())
                {
                    System.out.println(e.toString());
                    System.out.println("Error!Expressions for array accessing must be of type 'int'!");
                    System.exit(-1);
                }

                String tmpVar2;
                if (type.isArray())
                {
                    tmpVar2 = "[" + type.getTempVar() + "]";
                }
                else
                {
                    tmpVar2 = type.getTempVar();
                }

                String tempVarName = newTempVariable("address");
                quads.add(new Quadruple(quads.size() + 1, "array", id, tmpVar2, tempVarName));

                lvalueTempVar = tempVarName;
            }
            else//if array has more than 1 dimensions, generate quads to calculate array offset (we need to do some math)
            {
                //check if all expressions for array accessing are ints and save their 'name' ($ or lvalue) in an array
                String[] expressions = new String[givenDimensions];
                int i = 0;

                List<PExpr> copy = new ArrayList<PExpr>(node.getExpr());
                for(PExpr e : copy)
                {
                    Type type = getTypeEvaluation(e);

                    if (!type.isInt())
                    {
                        System.out.println(e.toString());
                        System.out.println("Error!Expressions for array accessing must be of type 'int'!");
                        System.exit(-1);
                    }

                    String tmpVar;
                    if (type.isArray())
                    {
                        tmpVar = "[" + type.getTempVar() + "]";
                    }
                    else
                    {
                        tmpVar = type.getTempVar();
                    }

                    expressions[i++] = tmpVar;
                }

                //we need 2 temporary vars to calculate the offset
                String tmp1 = newTempVariable("int");
                String tmp2 = newTempVariable("int");

                //for all given dimensions
                List<Integer> dimensions = var.getDimensions();
                String mult1 =  expressions[0];
                String mult2;

                for (i=0; i<givenDimensions-1;i++)
                {
                    mult2 = dimensions.get(i+1).toString();

                    quads.add(new Quadruple(quads.size() + 1, "*", mult1, mult2, tmp1));
                    quads.add(new Quadruple(quads.size() + 1, "+", expressions[i+1], tmp1, tmp2));

                    mult1 = tmp2;
                }

                if (givenDimensions < dimensionNum)
                {//the rest dimensions need to be multiplied along with offset so far
                    // Since they are constants, first multiply them here and add multiply the result
                    //to produce the final offset
                    int restDimensionsMultiplied = 1;
                    for (i = givenDimensions; i < dimensionNum; i++)//if some dimensions were not given
                        restDimensionsMultiplied *= dimensions.get(i);

                    //multiply them as well in the final result
                    String rest = Integer.toString(restDimensionsMultiplied);
                    quads.add(new Quadruple(quads.size() + 1, "*", tmp2, rest, tmp2));
                }

                //tmp2 will contain the final offset
                String tempVarName = newTempVariable("address");
                quads.add(new Quadruple(quads.size() + 1, "array", id, tmp2, tempVarName));

                lvalueTempVar = tempVarName;
            }
        }

        //type of variable/id use (result) is the type of the variable (depending of how many dimensions were used*/
        //the resulting type of an lvalue depends on the number of dimensions given and the dimension themselfs
        //to explain what I am doing below consider the following example.

        //if a variable x is defined as follows:
        // var x : int[5][10];
        //then :
        // x is of type int[5][10]
        // x[2] is of type int[10]
        // x[2][0] is of type int

        //dimensions given are less then the total dimensions
        if (givenDimensions < dimensionNum)
        {
            List<Integer> dimensions = var.getDimensions();
            List<Integer> dimensionsUnused = new ArrayList();

            Integer totalDimensions = dimensions.size();
            int i;

            for (i = givenDimensions; i < totalDimensions; i++)
                dimensionsUnused.add(dimensions.get(i));

            this.type = new Type(var.getType(), dimensionsUnused, lvalueTempVar, true);
        }
        else//all dimensions given
        {
            boolean isArray;
            if (dimensionNum != 0)
                isArray = true;
            else //its just a variable (not an array)
                isArray = false;

            this.type = new Type(var.getType(), lvalueTempVar, isArray);
        }

    }

    @Override
    public void caseAStringLvalue(AStringLvalue node)
    {
        inAStringLvalue(node);
        Integer dimensionsGiven;
        Type type = null;

        if(node.getString() != null)
        {
            node.getString().apply(this);
        }
        {
            List<PExpr> copy = new ArrayList<PExpr>(node.getExpr());
            dimensionsGiven =  copy.size();

            if (dimensionsGiven > 1)
            {
                System.out.println("Error. A string only has one dimension to access!");
                System.exit(-1);
            }
            else if (dimensionsGiven == 1)
            {
                type = getTypeEvaluation(copy.get(0));
            }
            for(PExpr e : copy)
            {
                e.apply(this);
            }
        }

        if (dimensionsGiven == 0)
        {
            List<Integer> dimensions = new ArrayList();
            dimensions.add(0);
            this.type = new Type("char", dimensions, node.getString().toString());
        }
        else//one dimension given
        {
            String tempVarName = newTempVariable("address");
            quads.add(new Quadruple(quads.size() + 1, "array", node.getString().toString(), type.getTempVar(), tempVarName));
            this.type = new Type("char", tempVarName, true);
        }
    }


    /*Expressions start here*/

    @Override
    public void caseANumberExpr(ANumberExpr node)
    {
        String tmpVar = node.getNumber().getText();
        {
            List<PSign> copy = new ArrayList<PSign>(node.getSign());
            int minuses = 0;
            for(PSign e : copy)
            {
                if (e instanceof compiler.node.ANegativeSign) {
                    minuses++;
                }
                e.apply(this);
            }
            if (minuses % 2 != 0)
            {
                String tempVarName = newTempVariable("int");
                quads.add(new Quadruple(quads.size() + 1, "-", "0", tmpVar, tempVarName));
                tmpVar = tempVarName;
            }
        }
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        this.type = new Type("int", tmpVar);
    }

    @Override
    public void caseACharExpr(ACharExpr node)
    {
        this.type = new Type("char", node.getSingleChar().getText());

        if(node.getSingleChar() != null)
        {
            node.getSingleChar().apply(this);
        }
    }

    @Override
    public void caseAFcallExpr(AFcallExpr node) {
        Type type = getTypeEvaluation(node.getFuncCall());
        if (node.getSign().size() != 0) {
            if (!type.isInt())//if expression is neither an int nor a char.it cant have a sign!
            {
                System.out.println("Error!You can only put a sign in front of an 'int'!");
                System.exit(-1);
            }
            else //ok.type is int so we can have a sign.if it turns out to be a minus.do (0 - function_result)
            {
                this.type = type;//fcallExpression type is the same as the fcall's type
                {
                    List<PSign> copy = new ArrayList<PSign>(node.getSign());
                    int minuses = 0;
                    for (PSign e : copy) {
                        if (e instanceof compiler.node.ANegativeSign)
                            minuses++;
                        e.apply(this);
                    }
                    if (minuses % 2 != 0) {
                        String tempVarName = newTempVariable("int");
                        quads.add(new Quadruple(quads.size() + 1, "-", "0", type.getTempVar(), tempVarName));
                    }
                }
            }
        }
        else
        {
            this.type = type;//fcallExpression type is the same as the fcall's type
        }
    }

    @Override
    public void caseASignedExpr(ASignedExpr node) {
        Type type = getTypeEvaluation(node.getExpr());

        String tmpVar;
        if (type.isArray())
        {
            tmpVar = "[" + type.getTempVar() + "]";
        }
        else
        {
            tmpVar = type.getTempVar();
        }

        if (node.getSign().size() != 0)
        {
            if (!type.isInt())//if expression is not an int.it cant have a sign!
            {
                System.out.println("Error!You can only put a sign in front of an 'int'!");
                System.exit(-1);
            }
            else
            {
                {
                    List<PSign> copy = new ArrayList<PSign>(node.getSign());
                    int minuses = 0;
                    for (PSign e : copy) {
                        if (e instanceof compiler.node.ANegativeSign) {
                            minuses++;
                        }

                        e.apply(this);
                    }
                    if (minuses % 2 != 0)
                    {
                        String tempVarName = newTempVariable("int");
                        quads.add(new Quadruple(quads.size() + 1, "-", "0", tmpVar, tempVarName));
                        tmpVar = tempVarName;

                    }

                }
            }
        }
        this.type = new Type(type.getType(), type.getDimensions(), tmpVar);
    }


    //the right part of an assignment should be the same type as the left part
    @Override
    public void caseAAssignmentStatement(AAssignmentStatement node)
    {
        Type left = null;
        Type right = null;

        if(node.getLvalue() != null)
        {
            left = getTypeEvaluation(node.getLvalue());
        }
        else
        {
            System.out.println("Assignment left is null error!");
            System.exit(-1);
        }
        if(node.getExpr() != null)
        {
            right = getTypeEvaluation(node.getExpr());
        }
        else
        {
            System.out.println("Assignment right is null error!");
            System.exit(-1);
        }

        if (left.getDimensions() != null && left.getDimensions().size() != 0)
        {
            System.out.println("Assignment error!Left lvalue cannot be an array!");
            System.exit(-1);
        }

        if (!left.sameType(right))
        {
            System.out.println("Assignment error!Expecting '" + left.makeReadable() + "' as expression!('" + right.makeReadable() + "' given)");
            System.exit(-1);
        }

        String tmpLeft;
        if (left.isArray())
        {
            tmpLeft = "[" + left.getTempVar() + "]";
        }
        else
        {
            tmpLeft = left.getTempVar();
        }

        String tmpRight;
        if (right.isArray())
        {
            tmpRight = "[" + right.getTempVar() + "]";
        }
        else
        {
            tmpRight = right.getTempVar();
        }

        quads.add(new Quadruple(quads.size() + 1, ":=", tmpRight,null,tmpLeft));
    }

    //a return Expr should return the most resent Return type (saved from the most resent function definition*/
    @Override
    public void caseAReturnStatement(AReturnStatement node)
    {
        Type retType = null;
        Type latestReturnType = returnTypes.lastElement();

        if(node.getExpr() != null)
        {
            retType = getTypeEvaluation(node.getExpr());
            if (!retType.sameType(latestReturnType))
            {
                System.out.println("Invalid return type!Expecting '" + latestReturnType.makeReadable() + "'.(" + retType.makeReadable() + " given)");
                System.exit(-1);
            }

            String typeStr;
            if (retType.isArray())
                typeStr = "[" + retType.getTempVar() + "]";
            else
                typeStr = retType.getTempVar();

            quads.add(new Quadruple(quads.size() + 1, ":=", typeStr, null, "$$"));
            quads.add(new Quadruple(quads.size() + 1, "ret", null, null, null));
        }
        else //if its a plain 'return' (returns nothing), make sure that the function returns 'nothing'
        {
            if (!latestReturnType.getType().equals("nothing"))
            {
                System.out.println("Invalid return type!Expecting '" + latestReturnType.makeReadable() + "'.You returned 'nothing'!");
                System.exit(-1);
            }
            quads.add(new Quadruple(quads.size() + 1, "ret", null, null, null));
        }

        //everything is ok.note that we found a (valid) return in the most recent function
        returnFound.pop();
        returnFound.push(new Boolean(true));
    }

    /*Make sure that 'math' things (e.g addition) are being done between the right type of 'nodes' (ints)*/
    @Override
    public void caseAAddExpr(AAddExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        exprQuad("+", left, right);

    }

    @Override
    public void caseASubExpr(ASubExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        exprQuad("-", left, right);
    }

    @Override
    public void caseAMultExpr(AMultExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        exprQuad("*", left, right);
    }

    @Override
    public void caseADivExpr(ADivExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        exprQuad("div", left, right);
    }

    @Override
    public void caseAModExpr(AModExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        exprQuad("mod", left, right);
    }

    /*Make sure that comparisions (e.g >=) are being done between the right type of 'nodes' (ints)*/
    @Override
    public void caseAEqCond(AEqCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        relopQuad("=", left, right);
    }

    @Override
    public void caseANeqCond(ANeqCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        relopQuad("#", left, right);
    }

    @Override
    public void caseALeqCond(ALeqCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        relopQuad("<=", left, right);

    }

    @Override
    public void caseAGeqCond(AGeqCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        relopQuad(">=", left, right);

    }


    @Override
    public void caseALtCond(ALtCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        relopQuad("<", left, right);
    }

    @Override
    public void caseAGtCond(AGtCond node) {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());
        relopQuad(">", left, right);
    }

    @Override
    public void caseAOrCond(AOrCond node) {
        Type left = null;
        Type right = null;

        inAOrCond(node);
        if (node.getLeft() != null) {
            left = getTypeEvaluation(node.getLeft());
        }

        backpatch(left.getFalseList(), nextQuad());
        if (node.getRight() != null) {
            right = getTypeEvaluation(node.getRight());
        }
        Type thisType = new Type(left.getType(), left.getDimensions(), left.getTempVar());

        left.getTrueList().addAll(right.getTrueList());
        thisType.setTrueList(left.getTrueList());
        thisType.setFalseList(right.getFalseList());


        this.type = thisType;
        outAOrCond(node);
    }

    @Override
    public void caseAAndCond(AAndCond node) {
        Type left = null;
        Type right = null;

        inAAndCond(node);
        if (node.getLeft() != null) {
            left = getTypeEvaluation(node.getLeft());
        }

        backpatch(left.getTrueList(), nextQuad());
        if (node.getRight() != null) {
            right = getTypeEvaluation(node.getRight());
        }
        Type thisType = new Type(left.getType(), left.getDimensions(), left.getTempVar());

        left.getFalseList().addAll(right.getFalseList());
        thisType.setTrueList(right.getTrueList());
        thisType.setFalseList(left.getFalseList());


        this.type = thisType;
        outAAndCond(node);
    }

    @Override
    public void caseAWhileStatement(AWhileStatement node) {

//        System.out.println("---AWhileStatement Start---");

        Type cond = null;
        Type stmt = null;

        String Q = nextQuad();


        inAWhileStatement(node);
        if (node.getCond() != null) {
            cond = getTypeEvaluation(node.getCond());
            backpatch(cond.getTrueList(), nextQuad());
        }

        if (node.getStatement() != null) {
            stmt = getTypeEvaluation(node.getStatement());
        }
        outAWhileStatement(node);

        quads.add(new Quadruple(quads.size() + 1, "jump", null, null, Q));    //If there is an else block u must jump to the and of it (if condition was true)

        backpatch(cond.getFalseList(), nextQuad());
        if (stmt != null) {
            backpatch(stmt.getNextList(), Q);
        }


        Type thisType = new Type(null, null, null);
        thisType.setNextList(cond.getNextList());

//        System.out.println("---AWhileStatement END");
    }


    @Override
    public void caseAIfElseStatement(AIfElseStatement node) {
        Type cond = null;
        Type elseStmt = null;
        List<Quadruple> L1 = new ArrayList<Quadruple>();
        List<Quadruple> L2 = new ArrayList<Quadruple>();

//        System.out.println("---caseAIfElseStatement---");

        inAIfElseStatement(node);
        if (node.getCond() != null) {
            cond = getTypeEvaluation(node.getCond());
        }

        backpatch(cond.getTrueList(), nextQuad());      //Backpath if the condition is true
        L1 = cond.getFalseList();

        if (node.getThen() != null) {
            node.getThen().apply(this);
        }


        if (node.getElse() != null) {
            quads.add(new Quadruple(quads.size() + 1, "jump", null, null, "*"));    //If there is an else block u must jump to the and of it (if condition was true)
            L1 = new ArrayList<Quadruple>();
            L1.add(quads.get(quads.size() - 1));
            backpatch(cond.getFalseList(), nextQuad());     //If it is false go jump after the "then" block

            elseStmt = getTypeEvaluation(node.getElse());

            if (elseStmt != null)
                L2 = elseStmt.getNextList();
        }
        outAIfElseStatement(node);

        Type thisType = new Type(cond.getType(), cond.getDimensions(), cond.getTempVar());
        List<Quadruple> tmp = new ArrayList<Quadruple>();
        tmp.addAll(L1);
        tmp.addAll(L2);
        thisType.setNextList(tmp);
        this.type = thisType;
        backpatch(this.type.getNextList(), nextQuad());

//        System.out.println("---caseAIfElseStatement END");

    }

    public void caseANotCond(ANotCond node) {
        Type cond = null;
        inANotCond(node);
        if (node.getCond() != null) {
            cond = getTypeEvaluation(node.getCond());
        }
        outANotCond(node);

        List<Quadruple> tmp = cond.getTrueList();
        cond.setTrueList(cond.getFalseList());
        cond.setFalseList(tmp);
        this.type = cond;
    }




    //Following are functions used across cases (things that are repeated in many cases)

    public void exprQuad(String expr, Type left, Type right)//this is used at each expr type (add,sub,mult etc)
    {
        if (left == null)
        {
            System.out.println("'" + expr + "' expression error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("'" + expr + "' expression error. Right part is null!");
            System.exit(-1);
        }

        if (! (left.isInt() && right.isInt()) )
        {
            System.out.println("'" + expr + "' expression error.Left and right need to be ints!");
            System.exit(-1);
        }

        String tmpLeft;
        if (left.isArray())
        {
            tmpLeft = "[" + left.getTempVar() + "]";
        }
        else
        {
            tmpLeft = left.getTempVar();
        }

        String tmpRight;
        if (right.isArray())
        {
            tmpRight = "[" + right.getTempVar() + "]";
        }
        else
        {
            tmpRight = right.getTempVar();
        }

        String tempVarName = newTempVariable("int");
        this.type = new Type(left.getType(), left.getDimensions(), tempVarName);
        quads.add(new Quadruple(quads.size() + 1, expr, tmpLeft, tmpRight, tempVarName));
    }

    String nextQuad() {
        return Integer.toString(quads.size() + 1);
    }

    public void backpatch(List<Quadruple> list, String label) {
        //System.out.println("Bainw gia backpatch me lista: ");
        //System.out.println(list);
        //System.out.println("timi label = " + label);
        for (Quadruple quadruple : list) {
            quadruple.setResult(label);
        }
    }

    public void relopQuad(String relop, Type left, Type right) {
        //error checking
        if (left == null)
        {
            System.out.println("'" + relop + "' condition Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("'" + relop + "' condition Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (!(left.isInt() && right.isInt()))
            {
                System.out.println("'" + relop + "' condition error!You can only compare integers!");
                System.exit(-1);
            }
        }

        String leftTmp;
        String rightTmp;

        if (left.isArray())
            leftTmp = "[" + left.getTempVar() + "]";
        else
            leftTmp = left.getTempVar();

        if (right.isArray())
            rightTmp = "[" + right.getTempVar() + "]";
        else
            rightTmp = right.getTempVar();

        Type tmpType = new Type(left.getType(), left.getDimensions());
        quads.add(new Quadruple(quads.size() + 1, relop, leftTmp, rightTmp, "*"));
        tmpType.getTrueList().add(quads.get(quads.size() - 1));
        quads.add(new Quadruple(quads.size() + 1, "jump", null, null, "*"));
        tmpType.getFalseList().add(quads.get(quads.size() - 1));
        this.type = tmpType;
    }


    //'creates' a new temp variable and saves it at our hashtable
    String newTempVariable(String type)
    {
        String name = "$" + tempVarsCounter;
        int size = 0;

        if (type.equals("int") || type.equals("address"))
        {
            size = 4;
        }
        else if (type.equals("char"))
        {
            size = 1;
        }
        else//error
        {
            System.out.println("Invalid type given for a tempVar! : " + type );
            System.exit(-1);
        }

        //update nextBpOffset according to this tempVar's size
        int nextBpOffset = functionVarsBpOffset.pop() - size;
        functionVarsBpOffset.push(nextBpOffset);

        //create and add tempVariable to the hashtable
        TempVar tempVariable = new TempVar(name, type, size, nextBpOffset);
        tempVarsHashTable.put(name, tempVariable);

        tempVarsCounter++;

        return name;
    }


}