package compiler;

import com.sun.org.apache.xpath.internal.SourceTree;
import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import compiler.SymbolTable.SymbolTable;
import compiler.Definition.*;
import compiler.Others.*;

import java.awt.*;
import java.util.List;
import java.lang.String;
import java.lang.Object;
import java.util.*;


public class GenericsVisitor extends DepthFirstAdapter {
    private int indent = 0;
    private String indentation = "  ";
    private Type type = null;
    private int tmpVars;
    private Stack<Type> returnTypes = new Stack();
    private SymbolTable symbolTable = new SymbolTable();
    private List<Quadruple> quads = new ArrayList<Quadruple>();

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
        tmpVars = 1;
        //create the first scope (for or main func to be defined in
        symbolTable.enter();

        //define grace's standard functions
        List<Variable> params;
        List<Integer> dimensions;

        //puti
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("n", "int", dimensions, true, false));
        symbolTable.insertAFunction("puti", "nothing", params, true);

        //putc
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("c", "char", dimensions, true, false));
        symbolTable.insertAFunction("putc", "nothing", params, true);

        //puts
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s", "char", dimensions, true, true));
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
        params.add(new Variable("n", "int", dimensions, true, false));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s", "char", dimensions, true, true));
        symbolTable.insertAFunction("gets", "nothing", params, true);

        //abs
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("n", "int", dimensions, true, false));
        symbolTable.insertAFunction("abs", "int", params, true);

        //ord
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("c", "char", dimensions, true, false));
        symbolTable.insertAFunction("ord", "int", params, true);

        //chr
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("n", "int", dimensions, true, false));
        symbolTable.insertAFunction("chr", "char", params, true);

        //strlen
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s", "char", dimensions, true, true));
        symbolTable.insertAFunction("strlen", "int", params, true);

        //strcmp
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s1", "char", dimensions, true, true));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s2", "char", dimensions, true, true));
        symbolTable.insertAFunction("strcmp", "int", params, true);

        //strcpy
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("trg", "char", dimensions, true, true));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("src", "char", dimensions, true, true));
        symbolTable.insertAFunction("strcpy", "nothing", params, true);

        //strcat
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("trg", "char", dimensions, true, true));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("src", "char", dimensions, true, true));
        symbolTable.insertAFunction("strcat", "nothing", params, true);


    }

    @Override
    public void outStart(Start node)
    {
            symbolTable.exit();
    }



    /*HANDLE FUNCTION DEFINITIONS/DECLARATIONS AND VAR DEFINITIONS*/
    /*Save them (scopes as well) at the symbol table*/
    @Override
    public void inAFuncDef(AFuncDef node)
    {
        LinkedList<PPar> pars = node.getPar();

        APar tmpParameter;
        List<Variable> variableList = new ArrayList();


        for (PPar pPar : pars) {
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

            for (TId tId : tmpParameter.getId()) {
                variableList.add(new Variable(tId.toString(), type, dimensionList, true, isReference));
             }

        }

        returnTypes.push(new Type(node.getRetType().toString().trim()));
        //define function
        symbolTable.insertAFunction(node.getId().toString(), node.getRetType().toString(), variableList, true);
        symbolTable.enter();
        //define the functions parameters inside the scope of the function
        int i;
        for (i=0; i<variableList.size(); i++)
        {
            Variable var = variableList.get(i);
            symbolTable.insertAParameter(var.getId(), var.getType(), var.getDimensions(), var.isReference());
        }
    }

    @Override
    public void outAFuncDef(AFuncDef node)
    {
        returnTypes.pop();
        symbolTable.exit();
    }

    @Override
    public void inAFuncDecl(AFuncDecl node)
    {
        LinkedList<PPar> pars = node.getPar();

        APar tmpParameter;
        List<Variable> variableList = new ArrayList();

        for (PPar pPar : pars) {
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

            for (TId tId : tmpParameter.getId()) {
                variableList.add(new Variable(tId.toString(), type, dimensionList, true, isReference));
            }

        }

        symbolTable.insertAFunction(node.getId().toString(), node.getRetType().toString(), variableList, false);
    }

    public void inAVarDef(AVarDef node) {

        String type = node.getType().toString().trim();

        List<Integer> dimensionList = new ArrayList();

        for (TNumber tNumber : node.getNumber()) {
            dimensionList.add(Integer.parseInt(tNumber.toString().trim()));
        }

        for (TId tId : node.getId()) {
            symbolTable.insertAVariable(tId.toString(), type, dimensionList);
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
    /*These are: (a)constant numbers (b)constant chars (c)functions (d)variables*/
    /*we know the type of functions and variables thanks to the symbol table*/

    @Override
    public void caseASignedExpr(ASignedExpr node) {
        System.out.println("edwwwwwwwwwww\n\n");
        inASignedExpr(node);
        {
            List<PSign> copy = new ArrayList<PSign>(node.getSign());
            int minuses = 0;
            for (PSign e : copy) {
                if (e instanceof compiler.node.ANegativeSign)
                    minuses++;

                e.apply(this);
            }
            if (minuses % 2 != 0) {
                quads.add(new Quadruple("load", "0", null, "$" + tmpVars));
                String zero = "$" + tmpVars;
                String num = "$" + (tmpVars - 1);
                tmpVars++;

                quads.add(new Quadruple("-", zero, num, "$" + tmpVars));
                tmpVars++;
            }

        }
        if (node.getExpr() != null) {
            node.getExpr().apply(this);
        }
        outASignedExpr(node);
    }

    @Override
    public void caseANumberExpr(ANumberExpr node)
    {
        this.type = new Type("int", "$" + tmpVars);
        quads.add(new Quadruple("load", node.getNumber().toString(), null, "$" + tmpVars));
        tmpVars++;
//        System.out.println("made a new tmpVar = $"+tmpVars);
        {
            List<PSign> copy = new ArrayList<PSign>(node.getSign());
            int minuses = 0;
            for(PSign e : copy)
            {
                if (e instanceof compiler.node.ANegativeSign)
                    minuses++;
                e.apply(this);
            }
            if (minuses % 2 != 0) {
                quads.add(new Quadruple("load", "0", null, "$" + tmpVars));
                String zero = "$" + tmpVars;
                String num = "$" + (tmpVars - 1);
                tmpVars++;

                quads.add(new Quadruple("-", zero, num, "$" + tmpVars));
                tmpVars++;
            }
        }
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
    }

    @Override
    public void caseACharExpr(ACharExpr node)
    {
        this.type = new Type("char", "$" + tmpVars);
        quads.add(new Quadruple("load", node.toString(), null, "$" + tmpVars));
        tmpVars++;

        {
            List<PSign> copy = new ArrayList<PSign>(node.getSign());
            for(PSign e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getSingleChar() != null)
        {
            node.getSingleChar().apply(this);
        }
    }

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

                if (!givenParamType.matchesParameter(definedParam))
                {
                    System.out.println("Invalid function call of '" + node.getId().getText() +
                            "'.Expecting '" + definedParam.toString() + "' as parameter in position: " + (i+1) );
                    System.out.println(givenParamType + " was given.");
                    System.exit(-1);
                }

                i++;
            }

        }

        //if we are here,the function was called in a correct way
        this.type = new Type(function.getType());//type of function call (result) is the type of the function
    }

    public void caseAIdLvalue(AIdLvalue node)
    {
        //first check if the variable/lvalue is defined and called with a valid number of dimensions
        Definition definition = symbolTable.lookup(node.getId().getText());
        Variable var = null;
        Integer dimensionNum = 0;
        Integer givenDimensions = 0;

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
        //check for each expression given in dimensions if its an int!
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PExpr> copy = new ArrayList<PExpr>(node.getExpr());
            for(PExpr e : copy)
            {
                Type type = getTypeEvaluation(e);

                if (!type.isInt())
                {
                    System.out.println(e.toString());
                    System.out.println("Error!Expressions for array accessing must be of type 'int'!");
                }
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

            this.type = new Type(var.getType(), dimensionsUnused);
        }
        else//all dimensions given
        {
            this.type = new Type(var.getType());
        }

    }


    @Override
    public void caseAStringLvalue(AStringLvalue node)
    {
        inAStringLvalue(node);
        Integer dimensionsGiven;
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
            this.type = new Type("char", dimensions);
        }
        else//one dimension given
        {
            this.type = new Type("char");
        }
    }


    //the right part of an assignment should be the same type as the left part
    @Override
    public void caseAAssignmentStatement(AAssignmentStatement node)
    {

        System.out.println("tha kanw assign\n");
        System.out.println(node.getExpr().getClass());
        System.out.println(node.getLvalue());



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

        if (!left.sameType(right))
        {
            System.out.println("Assignment error!Expecting '" + left.toString() + "' as expression!('" + right + "' given)");
            System.exit(-1);
        }
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
                System.out.println("Invalid return type!Expecting '" + latestReturnType + "'.(" + retType + " given)");
                System.exit(-1);
            }
        }
        else //if its a plain 'return' (returns nothing), make sure that the function returns 'nothing'
        {
            if (!latestReturnType.getType().equals("nothing"))
            {
                System.out.println("Invalid return type!Expecting '" + latestReturnType + "'.You returned nothing!");
                System.exit(-1);
            }
        }
    }

    /*Make sure that 'math' things (e.g addition) are being done between the right type of 'nodes' (ints)*/
    @Override
    public void caseAAddExpr(AAddExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

/*
        System.out.println("Add expression");
        System.out.println(node);
        System.out.println("Left: " + node.getLeft());
        System.out.println("Right: " + node.getRight());
        System.out.println("tmpVars = " + tmpVars);
*/
        if (left == null)
        {
            System.out.println("Add expression Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Add expression Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt()) {
                this.type = new Type(left.getType(), left.getDimensions(), "$" + tmpVars);
            }
            else
            {
                System.out.println("Error!You can only add integers!");
                System.exit(-1);
            }
            quads.add(new Quadruple("+", left.getTempVar(), right.getTempVar(), "$" + tmpVars));
            tmpVars++;
        }
    }

    @Override
    public void caseASubExpr(ASubExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Subtract expression Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Subtract expression Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt()) {
                this.type = new Type(left.getType(), left.getDimensions(), "$" + tmpVars);
            }
            else
            {
                System.out.println("Error!You can only subtract integers!");
                System.exit(-1);
            }
            quads.add(new Quadruple("-", left.getTempVar(), right.getTempVar(), "$" + tmpVars));
            tmpVars++;
        }
    }

    @Override
    public void caseAMultExpr(AMultExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

/*
        System.out.println("Mult expression");
        System.out.println(node);
        System.out.println("Left: " + node.getLeft());
        System.out.println("Right: " + node.getRight());
        System.out.println("tmpVars = " + tmpVars);
*/

        if (left == null)
        {
            System.out.println("Multiplication expression Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Multiplication expression Error. Right part is null!");
            System.exit(-1);
        }
        else
        {

            if (left.isInt() && right.isInt()) {
                this.type = new Type(left.getType(), left.getDimensions(), "$" + tmpVars);
            }
            else
            {
                System.out.println("Error!You can only multiply integers!");
                System.exit(-1);
            }
            quads.add(new Quadruple("*", left.getTempVar(), right.getTempVar(), "$" + tmpVars));
            tmpVars++;
        }
    }

    @Override
    public void caseADivExpr(ADivExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Division expression Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Division expression Error. Right part is null!");
            System.exit(-1);
        }
        else
        {

            if (left.isInt() && right.isInt()) {
                this.type = new Type(left.getType(), left.getDimensions(), "$" + tmpVars);
            }
            else
            {
                System.out.println("Error!You can only divide integers!");
                System.exit(-1);
            }

            quads.add(new Quadruple("div", left.getTempVar(), right.getTempVar(), "$" + tmpVars));
            tmpVars++;
        }
    }

    @Override
    public void caseAModExpr(AModExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Mod expression Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Mod expression Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = new Type(left.getType(), left.getDimensions(), "$" + tmpVars);
            else
            {
                System.out.println("Error!You can only mod integers!");
                System.exit(-1);
            }
            quads.add(new Quadruple("div", left.getTempVar(), right.getTempVar(), "$" + tmpVars));
            tmpVars++;

        }
    }

    /*Make sure that comparisions (e.g >=) are being done between the right type of 'nodes' (ints)*/
    @Override
    public void caseAEqCond(AEqCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Equal condition Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Equal condition Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Equal condition error!You can only compare integers!");
                System.exit(-1);
            }
        }
    }

    @Override
    public void caseANeqCond(ANeqCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Not equal condition Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Not equal condition Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Not equal condition error!You can only compare integers!");
                System.exit(-1);
            }
        }
    }

    @Override
    public void caseALeqCond(ALeqCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Less equal condition Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Less equal condition Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Less equal condition error!You can only compare integers!");
                System.exit(-1);
            }
        }
    }

    @Override
    public void caseAGeqCond(AGeqCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Greater equal condition Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Greater equal condition Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Greater equal condition error!You can only compare integers!");
                System.exit(-1);
            }
        }
    }


    @Override
    public void caseALtCond(ALtCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Less than condition Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Less than condition Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Less than condition error!You can only compare integers!");
                System.exit(-1);
            }
        }
    }

    @Override
    public void caseAGtCond(AGtCond node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Greater than condition Error. Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Greater than condition Error. Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Greater than condition error!You can only compare integers!");
                System.exit(-1);
            }
        }
    }

}