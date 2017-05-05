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
        Definition newVar = new Variable(id, type, dimensions);
        Hashtable tmpHash = this.symbolTable.lastElement();
        if(tmpHash.put(id, newVar) != null)
        {
            System.out.println("Variable: " + id + " is already defined in this scope." );
            return -1;

        }

        return 0;
    }

    public int insertAFunction(String id, String type, List parameters)
    {
        Definition newVar = new Function(id, type, parameters);
        Hashtable tmpHash = this.symbolTable.lastElement();
        if(tmpHash.put(id, newVar) != null)
        {
            symbolTable.push(tmpHash);
            System.out.println("Function : " + id + " is already defined in this scope." );
            return -1;

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
            for (int i1 = 0; i1< i; i++)
                ret+="  ";
            ret += hashtable.toString();
        }
        return ret;
    }
}
