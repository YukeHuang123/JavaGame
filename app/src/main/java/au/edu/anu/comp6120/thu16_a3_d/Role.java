package au.edu.anu.comp6120.thu16_a3_d;

/**
 * the super class of enemy and player
 */
public class Role {
    public enum Type {
        ENEMY, PLAYER;
    }

    String name;
    int hp;
    int maxHp;
    Type type;
    Location location;
    boolean isDead;

    public Role(String name, int maxHp, Type type, Location location) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.type = type;
        this.location = location;
        this.isDead = false;
    }

    @Override
    public String toString() {
        return name + " " + hp + "/" + maxHp;
    }
}
