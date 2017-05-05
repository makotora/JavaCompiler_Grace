package compiler;

import com.sun.org.apache.xpath.internal.SourceTree;
import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import compiler.SymbolTable.SymbolTable;
import compiler.Definition.*;

import java.awt.*;
import java.lang.String;
import java.lang.Object;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class GenericsVisitor extends DepthFirstAdapter {
    private int indent = 0;
    private String indentation = "  ";
    private SymbolTable st = new SymbolTable();

    @Override
    public void inAFuncDef(AFuncDef node)
    {
        st.enter();

        LinkedList<PPar> pars = node.getPar();

        ArrayList vars = new ArrayList();
        int i = 0;
        while (i < pars.size())
        {
            PPar parameter = pars.get(i);
            System.out.println(parameter);
            i++;
        }
        
        System.out.println("In a func_def of " + node.getId() + "returning " + node.getRetType());
        LinkedList<PLocalDef> l = node.getLocalDef();

        System.out.println("Local def: " + node.getLocalDef());
        i = 0;
        while (i < l.size()) {
            System.out.println(l.get(i));
            i++;
        }

        System.out.println("Parameters:" + node.getPar());
        System.out.println();
    }

    @Override
    public void outAFuncDef(AFuncDef node)
    {
;       st.exit();
    }

}