package compiler.SymbolTable;

import compiler.Definition.*;

import java.util.*;

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

    public int insertAVariable(String id, String type, List<Integer> dimensions, int bpOffset)
    {
        id = id.trim();
        type = type.trim();
        Hashtable tmpHash = this.symbolTable.lastElement();
        if(tmpHash.containsKey(id))
        {
            System.out.println("Variable or Function '" + id + "' is already defined in this scope." );
            System.exit(-1);

        }
        else
        {
            Definition newVar = new Variable(id, type, dimensions, this.symbolTable.size(), false, false, bpOffset);
            tmpHash.put(id, newVar);
        }

        return 0;
    }

    public int getSize() {
        return symbolTable.size();
    }

    public int insertAParameter(String id, String type, List<Integer> dimensions, boolean isReference, int bpOffset)
    {
        id = id.trim();
        type = type.trim();
        Hashtable tmpHash = this.symbolTable.lastElement();
        if(tmpHash.containsKey(id))
        {
            System.out.println("Variable or Function '" + id + "' is already defined in this scope." );
            System.exit(-1);

        }
        else
        {
            Definition newVar = new Variable(id, type, dimensions,this.symbolTable.size(), true, isReference, bpOffset);
            tmpHash.put(id, newVar);
        }

        return 0;
    }

    public int insertAFunction(String id, String type, List<Variable> parameters, boolean isDefinition)
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
                System.exit(-1);
            }
            else
            {
                Function func = (Function) tmpDef;

                if (func.isDefinition() == true)
                {
                    System.out.println("Function : " + id + " is already defined in this scope." );
                    System.exit(-1);
                }
                else
                {
                    if (isDefinition == true)
                    {
                        if (func.sameParameters(parameters) && type.equals(func.getType())) {
                            func.setDefinition(true);
                            return 0;
                        }
                        else
                        {
                            System.out.println("Function '" + id + "' was declared with different parameters or type");
                            System.exit(-1);
                        }
                    }
                }
            }
        }
        else
        {
            Definition newFunc = new Function(id, type, parameters, this.symbolTable.size(), isDefinition);
            tmpHash.put(id, newFunc);
        }

        return 0;
    }

    public Definition lookup(String id)
    {
        int i;

        for (i=this.symbolTable.size()-1; i>-1; i--)
        {
            Hashtable hashtable = this.symbolTable.get(i);
            if (hashtable.containsKey(id))
                return (Definition) hashtable.get(id);
        }
        return null;
    }

    private void checkForPendingDeclarations(Hashtable scope)
    {
        Set<String> keys = scope.keySet();
        Iterator<String> itr = keys.iterator();
        String currentId;
        Definition definition;
        Function function;

        while (itr.hasNext())
        {
            currentId = itr.next();
            definition = (Definition) scope.get(currentId);

            if (definition instanceof Function)
            {
                function = (Function) definition;

                //if function is on this scope but it is not defined,it was declared but never defined!
                if (!function.isDefinition())
                {
                    System.out.println("Error!Function '" + function.getId() + "' was declared but wasn't defined in that scope!");
                    System.exit(-1);
                }
            }

        }
    }


    public void exit()
    {
        //check if the most resent scope (the one we are about to 'destroy'/pop)
        //has functions that were declared but not defined!
        checkForPendingDeclarations(this.symbolTable.lastElement());
        this.symbolTable.pop();
    }

    public boolean isLocal(Definition definition)
    {
        return (definition.getScopeNumber() == getSize());
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