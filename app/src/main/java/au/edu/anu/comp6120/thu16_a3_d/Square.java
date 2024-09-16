package au.edu.anu.comp6120.thu16_a3_d;

/**
 * the basic element of map
 */
public class Square {
    enum SquareType{
        WALL, PATH, PLAYER, ENEMY, DESTINATION,BONUS, NONE;
    }

    SquareType type;
    char squareChar;
    Location Location;
    boolean canSeeEntity;

    public Square(char squareChar, Location Location) {
        switch (squareChar){
            case 'W' -> this.type = SquareType.WALL;
            case 'P' -> this.type = SquareType.PATH;
            default -> this.type = SquareType.NONE;
        }
        this.squareChar = squareChar;
        this.Location = Location;
        this.canSeeEntity = true;
    }

    public void setType(SquareType type) {
        this.type = type;
    }

    public void setSquareChar(char squareChar) {
        this.squareChar = squareChar;
    }

    public void setCanSeeEntity(boolean canSeeEntity) {
        this.canSeeEntity = canSeeEntity;
    }

    public boolean isCanSeeEntity() {
        return canSeeEntity;
    }

    public char getSquareChar() {
        return squareChar;
    }

    public SquareType getType() {
        return type;
    }
}
