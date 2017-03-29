/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.analysis;

import java.util.*;
import compiler.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    public void caseAProgram(AProgram node)
    {
        defaultCase(node);
    }

    public void caseAIntDataType(AIntDataType node)
    {
        defaultCase(node);
    }

    public void caseACharDataType(ACharDataType node)
    {
        defaultCase(node);
    }

    public void caseAType(AType node)
    {
        defaultCase(node);
    }

    public void caseASomethingRetType(ASomethingRetType node)
    {
        defaultCase(node);
    }

    public void caseANothingRetType(ANothingRetType node)
    {
        defaultCase(node);
    }

    public void caseAStatFparType(AStatFparType node)
    {
        defaultCase(node);
    }

    public void caseAEmptyBrack(AEmptyBrack node)
    {
        defaultCase(node);
    }

    public void caseAArrayBrack(AArrayBrack node)
    {
        defaultCase(node);
    }

    public void caseAVarDef(AVarDef node)
    {
        defaultCase(node);
    }

    public void caseAIdList(AIdList node)
    {
        defaultCase(node);
    }

    public void caseAIdListTail(AIdListTail node)
    {
        defaultCase(node);
    }

    public void caseAHeader(AHeader node)
    {
        defaultCase(node);
    }

    public void caseAFparDefList(AFparDefList node)
    {
        defaultCase(node);
    }

    public void caseAFparDef(AFparDef node)
    {
        defaultCase(node);
    }

    public void caseAParListTail(AParListTail node)
    {
        defaultCase(node);
    }

    public void caseAFuncDef(AFuncDef node)
    {
        defaultCase(node);
    }

    public void caseAFuncDecl(AFuncDecl node)
    {
        defaultCase(node);
    }

    public void caseAFdefLocalDef(AFdefLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAFdecLocalDef(AFdecLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAVdefLocalDef(AVdefLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAFuncCall(AFuncCall node)
    {
        defaultCase(node);
    }

    public void caseAExprList(AExprList node)
    {
        defaultCase(node);
    }

    public void caseAExprTail(AExprTail node)
    {
        defaultCase(node);
    }

    public void caseAIdLvalue(AIdLvalue node)
    {
        defaultCase(node);
    }

    public void caseAStringLvalue(AStringLvalue node)
    {
        defaultCase(node);
    }

    public void caseALvalueLvalue(ALvalueLvalue node)
    {
        defaultCase(node);
    }

    public void caseATermExpr(ATermExpr node)
    {
        defaultCase(node);
    }

    public void caseAPositiveExpr(APositiveExpr node)
    {
        defaultCase(node);
    }

    public void caseANegativeExpr(ANegativeExpr node)
    {
        defaultCase(node);
    }

    public void caseAAddExpr(AAddExpr node)
    {
        defaultCase(node);
    }

    public void caseASubExpr(ASubExpr node)
    {
        defaultCase(node);
    }

    public void caseAFactTerm(AFactTerm node)
    {
        defaultCase(node);
    }

    public void caseAMultTerm(AMultTerm node)
    {
        defaultCase(node);
    }

    public void caseADivTerm(ADivTerm node)
    {
        defaultCase(node);
    }

    public void caseAModTerm(AModTerm node)
    {
        defaultCase(node);
    }

    public void caseANumberFact(ANumberFact node)
    {
        defaultCase(node);
    }

    public void caseACharFact(ACharFact node)
    {
        defaultCase(node);
    }

    public void caseAFcallFact(AFcallFact node)
    {
        defaultCase(node);
    }

    public void caseALvalFact(ALvalFact node)
    {
        defaultCase(node);
    }

    public void caseAParenFact(AParenFact node)
    {
        defaultCase(node);
    }

    public void caseACtermCond(ACtermCond node)
    {
        defaultCase(node);
    }

    public void caseAOrCond(AOrCond node)
    {
        defaultCase(node);
    }

    public void caseACfactCterm(ACfactCterm node)
    {
        defaultCase(node);
    }

    public void caseAAndCterm(AAndCterm node)
    {
        defaultCase(node);
    }

    public void caseACompCfact(ACompCfact node)
    {
        defaultCase(node);
    }

    public void caseANotCfact(ANotCfact node)
    {
        defaultCase(node);
    }

    public void caseAEqComp(AEqComp node)
    {
        defaultCase(node);
    }

    public void caseANeqComp(ANeqComp node)
    {
        defaultCase(node);
    }

    public void caseALeqComp(ALeqComp node)
    {
        defaultCase(node);
    }

    public void caseAGeqComp(AGeqComp node)
    {
        defaultCase(node);
    }

    public void caseALtComp(ALtComp node)
    {
        defaultCase(node);
    }

    public void caseAGtComp(AGtComp node)
    {
        defaultCase(node);
    }

    public void caseACparenComp(ACparenComp node)
    {
        defaultCase(node);
    }

    public void caseABlock(ABlock node)
    {
        defaultCase(node);
    }

    public void caseANoopStmt(ANoopStmt node)
    {
        defaultCase(node);
    }

    public void caseAAssignmentStmt(AAssignmentStmt node)
    {
        defaultCase(node);
    }

    public void caseABlockStmt(ABlockStmt node)
    {
        defaultCase(node);
    }

    public void caseAFcallStmt(AFcallStmt node)
    {
        defaultCase(node);
    }

    public void caseAIfStmt(AIfStmt node)
    {
        defaultCase(node);
    }

    public void caseAWhileStmt(AWhileStmt node)
    {
        defaultCase(node);
    }

    public void caseAReturnStmt(AReturnStmt node)
    {
        defaultCase(node);
    }

    public void caseANoElseIfStmt(ANoElseIfStmt node)
    {
        defaultCase(node);
    }

    public void caseAElseIfStmt(AElseIfStmt node)
    {
        defaultCase(node);
    }

    public void caseANoopStmtWithElse(ANoopStmtWithElse node)
    {
        defaultCase(node);
    }

    public void caseAAssignmentStmtWithElse(AAssignmentStmtWithElse node)
    {
        defaultCase(node);
    }

    public void caseABlockStmtWithElse(ABlockStmtWithElse node)
    {
        defaultCase(node);
    }

    public void caseAFcallStmtWithElse(AFcallStmtWithElse node)
    {
        defaultCase(node);
    }

    public void caseAIfStmtWithElse(AIfStmtWithElse node)
    {
        defaultCase(node);
    }

    public void caseAWhileStmtWithElse(AWhileStmtWithElse node)
    {
        defaultCase(node);
    }

    public void caseAReturnStmtWithElse(AReturnStmtWithElse node)
    {
        defaultCase(node);
    }

    public void caseAIfElseStmt(AIfElseStmt node)
    {
        defaultCase(node);
    }

    public void caseANoElseWhileStmt(ANoElseWhileStmt node)
    {
        defaultCase(node);
    }

    public void caseAWhileElseWhileWithElse(AWhileElseWhileWithElse node)
    {
        defaultCase(node);
    }

    public void caseTAnd(TAnd node)
    {
        defaultCase(node);
    }

    public void caseTChar(TChar node)
    {
        defaultCase(node);
    }

    public void caseTDiv(TDiv node)
    {
        defaultCase(node);
    }

    public void caseTDo(TDo node)
    {
        defaultCase(node);
    }

    public void caseTElse(TElse node)
    {
        defaultCase(node);
    }

    public void caseTFun(TFun node)
    {
        defaultCase(node);
    }

    public void caseTIf(TIf node)
    {
        defaultCase(node);
    }

    public void caseTInt(TInt node)
    {
        defaultCase(node);
    }

    public void caseTMod(TMod node)
    {
        defaultCase(node);
    }

    public void caseTNot(TNot node)
    {
        defaultCase(node);
    }

    public void caseTNothing(TNothing node)
    {
        defaultCase(node);
    }

    public void caseTOr(TOr node)
    {
        defaultCase(node);
    }

    public void caseTRef(TRef node)
    {
        defaultCase(node);
    }

    public void caseTReturn(TReturn node)
    {
        defaultCase(node);
    }

    public void caseTThen(TThen node)
    {
        defaultCase(node);
    }

    public void caseTVar(TVar node)
    {
        defaultCase(node);
    }

    public void caseTWhile(TWhile node)
    {
        defaultCase(node);
    }

    public void caseTComment(TComment node)
    {
        defaultCase(node);
    }

    public void caseTSimpleCom(TSimpleCom node)
    {
        defaultCase(node);
    }

    public void caseTId(TId node)
    {
        defaultCase(node);
    }

    public void caseTNumber(TNumber node)
    {
        defaultCase(node);
    }

    public void caseTSingleChar(TSingleChar node)
    {
        defaultCase(node);
    }

    public void caseTString(TString node)
    {
        defaultCase(node);
    }

    public void caseTWhitespace(TWhitespace node)
    {
        defaultCase(node);
    }

    public void caseTLpar(TLpar node)
    {
        defaultCase(node);
    }

    public void caseTRpar(TRpar node)
    {
        defaultCase(node);
    }

    public void caseTLbrack(TLbrack node)
    {
        defaultCase(node);
    }

    public void caseTRbrack(TRbrack node)
    {
        defaultCase(node);
    }

    public void caseTLbrace(TLbrace node)
    {
        defaultCase(node);
    }

    public void caseTRbrace(TRbrace node)
    {
        defaultCase(node);
    }

    public void caseTComma(TComma node)
    {
        defaultCase(node);
    }

    public void caseTSemicolon(TSemicolon node)
    {
        defaultCase(node);
    }

    public void caseTColon(TColon node)
    {
        defaultCase(node);
    }

    public void caseTAssign(TAssign node)
    {
        defaultCase(node);
    }

    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    public void caseTMinus(TMinus node)
    {
        defaultCase(node);
    }

    public void caseTMult(TMult node)
    {
        defaultCase(node);
    }

    public void caseTEq(TEq node)
    {
        defaultCase(node);
    }

    public void caseTNeq(TNeq node)
    {
        defaultCase(node);
    }

    public void caseTLeq(TLeq node)
    {
        defaultCase(node);
    }

    public void caseTGeq(TGeq node)
    {
        defaultCase(node);
    }

    public void caseTLt(TLt node)
    {
        defaultCase(node);
    }

    public void caseTGt(TGt node)
    {
        defaultCase(node);
    }

    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
