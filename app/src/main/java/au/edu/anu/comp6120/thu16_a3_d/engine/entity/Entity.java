package au.edu.anu.comp6120.thu16_a3_d.engine.entity;

import au.edu.anu.comp6120.thu16_a3_d.utils.Direction;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;

/**
 * Entity class is the base class for all entities in the game
 */
public class Entity {

    private int health;
    private final int maxHealth;
    private final Location location;

    public Entity(int maxHealth, Location location) {
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.location = location;
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

}
