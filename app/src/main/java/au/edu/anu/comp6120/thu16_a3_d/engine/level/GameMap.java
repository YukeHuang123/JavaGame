package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GameMap implements ISerializable, IDisplayable {

    private static final int WIDTH = 35;
    private static final int HEIGHT = 12;

    private Grid[][] grids;

    public GameMap() {
        this.grids = new Grid[WIDTH][HEIGHT];
        generate();
    }

    public void generate() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                grids[x][y] = new WallGrid();
            }
        }

        int startX = (int) (Math.random() * WIDTH);
        int startY = (int) (Math.random() * HEIGHT);
        generateMaze(startX, startY);
    }

    private void generateMaze(int x, int y) {
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
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();
        JsonArray gridsArray = new JsonArray();

        for (int x = 0; x < WIDTH; x++) {
            JsonArray column = new JsonArray();
            for (int y = 0; y < HEIGHT; y++) {
                JsonObject gridObject = new JsonObject();
                gridObject.addProperty("type", grids[x][y].getClass().getSimpleName());
                column.add(gridObject);
            }
            gridsArray.add(column);
        }

        jsonObject.add("grids", gridsArray);
        return DataManager.GSON.toJson(jsonObject);
    }

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
                    // Add cases for other grid types if needed
                    default:
                        throw new IllegalArgumentException("Unknown grid type: " + gridType);
                }
            }
        }
    }

    @Override
    public void display() {
        // ANSI color codes
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        // Unicode box drawing characters
        final String TOP_LEFT = "┌";
        final String TOP_RIGHT = "┐";
        final String BOTTOM_LEFT = "└";
        final String BOTTOM_RIGHT = "┘";
        final String HORIZONTAL = "─";
        final String VERTICAL = "│";

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
}