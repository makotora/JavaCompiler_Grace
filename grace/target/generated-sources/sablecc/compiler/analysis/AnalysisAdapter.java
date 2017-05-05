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

    public void caseAFuncDef(AFuncDef node)
    {
        defaultCase(node);
    }

    public void caseASmthRetType(ASmthRetType node)
    {
        defaultCase(node);
    }

    public void caseANothingRetType(ANothingRetType node)
    {
        defaultCase(node);
    }

    public void caseAIntType(AIntType node)
    {
        defaultCase(node);
    }

    public void caseACharType(ACharType node)
    {
        defaultCase(node);
    }

    public void caseAPar(APar node)
    {
        defaultCase(node);
    }

    public void caseAFdefLocalDef(AFdefLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAFdeclLocalDef(AFdeclLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAVdefLocalDef(AVdefLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAFuncDecl(AFuncDecl node)
    {
        defaultCase(node);
    }

    public void caseAVarDef(AVarDef node)
    {
        defaultCase(node);
    }

    public void caseABlock(ABlock node)
    {
        defaultCase(node);
    }

    public void caseAFuncCall(AFuncCall node)
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

    public void caseANoopStatement(ANoopStatement node)
    {
        defaultCase(node);
    }

    public void caseAAssignmentStatement(AAssignmentStatement node)
    {
        defaultCase(node);
    }

    public void caseABlockStatement(ABlockStatement node)
    {
        defaultCase(node);
    }

    public void caseAFcallStatement(AFcallStatement node)
    {
        defaultCase(node);
    }

    public void caseAIfElseStatement(AIfElseStatement node)
    {
        defaultCase(node);
    }

    public void caseAWhileStatement(AWhileStatement node)
    {
        defaultCase(node);
    }

    public void caseAReturnStatement(AReturnStatement node)
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

    public void caseAMultExpr(AMultExpr node)
    {
        defaultCase(node);
    }

    public void caseADivExpr(ADivExpr node)
    {
        defaultCase(node);
    }

    public void caseAModExpr(AModExpr node)
    {
        defaultCase(node);
    }

    public void caseANumberExpr(ANumberExpr node)
    {
        defaultCase(node);
    }

    public void caseACharExpr(ACharExpr node)
    {
        defaultCase(node);
    }

    public void caseAFcallExpr(AFcallExpr node)
    {
        defaultCase(node);
    }

    public void caseALvalueExpr(ALvalueExpr node)
    {
        defaultCase(node);
    }

    public void caseASignedExpr(ASignedExpr node)
    {
        defaultCase(node);
    }

    public void caseAPositiveSign(APositiveSign node)
    {
        defaultCase(node);
    }

    public void caseANegativeSign(ANegativeSign node)
    {
        defaultCase(node);
    }

    public void caseAOrCond(AOrCond node)
    {
        defaultCase(node);
    }

    public void caseAAndCond(AAndCond node)
    {
        defaultCase(node);
    }

    public void caseANotCond(ANotCond node)
    {
        defaultCase(node);
    }

    public void caseAEqCond(AEqCond node)
    {
        defaultCase(node);
    }

    public void caseANeqCond(ANeqCond node)
    {
        defaultCase(node);
    }

    public void caseALeqCond(ALeqCond node)
    {
        defaultCase(node);
    }

    public void caseAGeqCond(AGeqCond node)
    {
        defaultCase(node);
    }

    public void caseALtCond(ALtCond node)
    {
        defaultCase(node);
    }

    public void caseAGtCond(AGtCond node)
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

    public void caseTErrorNumber(TErrorNumber node)
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
