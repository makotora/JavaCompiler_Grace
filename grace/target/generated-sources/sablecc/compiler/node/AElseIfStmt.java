/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AElseIfStmt extends PIfStmt
{
    private TIf _if_;
    private PCond _cond_;
    private TThen _then_;
    private PStmtWithElse _thenStmt_;
    private TElse _else_;
    private PStmt _elseStmt_;

    public AElseIfStmt()
    {
        // Constructor
    }

    public AElseIfStmt(
        @SuppressWarnings("hiding") TIf _if_,
        @SuppressWarnings("hiding") PCond _cond_,
        @SuppressWarnings("hiding") TThen _then_,
        @SuppressWarnings("hiding") PStmtWithElse _thenStmt_,
        @SuppressWarnings("hiding") TElse _else_,
        @SuppressWarnings("hiding") PStmt _elseStmt_)
    {
        // Constructor
        setIf(_if_);

        setCond(_cond_);

        setThen(_then_);

        setThenStmt(_thenStmt_);

        setElse(_else_);

        setElseStmt(_elseStmt_);

    }

    @Override
    public Object clone()
    {
        return new AElseIfStmt(
            cloneNode(this._if_),
            cloneNode(this._cond_),
            cloneNode(this._then_),
            cloneNode(this._thenStmt_),
            cloneNode(this._else_),
            cloneNode(this._elseStmt_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAElseIfStmt(this);
    }

    public TIf getIf()
    {
        return this._if_;
    }

    public void setIf(TIf node)
    {
        if(this._if_ != null)
        {
            this._if_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._if_ = node;
    }

    public PCond getCond()
    {
        return this._cond_;
    }

    public void setCond(PCond node)
    {
        if(this._cond_ != null)
        {
            this._cond_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._cond_ = node;
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

    public PStmtWithElse getThenStmt()
    {
        return this._thenStmt_;
    }

    public void setThenStmt(PStmtWithElse node)
    {
        if(this._thenStmt_ != null)
        {
            this._thenStmt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._thenStmt_ = node;
    }

    public TElse getElse()
    {
        return this._else_;
    }

    public void setElse(TElse node)
    {
        if(this._else_ != null)
        {
            this._else_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._else_ = node;
    }

    public PStmt getElseStmt()
    {
        return this._elseStmt_;
    }

    public void setElseStmt(PStmt node)
    {
        if(this._elseStmt_ != null)
        {
            this._elseStmt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._elseStmt_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._if_)
            + toString(this._cond_)
            + toString(this._then_)
            + toString(this._thenStmt_)
            + toString(this._else_)
            + toString(this._elseStmt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._if_ == child)
        {
            this._if_ = null;
            return;
        }

        if(this._cond_ == child)
        {
            this._cond_ = null;
            return;
        }

        if(this._then_ == child)
        {
            this._then_ = null;
            return;
        }

        if(this._thenStmt_ == child)
        {
            this._thenStmt_ = null;
            return;
        }

        if(this._else_ == child)
        {
            this._else_ = null;
            return;
        }

        if(this._elseStmt_ == child)
        {
            this._elseStmt_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._if_ == oldChild)
        {
            setIf((TIf) newChild);
            return;
        }

        if(this._cond_ == oldChild)
        {
            setCond((PCond) newChild);
            return;
        }

        if(this._then_ == oldChild)
        {
            setThen((TThen) newChild);
            return;
        }

        if(this._thenStmt_ == oldChild)
        {
            setThenStmt((PStmtWithElse) newChild);
            return;
        }

        if(this._else_ == oldChild)
        {
            setElse((TElse) newChild);
            return;
        }

        if(this._elseStmt_ == oldChild)
        {
            setElseStmt((PStmt) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
