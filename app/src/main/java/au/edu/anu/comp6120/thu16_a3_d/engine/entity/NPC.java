package au.edu.anu.comp6120.thu16_a3_d.engine.entity;

import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;

public class NPC extends Entity implements IDisplayable {

    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";


    public NPC(int maxHealth, int attack, Location location) {
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
