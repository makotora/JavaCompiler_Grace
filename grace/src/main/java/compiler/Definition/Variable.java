package compiler.Definition;

import java.util.List;

/**
 * Created by valyo95 on 5/5/2017.
 */

public class Variable extends Definition {
    private List<Integer> dimensions;
    private boolean isAParameter;
    private boolean isReference;

    public boolean isAParameter() {
        return isAParameter;
    }

    public boolean isReference() {
        return isReference;
    }

    public Variable(String id, String type, List<Integer> dimensions, boolean isAParameter, boolean isReference) {
        super(id, type);
        this.dimensions = dimensions;
        this.isAParameter = isAParameter;
        this.isReference = isReference;

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
//            if (dim1*dim2 != 0 && dim1 != dim2)
//                return false;
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
        return str;
    }

}
