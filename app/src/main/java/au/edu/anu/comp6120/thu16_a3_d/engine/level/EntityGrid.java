package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.Entity;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityType;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class EntityGrid implements Grid {
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_RESET = "\u001B[0m";

    private final Entity entity;

    public EntityGrid(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public String serialize() {
        Map<String, String> data = new HashMap<>();
        data.put("entity", entity.serialize());
        data.put("type", entity.getType().getName());
        return DataManager.GSON.toJson(data);
    }

    @Override
    public void deserialize(String data) {
        Map<String, String> map = DataManager.GSON.fromJson(data, new TypeToken<Map<String, String>>(){}.getType());
        entity.deserialize(map.get("entity"));
    }

    @Override
    public void display() {

        if(entity.getType() == EntityType.ENEMY) {
            System.out.print(ANSI_YELLOW);
        }
        System.out.print(entity.getType().getDisplayName());
        //reset the color
        System.out.print(ANSI_RESET);
    }

    @Override
    public String toString() {
        EntityType type = entity.getType();
        switch(type){
            case ENEMY -> {return "E";}
            case PLAYER -> {return "P";}
            case NONE -> {return " ";}
            default -> {return " ";}
        }
    }
}
