package au.edu.anu.comp6120.thu16_a3_d.engine.level;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.Item;

import java.util.HashMap;
import java.util.Map;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_GREEN;
import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.ANSI_RESET;

/**
 * Represents an item grid in the game, which contains an item that can be collected.
 *
 * @author He Wang (u7837288)
 */
public class ItemGrid implements Grid {

    private final Item item;

    /**
     * Constructs an ItemGrid with the specified item.
     *
     * @param item the item to be placed in this grid.
     */
    public ItemGrid(Item item) {
        this.item = item;
    }

    /**
     * Gets the item contained in this grid.
     *
     * @return the item in this ItemGrid.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Checks if the grid is solid.
     *
     * @return true since an ItemGrid is solid.
     */
    @Override
    public boolean isSolid() {
        return true;
    }

    /**
     * Serializes the ItemGrid to a string representation.
     *
     * @return a JSON string representing the item and its type.
     */
    @Override
    public String serialize() {
        Map<String, String> data = new HashMap<>();
        data.put("item", item.serialize());
        data.put("type", item.getType().getName());
        return DataManager.GSON.toJson(data);
    }

    /**
     * Deserializes the ItemGrid from a string representation.
     * This implementation currently does not change state from the provided data.
     *
     * @param data the string data to deserialize.
     */
    @Override
    public void deserialize(String data) {

    }

    /**
     * Displays the item grid on the console using a green color.
     */
    @Override
    public void display() {
        System.out.print(ANSI_GREEN + "?" + ANSI_RESET);
    }

    /**
     * Returns a string representation of the ItemGrid.
     *
     * @return a string representing the ItemGrid, which is "?".
     */
    @Override
    public String toString() {
        return "?";
    }
}
