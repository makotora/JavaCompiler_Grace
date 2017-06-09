package compiler.Others;

/**
 * Created by mt on 6/9/17.
 */
public class TempVar {
    private String name;
    private String type;//A temp var can be: int char address
    private int bpOffset;

    public TempVar(String name, String type, int bpOffset) {

        this.name = name;
        this.type = type;
        this.bpOffset = bpOffset;
    }

    @Override
    public String toString() {
        return "TempVar{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", bpOffset=" + bpOffset +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getBpOffset() {
        return bpOffset;
    }

    public boolean isInt()
    {
        return (type.equals("int"));
    }

    public boolean isChar()
    {
        return (type.equals("char"));
    }

    public boolean isAddress()
    {
        return (type.equals("address"));
    }
}
