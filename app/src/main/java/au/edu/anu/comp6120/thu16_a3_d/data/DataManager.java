package au.edu.anu.comp6120.thu16_a3_d.data;

import au.edu.anu.comp6120.thu16_a3_d.engine.GameState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * DataManager class is responsible for saving and loading game state data
 */
public class DataManager {

    public static boolean READ_CONFIG_FROM_FILE = true;
    public static boolean PRINT_SAVE_JASON = false;

    private static final String SAVE_FILE = "save.json";
    private static DataManager instance;


    /**
     * Gson instance, singleton
     * We gonna use this instance to serialize and deserialize game state dataq
     * and all other data
     */
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private DataManager() {
        // Private constructor to prevent instantiation
    }

    /**
     * Get the singleton instance of DataManager
     */
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    /**
     *
     * Save the game state data to `save.json` in a formatted JSON
     */
    public void save(GameState gameState) {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            String serializedGameState = gameState.serialize();
            JsonElement jsonElement = JsonParser.parseString(serializedGameState);
            String formattedJson = GSON.toJson(jsonElement);

            System.out.println("Saving game state: " + ((PRINT_SAVE_JASON)? formattedJson : ""));

            writer.write(formattedJson);
        } catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
        }
    }

    /**
     * Load the game state data from `save.json`
     */
    public GameState load() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return new GameState(); // Return a new game state if save file doesn't exist
        }

        try (FileReader reader = new FileReader(file)) {
            StringBuilder content = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                content.append((char) character);
            }
            String serializedGameState = content.toString();
            System.out.println("Loaded game state: " + ((PRINT_SAVE_JASON)? serializedGameState : ""));
            GameState gameState = new GameState();
            gameState.deserialize(serializedGameState);
            gameState.initialize();
            return gameState;
        } catch (IOException e) {
            System.err.println("Error loading game state: " + e.getMessage());
            return new GameState(); // Return a new game state if there's an error
        }
    }
}
