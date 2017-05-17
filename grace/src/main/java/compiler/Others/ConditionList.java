package compiler.Others;

import compiler.Quadruple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valyo95 on 17/5/2017.
 */
public class ConditionList {
    private List<Quadruple> trueList;
    private List<Quadruple> falseList;

    public ConditionList() {
        trueList = new ArrayList<Quadruple>();
        falseList = new ArrayList<Quadruple>();

    }

    public List<Quadruple> getTrueList() {
        return trueList;
    }

    public void setTrueList(List<Quadruple> trueList) {
        this.trueList = trueList;
    }

    @Override
    public String toString() {
        return "ConditionList{" +
                "trueList=" + trueList +
                ", falseList=" + falseList +
                '}';
    }

    public List<Quadruple> getFalseList() {
        return falseList;
    }

    public void setFalseList(List<Quadruple> falseList) {
        this.falseList = falseList;
    }
}
