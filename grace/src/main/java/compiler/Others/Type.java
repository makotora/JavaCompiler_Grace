package compiler.Others;

import java.util.List;

public class Type
{
    private String type;
    private List<Integer> dimensions;

    public Type(String type, List<Integer> dimensions)
    {
        this.type = type;
        this.dimensions = dimensions;
    }

    public String getType() {
        return type;
    }

    public List<Integer> getDimensions() {
        return dimensions;
    }

    public Type(String type)
    {
        this.type = type;
        this.dimensions = null;
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
}