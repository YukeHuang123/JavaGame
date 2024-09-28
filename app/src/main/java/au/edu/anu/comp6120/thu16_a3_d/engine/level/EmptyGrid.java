package au.edu.anu.comp6120.thu16_a3_d.engine.level;

public class EmptyGrid implements Grid{

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public String serialize() {
        return "";
    }

    @Override
    public void deserialize(String data) {

    }

    @Override
    public void display() {
        System.out.print(" ");
    }

    @Override
    public String toString() {
        return " ";
    }
}
