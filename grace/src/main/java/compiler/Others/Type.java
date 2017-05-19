package compiler.Others;

import compiler.Definition.*;
import compiler.Quadruple;

import java.util.ArrayList;
import java.util.List;

public class Type
{
    private String type;
    private List<Integer> dimensions;

    private String tempVar;
    private boolean isArray;

    private List<Quadruple> trueList;
    private List<Quadruple> falseList;
    private List<Quadruple> nextList;


    public Type(String type, List<Integer> dimensions, String tempVar) {
        this.type = type;
        this.dimensions = dimensions;
        this.tempVar = tempVar;
        this.isArray = false;
        this.trueList = new ArrayList<Quadruple>();
        this.falseList = new ArrayList<Quadruple>();
        this.nextList = new ArrayList<Quadruple>();
    }

    public Type(String type, String tempVar, boolean isArray) {

        this.type = type;
        this.tempVar = tempVar;
        this.isArray = isArray;
        this.trueList = new ArrayList<Quadruple>();
        this.falseList = new ArrayList<Quadruple>();
        this.nextList = new ArrayList<Quadruple>();

    }

    public Type(String type, List<Integer> dimensions, String tempVar, boolean isArray) {

        this.type = type;
        this.dimensions = dimensions;
        this.tempVar = tempVar;
        this.isArray = isArray;
        this.trueList = new ArrayList<Quadruple>();
        this.falseList = new ArrayList<Quadruple>();
        this.nextList = new ArrayList<Quadruple>();

    }

    public Type(String type, List<Integer> dimensions)
    {

        this.type = type;
        this.dimensions = dimensions;
        this.isArray = false;
        this.trueList = new ArrayList<Quadruple>();
        this.falseList = new ArrayList<Quadruple>();
        this.nextList = new ArrayList<Quadruple>();

    }

    public Type(String type) {
        this.type = type;
        this.dimensions = null;
        this.isArray = false;
        this.trueList = new ArrayList<Quadruple>();
        this.falseList = new ArrayList<Quadruple>();
        this.nextList = new ArrayList<Quadruple>();

    }

    public Type(String type, String tempVar) {
        this.type = type;
        this.tempVar = tempVar;
        this.isArray = false;
        this.trueList = new ArrayList<Quadruple>();
        this.falseList = new ArrayList<Quadruple>();
        this.nextList = new ArrayList<Quadruple>();

    }

    public boolean isArray() {
        return isArray;
    }

    public String getType() {
        return type;
    }

    public String makeReadable()
    {
        if (dimensions != null && dimensions.size() != 0)
            return (type + dimensions);
        else
            return type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type='" + type + '\'' +
                ", dimensions=" + dimensions +
                ", tempVar='" + tempVar + '\'' +
                ", isArray=" + isArray +
                ", trueList=" + trueList +
                ", falseList=" + falseList +
                ", nextList=" + nextList +
                '}';
    }

    public List<Quadruple> getNextList() {
        return nextList;
    }

    public void setNextList(List<Quadruple> nextList) {
        this.nextList = nextList;
    }

    public List<Quadruple> getTrueList() {
        return trueList;
    }

    public void setTrueList(List<Quadruple> trueList) {
        this.trueList = trueList;
    }

    public List<Quadruple> getFalseList() {
        return falseList;
    }

    public void setFalseList(List<Quadruple> falseList) {
        this.falseList = falseList;
    }

    public List<Integer> getDimensions() {
        return dimensions;
    }

    public boolean isInt()
    {
        if (this.type.equals("int") && this.dimensions == null)
            return true;
        else
            return  false;
    }

    public boolean isChar()
    {
        if (this.type.equals("char") && this.dimensions == null)
            return true;
        else
            return  false;
    }

    public boolean matchesParameter(Variable variable)
    {
        //'ref int' parameter should accept 'int' (and 'ref char' should accept 'char')
        if (variable.getType().equals(type))
        {
            if (dimensions == null)//if this instance 'Type' has no dimensions
            {
                if (variable.getDimensions().isEmpty())//if the variable doesnt have dimensions either
                    return true;
                else
                    return false;
            }
            else //'Type' has dimensions
            {
                if (variable.sameDimensions(dimensions))
                    return true;
                else
                    return false;
            }
        }
        else
        {
            return false;
        }

    }

    public boolean sameType(Type otherType)
    {
        if (type.equals(otherType.type))
        {

            if (dimensions == null && otherType.dimensions == null)//if neither of them has dimensions
            {
                return true;
            }
            else
            {
                if (dimensions != null && otherType.dimensions != null)//if they are both not null check if they are the same
                {
                    if (dimensions.size() == otherType.dimensions.size())
                        return true;
                    else
                        return  false;
                }
                else//only one of them is null
                {
                    return false;
                }
            }
        }
        else //type string is not the same.types surely different
            return false;
    }

    public String getTempVar() {
        return tempVar;
    }

    public void setTempVar(String tempVar) {
        this.tempVar = tempVar;
    }
}