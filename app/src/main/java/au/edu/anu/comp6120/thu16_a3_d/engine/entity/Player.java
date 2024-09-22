package au.edu.anu.comp6120.thu16_a3_d.engine.entity;

import au.edu.anu.comp6120.thu16_a3_d.utils.Location;

/**
 * Player class is responsible for player entity
 */
public class Player extends Entity {

    private static final int MAX_HEALTH = 100;

    public Player(Location location) {
        super(MAX_HEALTH, location, EntityType.PLAYER);
    }
}
