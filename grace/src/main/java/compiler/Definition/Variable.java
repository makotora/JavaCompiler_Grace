package compiler.Definition;

import java.util.List;

/**
 * Created by valyo95 on 5/5/2017.
 */

public class Variable extends Definition {
    private List dimensions;

    public Variable(String id, String type, List dimensions) {
        super(id, type);
        this.dimensions = dimensions;
    }

    @Override
    public String toString()
    {
        String str = "Variable: " + id + " Type: " + type;
        if(! dimensions.isEmpty())
        {
            for (Object dimension : dimensions) {
                str+= "[" + dimension + "]";
            }
        }
        return str;
    }
}
