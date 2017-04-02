/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AThenStmt extends PThenStmt
{
    private TThen _then_;
    private PStmt _stmt_;

    public AThenStmt()
    {
        // Constructor
    }

    public AThenStmt(
        @SuppressWarnings("hiding") TThen _then_,
        @SuppressWarnings("hiding") PStmt _stmt_)
    {
        // Constructor
        setThen(_then_);

        setStmt(_stmt_);

    }

    @Override
    public Object clone()
    {
        return new AThenStmt(
            cloneNode(this._then_),
            cloneNode(this._stmt_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAThenStmt(this);
    }

    public TThen getThen()
    {
        return this._then_;
    }

    public void setThen(TThen node)
    {
        if(this._then_ != null)
        {
            this._then_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._then_ = node;
    }

    public PStmt getStmt()
    {
        return this._stmt_;
    }

    public void setStmt(PStmt node)
    {
        if(this._stmt_ != null)
        {
            this._stmt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._stmt_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._then_)
            + toString(this._stmt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._then_ == child)
        {
            this._then_ = null;
            return;
        }

        if(this._stmt_ == child)
        {
            this._stmt_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._then_ == oldChild)
        {
            setThen((TThen) newChild);
            return;
        }

        if(this._stmt_ == oldChild)
        {
            setStmt((PStmt) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}