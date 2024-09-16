package au.edu.anu.comp6120.thu16_a3_d;

import java.util.List;

/**
 * store the element of each square, also store the information of enemy and bonus flag in each square
 */
public class Map {
    static char WALL_CHAR = '#';
    static char PATH_CHAR = '.';


    int rowSize;
    int colSize;
    Square[][] squareArray;

    public Map(String mapString) {
        String[] strings = mapString.split("\n");

        rowSize = strings.length;
        colSize = strings[0].length();
        squareArray = new Square[rowSize][colSize];
        
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                squareArray[row][col] = new Square(strings[row].charAt(col), new Location(row,col));
            }
        }
    }

    static void createMapByJson(String jsonString){

    }

    public void setEnemyOnMap(Enemy enemy) {

        int row = enemy.location.row;
        int col = enemy.location.col;
        this.squareArray[row][col].setSquareChar(Enemy.ENEMY_CHAR);
        this.squareArray[row][col].setCanSeeEntity(enemy.isCanSee());
    }

    public void setPlayerOnMap(Player player) {
        int row = player.location.row;
        int col = player.location.col;

        this.squareArray[row][col].setSquareChar(Player.PLAYER_CHAR);
        this.squareArray[row][col].setCanSeeEntity(true);

    }

    public void setDestinationOnMap(Destination destination) {
        int row = destination.location.row;
        int col = destination.location.col;

        this.squareArray[row][col].setSquareChar(Destination.DESTINATION_CHAR);
        this.squareArray[row][col].setCanSeeEntity(destination.isCanSee());
    }

    public void setBonusOnMap(Bonus bonus) {
            int row = bonus.location.row;
            int col = bonus.location.col;

            this.squareArray[row][col].setSquareChar(Bonus.BONUS_CHAR);
            this.squareArray[row][col].setCanSeeEntity(true);

    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < rowSize; row++) {
            if(row!=0){
                builder.append("\n");
            }
            for (int col = 0; col < colSize; col++) {
                char ch = squareArray[row][col].isCanSeeEntity() ? squareArray[row][col].getSquareChar() : PATH_CHAR;
                builder.append(ch);
            }
        }
        return builder.toString();
    }
}
