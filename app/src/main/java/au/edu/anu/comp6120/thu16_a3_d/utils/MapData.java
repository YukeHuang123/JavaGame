package au.edu.anu.comp6120.thu16_a3_d.utils;

import au.edu.anu.comp6120.thu16_a3_d.engine.level.EmptyGrid;
import au.edu.anu.comp6120.thu16_a3_d.engine.level.Grid;
import au.edu.anu.comp6120.thu16_a3_d.engine.level.WallGrid;

public class MapData {

    /**
     * # # # # # # # # # #
     * # P     #   #     #
     * #       #   #   E #
     * #     E #         #
     * # # # # #   # # # #
     * # E         E     #
     * #   # #   # # # # #
     * #     #   # # # # #
     * # L             X #
     * # # # # # # # # # #
     */

     public static final Grid[][] LEVEL_1 = {
        { new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid()},
        { new WallGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new WallGrid(), new EmptyGrid(), new WallGrid(), new EmptyGrid(), new WallGrid()},
        { new WallGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new WallGrid(), new EmptyGrid(), new WallGrid(), new EmptyGrid(), new WallGrid()},
        { new WallGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new WallGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new WallGrid()},
        { new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new EmptyGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid()},
        { new WallGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new WallGrid()},
        { new WallGrid(), new EmptyGrid(), new WallGrid(), new WallGrid(), new EmptyGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid()},
        { new WallGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new WallGrid(), new EmptyGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid()},
        { new WallGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new EmptyGrid(), new WallGrid()},
        { new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid(), new WallGrid()}
    };


    
}
