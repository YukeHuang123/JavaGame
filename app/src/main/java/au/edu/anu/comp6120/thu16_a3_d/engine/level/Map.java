package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;

public class Map implements ISerializable, IDisplayable {

    private static final int MAX_X = 10;
    private static final int MAX_Y = 10;

    private Grid[][] grids;

    public Map() {
        grids = new Grid[MAX_X][MAX_Y];
        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                grids[i][j] = new WallGrid();
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
        for (Grid[] row : grids) {
            for (Grid grid : row) {
                grid.display();
            }
            System.out.println();
        }
    }
}
