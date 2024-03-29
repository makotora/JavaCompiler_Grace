/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import java.util.*;
import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AFuncDecl extends PFuncDecl
{
    private TId _id_;
    private final LinkedList<PPar> _par_ = new LinkedList<PPar>();
    private PRetType _retType_;

    public AFuncDecl()
    {
        // Constructor
    }

    public AFuncDecl(
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") List<PPar> _par_,
        @SuppressWarnings("hiding") PRetType _retType_)
    {
        // Constructor
        setId(_id_);

        setPar(_par_);

        setRetType(_retType_);

    }

    @Override
    public Object clone()
    {
        return new AFuncDecl(
            cloneNode(this._id_),
            cloneList(this._par_),
            cloneNode(this._retType_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFuncDecl(this);
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public LinkedList<PPar> getPar()
    {
        return this._par_;
    }

    public void setPar(List<PPar> list)
    {
        this._par_.clear();
        this._par_.addAll(list);
        for(PPar e : list)
        {
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
        }
    }

    public PRetType getRetType()
    {
        return this._retType_;
    }

    public void setRetType(PRetType node)
    {
        if(this._retType_ != null)
        {
            this._retType_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._retType_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._id_)
            + toString(this._par_)
            + toString(this._retType_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._par_.remove(child))
        {
            return;
        }

        if(this._retType_ == child)
        {
            this._retType_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        for(ListIterator<PPar> i = this._par_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PPar) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._retType_ == oldChild)
        {
            setRetType((PRetType) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
