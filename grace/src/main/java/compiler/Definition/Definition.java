package compiler.Definition;

import java.util.List;

/**
 * Created by valyo95 on 5/5/2017.
 */
abstract public class Definition {
    protected String id;
    protected String type;
    protected int scopeNumber;

    public int getScopeNumber() {
        return scopeNumber;
    }

    public Definition(String id, String type, int scopeNumber) {
        this.id = id.trim();
        this.type = type.trim();
        this.scopeNumber = scopeNumber;

    }

    public String getId() {
        return id;
    }

    public String getType() {

        return type;
    }
}
