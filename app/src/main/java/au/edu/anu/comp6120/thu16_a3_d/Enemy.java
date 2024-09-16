package au.edu.anu.comp6120.thu16_a3_d;

/**
 * NPC
 */
public class Enemy extends Role {
    static char ENEMY_CHAR = 'E';
    boolean canSee;
    int attack;

    public Enemy(String name, int maxHp, Location location, int attack) {
        super(name, maxHp, Type.ENEMY, location);
        this.canSee = false;
    }

    public void setCanSee(boolean canSee) {
        this.canSee = canSee;
    }

    public boolean isCanSee() {
        return canSee;
    }

    @Override
    public String toString() {
        return super.toString() + " Attack:"+ attack;
    }
}
