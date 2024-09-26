package au.edu.anu.comp6120.thu16_a3_d.engine.item;

import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityType;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;

public enum ItemType {
        RECOVER, WEAPON;

    private Location location;
    private ItemType type;

    public static ItemType fromName(String name) {
        return ItemType.valueOf(name.toUpperCase());
    }
    /**
     * Get the name of the entity type
     * @return the name
     */
    public String getName() {
        return name().toLowerCase();
    }

    @Override
    public String toString() {
        return super.toString().substring(0, 1).toUpperCase();
    }
}

