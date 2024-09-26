package au.edu.anu.comp6120.thu16_a3_d.engine.item;

public enum ItemType {
        RECOVER, WEAPON;

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

