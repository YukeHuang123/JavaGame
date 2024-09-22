package au.edu.anu.comp6120.thu16_a3_d.engine.entity;

/**
 * Entity type enum
 */
public enum EntityType {
    PLAYER,
    ENEMY,
    ITEM;

    /**
     * Get the name of the entity type
     * @return the name
     */
    public String getName() {
        return name().toLowerCase();
    }

    /**
     * Get the display name of the entity type
     * @return the display name
     */
    public String getDisplayName() {
        return name().substring(0, 1).toUpperCase();
    }
}
