package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_BLUE;
import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_RESET;

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
        // Unicode character for a solid block
        String WALL_CHAR = "#";
        System.out.print(ANSI_BLUE + WALL_CHAR + ANSI_RESET);
    }

    @Override
    public String toString() {
        return "#";
    }
}
