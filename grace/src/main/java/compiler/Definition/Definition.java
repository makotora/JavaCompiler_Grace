package compiler.Definition;

import java.util.List;

/**
 * Created by valyo95 on 5/5/2017.
 */
abstract public class Definition {
    protected String id;
    protected String type;
    
    public Definition(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
