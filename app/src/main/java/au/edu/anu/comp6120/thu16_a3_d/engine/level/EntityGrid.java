package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.Entity;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public abstract class EntityGrid implements Grid {

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
        System.out.print(entity.getType().getDisplayName());
    }

}
