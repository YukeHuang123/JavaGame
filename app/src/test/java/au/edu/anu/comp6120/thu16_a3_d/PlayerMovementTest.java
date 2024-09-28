package au.edu.anu.comp6120.thu16_a3_d;

import static org.junit.jupiter.api.Assertions.assertEquals;
import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.engine.GameState;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityPlayer;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerMovementTest {
    private GameState gameState;

    @BeforeEach
    public void setUp() {
        gameState = GameState.getInstance();
        gameState.initialize();
        // Reset the player and place it at a known location
        EntityPlayer player = new EntityPlayer(new Location(7, 2));
        gameState.spawnEntity(player);
        gameState.findPlayer(); // Initialize the player entity in the game state
    }

    private static void moveProcess(String input, GameState gameState) {
        switch (input) {
            case "w", "W":
                System.out.println("Moving up");
                gameState.movePlayer(0, -1);
                break;
            case "s", "S":
                System.out.println("Moving down");
                gameState.movePlayer(0, 1);
                break;
            case "a", "A":
                System.out.println("Moving left");
                gameState.movePlayer(-1, 0);
                break;
            case "d", "D":
                System.out.println("Moving right");
                gameState.movePlayer(1, 0);
                break;
            default:
                // Ignore other inputs
        }
    }

    @Test
    public void testPlayerMovementW() {
        // Simulate pressing 'W'
        moveProcess("W", gameState);

        // Verify the player's new location
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(7, newLocation.getLocationX()); // X should remain the same
        assertEquals(1, newLocation.getLocationY()); // Y should be 1, 因为 Y 从 2 变为 1
    }

    @Test
    public void testPlayerMovementA() {
        // Simulate pressing 'A'
        moveProcess("A", gameState);

        // Verify the player's new location
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(6, newLocation.getLocationX()); // X should be -1
        assertEquals(2, newLocation.getLocationY()); // Y should remain the same
    }

    @Test
    public void testPlayerMovementS() {
        // Simulate pressing 'S'
        moveProcess("S", gameState);

        // Verify the player's new location
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(7, newLocation.getLocationX()); // X should remain the same
        assertEquals(3, newLocation.getLocationY()); // Y should be 1
    }

    @Test
    public void testPlayerMovementD() {
        // Simulate pressing 'D'
        moveProcess("D", gameState);

        // Verify the player's new location
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(7, newLocation.getLocationX()); // X should be 1
        assertEquals(2, newLocation.getLocationY()); // Y should remain the same
    }

    @Test
    public void testPlayerMovementInvalidInput() {
        // Store the initial location
        Location initialLocation = gameState.getEntities().get(0).getLocation();

        // Simulate invalid key press
        simulateKeyPress("X"); // Invalid input should not change position

        // Verify the player's location hasn't changed
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(initialLocation.getLocationX(), newLocation.getLocationX());
        assertEquals(initialLocation.getLocationY(), newLocation.getLocationY());
    }
}
