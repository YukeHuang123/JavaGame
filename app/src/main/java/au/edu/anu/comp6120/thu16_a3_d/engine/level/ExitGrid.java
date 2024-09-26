package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_RED;
import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_RESET;

public class ExitGrid implements Grid{
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
        String WALL_CHAR = "@";
        System.out.print(ANSI_RED + WALL_CHAR + ANSI_RESET);
    }

    @Override
    public String toString() {
        return "@";
    }
}
