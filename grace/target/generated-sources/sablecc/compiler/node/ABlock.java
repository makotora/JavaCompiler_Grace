/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import java.util.*;
import compiler.analysis.*;

@SuppressWarnings("nls")
public final class ABlock extends PBlock
{
    private final LinkedList<PStatement> _statement_ = new LinkedList<PStatement>();

    public ABlock()
    {
        // Constructor
    }

    public ABlock(
        @SuppressWarnings("hiding") List<PStatement> _statement_)
    {
        // Constructor
        setStatement(_statement_);

    }

    @Override
    public Object clone()
    {
        return new ABlock(
            cloneList(this._statement_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseABlock(this);
    }

    public LinkedList<PStatement> getStatement()
    {
        return this._statement_;
    }

    public void setStatement(List<PStatement> list)
    {
        this._statement_.clear();
        this._statement_.addAll(list);
        for(PStatement e : list)
        {
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._statement_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._statement_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        for(ListIterator<PStatement> i = this._statement_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PStatement) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}
