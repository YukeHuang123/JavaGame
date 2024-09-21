package au.edu.anu.comp6120.thu16_a3_d.engine.level;

public class WallGrid implements Grid{

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public String serialize() {
        return "W";
    }

    @Override
    public void deserialize(String data) {

    }

    @Override
    public void display() {
        System.out.print("X");
    }
}
