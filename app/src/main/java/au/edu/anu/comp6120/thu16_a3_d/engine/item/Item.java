package au.edu.anu.comp6120.thu16_a3_d.engine.item;

import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityType;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;

public abstract class Item implements ISerializable, IDisplayable {
    ItemType type;
    Location location;

    public Item(Location location, ItemType type) {
        this.location = location;
        this.type = type;
    }

    public ItemType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public abstract int getAttributes();

    @Override
    public String toString() {
        return  "type:" + type + ((type==ItemType.WEAPON)?("  attack:"):("  recover:")) + getAttributes();
    }
}
