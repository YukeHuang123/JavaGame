package au.edu.anu.comp6120.thu16_a3_d.engine.entity;

import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_BLUE;
import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_RESET;

public class EntityNPC extends Entity implements IDisplayable {

    public EntityNPC(int maxHealth, int attack, Location location) {
        super(maxHealth, attack , location, EntityType.ENEMY);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void display() {
        int health = getHealth();
        String healthString = String.valueOf(health);
        if(health < 10) {
            healthString = "  " + health;
        } else if(health < 100) {
            healthString = " " + health ;
        } else {
            healthString = String.valueOf(health);
        }


        System.out.println(ANSI_BLUE + "NPC-----HP:" + healthString + "  Attack:" + getAttack() + ANSI_RESET);

    }
}
