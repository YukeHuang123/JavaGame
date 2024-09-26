package au.edu.anu.comp6120.thu16_a3_d.engine.entity;

import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_BLUE;
import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_RESET;

/**
 * Player class is responsible for player entity
 */
public class EntityPlayer extends Entity implements IDisplayable {

    private static final int MAX_HEALTH = 100;

    public EntityPlayer(Location location) {
        super(MAX_HEALTH,0, location, EntityType.PLAYER);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void display() {
        int health = getHealth();
        String healthString;
        if(health < 10) {
            healthString = "  " + health;
        } else if(health < 100) {
            healthString = " " + health ;
        } else {
            healthString = String.valueOf(health);
        }

        int maxHealth = getMaxHealth();
        String maxHealthString = String.valueOf(health);
        if(health < 10) {
            maxHealthString = "  " + maxHealth;
        } else if(health < 100) {
            maxHealthString = " " + maxHealth ;
        } else {
            maxHealthString = String.valueOf(maxHealth);
        }

        System.out.print(ANSI_BLUE + "Player--HP:" + healthString + "(" + getMaxHealth()+")" + ANSI_RESET);
    }
}
