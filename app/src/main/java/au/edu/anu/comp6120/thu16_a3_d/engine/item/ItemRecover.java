package au.edu.anu.comp6120.thu16_a3_d.engine.item;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_BLUE;
import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_RESET;

/**
 * Represents a recovery item that restores health or other attributes.
 * @author He Wang (u7837288)
 */
public class ItemRecover extends Item{

    int recover;

    /**
     * Constructs an ItemRecover with a specified location and recovery amount.
     * @param location the location of the item
     * @param recover the amount of recovery this item provides
     */
    public ItemRecover(Location location, int recover) {
        super(location, ItemType.RECOVER);
        this.recover = recover;
    }

    /**
     * Gets the recovery attributes of the item.
     * @return the recovery amount
     */
    @Override
    public int getAttributes() {
        return recover;
    }

    /**
     * Serializes the item into a JSON string representation.
     * @return a JSON string representing the item
     */
    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", type.getName());
        jsonObject.addProperty("location", location.serialize());
        jsonObject.addProperty("recover",this.recover);
        return DataManager.GSON.toJson(jsonObject);
    }

    /**
     * Deserializes the item from a JSON string representation.
     * @param data the JSON string representing the item
     */
    @Override
    public void deserialize(String data) {
        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
        String typeName = jsonObject.get("type").getAsString();
        this.type = ItemType.fromName(typeName);
        this.location = new Location();
        this.location.deserialize(jsonObject.get("location").getAsString());
        this.recover = jsonObject.get("recover").getAsInt();
    }

    /**
     * Displays the item's details in the console.
     * The output is styled with ANSI color codes.
     */
    @Override
    public void display() {
        String out = "Bonus: Recover" + "  recover:"+ recover;
        System.out.println(ANSI_BLUE + out + ANSI_RESET);
    }
}
