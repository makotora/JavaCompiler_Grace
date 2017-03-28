/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.analysis;

import compiler.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAProgram(AProgram node);
    void caseAIntDataType(AIntDataType node);
    void caseACharDataType(ACharDataType node);
    void caseAType(AType node);
    void caseASomethingRetType(ASomethingRetType node);
    void caseANothingRetType(ANothingRetType node);
    void caseAStatFparType(AStatFparType node);
    void caseAEmptyBrack(AEmptyBrack node);
    void caseAArrayBrack(AArrayBrack node);
    void caseAVarDef(AVarDef node);
    void caseAIdList(AIdList node);
    void caseAIdListTail(AIdListTail node);
    void caseAHeader(AHeader node);
    void caseAFparDefList(AFparDefList node);
    void caseAFparDef(AFparDef node);
    void caseAParListTail(AParListTail node);
    void caseAFuncDef(AFuncDef node);
    void caseAFuncDecl(AFuncDecl node);
    void caseAFdefLocalDef(AFdefLocalDef node);
    void caseAFdecLocalDef(AFdecLocalDef node);
    void caseAVdefLocalDef(AVdefLocalDef node);
    void caseABlock(ABlock node);
    void caseAFuncCall(AFuncCall node);
    void caseAExprList(AExprList node);
    void caseAExprTail(AExprTail node);
    void caseAIdLvalue(AIdLvalue node);
    void caseAStringLvalue(AStringLvalue node);
    void caseALvalueLvalue(ALvalueLvalue node);
    void caseATermExpr(ATermExpr node);
    void caseAAddExpr(AAddExpr node);
    void caseASubExpr(ASubExpr node);
    void caseAFactTerm(AFactTerm node);
    void caseAMultTerm(AMultTerm node);
    void caseADivTerm(ADivTerm node);
    void caseANumberFact(ANumberFact node);
    void caseACharFact(ACharFact node);
    void caseAFcallFact(AFcallFact node);
    void caseALvalFact(ALvalFact node);
    void caseAParenFact(AParenFact node);
    void caseACtermCond(ACtermCond node);
    void caseAOrCond(AOrCond node);
    void caseACfactCterm(ACfactCterm node);
    void caseAAndCterm(AAndCterm node);
    void caseACompCfact(ACompCfact node);
    void caseANotCfact(ANotCfact node);
    void caseAEqComp(AEqComp node);
    void caseANeqComp(ANeqComp node);
    void caseALeqComp(ALeqComp node);
    void caseAGeqComp(AGeqComp node);
    void caseALtComp(ALtComp node);
    void caseAGtComp(AGtComp node);
    void caseACparenComp(ACparenComp node);
    void caseAExpr2(AExpr2 node);
    void caseANoopStmt(ANoopStmt node);
    void caseAAssignmentStmt(AAssignmentStmt node);
    void caseABlockStmt(ABlockStmt node);
    void caseAFcallStmt(AFcallStmt node);

    void caseTAnd(TAnd node);
    void caseTChar(TChar node);
    void caseTDiv(TDiv node);
    void caseTDo(TDo node);
    void caseTElse(TElse node);
    void caseTFun(TFun node);
    void caseTIf(TIf node);
    void caseTInt(TInt node);
    void caseTMod(TMod node);
    void caseTNot(TNot node);
    void caseTNothing(TNothing node);
    void caseTOr(TOr node);
    void caseTRef(TRef node);
    void caseTReturn(TReturn node);
    void caseTThen(TThen node);
    void caseTVar(TVar node);
    void caseTWhile(TWhile node);
    void caseTComment(TComment node);
    void caseTSimpleCom(TSimpleCom node);
    void caseTId(TId node);
    void caseTNumber(TNumber node);
    void caseTSingleChar(TSingleChar node);
    void caseTString(TString node);
    void caseTWhitespace(TWhitespace node);
    void caseTLpar(TLpar node);
    void caseTRpar(TRpar node);
    void caseTLbrack(TLbrack node);
    void caseTRbrack(TRbrack node);
    void caseTLbrace(TLbrace node);
    void caseTRbrace(TRbrace node);
    void caseTComma(TComma node);
    void caseTSemicolon(TSemicolon node);
    void caseTColon(TColon node);
    void caseTAssign(TAssign node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTMult(TMult node);
    void caseTEq(TEq node);
    void caseTNeq(TNeq node);
    void caseTLeq(TLeq node);
    void caseTGeq(TGeq node);
    void caseTLt(TLt node);
    void caseTGt(TGt node);
    void caseEOF(EOF node);
}
