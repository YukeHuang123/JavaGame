package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.Entity;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.NPC;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.Item;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.Recover;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.Weapon;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import au.edu.anu.comp6120.thu16_a3_d.utils.MapData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.List;

public class GameMap implements ISerializable, IDisplayable {

    private static final int WIDTH = 35;
    private static final int HEIGHT = 12;

    private Grid[][] grids;
    private Location exitLocation;
    private Location startLocation;

    public GameMap() {
        this.grids = new Grid[WIDTH][HEIGHT];

        if(!DataManager.READ_CONFIG_FROM_FILE){
            generate();
        }
    }

    public void generate() {
        startLocation = new Location();
        exitLocation = new Location();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                grids[x][y] = new WallGrid();
            }
        }

        int startX = (int) (Math.random() * WIDTH);
        int startY = (int) (Math.random() * HEIGHT);

        generateMaze(startX, startY);
    }

    /**
     * randomly generate the map
     * @param x
     * @param y
     */
    private void generateMaze(int x, int y) {
        if(!startLocation.hasSetLocation()){
            startLocation.setLocationX(x);
            startLocation.setLocationY(y);
        }


        grids[x][y] = new EmptyGrid();

        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        
        for (int i = 0; i < directions.length; i++) {
            int j = (int) (Math.random() * directions.length);
            int[] temp = directions[i];
            directions[i] = directions[j];
            directions[j] = temp;
        }

        for (int[] dir : directions) {
            int newX = x + dir[0] * 2;
            int newY = y + dir[1] * 2;

            if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT && grids[newX][newY] instanceof WallGrid) {
                grids[x + dir[0]][y + dir[1]] = new EmptyGrid();
                generateMaze(newX, newY);
            }
        }


        if(!exitLocation.hasSetLocation()){
            exitLocation.setLocationX(x);
            exitLocation.setLocationY(y);
            grids[x][y] = new OutGrid();
        }
    }

    public void putOnEntities(List<Entity> entities){
        for (Entity entity : entities) {
            Location location = entity.getLocation();
            int X = location.getLocationX();
            int Y = location.getLocationY();

            grids[X][Y] = new EntityGrid(entity);
        }
    }

    public void putOnItems(List<Item> items){
        for (Item item : items) {
            Location location = item.getLocation();
            int X = location.getLocationX();
            int Y = location.getLocationY();

            grids[X][Y] = new ItemGrid(item);
        }
    }

    /**
     * judge whether in bonus
     * @param x
     * @param y
     * @return
     */
    public boolean isInBounds(int x, int y){
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    public boolean isTargetPositionCanMove(int x, int y){
        if(!isInBounds(x ,y)){
            return false;
        }
        if(grids[x][y] instanceof WallGrid){
            return false;
        }

        return true;
    }

    public boolean isExit(int x, int y){
        return grids[x][y] instanceof OutGrid;
    }

    public boolean isEnemy(int x, int y){
        return grids[x][y] instanceof EntityGrid;
    }

    public boolean isBonus(int x, int y){
        return grids[x][y] instanceof ItemGrid;
    }

    public Entity getEntity(int x, int y){
        if(grids[x][y] instanceof EntityGrid){
            return ((EntityGrid) grids[x][y]).getEntity();
        }
        return null;
    }


    public Item getBonus(int x, int y){
        if(grids[x][y] instanceof ItemGrid){
            return ((ItemGrid) grids[x][y]).getItem();
        }
        return null;
    }

    public void exchangeGrid(int firstX, int firstY, int secondX, int secondY){
        if(!isInBounds(firstX, firstY) || !isInBounds(secondX, secondY)){
            return;
        }

        Grid temp = grids[firstX][firstY];
        grids[firstX][firstY] = grids[secondX][secondY];
        grids[secondX][secondY] = temp;
    }

    public void setGridEmpty(int X, int Y){
        if(!isInBounds(X,Y)){
            return;
        }
        grids[X][Y] = new EmptyGrid();
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();
        JsonArray gridsArray = new JsonArray();

        jsonObject.addProperty("start", this.startLocation.serialize());
        jsonObject.addProperty("exit", this.exitLocation.serialize());


        for (int x = 0; x < WIDTH; x++) {
            JsonArray column = new JsonArray();
            for (int y = 0; y < HEIGHT; y++) {
                JsonObject gridObject = new JsonObject();

                //entity and item will not save in the map--data from other source
                if(grids[x][y] instanceof EntityGrid || grids[x][y] instanceof ItemGrid || grids[x][y] instanceof OutGrid){
                    gridObject.addProperty("type", EmptyGrid.class.getSimpleName());
                } else {
                    gridObject.addProperty("type", grids[x][y].getClass().getSimpleName());
                }

                column.add(gridObject);
            }
            gridsArray.add(column);
        }


        jsonObject.add("grids", gridsArray);
        return DataManager.GSON.toJson(jsonObject);
    }

    /**
     * read json from the string
     * @param data json string
     */
    @Override
    public void deserialize(String data) {
        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();

        JsonArray gridsArray = jsonObject.getAsJsonArray("grids");


        for (int x = 0; x < WIDTH; x++) {
            JsonArray column = gridsArray.get(x).getAsJsonArray();
            for (int y = 0; y < HEIGHT; y++) {
                JsonObject gridObject = column.get(y).getAsJsonObject();
                String gridType = gridObject.get("type").getAsString();
                
                switch (gridType) {
                    case "EmptyGrid":
                        grids[x][y] = new EmptyGrid();
                        break;
                    case "WallGrid":
                        grids[x][y] = new WallGrid();
                        break;

                        //first for empty grid
                    case "OutGrid":
                    case "EntityGrid":
                    case "ItemGrid":
                        grids[x][y] = new EmptyGrid();
                        continue;

                    // other grid will use entity or item to put on the map
                    default:
                        throw new IllegalArgumentException("Unknown grid type: " + gridType);
                }
            }
        }

        //start Location will not show, just put the player on it
        this.startLocation = new Location();
        this.startLocation.deserialize(jsonObject.get("start").getAsString());

        this.exitLocation = new Location();
        this.exitLocation.deserialize(jsonObject.get("exit").getAsString());
        grids[exitLocation.getLocationX()][exitLocation.getLocationY()] = new OutGrid();
    }
    //TODO put in correct array

    @Override
    public void display() {
        // ANSI color codes
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        // Unicode box drawing characters
        final String TOP_LEFT = "+";
        final String TOP_RIGHT = "+";
        final String BOTTOM_LEFT = "+";
        final String BOTTOM_RIGHT = "+";
        final String HORIZONTAL = "-";
        final String VERTICAL = "|";

        // Top border
        System.out.print(ANSI_YELLOW + TOP_LEFT);
        for (int x = 0; x < WIDTH; x++) {
            System.out.print(HORIZONTAL);
        }
        System.out.println(TOP_RIGHT + ANSI_RESET);

        // Map content with side borders
        for (int y = 0; y < HEIGHT; y++) {
            System.out.print(ANSI_YELLOW + VERTICAL + ANSI_RESET);
            for (int x = 0; x < WIDTH; x++) {
                if (grids[x][y] != null) {
                    grids[x][y].display();
                } else {
                    System.out.print(" "); // Print space for null grids
                }
            }
            System.out.println(ANSI_YELLOW + VERTICAL + ANSI_RESET);
        }

        // Bottom border
        System.out.print(ANSI_YELLOW + BOTTOM_LEFT);
        for (int x = 0; x < WIDTH; x++) {
            System.out.print(HORIZONTAL);
        }
        System.out.println(BOTTOM_RIGHT + ANSI_RESET);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("start:" + startLocation+ "\n");
        stringBuilder.append("exit:" + exitLocation+ "\n");
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (grids[x][y] != null) {
                    stringBuilder.append(grids[x][y]);
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
