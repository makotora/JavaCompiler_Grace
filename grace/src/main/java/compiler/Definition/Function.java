package compiler.Definition;

import java.util.List;

/**
 * Created by valyo95 on 5/5/2017.
 */
public class Function extends Definition {
    private List <Variable> parameters;

    public Function(String id, String type, List<Variable> parameters) {
        super(id, type);
        this.parameters = parameters;
    }
    @Override
    public String toString()
    {
        String str = "Function: " + id + " Type " + type + " ";
        if(parameters != null)
        {
            str+= "( ";
            for (Definition parameter : parameters) {
                str+= parameter.toString() + ", ";
            }
            str = str.substring(0, str.length()-2);
            str+="  )";
        }
        return str;
    }
}
