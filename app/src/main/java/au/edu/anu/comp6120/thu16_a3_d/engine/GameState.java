package au.edu.anu.comp6120.thu16_a3_d.engine;

import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.Entity;
import au.edu.anu.comp6120.thu16_a3_d.engine.level.Map;

import java.util.ArrayList;
import java.util.List;

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
    private final Map map = new Map();

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
        return "";
    }

    @Override
    public void deserialize(String data) {

    }


    @Override
    public void display() {
        this.map.display();
    }
}
