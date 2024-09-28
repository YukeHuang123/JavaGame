package au.edu.anu.comp6120.thu16_a3_d;

import static org.junit.jupiter.api.Assertions.assertEquals;
import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.engine.GameState;
import au.edu.anu.comp6120.thu16_a3_d.engine.entity.EntityPlayer;
import au.edu.anu.comp6120.thu16_a3_d.engine.level.GameMap;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PlayerMovementTest {
    private GameState gameState;

    static int locationX = 7;
    static int locationY = 2;

    @BeforeEach
    public void setUp() {

        gameState = GameState.getInstance();
        gameState.initialize();
        // Reset the player and place it at a known location
        EntityPlayer player = new EntityPlayer(new Location(locationX, locationY));
        gameState.spawnEntity(player);
        gameState.findPlayer(); // Initialize the player entity in the game state


        //set entity 4 direction be the empty grid
        GameMap gameMap = gameState.getMap();
        gameMap.setGridEmpty(locationX-1,locationY);
        gameMap.setGridEmpty(locationX+1,locationY);
        gameMap.setGridEmpty(locationX,locationY-1);
        gameMap.setGridEmpty(locationX,locationY+1);
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
        // Simulate pressing 'A'
        moveProcess("W", gameState);
        locationY -= 1;
        // Verify the player's new location
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(locationX, newLocation.getLocationX());
        assertEquals(locationY, newLocation.getLocationY());
    }


    @Test
    public void testPlayerMovementA() {
        // Simulate pressing 'A'
        moveProcess("A", gameState);
        locationX -= 1;
        // Verify the player's new location
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(locationX, newLocation.getLocationX());
        assertEquals(locationY, newLocation.getLocationY());
    }

    @Test
    public void testPlayerMovementS() {
        // Simulate pressing 'S'
        moveProcess("S", gameState);
        locationY += 1;
        // Verify the player's new location
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(locationX, newLocation.getLocationX());
        assertEquals(locationY, newLocation.getLocationY());
    }

    @Test
    public void testPlayerMovementD() {
        // Simulate pressing 'D'
        moveProcess("D", gameState);
        locationX += 1;
        // Verify the player's new location
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(locationX, newLocation.getLocationX());
        assertEquals(locationY, newLocation.getLocationY());
    }

    @Test
    public void testPlayerMovementInvalidInput() {
        // Store the initial location
        Location initialLocation = gameState.getEntities().get(0).getLocation();

        // Simulate invalid key press
        moveProcess("X",gameState); // Invalid input should not change position

        // Verify the player's location hasn't changed
        Location newLocation = gameState.getEntities().get(0).getLocation();
        assertEquals(initialLocation.getLocationX(), newLocation.getLocationX());
        assertEquals(initialLocation.getLocationY(), newLocation.getLocationY());
    }
}
