package compiler.SymbolTable;

import compiler.Definition.*;

import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

/**
 * Created by valyo95 on 5/5/2017.
 */
public class SymbolTable {
    private Stack<Hashtable> symbolTable;


    public SymbolTable() {
        this.symbolTable = new Stack<Hashtable>();
    }

    public void enter()
    {
        Hashtable tmpHash = new Hashtable<String, Definition>();
        this.symbolTable.push(tmpHash);
    }

    public int insertAVariable(String id, String type, List dimensions)
    {
        id = id.trim();
        type = type.trim();
        Hashtable tmpHash = this.symbolTable.lastElement();
        if(tmpHash.containsKey(id))
        {
            System.out.println("Variable or Function '" + id + "' is already defined in this scope." );
            return -1;

        }
        else
        {
            Definition newVar = new Variable(id, type, dimensions);
            tmpHash.put(id, newVar);
        }

        return 0;
    }

    public int insertAFunction(String id, String type, List parameters, boolean isDefinition)
    {
        id = id.trim();
        type = type.trim();
        Hashtable tmpHash = this.symbolTable.lastElement();
        if(tmpHash.containsKey(id))
        {
            Definition tmpDef = (Definition) tmpHash.get(id);
            if ( tmpDef instanceof Variable)
            {
                System.out.printf("Error cannot define function '" + id + "'.There is a variable definition with that id");
                return  -1;
            }
            else
            {
                Function func = (Function) tmpDef;

                if (func.isDefinition() == true)
                {
                    System.out.println("Function : " + id + " is already defined in this scope." );
                    return  -1;
                }
                else
                {
                    if (isDefinition == true)
                    {
                        if (parameters.equals(func.getParameters()) && type == func.getType()) {
                            func.setDefinition(true);
                            return 0;
                        }
                        else
                        {
                            System.out.println("Function '" + id + "' was declared with different parameters or type");
                            return  -1;
                        }
                    }
                }
            }
        }
        else
        {
            System.out.println("PUTTING FUN");
            Definition newFunc = new Function(id, type, parameters, isDefinition);
            tmpHash.put(id, newFunc);
        }

        return 0;
    }

    public boolean lookup(String id)
    {
        for (Hashtable hashtable : this.symbolTable) {
            if (hashtable.containsKey(id))
                return true;
        }
        return false;
    }

    public void exit()
    {
        this.symbolTable.pop();
    }

    @Override
    public String toString()
    {
        int i = 0;
        String ret = "Symbol Table\n";
        for (Hashtable hashtable : this.symbolTable) {
            for (int i1 = 0; i1< i; i1++)
                ret+="  ";
            ret += hashtable.toString();
            ret+="\n";
        }
        return ret;
    }
}