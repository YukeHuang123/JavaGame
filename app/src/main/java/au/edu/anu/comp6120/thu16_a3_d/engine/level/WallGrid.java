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
        // ANSI escape code for blue color
        String ANSI_BLUE = "\u001B[34m";
        // ANSI escape code to reset color
        String ANSI_RESET = "\u001B[0m";
        // Unicode character for a solid block
        String WALL_CHAR = "■";
        
        System.out.print(ANSI_BLUE + WALL_CHAR + ANSI_RESET);
    }
}
