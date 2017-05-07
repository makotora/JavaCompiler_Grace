package compiler.Definition;

import java.util.List;

/**
 * Created by valyo95 on 5/5/2017.
 */
public class Function extends Definition {
    private List <Variable> parameters;
    private  boolean isDefinition;

    public Function(String id, String type, List<Variable> parameters, boolean isDefinition) {
        super(id, type);
        this.parameters = parameters;
        this.isDefinition = isDefinition;
    }

    public boolean isDefinition() {
        return isDefinition;
    }

    public void setParameters(List<Variable> parameters) {
        this.parameters = parameters;
    }

    public void setDefinition(boolean definition) {
        isDefinition = definition;
    }

    public List<Variable> getParameters() {

        return parameters;
    }

    public boolean sameParameters (List<Variable> otherParams)
    {
        if (parameters.size() != otherParams.size())
            return false;

        int i = 0;
        for (i=0; i<parameters.size(); i++)
        {
            Variable par1 = parameters.get(i);
            Variable par2 = otherParams.get(i);

            if (!par1.getType().equals(par2.getType()))
                return false;
            if (!par1.sameDimensions(par2.getDimensions()))
                return  false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        String str = "Function: " + id + " Type " + type + " ";
        if(!parameters.isEmpty())
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
