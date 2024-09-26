package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityType;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.Item;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.ItemType;

import java.util.HashMap;
import java.util.Map;

import static au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityType.ENEMY;

public class ItemGrid implements Grid {
    // ANSI escape code for green color
    String ANSI_GREEN = "\u001B[32m";

    // ANSI escape code to reset color to default
    String ANSI_RESET = "\u001B[0m";

    private final Item item;

    public ItemGrid(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public String serialize() {
        Map<String, String> data = new HashMap<>();
        data.put("item",item.serialize());
        data.put("type", item.getType().getName());
        return DataManager.GSON.toJson(data);
    }

    @Override
    public void deserialize(String data) {

    }

    @Override
    public void display() { System.out.print(ANSI_GREEN + "?" + ANSI_RESET); }

    @Override
    public String toString() {
        return "?";
    }
}
