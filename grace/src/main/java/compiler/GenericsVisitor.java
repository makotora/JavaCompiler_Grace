package compiler;

import com.sun.org.apache.xpath.internal.SourceTree;
import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;

import java.lang.String;
import java.lang.Object;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class GenericsVisitor extends DepthFirstAdapter {
    private int indent = 0;
    private String indentation = "    ";
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
    public void inANoElseIfStmt(ANoElseIfStmt node) {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("If statement:");

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Condition:");

        indent += 2;
    }

    public void outANoElseIfStmt(ANoElseIfStmt node) {
        indent -= 2;
    }

    @Override
    public void inAElseIfStmt(AElseIfStmt node) {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("If statement:");

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Condition:");

        indent += 2;
    }

    public void outAElseIfStmt(AElseIfStmt node) {
        indent -= 2;
    }

    /*If with else*/
    @Override
    public void inAIfElseStmt(AIfElseStmt node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("If statement:");

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.print("Condition: ");

        indent += 2;

    }

    @Override
    public void outAIfElseStmt(AIfElseStmt node)
    { indent -= 2;}

    @Override
    public void inAThenStmt(AThenStmt node)
    {
        for (int i = 0; i < indent - 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Then:");
    }

    @Override
    public void inAThenStmtWelse(AThenStmtWelse node)
    {
        for (int i = 0; i < indent - 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Then:");
    }

    @Override
    public void inAElseStmt(AElseStmt node)
    {
        for (int i = 0; i < indent - 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Else:");
    }

    @Override
    public void inAElseStmtWithElse(AElseStmtWithElse node)
    {
        for (int i = 0; i < indent - 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Else:");
    }

    /*While*/
    @Override
    public void inANoElseWhileStmt(ANoElseWhileStmt node) {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("While statement:");

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Condition:");

        indent += 2;
    }


    @Override
    public void outANoElseWhileStmt(ANoElseWhileStmt node) {
        indent -= 2;
    }

    @Override
    public void inAWhileElseWhileWithElse(AWhileElseWhileWithElse node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("While statement:");

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Condition:");

        indent += 2;
    }

    @Override
    public void outAWhileElseWhileWithElse(AWhileElseWhileWithElse node) {
        indent -= 2;
    }

    @Override
    public void inADoStmt(ADoStmt node)
    {
        for (int i = 0; i < indent - 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Do:");
    }

    @Override
    public void inADoStmtWelse(ADoStmtWelse node)
    {
        for (int i = 0; i < indent - 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Do:");
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

        if (node.getFparDefList() == null) {
            for (int i = 0; i < indent; i++) {
                System.out.print(indentation);
            }
            System.out.println("No parameters");
        }
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
        System.out.print(node.getId().toString());
    }

    @Override
    public void inAIdListTail(AIdListTail node)
    {
        System.out.print(", " + node.getId().toString());
    }

    /*Variable definition*/
    @Override
    public void inAVarDef(AVarDef node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.print("Variable definition: " + node.getType().toString() + " ");
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

    @Override
    public void inANoop(ANoop node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("No operation");
    }

    @Override
    public void inAAssignment(AAssignment node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Assignment: " + node.getLvalue().toString() + "<- " + node.getExpr().toString());
    }

    @Override
    public void inAReturnStmt(AReturnStmt node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.print("Return");

        if (node.getExpr() != null)
        {
            System.out.print(" " + node.getExpr().toString());
        }
        System.out.println();
    }

    @Override
    public void inACtermCond(ACtermCond node)
    {
    }

    @Override
    public void outACtermCond(ACtermCond node)
    {
    }

    @Override
    public void inAOrCond(AOrCond node)
    {
    }

    @Override
    public void outAOrCond(AOrCond node)
    {
    }

    @Override
    public void inACfactCterm(ACfactCterm node)
    {
    }

    @Override
    public void outACfactCterm(ACfactCterm node)
    {
    }

    @Override
    public void inAAndCterm(AAndCterm node) {
    }

    @Override
    public void outAAndCterm(AAndCterm node){}

    @Override
    public void inACompCfact(ACompCfact node)
{}

    @Override
    public void outACompCfact(ACompCfact node){}

    @Override
    public void inANotCfact(ANotCfact node)
{}

    @Override
    public void outANotCfact(ANotCfact node){}

    @Override
    public void inAEqComp(AEqComp node)
{}

    @Override
    public void outAEqComp(AEqComp node){}

    @Override
    public void inANeqComp(ANeqComp node)
{}

    @Override
    public void outANeqComp(ANeqComp node){}

    @Override
    public void inALeqComp(ALeqComp node)
{}

    @Override
    public void outALeqComp(ALeqComp node){}

    @Override
    public void inAGeqComp(AGeqComp node)
{}

    @Override
    public void outAGeqComp(AGeqComp node){}

    @Override
    public void inALtComp(ALtComp node)
{}

    @Override
    public void outALtComp(ALtComp node){}

    @Override
    public void inAGtComp(AGtComp node)
{}

    @Override
    public void outAGtComp(AGtComp node){}

    @Override
    public void inACparenComp(ACparenComp node)
{}

    @Override
    public void outACparenComp(ACparenComp node){}

}