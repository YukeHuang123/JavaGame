package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;

public interface Grid extends ISerializable, IDisplayable {

    /**
     * Check if the given position is solid
     * @return true if the position is solid
     */
    boolean isSolid();

}
