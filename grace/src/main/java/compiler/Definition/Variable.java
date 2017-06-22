package compiler.Definition;

import java.util.List;

/**
 * Created by valyo95 on 5/5/2017.
 */

public class Variable extends Definition {
    private List<Integer> dimensions;
    private boolean isAParameter;
    private boolean isReference;
    private int bpOffset;

    public boolean isAParameter() {
        return isAParameter;
    }

    public boolean isReference() {
        return isReference;
    }

    public int getBpOffset() {
        return bpOffset;
    }

    public Variable(String id, String type, List<Integer> dimensions, int scopeNumber, boolean isAParameter, boolean isReference, int bpOffset) {
        super(id, type, scopeNumber);
        this.dimensions = dimensions;
        this.isAParameter = isAParameter;
        this.isReference = isReference;
        this.bpOffset = bpOffset;

    }

    public List<Integer> getDimensions() {
        return dimensions;
    }

    public boolean sameDimensions(List<Integer> otherDimensions)
    {
        if (dimensions.size() != otherDimensions.size())
            return false;

        int i;
        for (i=0; i<dimensions.size(); i++)
        {
            Integer dim1 = dimensions.get(i);
            Integer dim2 = otherDimensions.get(i);

            //if dim1*dim2 is 0,then at least one of them is 0
            //if one of them is 0 than the dimensions match
            if (dim1*dim2 != 0 && dim1 != dim2)
                return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        String str = type;
        if(! dimensions.isEmpty())
        {
            for (Integer dimension : dimensions) {
                str+= "[" + dimension + "]";
            }
        }
        return str + ", " + id +", bpOffset: " + bpOffset;
    }

    public void setBpOffset(int bpOffset) {
        this.bpOffset = bpOffset;
    }
}
