package au.edu.anu.comp6120.thu16_a3_d;

/**
 * there are 4 types of bonus,
 * give weapon, recover all HP, strengthen maxHp, give material that can recover part of HP
 */
public class Bonus {
    enum Type{
        WEAPON, RECOVER, STRENGTHEN, RECOVER_MATERIAL;
        static public Type stringToBonusType(String string) {
            switch (string) {
                case "WEAPON" -> { return WEAPON; }
                case "RECOVER" -> { return RECOVER; }
                case "STRENGTHEN" -> { return STRENGTHEN; }
                case "RECOVER_MATERIAL" -> { return RECOVER_MATERIAL; }
                default -> {return WEAPON;}
            }
        }

    }

    static char BONUS_CHAR = '?';
    Weapon weapon;
    int strengthen;
    Type bonusType;
    RecoverMaterial  recoverMaterial;

    Location location;

    public Bonus(Type bonusType, Weapon weapon, Location location) {
        this.bonusType = bonusType;
        this.weapon = weapon;
        this.location = location;
    }

    public Bonus(Type bonusType, int strengthen, Location location) {
        this.bonusType = bonusType;
        this.strengthen = strengthen;
        this.location = location;
    }

    public Bonus(Type bonusType, RecoverMaterial recoveryMaterial, Location location) {
        this.bonusType = bonusType;
        this.recoverMaterial = recoveryMaterial;
        this.location = location;
    }

    public Bonus(Type bonusType, Location location) {
        this.bonusType = bonusType;
        this.location = location;
    }

    @Override
    public String toString() {
        String s;
        switch (bonusType) {
            case RECOVER -> s = "fill all hp";
            case RECOVER_MATERIAL -> s = recoverMaterial.toString();
            case STRENGTHEN -> s = "strengthen:" + strengthen;
            case WEAPON -> s = weapon.toString();
            default -> s = "";
        }
        return s;
    }
}
