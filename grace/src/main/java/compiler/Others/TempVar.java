package compiler.Others;

/**
 * Created by mt on 6/9/17.
 */
public class TempVar {
    private String name;
    private String type;//A temp var can be: int char address

    private int size;
    private int bpOffset;

    public TempVar(String name, String type, int size, int bpOffset) {

        this.name = name;
        this.type = type;
        this.size = size;

        this.bpOffset = bpOffset;
    }

    public int getSize() {
        return size;
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
