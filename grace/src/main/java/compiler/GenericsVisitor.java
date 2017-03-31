package compiler;

import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;

import java.lang.String;
import java.lang.Object;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class GenericsVisitor extends DepthFirstAdapter {
    private int indent = 0;
    private String indentation = "\t";
    private Queue<String> id_list = new LinkedList<String>();

    /*FIRST DEAL WITH PRODUCTIONS THAT HAVE A BODY,MEANING THE HAVE TO indent++ or --*/

    /*Function definition*/
    @Override
    public void inAFuncDef(AFuncDef node) {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Function Definition:");
        indent++;
    }

    @Override
    public void outAFuncDef(AFuncDef node) {
        indent--;
    }

    /*Parameters*/
    @Override
    public void inAFparDefList(AFparDefList node) {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Parameters:");
        indent++;
    }

    @Override
    public void outAFparDefList(AFparDefList node) {
        indent--;
    }

    /*Function Declaration*/
    @Override
    public void inAFuncDecl(AFuncDecl node) {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Function declaration:");
        indent++;
    }

    @Override
    public void outAFuncDecl(AFuncDecl node) {
        indent--;
    }

    /*Block*/
    @Override
    public void inABlock(ABlock node)

    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Block:");
        indent++;
    }

    @Override
    public void outABlock(ABlock node) {
        indent--;
    }

    /*If*/
    @Override
    public void inAIfStmt(AIfStmt node) {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("If statement:");
        indent++;
    }

    public void outAIfStmt(AIfStmt node) {
        indent--;
    }

    /*While*/
    @Override
    public void inAWhileStmt(AWhileStmt node) {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("If statement:");
        indent++;
    }

    @Override
    public void outAWhileStmt(AWhileStmt node) {
        indent--;
    }


    /*NOW DEAL WITH THE BODY ITSELF*/

    /*Function header*/
    @Override
    public void inAHeader(AHeader node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Name: " + node.getId().toString());

        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Return Type: " + node.getRetType().toString());

        if (node.getFparDefList() == null)
            System.out.println("No parameters");
    }

    @Override
    public void outAHeader(AHeader node) {
        System.out.println();
    }


    /*Function parameters*/
    @Override
    public void inAFparDef(AFparDef node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        if (node.getRef() != null)
            System.out.print("Reference to ");
        System.out.print(node.getFparType().toString() + " ");
    }

    @Override
    public void outAFparDef(AFparDef node)
    {
        System.out.println();
    }

    /*Id list (for parameters and var definitions)*/
    @Override
    public void inAIdList(AIdList node)
    {
        System.out.print(node.getId());
    }

    @Override
    public void inAIdListTail(AIdListTail node)
    {
        System.out.print(", " + node.getId());
    }

    /*Variable definition*/
    @Override
    public void inAVarDef(AVarDef node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.print("Variable definition: " + node.getType() + " ");
    }

    @Override
    public void outAVarDef(AVarDef node) {
        System.out.println();
    }

    /*Function call*/
    @Override
    public void inAFuncCall(AFuncCall node) {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Function Call:");

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Name: " + node.getId().toString());

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.print("Arguments: ");

    }

    /*Expression list*/
    @Override
    public void inAExprList(AExprList node)
    {
        System.out.print(node.getExpr().toString());
    }

    @Override
    public void outAExprList(AExprList node)
    {
        System.out.println();
    }

    @Override
    public void inAExprTail(AExprTail node)
    {
        System.out.print(", " + node.getExpr().toString());
    }

}