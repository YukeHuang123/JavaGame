package au.edu.anu.comp6120.thu16_a3_d;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * use for read the configuration from json file
 */

public class JsonRead {
    JSONObject jsonObject;

    public JsonRead(String jsonString) {
        this.jsonObject = new JSONObject(jsonString);
    }

    public Map getMapFromJson(){
        return new Map(jsonObject.getString("map"));
    }

    public Player getPlayerFromJson(){
        JSONObject playerObject = jsonObject.getJSONObject("player");
        String name = playerObject.getString("name");
        int maxHp = playerObject.getInt("maxHp");
        int row = playerObject.getInt("row");
        int col = playerObject.getInt("col");
        int life = playerObject.getInt("life");
        Weapon weapon = Weapon.stringToWeapon(playerObject.getString("weapon"));

        return new Player(name, maxHp, new Location(row,col), weapon, life);
    }

    public List<Enemy> getEnemiesFromJson(){
        List<Enemy> enemies = new ArrayList<>();
        JSONArray enemiesArray = jsonObject.getJSONArray("enemy");

        for (int i = 0; i < enemiesArray.length(); i++) {
            JSONObject enemyObject = enemiesArray.getJSONObject(i);
            String name = enemyObject.getString("name");
            int maxHp = enemyObject.getInt("maxHp");
            int row = enemyObject.getInt("row");
            int col = enemyObject.getInt("col");
            int attack = enemyObject.getInt("attack");

            enemies.add(new Enemy(name, maxHp, new Location(row,col), attack));
        }
        return enemies;
    }

    public List<Bonus> getBonusesFromJson(){
        List<Bonus> bonuses = new ArrayList<>();
        JSONArray bonusArray = jsonObject.getJSONArray("bonus");

        for (int i = 0; i < bonusArray.length(); i++) {
            JSONObject bonusObject = bonusArray.getJSONObject(i);
            Bonus.Type bonusType = Bonus.Type.stringToBonusType(bonusObject.getString("type"));
            int row = bonusObject.getInt("row");
            int col = bonusObject.getInt("col");
            Bonus bonus;
            switch (bonusType) {
                case WEAPON -> {
                    Weapon weapon = Weapon.stringToWeapon(bonusObject.getString("weapon"));
                    bonus = new Bonus(Bonus.Type.WEAPON, weapon, new Location(row,col));
                }
                case RECOVER -> {
                    bonus = new Bonus(Bonus.Type.RECOVER, new Location(row,col));
                }
                case STRENGTHEN -> {
                    int strengthen = bonusObject.getInt("strengthen");
                    bonus = new Bonus(Bonus.Type.STRENGTHEN, strengthen, new Location(row,col));
                }
                case RECOVER_MATERIAL -> {
                    RecoverMaterial recoverMaterial = RecoverMaterial.stringToRecoverMaterial(bonusObject.getString("recoverMaterial"));
                    bonus = new Bonus(Bonus.Type.RECOVER_MATERIAL, recoverMaterial, new Location(row,col));
                }
                default -> {
                    bonus = null;
                }
            }
            bonuses.add(bonus);
        }
        return bonuses;
    }

    public Destination getDestinationFromJson(){
        JSONObject destinationObject = jsonObject.getJSONObject("destination");
        int row = destinationObject.getInt("row");
        int col = destinationObject.getInt("col");
        return new Destination(new Location(row,col));
    }
}
