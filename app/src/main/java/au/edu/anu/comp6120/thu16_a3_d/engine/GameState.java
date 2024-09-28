package au.edu.anu.comp6120.thu16_a3_d.engine;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.Entity;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityNPC;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityPlayer;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityType;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.*;
import au.edu.anu.comp6120.thu16_a3_d.engine.level.GameMap;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.*;

public class GameState implements ISerializable, IDisplayable {

    /**
     * singleton instance
     */
    private static GameState INSTANCE;

    /**
     * get the singleton instance
     * if the instance is null, create a new instance
     * @return the singleton instance
     */
    public static GameState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameState();
        }
        return INSTANCE;
    }

    private final List<Entity> entities = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final GameMap map = new GameMap();
    private final Inventory inventory = new Inventory();
    private int life;
    private EntityPlayer entityPlayer;
    private GameStatus gameStatus;
    private Item findBonus;
    private EntityNPC meetEntityNPC;

    public GameState() {
        INSTANCE = this;
        if(!DataManager.READ_CONFIG_FROM_FILE){
            generateEntity();
            generateItem();
            life = 2;
            initialize();
        }
    }

    public void initialize(){
        map.putOnEntities(entities);
        map.putOnItems(items);
        findPlayer();
        gameStatus = GameStatus.READY_MOVE;
    }
    public List<Entity> getEntities() {
        return entities;
    }

    public void spawnEntity(Entity entity) {
        entities.add(entity);
    }

    public void findPlayer(){
        for(Entity entity : entities){
            if(entity.getType() == EntityType.PLAYER){
                this.entityPlayer = (EntityPlayer) entity;
                return;
            }
        }
        System.out.println(ANSI_RED+ "no player" + ANSI_RESET);
    }

    public void movePlayer(int deltaX, int deltaY){
        int currentX = entityPlayer.getLocation().getLocationX();
        int currentY = entityPlayer.getLocation().getLocationY();
        int nextX = currentX + deltaX;
        int nextY = currentY + deltaY;

        //out of range
        if(!map.isTargetPositionCanMove(nextX, nextY)){
            System.out.println(ANSI_RED+ "invalid move" + ANSI_RESET);
            return;
        }

        //enemy can not overlap
        if(map.isEnemy(nextX,nextY)){
            gameStatus = GameStatus.MEET_NPC;
            meetEntityNPC = (EntityNPC)map.getEntity(nextX, nextY);

            System.out.println(ANSI_RED+ "Meet NPC!");
            System.out.println(ANSI_RED+ "NPC: I am very powerful, do you want to fight with me? " +   ANSI_RESET);
            return;
        } else if(map.isExit(nextX,nextY)) {
            gameStatus = GameStatus.WIN;
            //then clear the bonus grid and let it become empty
            map.setGridEmpty(nextX, nextY);

            System.out.println(ANSI_GREEN+ "Arrive the exit!" + ANSI_RESET);

        } else if (map.isBonus(nextX, nextY)){
            //bonus can overlap
            gameStatus = GameStatus.FIND_BONUS;
            //first get the bonus
            findBonus = map.getBonus(nextX,nextY);
            //then clear the bonus grid and let it become empty
            map.setGridEmpty(nextX, nextY);
            System.out.println(ANSI_RED+ "Find Bonus! -- " + findBonus + ANSI_RESET);
        }

        map.exchangeGrid(currentX, currentY, nextX, nextY);
        entityPlayer.getLocation().setLocationX(nextX);
        entityPlayer.getLocation().setLocationY(nextY);
    }

    public void fetchBonus(){
        if(findBonus == null){
            return;
        }
        boolean isAddSuccessful = false;

        if(findBonus instanceof ItemWeapon){
            isAddSuccessful = inventory.addWeapon((ItemWeapon) findBonus);
        } else if(findBonus instanceof ItemRecover){
            isAddSuccessful = inventory.addRecover((ItemRecover) findBonus);
        }

        if(isAddSuccessful){
            gameStatus = GameStatus.READY_MOVE;
        }
    }

    public void dropBonus(){
        gameStatus = GameStatus.READY_MOVE;
    }

    public void chooseToFightNPC(){
        entityPlayer.setPreFightHealth();
        meetEntityNPC.setPreFightHealth();
        gameStatus = GameStatus.FIGHTING;
    }
    public void chooseToRunAwayNPC(){
        gameStatus = GameStatus.READY_MOVE;
    }

    public void fightNPC(int weaponIndex){
        ItemWeapon itemWeapon = inventory.getWeapon(weaponIndex);
        meetEntityNPC.damage(itemWeapon.getAttributes());

        if(meetEntityNPC.isDied()){
            System.out.println(ANSI_RED + "NPC: I can't believe you can beat me!" + ANSI_RESET);
            Location location = meetEntityNPC.getLocation();
            //clear the NPC grid
            map.setGridEmpty(location.getLocationX(), location.getLocationY());

            gameStatus = GameStatus.READY_MOVE;
            return;
        }

        //first we fight NPC first, if NPC died we will not get the attack
        entityPlayer.damage(meetEntityNPC.getAttack());
        if(entityPlayer.isDied()){
            System.out.println(ANSI_RED + "NPC: You can't beat me, you are a looser!" + ANSI_RESET);
            life--;
            if(life == 0){
                System.out.println(ANSI_RED + "You have no life to resurrect, you lose the game!" + ANSI_RESET);
                gameStatus = GameStatus.LOSS;
            } else {
                System.out.println(ANSI_BLUE + "You resurrected!");
                meetEntityNPC.recover();
                entityPlayer.recover();
                gameStatus = GameStatus.READY_MOVE;
            }
        }
    }

    public void removeInventory(ItemType type, int index){
        switch (type){
            case WEAPON -> inventory.removeWeapon(index);
            case RECOVER -> inventory.removeRecover(index);
        }
    }

    public void userRecover(int index){
        ItemRecover itemRecover = inventory.removeRecover(index);
        if(itemRecover != null){
            entityPlayer.heal(itemRecover.getAttributes());
        }
    }


    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();
        // serialize life
        jsonObject.addProperty("life", 10);

        // serialize entities
        JsonArray entitiesArray = new JsonArray();
        for (Entity entity : this.entities) {
            entitiesArray.add(JsonParser.parseString(entity.serialize()));
        }
        jsonObject.add("entities", entitiesArray);

        JsonArray itemsArray = new JsonArray();
        for (Item item : this.items) {
            itemsArray.add(JsonParser.parseString(item.serialize()));
        }
        jsonObject.add("items", itemsArray);

        // serialize inventory
        jsonObject.add("inventory", JsonParser.parseString(inventory.serialize()));
        
        // serialize map
        jsonObject.add("map", JsonParser.parseString(map.serialize()));

        return jsonObject.toString();
    }

    @Override
    public void deserialize(String data) {
        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
        // deserialize life
        life = jsonObject.get("life").getAsInt();

        // deserialize entities
        JsonArray entitiesArray = jsonObject.getAsJsonArray("entities");
        for (JsonElement entityElement : entitiesArray) {
            JsonObject entityObject = entityElement.getAsJsonObject();
            if(entityObject.get("type").getAsString().equals(EntityType.PLAYER.getName()) ){
                EntityPlayer entityPlayer = new EntityPlayer(new Location());
                entityPlayer.deserialize(entityElement.toString());
                entities.add(entityPlayer);
            } else if(entityObject.get("type").getAsString().equals(EntityType.ENEMY.getName()) ) {
                EntityNPC enemy = new EntityNPC(0, 0, new Location());
                enemy.deserialize(entityElement.toString());
                entities.add(enemy);
            } else {
                Entity entity = new Entity(0,0, new Location(), EntityType.NONE);
                entity.deserialize(entityElement.toString());
                entities.add(entity);
            }
        }

        // deserialize items
        JsonArray itemsArray = jsonObject.getAsJsonArray("items");
        for(JsonElement itemElement : itemsArray){
            JsonObject itemObject = itemElement.getAsJsonObject();
            if(itemObject.get("type").getAsString().equals(ItemType.WEAPON.getName()) ){
                ItemWeapon itemWeapon = new ItemWeapon(new Location(), 0);
                itemWeapon.deserialize(itemElement.toString());
                items.add(itemWeapon);
            } else if(itemObject.get("type").getAsString().equals(ItemType.RECOVER.getName()) ) {
                ItemRecover itemRecover = new ItemRecover(new Location(), 0);
                itemRecover.deserialize(itemElement.toString());
                items.add(itemRecover);
            }
        }

        // deserialize map
        map.deserialize(jsonObject.get("map").toString());

        //deserialize inventory
        inventory.deserialize(jsonObject.get("inventory").toString());
    }


    @Override
    public void display() {
        if(gameStatus == GameStatus.READY_MOVE || gameStatus == GameStatus.WIN){
            this.map.display();
        }
        if(gameStatus == GameStatus.FIGHTING){
            meetEntityNPC.display();
        }

        if(gameStatus == GameStatus.FIND_BONUS){
            findBonus.display();
        }

        // debug
        // print current work directory
        System.out.println(ANSI_BLUE + "Current work directory: " + System.getProperty("user.dir") + ANSI_RESET);


        entityPlayer.display();
        System.out.println(ANSI_BLUE + "   life: " + life + ANSI_RESET);


        this.inventory.display();
    }

    void generateEntity(){
        entities.add(new EntityNPC(0, 0, new Location(3,1)));
        entities.add(new EntityNPC(0, 0, new Location(8,2)));
        entities.add(new EntityPlayer(new Location(20,6)));
    }

    void generateItem(){
        items.add(new ItemWeapon(new Location(10,10), 10));
        items.add(new ItemRecover(new Location(15,4), 220));
    }

    @Override
    public String toString() {
        return "GameState{" +
                "\nlife: " + life +
                "\nmap:\n" + map +
                "\nentities:" + entities +
                "\nitems:" + items +
                "\ninventory:" + inventory;
    }
}
