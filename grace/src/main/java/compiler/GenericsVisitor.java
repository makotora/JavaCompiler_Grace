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
    private Stack<Type> returnTypes = new Stack<Type>();
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
        params.add(new Variable("n", "int", dimensions, 1, true, false));
        symbolTable.insertAFunction("puti", "nothing", params, true);

        //putc
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("c", "char", dimensions, 1, true, false));
        symbolTable.insertAFunction("putc", "nothing", params, true);

        //puts
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s", "char", dimensions, 1, true, true));
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
        params.add(new Variable("n", "int", dimensions, 1, true, false));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s", "char", dimensions, 1, true, true));
        symbolTable.insertAFunction("gets", "nothing", params, true);

        //abs
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("n", "int", dimensions, 1, true, false));
        symbolTable.insertAFunction("abs", "int", params, true);

        //ord
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("c", "char", dimensions, 1, true, false));
        symbolTable.insertAFunction("ord", "int", params, true);

        //chr
        params = new ArrayList();

        dimensions = new ArrayList();
        params.add(new Variable("n", "int", dimensions, 1, true, false));
        symbolTable.insertAFunction("chr", "char", params, true);

        //strlen
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s", "char", dimensions, 1, true, true));
        symbolTable.insertAFunction("strlen", "int", params, true);

        //strcmp
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s1", "char", dimensions, 1, true, true));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("s2", "char", dimensions, 1, true, true));
        symbolTable.insertAFunction("strcmp", "int", params, true);

        //strcpy
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("trg", "char", dimensions, 1, true, true));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("src", "char", dimensions, 1, true, true));
        symbolTable.insertAFunction("strcpy", "nothing", params, true);

        //strcat
        params = new ArrayList();

        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("trg", "char", dimensions, 1, true, true));
        dimensions = new ArrayList();
        dimensions.add(0);
        params.add(new Variable("src", "char", dimensions, 1, true, true));
        symbolTable.insertAFunction("strcat", "nothing", params, true);

    }

    @Override
    public void outStart(Start node)
    {
        System.out.println("\n------Compiling End------");
        for (Quadruple quad : quads) {
            System.out.println(quad);
        }
            symbolTable.exit();
    }



    /*HANDLE FUNCTION DEFINITIONS/DECLARATIONS AND VAR DEFINITIONS*/
    /*Save them (scopes as well) at the symbol table*/
    @Override
    public void caseAFuncDef(AFuncDef node)
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
                variableList.add(new Variable(tId.toString(), type, dimensionList, symbolTable.getSize(), true, isReference));
             }

        }

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
        //visit the function's block
        if(node.getBlock() != null) {
            node.getBlock().apply(this);
        }

        //unit ends after the functions block
        returnTypes.pop();
        quads.add(new Quadruple(quads.size() + 1,"endu", node.getId().getText() + (symbolTable.getSize() - 1), null, null));
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
                variableList.add(new Variable(tId.toString(), type, dimensionList, symbolTable.getSize(), true, isReference));
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
                            "'.Expecting '" + definedType + "' as parameter in position: " + (i+1) );
                    System.out.println(givenParamType + " was given.");
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
            quads.add(new Quadruple(quads.size() + 1, "par", "$" + tmpVars, "RET", null));
            this.type = new Type(function.getType(), "$" + (tmpVars));//type of function call (result) is the type of the function
            tmpVars++;
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
        boolean isArray = false;
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
        //check for each expression given in dimensions if its an int!
        String tmpVar = node.getId().toString().trim();
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PExpr> copy = new ArrayList<PExpr>(node.getExpr());

            for(PExpr e : copy)
            {
                isArray = true;
                Type type = getTypeEvaluation(e);
                String tmpVar2;
                if (type.isArray())
                {
                    tmpVar2 = "[" + type.getTempVar() + "]";
                }
                else
                {
                    tmpVar2 = type.getTempVar();
                }

                quads.add(new Quadruple(quads.size() + 1, "array", tmpVar, tmpVar2, "$" + tmpVars));
                tmpVar = "$" + tmpVars;
                tmpVars++;

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

            this.type = new Type(var.getType(), dimensionsUnused, tmpVar, isArray);
        }
        else//all dimensions given
        {
            this.type = new Type(var.getType(), tmpVar, isArray);
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
            if (minuses % 2 != 0) {

                quads.add(new Quadruple(quads.size() + 1, "-", "0", tmpVar, "$" + tmpVars));
                tmpVar = "$" + tmpVars;
                tmpVars++;
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
        this.type = new Type("char", "$" + tmpVars);
        quads.add(new Quadruple(quads.size() + 1, "load", node.toString(), null, "$" + tmpVars));
        tmpVars++;

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
                        quads.add(new Quadruple(quads.size() + 1, "-", "0", type.getTempVar(), "$" + tmpVars));
                        tmpVars++;
                    }
                }
            }
        }
        else
        {
            this.type = type;//fcallExpression type is the same as the fcall's type
        }
    }
//
//    @Override
//    public void caseALvalueExpr(ALvalueExpr node) {
//        this.inALvalueExpr(node);
//        Type type = getTypeEvaluation(node.getLvalue());
//        System.out.println(node.getLvalue().getClass()+" \nhahaha");
//        if (node.getSign().size() != 0)
//        {
//            if (!type.isInt())//if expression is neither an int nor a char.it cant have a sign!
//            {
//                System.out.println("Error!You can only put a sign in front of an 'int'!");
//                System.exit(-1);
//            }
//            else
//            {
//                this.type = new Type(type.getType(), type.getDimensions(), "$" + tmpVars);
//                quads.add(new Quadruple(quads.size() + 1, "load", node.getLvalue().toString().trim(), null, "$" + tmpVars));
//                tmpVars++;
////        System.out.println("made a new tmpVar = $"+tmpVars);
//                {
//                    List<PSign> copy = new ArrayList<PSign>(node.getSign());
//                    int minuses = 0;
//                    for(PSign e : copy)
//                    {
//                        if (e instanceof compiler.node.ANegativeSign)
//                            minuses++;
//                        e.apply(this);
//                    }
//                    if (minuses % 2 != 0) {
//                        quads.add(new Quadruple(quads.size() + 1, "load", "0", null, "$" + tmpVars));
//                        String zero = "$" + tmpVars;
//                        String num = "$" + (tmpVars - 1);
//                        tmpVars++;
//
//                        quads.add(new Quadruple(quads.size() + 1, "-", zero, num, "$" + tmpVars));
//                        tmpVars++;
//                    }
//                }
//            }
//        }
//        else
//        {
//            this.type = new Type(type.getType(), type.getDimensions(), "$" + tmpVars);
//            quads.add(new Quadruple(quads.size() + 1, "load", node.getLvalue().toString().trim(), null, "$" + tmpVars));
//            tmpVars++;
//        }
//
//
//        this.outALvalueExpr(node);
//    }


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
            if (!type.isInt())//if expression is neither an int nor a char.it cant have a sign!
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
                    if (minuses % 2 != 0) {
                        quads.add(new Quadruple(quads.size() + 1, "-", "0", tmpVar, "$" + tmpVars));
                        tmpVar = "$"+tmpVars;
                        tmpVars++;

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

        String tmpLeft = left.getTempVar();

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
                System.out.println("Invalid return type!Expecting '" + latestReturnType + "'.(" + retType + " given)");
                System.exit(-1);
            }
            quads.add(new Quadruple(quads.size() + 1, "ret", retType.getTempVar(), null, null));
        }
        else //if its a plain 'return' (returns nothing), make sure that the function returns 'nothing'
        {
            if (!latestReturnType.getType().equals("nothing"))
            {
                System.out.println("Invalid return type!Expecting '" + latestReturnType + "'.You returned nothing!");
                System.exit(-1);
            }
            quads.add(new Quadruple(quads.size() + 1, "ret", null, null, null));
        }
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

    public void exprQuad(String expr, Type left, Type right)
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

        this.type = new Type(left.getType(), left.getDimensions(), "$" + tmpVars);
        quads.add(new Quadruple(quads.size() + 1, expr, tmpLeft, tmpRight, "$" + tmpVars));
        tmpVars++;
    }

    String nextQuad() {
        return Integer.toString(quads.size() + 1);
    }

    public void backpatch(List<Quadruple> list, String label) {
        System.out.println("Bainw gia backpatch me lista: ");
        System.out.println(list);
        System.out.println("timi label = " + label);
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

}