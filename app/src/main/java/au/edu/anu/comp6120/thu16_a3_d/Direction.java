package au.edu.anu.comp6120.thu16_a3_d;

/**
 * move direction
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;
    static public Direction stringToDirection(String string) {
        switch (string) {
            case "W" -> {
                return UP;
            }
            case "A" -> {
                return DOWN;
            }
            case "S" -> {
                return LEFT;
            }
            case "D" -> {
                return RIGHT;
            }
            default -> {
                return UP;
            }
        }
    }
}
