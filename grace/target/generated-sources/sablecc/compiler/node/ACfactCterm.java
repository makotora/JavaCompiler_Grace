/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class ACfactCterm extends PCterm
{
    private PCfact _cfact_;

    public ACfactCterm()
    {
        // Constructor
    }

    public ACfactCterm(
        @SuppressWarnings("hiding") PCfact _cfact_)
    {
        // Constructor
        setCfact(_cfact_);

    }

    @Override
    public Object clone()
    {
        return new ACfactCterm(
            cloneNode(this._cfact_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACfactCterm(this);
    }

    public PCfact getCfact()
    {
        return this._cfact_;
    }

    public void setCfact(PCfact node)
    {
        if(this._cfact_ != null)
        {
            this._cfact_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._cfact_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._cfact_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._cfact_ == child)
        {
            this._cfact_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._cfact_ == oldChild)
        {
            setCfact((PCfact) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
