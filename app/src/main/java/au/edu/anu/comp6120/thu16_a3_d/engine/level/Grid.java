package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;

/**
 * Represents a grid cell in the game.
 * All grid cells must implement serialization and display functionality.
 */
public interface Grid extends ISerializable, IDisplayable {

    /**
     * Check if the given position is solid
     * @return true if the position is solid
     */
    boolean isSolid();

}
