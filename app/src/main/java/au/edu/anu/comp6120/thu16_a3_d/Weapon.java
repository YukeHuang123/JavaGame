package au.edu.anu.comp6120.thu16_a3_d;

/**
 * weapon is the enum that has different attack and rarity
 */
public enum Weapon {
    W1(1, 10),
    W2(2, 20),
    W3(3, 30),
    W4(4, 40),
    W5(5, 50);

    final int Attack;
    final int rarity;

    Weapon(int rarity,int attack) {
        this.Attack = attack;
        this.rarity = rarity;
    }

    static public Weapon stringToWeapon(String string) {
        switch (string) {
            case "W1" -> { return W1; }
            case "W2" -> { return W2; }
            case "W3" -> { return W3; }
            case "W4" -> { return W4; }
            case "W5" -> { return W5; }
            default -> {return W1;}
        }
    }

    @Override
    public String toString() {
        return "weapon" + this.toString() + "(" + this.rarity + ")" + " Attack:"+ this.Attack;
    }
}
