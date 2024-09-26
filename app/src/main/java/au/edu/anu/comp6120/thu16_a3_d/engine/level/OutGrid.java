package au.edu.anu.comp6120.thu16_a3_d.engine.level;

public class OutGrid implements Grid{
    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public String serialize() {
        return "@";
    }

    @Override
    public void deserialize(String data) {

    }

    @Override
    public void display() {
        // ANSI escape code for blue color
        String ANSI_RED = "\u001B[31m";
        // ANSI escape code to reset color
        String ANSI_RESET = "\u001B[0m";
        // Unicode character for a solid block
        String WALL_CHAR = "@";

        System.out.print(ANSI_RED + WALL_CHAR + ANSI_RESET);
    }

    @Override
    public String toString() {
        return "@";
    }
}
