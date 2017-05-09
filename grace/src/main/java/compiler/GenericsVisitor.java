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
    private Stack<Type> returnTypes = new Stack();
    private SymbolTable symbolTable = new SymbolTable();

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
        symbolTable.enter();
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
        
        System.out.println("In a func_def of " + node.getId() + "returning " + node.getRetType());

        APar tmpParameter;
        List<Variable> variableList = new ArrayList();


        for (PPar pPar : pars) {
             tmpParameter = (APar) pPar;
             String type = tmpParameter.getType().toString().trim();
             if (tmpParameter.getRef() != null)
                 type = "ref " + type;

             List<Integer> dimensionList = new ArrayList();

             if (tmpParameter.getLbrack() != null)
             {
                 dimensionList.add(0);
             }

            for (TNumber tNumber : tmpParameter.getNumber()) {
                dimensionList.add(Integer.parseInt(tNumber.toString().trim()));
            }

            for (TId tId : tmpParameter.getId()) {
                variableList.add(new Variable(tId.toString(), type, dimensionList));
             }

        }

        returnTypes.push(new Type(node.getRetType().toString().trim()));
        symbolTable.insertAFunction(node.getId().toString(), node.getRetType().toString(), variableList, true);
        symbolTable.enter();
        System.out.println(symbolTable);
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

        System.out.println("In a func_decl of " + node.getId() + "returning " + node.getRetType());

        APar tmpParameter;
        List<Variable> variableList = new ArrayList();

        for (PPar pPar : pars) {
            tmpParameter = (APar) pPar;
            String type = tmpParameter.getType().toString().trim();
            if (tmpParameter.getRef() != null)
                type = "ref " + type;

            List<Integer> dimensionList = new ArrayList();

            if (tmpParameter.getLbrack() != null)
            {
                dimensionList.add(0);
            }

            for (TNumber tNumber : tmpParameter.getNumber()) {
                dimensionList.add(Integer.parseInt(tNumber.toString().trim()));
            }

            for (TId tId : tmpParameter.getId()) {
                variableList.add(new Variable(tId.toString(), type, dimensionList));
            }

        }

        symbolTable.insertAFunction(node.getId().toString(), node.getRetType().toString(), variableList, false);
        System.out.println(symbolTable);
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
        System.out.println(symbolTable);

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
    public void caseANumberExpr(ANumberExpr node)
    {
        this.type = new Type("int");
        {
            List<PSign> copy = new ArrayList<PSign>(node.getSign());
            for(PSign e : copy)
            {
                e.apply(this);
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
        this.type = new Type("char");
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
                    System.out.println("Error.Function '" + node.getId().getText() + "' was defined with " + paramsNum + " parameters." +
                            givenParams + " given.");
                    System.exit(-1);
                }
            }
            else
            {
                System.out.println("Error.'" + node.getId().getText() + "' was defined as a variable.It's not a function.");
                System.exit(-1);
            }
        }
        else
        {
            System.out.println("Error.Undefined function '" + node.getId().getText() + "'");
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
                    System.out.println("Error.Var '" + node.getId().getText() + "' was defined with " + dimensionNum + " dimensions." +
                            givenDimensions + " given.");
                    System.exit(-1);
                }
            }
            else
            {
                System.out.println("Error.'" + node.getId().getText() + "' was defined as a function.It's not a variable.");
                System.exit(-1);
            }
        }
        else
        {
            System.out.println("Error.Undefined variable '" + node.getId().getText() + "'");
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

            Integer unusedNum = dimensionNum - givenDimensions;
            Integer totalDimensions = dimensions.size();
            int i;

            for (i = unusedNum - 1; i<totalDimensions; i++)
                dimensionsUnused.add(dimensions.get(i));

            this.type = new Type(var.getType(), dimensionsUnused);
        }
        else//all dimensions given
        {
            this.type = new Type(var.getType());
        }

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

        if (left == null)
        {
            System.out.println("Add expression error.Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Add expression error.Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Error!You can only add integers!");
                System.exit(-1);
            }
        }
    }

    @Override
    public void caseASubExpr(ASubExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Subtract expression error.Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Subtract expression error.Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Error!You can only subtract integers!");
                System.exit(-1);
            }
        }
    }

    @Override
    public void caseAMultExpr(AMultExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Multiplication expression error.Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Multiplication expression error.Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Error!You can only multiply integers!");
                System.exit(-1);
            }
        }
    }

    @Override
    public void caseADivExpr(ADivExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Division expression error.Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Division expression error.Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Error!You can only divide integers!");
                System.exit(-1);
            }
        }
    }

    @Override
    public void caseAModExpr(AModExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Mod expression error.Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Mod expression error.Right part is null!");
            System.exit(-1);
        }
        else
        {
            if (left.isInt() && right.isInt())
                this.type = left;//result is also an int
            else
            {
                System.out.println("Error!You can only mod integers!");
                System.exit(-1);
            }
        }
    }


}