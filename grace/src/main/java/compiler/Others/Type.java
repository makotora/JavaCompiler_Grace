package compiler.Others;

import compiler.Definition.*;
import java.util.List;

public class Type
{
    private String type;
    private List<Integer> dimensions;

    private String tempVar;
    private boolean isArray;

    public Type(String type, List<Integer> dimensions, String tempVar) {
        this.type = type;
        this.dimensions = dimensions;
        this.tempVar = tempVar;
        this.isArray = false;
    }

    public boolean isArray() {
        return isArray;
    }

    public Type(String type, String tempVar, boolean isArray) {

        this.type = type;
        this.tempVar = tempVar;
        this.isArray = isArray;
    }

    public Type(String type, List<Integer> dimensions, String tempVar, boolean isArray) {

        this.type = type;
        this.dimensions = dimensions;
        this.tempVar = tempVar;
        this.isArray = isArray;
    }

    public Type(String type, List<Integer> dimensions)
    {

        this.type = type;
        this.dimensions = dimensions;
        this.isArray = false;

    }

    public Type(String type) {
        this.type = type;
        this.dimensions = null;
        this.isArray = false;

    }

    public Type(String type, String tempVar) {
        this.type = type;
        this.tempVar = tempVar;
        this.isArray = false;

    }

    public String getType() {
        return type;
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

    public String toString()
    {
        String str = type;
        if( dimensions != null)
        {
            for (Integer dimension : dimensions) {
                str+= "[" + dimension + "]";
            }
        }
        return str;
    }

    public String getTempVar() {
        return tempVar;
    }

    public void setTempVar(String tempVar) {
        this.tempVar = tempVar;
    }
}