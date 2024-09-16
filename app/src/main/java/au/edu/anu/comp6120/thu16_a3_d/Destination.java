package au.edu.anu.comp6120.thu16_a3_d;

/**
 * the position that the player showed goto
 */
public class Destination {
    static char DESTINATION_CHAR = 'D';
    Location location;
    boolean canSee;

    public Destination(Location location) {
        this.location = location;
        this.canSee = false;
    }

    public void setCanSee(boolean canSee) {
        this.canSee = canSee;
    }

    public boolean isCanSee() {
        return canSee;
    }
}
