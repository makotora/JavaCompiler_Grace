/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import java.util.*;
import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AArrayBrackList extends PArrayBrackList
{
    private TLbrack _lbrack_;
    private TNumber _number_;
    private TRbrack _rbrack_;
    private final LinkedList<PArrayBrackTail> _arrayBrackTail_ = new LinkedList<PArrayBrackTail>();

    public AArrayBrackList()
    {
        // Constructor
    }

    public AArrayBrackList(
        @SuppressWarnings("hiding") TLbrack _lbrack_,
        @SuppressWarnings("hiding") TNumber _number_,
        @SuppressWarnings("hiding") TRbrack _rbrack_,
        @SuppressWarnings("hiding") List<PArrayBrackTail> _arrayBrackTail_)
    {
        // Constructor
        setLbrack(_lbrack_);

        setNumber(_number_);

        setRbrack(_rbrack_);

        setArrayBrackTail(_arrayBrackTail_);

    }

    @Override
    public Object clone()
    {
        return new AArrayBrackList(
            cloneNode(this._lbrack_),
            cloneNode(this._number_),
            cloneNode(this._rbrack_),
            cloneList(this._arrayBrackTail_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAArrayBrackList(this);
    }

    public TLbrack getLbrack()
    {
        return this._lbrack_;
    }

    public void setLbrack(TLbrack node)
    {
        if(this._lbrack_ != null)
        {
            this._lbrack_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lbrack_ = node;
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

    public TRbrack getRbrack()
    {
        return this._rbrack_;
    }

    public void setRbrack(TRbrack node)
    {
        if(this._rbrack_ != null)
        {
            this._rbrack_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rbrack_ = node;
    }

    public LinkedList<PArrayBrackTail> getArrayBrackTail()
    {
        return this._arrayBrackTail_;
    }

    public void setArrayBrackTail(List<PArrayBrackTail> list)
    {
        this._arrayBrackTail_.clear();
        this._arrayBrackTail_.addAll(list);
        for(PArrayBrackTail e : list)
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
            + toString(this._lbrack_)
            + toString(this._number_)
            + toString(this._rbrack_)
            + toString(this._arrayBrackTail_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lbrack_ == child)
        {
            this._lbrack_ = null;
            return;
        }

        if(this._number_ == child)
        {
            this._number_ = null;
            return;
        }

        if(this._rbrack_ == child)
        {
            this._rbrack_ = null;
            return;
        }

        if(this._arrayBrackTail_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lbrack_ == oldChild)
        {
            setLbrack((TLbrack) newChild);
            return;
        }

        if(this._number_ == oldChild)
        {
            setNumber((TNumber) newChild);
            return;
        }

        if(this._rbrack_ == oldChild)
        {
            setRbrack((TRbrack) newChild);
            return;
        }

        for(ListIterator<PArrayBrackTail> i = this._arrayBrackTail_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PArrayBrackTail) newChild);
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
