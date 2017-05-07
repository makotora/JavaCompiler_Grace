package compiler;

import com.sun.org.apache.xpath.internal.SourceTree;
import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import compiler.SymbolTable.SymbolTable;
import compiler.Definition.*;

import java.awt.*;
import java.util.List;
import java.lang.String;
import java.lang.Object;
import java.util.*;


public class GenericsVisitor extends DepthFirstAdapter {
    private int indent = 0;
    private String indentation = "  ";
    private SymbolTable symbolTable = new SymbolTable();

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

}