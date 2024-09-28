package au.edu.anu.comp6120.thu16_a3_d;

import au.edu.anu.comp6120.thu16_a3_d.engine.GameState;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityPlayer;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    private GameState gameState;

    @BeforeEach
    public void setUp() {
        gameState = GameState.getInstance();
        gameState.initialize();
        // Reset the player and place it at a known location
        EntityPlayer player = new EntityPlayer(new Location(0, 0));
        gameState.spawnEntity(player);
        gameState.findPlayer(); // Initialize the player entity in the game state
    }

    @Test
    public void testPlayerMovementValid() {

        // Move player to (1, 0)
        gameState.movePlayer(1, 0);

        // Verify the player's new location
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(1, newLocation.getLocationX());
        assertEquals(0, newLocation.getLocationY());
    }

    @Test
    public void testPlayerMovementInvalid() {
        // Store the initial location
        Location initialLocation = gameState.getEntities().get(0).getLocation();

        // Attempt to move player out of bounds (invalid move)
        gameState.movePlayer(-1, 0); // Assuming this is invalid

        // Verify the player's location hasn't changed
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(initialLocation.getLocationX(), newLocation.getLocationX());
        assertEquals(initialLocation.getLocationY(), newLocation.getLocationY());
    }
}
