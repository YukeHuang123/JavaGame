package au.edu.anu.comp6120.thu16_a3_d.engine.entity;

import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.utils.Direction;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Entity class is the base class for all entities in the game
 */
public class Entity implements ISerializable {

    private int health;
    private int maxHealth;
    private Location location;
    private EntityType type;

    public Entity(int maxHealth, Location location, EntityType type) {
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.location = location;
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * damage the entity
     */
    public void damage(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    /**
     * heal the entity
     */
    public void heal(int heal) {
        this.health = Math.min(this.maxHealth, this.health + heal);
    }

    public Location getLocation() {
        return location;
    }

    /**
     * move the entity one step to the specific direction
     * if move success, update entity position
     * if move fail, do nothing
     *
     * @param direction move direction
     * @param step      move step
     */
    public void forward(Direction direction, int step) {

    }

    /**
     * move the entity one step to the specific direction
     * if move success, update entity position
     * if move fail, do nothing
     *
     * @param direction move direction
     */
    public void forward(Direction direction) {
        forward(direction, 1);
    }

    /**
     * move to the specific position
     * if move success, update entity position
     * if move fail, do nothing
     */
    public void moveTo(Location location) {

    }


    public EntityType getType() {
        return type;
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", this.type.getName());
        jsonObject.addProperty("location", this.getLocation().serialize());
        jsonObject.addProperty("health", this.getHealth());
        jsonObject.addProperty("max_health", this.maxHealth);
        return DataManager.GSON.toJson(jsonObject);
    }

    @Override
    public void deserialize(String data) {
        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
        String typeName = jsonObject.get("type").getAsString();
        this.type = EntityType.fromName(typeName);
        this.location = new Location(0, 0);
        this.location.deserialize(jsonObject.get("location").getAsString());
        this.health = jsonObject.get("health").getAsInt();
        this.maxHealth = jsonObject.get("max_health").getAsInt();    
    }
}
