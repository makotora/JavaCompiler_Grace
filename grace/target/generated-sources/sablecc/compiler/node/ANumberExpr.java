/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import java.util.*;
import compiler.analysis.*;

@SuppressWarnings("nls")
public final class ANumberExpr extends PExpr
{
    private final LinkedList<PSign> _sign_ = new LinkedList<PSign>();
    private TNumber _number_;

    public ANumberExpr()
    {
        // Constructor
    }

    public ANumberExpr(
        @SuppressWarnings("hiding") List<PSign> _sign_,
        @SuppressWarnings("hiding") TNumber _number_)
    {
        // Constructor
        setSign(_sign_);

        setNumber(_number_);

    }

    @Override
    public Object clone()
    {
        return new ANumberExpr(
            cloneList(this._sign_),
            cloneNode(this._number_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANumberExpr(this);
    }

    public LinkedList<PSign> getSign()
    {
        return this._sign_;
    }

    public void setSign(List<PSign> list)
    {
        this._sign_.clear();
        this._sign_.addAll(list);
        for(PSign e : list)
        {
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
        }
    }

    public TNumber getNumber()
    {
        return this._number_;
    }

    public void setNumber(TNumber node)
    {
        if(this._number_ != null)
        {
            this._number_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._number_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._sign_)
            + toString(this._number_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._sign_.remove(child))
        {
            return;
        }

        if(this._number_ == child)
        {
            this._number_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        for(ListIterator<PSign> i = this._sign_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PSign) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._number_ == oldChild)
        {
            setNumber((TNumber) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
