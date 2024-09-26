package au.edu.anu.comp6120.thu16_a3_d.engine.item;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_BLUE;
import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_RESET;

public class Weapon extends Item {

    int attack;

    public Weapon(Location location, int attack) {
        super(location, ItemType.WEAPON);
        this.attack = attack;
    }

    @Override
    public int getAttributes() {
        return attack;
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", type.getName());
        jsonObject.addProperty("location", location.serialize());
        jsonObject.addProperty("attack", this.attack);
        return DataManager.GSON.toJson(jsonObject);
    }

    @Override
    public void deserialize(String data) {
        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
        String typeName = jsonObject.get("type").getAsString();
        this.type = ItemType.fromName(typeName);
        this.location = new Location();
        this.location.deserialize(jsonObject.get("location").getAsString());
        this.attack = jsonObject.get("attack").getAsInt();
    }

    @Override
    public void display() {
        String out = "Bonus: Weapon" + "  attack:" + attack;
        System.out.println(ANSI_BLUE + out + ANSI_RESET);
    }
}
