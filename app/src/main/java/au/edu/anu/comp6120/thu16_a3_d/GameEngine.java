package au.edu.anu.comp6120.thu16_a3_d;

import java.util.ArrayList;
import java.util.List;

/**
 * game engine, store map, enemy, bonus, destination and some action like move, combat, drop, pick, resurrect
 */
public class GameEngine {
    final int CAN_SEE_DISTANCE = 10;    //once see will not hide
    Map map;
    Player player;
    List<Enemy> enemyList = new ArrayList<>();
    List<Bonus> bonusList = new ArrayList<>();
    Destination destination;
    Enemy combatEnemy = null;
    Bonus meetBonus = null;

    public GameEngine(String jsonString) {
        JsonRead jsonRead = new JsonRead(jsonString);
        map = jsonRead.getMapFromJson();
        player = jsonRead.getPlayerFromJson();
        enemyList = jsonRead.getEnemiesFromJson();
        destination = jsonRead.getDestinationFromJson();
        bonusList = jsonRead.getBonusesFromJson();
    }

    String move(Direction direction) {
        //if out of range, or towards the wall give the error String
        //if valid give set the map
        //set the visible enemy and bonus
        return "";
    }

    String useInventory(int index){
        //error message
        //recover Hp
        //remove from the inventory
        player.recoverMaterialList.remove(index);
        return "";
    }
    String drop(int index){
        //error message
        player.recoverMaterialList.remove(index);
        return "";
    }
    boolean judgeWin(){
        return player.location.equals(destination.location);
    }

    boolean judgeLoss(){
        return player.life == 0;
    }

    String getMeetEnemyString(){
        if(combatEnemy==null){
            return null;
        }
        else {
            return combatEnemy.toString();
        }
    }

    String getMeetBonusString(){
        if(meetBonus==null){
            return null;
        }
        else {
            return meetBonus.toString();
        }
    }

    void combat(){
        player.hp -= combatEnemy.attack;
        if(player.hp <= 0){
            player.isDead = true;
        } else {

        }
    }

    void pickBonus(boolean pickFlag){
        if(pickFlag){
            //add to bonus into the
            //case different type of bonus, different influence
        }

        //remove from the map
        //remove from the bonus List
        //move player onto the bonusLocation
    }

    void resurrect(){
        //recover the enemy hp
        //recover the play a put on the death location
    }

    boolean idPlayerDead(){
        return player.isDead;
    }
    boolean canResurrect(){
        return player.life > 0;
    }
    String showMap(){
        return map.toString();
    }
    String showPlayer(){
        return player.toString();
    }
}
