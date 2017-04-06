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
    private String indentation = "  ";
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

    @Override
    public void inAFdefLocalDefList(AFdefLocalDefList node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Local Definitions:");
        indent++;

        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Local Function Definition:");
    }

    @Override
    public void outAFdefLocalDefList(AFdefLocalDefList node)
    {
        indent--;
    }

    @Override
    public void inAFdecLocalDefList(AFdecLocalDefList node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Local Definitions:");
        indent++;

        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Local Function Declaration:");
    }

    @Override
    public void outAFdecLocalDefList(AFdecLocalDefList node)
    {
        indent--;
    }

    @Override
    public void inAVdefLocalDefList(AVdefLocalDefList node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Local Definitions:");
        indent++;

        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Local Variable Definition:");
    }

    @Override
    public void outAVdefLocalDefList(AVdefLocalDefList node)
    {
        indent--;
    }

    @Override
    public void inAFdefLocalDefTail(AFdefLocalDefTail node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Local Function Definition:");
        indent++;
    }

    @Override
    public void outAFdefLocalDefTail(AFdefLocalDefTail node)
    {
        indent--;
    }

    @Override
    public void inAFdecLocalDefTail(AFdecLocalDefTail node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Local Function Declaration:");
        indent++;
    }

    @Override
    public void outAFdecLocalDefTail(AFdecLocalDefTail node)
    {
        indent--;
    }

    @Override
    public void inAVdefLocalDefTail(AVdefLocalDefTail node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Local Variable Definition:");
        indent++;
    }

    public void outAVdefLocalDefTail(AVdefLocalDefTail node)
    {
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
        System.out.println("Header:");

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Name:");

        for (int i = 0; i < indent + 2; i++) {
            System.out.print(indentation);
        }
        System.out.println("Id: " + node.getId().toString());

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Return Type: " + node.getRetType().toString());

        if (node.getFparDefList() == null) {
            for (int i = 0; i < indent + 1; i++) {
                System.out.print(indentation);
            }
            System.out.println("No parameters");
        }
        indent+=2;
    }

    @Override
    public void outAHeader(AHeader node) {
        indent-=2;
    }

    @Override
    public void inASomethingRetType(ASomethingRetType node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Something Return Type: " + node.getDataType());
        indent++;
    }

    @Override
    public void outASomethingRetType(ASomethingRetType node)
    {
        indent--;
    }

    @Override
    public void inANothingRetType(ANothingRetType node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Nothing Return Type: nothing");
    }

    @Override
    public void inAIntDataType(AIntDataType node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Int Type: int");
    }

    @Override
    public void inACharDataType(ACharDataType node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Char Type: char");
    }

    @Override
    public void inAType(AType node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.print("Type: " + node.getDataType().toString());

        if (node.getArrayBrackList() != null)
            System.out.print(node.getArrayBrackList());
        System.out.println();
        indent++;
    }

    @Override
    public void outAType(AType node)
    {
        indent--;
    }

    @Override
    public void inAEmptyBrack(AEmptyBrack node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Empty Bracket: []");
    }

    @Override
    public void inAArrayBrackList(AArrayBrackList node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Array Bracket List:");

        indent++;
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Array Bracket: [ " + node.getNumber().toString() + " ]");
    }

    @Override
    public void outAArrayBrackList(AArrayBrackList node)
    {
        indent--;
    }

    @Override
    public void inAArrayBrackTail(AArrayBrackTail node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Array Bracket: [ " + node.getNumber().toString() + " ]");
    }


    /*Function parameters*/
    @Override
    public void inAFparDef(AFparDef node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        if (node.getRef() != null)
            System.out.print("Reference ");
        System.out.println("FParameter definition: " + node.getFparType().toString());
        indent++;
    }

    @Override
    public void outAFparDef(AFparDef node)
    {
        indent--;
    }

    @Override
    public void inAStatFparType(AStatFparType node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.print("FParameter Type: " + node.getDataType().toString());

        if (node.getEmptyBrack() != null)
            System.out.print("[]");

        if (node.getArrayBrackList() != null)
            System.out.print(node.getArrayBrackList().toString());

        System.out.println();
        indent++;
    }

    @Override
    public void outAStatFparType(AStatFparType node)
    {
        indent--;
    }


    /*Id list (for parameters and var definitions)*/
    @Override
    public void inAIdList(AIdList node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Id List:");
        indent++;

        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Id: " + node.getId().toString());
    }

    @Override
    public void outAIdList(AIdList node)
    {
        indent--;
    }

    @Override
    public void inAIdListTail(AIdListTail node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Id: " + node.getId().toString());
    }

    /*Variable definition*/
    @Override
    public void inAVarDef(AVarDef node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Variable definition: " + node.getIdList().toString() + " : " + node.getType());
        indent++;
    }

    @Override
    public void outAVarDef(AVarDef node) {
        indent--;
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
        System.out.println("Name:");

        for (int i = 0; i < indent + 2; i++) {
            System.out.print(indentation);
        }
        System.out.println("Id: " + node.getId().toString());

        for (int i = 0; i < indent + 1; i++) {
            System.out.print(indentation);
        }
        System.out.println("Arguments: ");
        indent += 2;
    }

    @Override
    public void outAFuncCall(AFuncCall node)
    {
        indent -= 2;
    }


    /*Expression list*/
    @Override
    public void inAExprList(AExprList node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Expression list:");
        indent++;
    }

    @Override
    public void outAExprList(AExprList node)
    {
        indent--;
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
        System.out.println("Assignment: " + node.getLvalue().toString() + " <- " + node.getExpr().toString());
        indent++;
    }

    @Override
    public void outAAssignment(AAssignment node)
    {
        indent--;
    }

    @Override
    public void inAReturnStmt(AReturnStmt node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.print("Return statement:");

        if (node.getExpr() != null)
        {
            System.out.print(" " + node.getExpr().toString());
        }
        System.out.println();
        indent++;
    }

    @Override
    public void outAReturnStmt(AReturnStmt node)
    {
        indent--;
    }

    /*Expressions*/
    @Override
    public void inATermExpr(ATermExpr node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Term Expression: " + node.getTerm().toString());
        indent++;
    }

    @Override
    public void outATermExpr(ATermExpr node)
    {
        indent--;
    }


    @Override
    public void inAPositiveExpr(APositiveExpr node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Positive Expression: +" + node.getTerm().toString());
        indent++;
    }

    @Override
    public void outAPositiveExpr(APositiveExpr node)
    {
        indent--;
    }

    @Override
    public void inANegativeExpr(ANegativeExpr node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Negative Expression: -" + node.getTerm().toString());
        indent++;
    }

    @Override
    public void outANegativeExpr(ANegativeExpr node)
    {
        indent--;
    }

    @Override
    public void inAAddExpr(AAddExpr node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Add Expression: " + node.getExpr().toString() + " + " + node.getTerm().toString());
        indent++;
    }

    @Override
    public void outAAddExpr(AAddExpr node)
    {
        indent--;
    }

    @Override
    public void inASubExpr(ASubExpr node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Sub Expression: " + node.getExpr().toString() + " - " + node.getTerm().toString());
        indent++;
    }

    @Override
    public void outASubExpr(ASubExpr node)
    {
        indent--;
    }

    @Override
    public void inAFactTerm(AFactTerm node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }

        System.out.println("Fact Term: " + node.getFact().toString());
        indent++;
    }

    @Override
    public void outAFactTerm(AFactTerm node)
    {
        indent--;
    }


    @Override
    public void inAMultTerm(AMultTerm node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Mult Term: " + node.getTerm().toString() + " * " + node.getFact().toString());
        indent++;
    }

    @Override
    public void outAMultTerm(AMultTerm node)
    {
        indent--;
    }

    @Override
    public void inADivTerm(ADivTerm node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Div Term: " + node.getTerm().toString() + " div " + node.getFact().toString());
        indent++;
    }

    @Override
    public void outADivTerm(ADivTerm node)
    {
        indent--;
    }

    @Override
    public void inAModTerm(AModTerm node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Mod Term: " + node.getTerm().toString() + " mod " + node.getFact().toString());
        indent++;
    }

    @Override
    public void outAModTerm(AModTerm node)
    {
        indent--;
    }

    @Override
    public void inANumberFact(ANumberFact node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Number: " + node.getNumber().toString());
    }

    @Override
    public void inACharFact(ACharFact node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Single char: " + node.getSingleChar().toString());
    }

    @Override
    public void inAParenFact(AParenFact node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Parenthesis: (" + node.getExpr().toString() + ")" );
        indent++;
    }

    @Override
    public void outAParenFact(AParenFact node)
    {
        indent--;
    }


    /*Conditions*/
    @Override
    public void inACtermCond(ACtermCond node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Cterm Condition: " + node.getCterm().toString());
        indent++;
    }

    @Override
    public void outACtermCond(ACtermCond node)
    {indent--;}

    @Override
    public void inAOrCond(AOrCond node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Or Condition: " + node.getCond().toString() + " or " + node.getCterm().toString());
        indent++;
    }

    @Override
    public void outAOrCond(AOrCond node)
    {indent--;}

    @Override
    public void inACfactCterm(ACfactCterm node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("CFact CTerm: " + node.getCfact().toString());
        indent++;
    }

    @Override
    public void outACfactCterm(ACfactCterm node)
    {indent--;}

    @Override
    public void inAAndCterm(AAndCterm node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("And CTerm: " + node.getCterm().toString() + " and " + node.getCfact().toString());
        indent++;
    }

    @Override
    public void outAAndCterm(AAndCterm node) {indent--;}

    @Override
    public void inACompCfact(ACompCfact node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Comp CFact: " + node.getComp().toString());
        indent++;
    }

    @Override
    public void outACompCfact(ACompCfact node){indent--;}

    @Override
    public void inANotCfact(ANotCfact node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Not CFact: not " + node.getCfact().toString());
        indent++;
    }

    @Override
    public void outANotCfact(ANotCfact node){indent--;}

    @Override
    public void inAEqComp(AEqComp node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Eq Comp: " + node.getExpr1().toString() + " = " + node.getExpr2().toString());
        indent++;
    }

    @Override
    public void outAEqComp(AEqComp node){indent--;}

    @Override
    public void inANeqComp(ANeqComp node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Neq Comp: " + node.getExpr1().toString() + " # " + node.getExpr2().toString());
        indent++;
    }

    @Override
    public void outANeqComp(ANeqComp node){indent--;}

    @Override
    public void inALeqComp(ALeqComp node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Leq Comp: " + node.getExpr1().toString() + " <= " + node.getExpr2().toString());
        indent++;
    }

    @Override
    public void outALeqComp(ALeqComp node){indent--;}

    @Override
    public void inAGeqComp(AGeqComp node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Geq Comp: " + node.getExpr1().toString() + " >= " + node.getExpr2().toString());
        indent++;
    }

    @Override
    public void outAGeqComp(AGeqComp node){indent--;}

    @Override
    public void inALtComp(ALtComp node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Lt Comp: " + node.getExpr1().toString() + " < " + node.getExpr2().toString());
        indent++;
    }

    @Override
    public void outALtComp(ALtComp node){indent--;}

    @Override
    public void inAGtComp(AGtComp node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Gt Comp: " + node.getExpr1().toString() + " > " + node.getExpr2().toString());
        indent++;
    }

    @Override
    public void outAGtComp(AGtComp node){indent--;}

    @Override
    public void inACparenComp(ACparenComp node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Parenthesis Comp: (" + node.getCond().toString() + ')');
        indent++;
    }

    @Override
    public void outACparenComp(ACparenComp node){indent--;}


    /*Lvalue*/
    @Override
    public void inAIdLvalue(AIdLvalue node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Id Lvalue: " + node.getId().toString());
    }

    @Override
    public void inAStringLvalue(AStringLvalue node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("String Lvalue: " + node.getString().toString());
    }

    @Override
    public void inALvalueLvalue(ALvalueLvalue node)
    {
        for (int i = 0; i < indent; i++) {
            System.out.print(indentation);
        }
        System.out.println("Lvalue: " + node.getLvalue().toString() + "[ " + node.getExpr().toString() + " ]");
        indent++;
    }

    @Override
    public void outALvalueLvalue(ALvalueLvalue node)
    {
        indent--;
    }
}