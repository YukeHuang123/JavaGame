package au.edu.anu.comp6120.thu16_a3_d;

import java.util.ArrayList;
import java.util.List;

/**
 * store the information of player, the inventory system is the recoverMaterial
 * it also store the weapon, life
 */
public class Player extends Role {
    static char PLAYER_CHAR = 'P';
    Weapon weapon;
    List<RecoverMaterial> recoverMaterialList = new ArrayList<RecoverMaterial>();
    int life;

    public Player(String name, int maxHp, Location location, Weapon weapon, int life) {
        super(name, maxHp, Type.PLAYER, location);
        this.life = life;
    }

    @Override
    public String toString() {
        return super.toString() + " weapon:"+ weapon.toString() + "\n" +" life:" + life + "\n"+recoverMaterialList;
    }
}
