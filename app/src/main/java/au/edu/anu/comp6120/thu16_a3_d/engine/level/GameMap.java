package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;

public class GameMap implements ISerializable, IDisplayable {

    private static final int WIDTH = 25;
    private static final int HEIGHT = 12;

    private Grid[][] grids;

    public GameMap() {
        this.grids = new Grid[WIDTH][HEIGHT];
        generate();
    }

    public void generate() {
        // 初始化所有格子为墙
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                grids[x][y] = new WallGrid();
            }
        }

        // 从一个随机点开始生成迷宫
        int startX = (int) (Math.random() * WIDTH);
        int startY = (int) (Math.random() * HEIGHT);
        generateMaze(startX, startY);
    }

    private void generateMaze(int x, int y) {
        grids[x][y] = new EmptyGrid();

        // 定义四个方向：上、右、下、左
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        
        // 随机打乱方向顺序
        for (int i = 0; i < directions.length; i++) {
            int j = (int) (Math.random() * directions.length);
            int[] temp = directions[i];
            directions[i] = directions[j];
            directions[j] = temp;
        }

        // 对每个方向进行深度优先搜索
        for (int[] dir : directions) {
            int newX = x + dir[0] * 2;
            int newY = y + dir[1] * 2;

            if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT && grids[newX][newY] instanceof WallGrid) {
                // 在当前格子和新格子之间打通一个通道
                grids[x + dir[0]][y + dir[1]] = new EmptyGrid();
                generateMaze(newX, newY);
            }
        }
    }

    @Override
    public String serialize() {
        return "";
    }

    @Override
    public void deserialize(String data) {

    }

    @Override
    public void display() {
        // Top border
        System.out.print("+");
        for (int x = 0; x < WIDTH; x++) {
            System.out.print("-");
        }
        System.out.println("+");

        // Map content with side borders
        for (int y = 0; y < HEIGHT; y++) {
            System.out.print("|");
            for (int x = 0; x < WIDTH; x++) {
                if (grids[x][y] != null) {
                    grids[x][y].display();
                } else {
                    System.out.print(" "); // Print space for null grids
                }
            }
            System.out.println("|");
        }

        // Bottom border
        System.out.print("+");
        for (int x = 0; x < WIDTH; x++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
