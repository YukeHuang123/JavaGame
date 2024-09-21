package au.edu.anu.comp6120.thu16_a3_d.data;

import au.edu.anu.comp6120.thu16_a3_d.engine.GameState;
import com.google.gson.Gson;

/**
 * DataManager class is responsible for saving and loading game state data
 */
public class DataManager {

    /**
     * Gson instance, singleton
     * We gonna use this instance to serialize and deserialize game state data
     * and all other data
     */
    public static final Gson GSON = new Gson();

    public DataManager() {
    }

    /**
     * Save the game state data to `save.json`
     */
    public void save(GameState gameState) {
        // TODO: implement saving game state data to `save.json`
    }

    /**
     * Load the game state data from `save.json`
     */
    public GameState load() {
        // TODO: implement loading game state data from `save.json`
        return new GameState();
    }
}
