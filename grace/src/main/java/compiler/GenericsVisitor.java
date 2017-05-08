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


    @Override
    public void inAFuncDef(AFuncDef node)
    {
        LinkedList<PPar> pars = node.getPar();
        
        System.out.println("In a func_def of " + node.getId() + "returning " + node.getRetType());
        //System.out.println("Local def: " + node.getLocalDef());
        //System.out.println("Parameters:" + node.getPar());
        //System.out.println(node.getPar().getFirst().getClass());

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

        symbolTable.insertAFunction(node.getId().toString(), node.getRetType().toString(), variableList, true);
        symbolTable.enter();
        System.out.println(symbolTable);
    }

    @Override
    public void outAFuncDef(AFuncDef node)
    {
       symbolTable.exit();
    }

    @Override
    public void inAFuncDecl(AFuncDecl node)
    {
        LinkedList<PPar> pars = node.getPar();

        System.out.println("In a func_decl of " + node.getId() + "returning " + node.getRetType());
        //System.out.println("Local def: " + node.getLocalDef());
        //System.out.println("Parameters:" + node.getPar());
        //System.out.println(node.getPar().getFirst().getClass());

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

    public void inAIdLvalue(AIdLvalue node)
    {
        Definition definition = symbolTable.lookup(node.getId().getText());

        if (definition != null)
        {
            if (definition instanceof Variable)
            {
                Variable var = (Variable) definition;

                Integer dimensionNum = var.getDimensions().size();
                Integer givenDimensions = node.getExpr().size();

                if (dimensionNum != givenDimensions)
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
    }

    public void inAFuncCall(AFuncCall node)
    {
        Definition definition = symbolTable.lookup(node.getId().getText());

        if (definition != null)
        {
            if (definition instanceof Function)
            {
                Function fun = (Function) definition;

                Integer paramsNum = fun.getParameters().size();
                Integer givenParams = node.getExpr().size();

                if (paramsNum != givenParams)
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
    }

    @Override
    public void caseAAddExpr(AAddExpr node)
    {
        Type left = getTypeEvaluation(node.getLeft());
        Type right = getTypeEvaluation(node.getRight());

        if (left == null)
        {
            System.out.println("Add expressions error.Left part is null!");
            System.exit(-1);
        }
        else if (right == null)
        {
            System.out.println("Add expressions error.Right part is null!");
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



}