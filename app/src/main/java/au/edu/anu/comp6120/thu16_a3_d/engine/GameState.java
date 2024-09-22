package au.edu.anu.comp6120.thu16_a3_d.engine;

import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.Entity;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityType;
import au.edu.anu.comp6120.thu16_a3_d.engine.level.GameMap;

import java.util.ArrayList;
import java.util.List;

import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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

    private final List<Entity> entities;
    private final GameMap map = new GameMap();

    public GameState() {
        INSTANCE = this;
        entities = new ArrayList<>();
    }


    public List<Entity> getEntities() {
        return entities;
    }

    public void spawnEntity(Entity entity) {
        entities.add(entity);
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();
        
        // serialize entities
        JsonArray entitiesArray = new JsonArray();
        for (Entity entity : this.entities) {
            entitiesArray.add(JsonParser.parseString(entity.serialize()));
        }
        jsonObject.add("entities", entitiesArray);
        
        // serialize map
        jsonObject.add("map", JsonParser.parseString(map.serialize()));
        
        return jsonObject.toString();
    }

    @Override
    public void deserialize(String data) {
        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
        
        // deserialize entities
        JsonArray entitiesArray = jsonObject.getAsJsonArray("entities");
        for (JsonElement entityElement : entitiesArray) {
            Entity entity = new Entity(0, new Location(0, 0), EntityType.NONE);
            entity.deserialize(entityElement.toString());
            entities.add(entity);
        }

        // deserialize map
        map.deserialize(jsonObject.get("map").toString());
    }


    @Override
    public void display() {
        this.map.display();
    }
}
