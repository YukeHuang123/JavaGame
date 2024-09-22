/*
 * This source file was generated by the Gradle 'init' task
 */
package au.edu.anu.comp6120.thu16_a3_d;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.engine.GameState;
import java.io.IOException;

import java.io.IOException;
import java.io.Console;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    // ANSI color codes
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Unicode box drawing characters
    private static final String TOP_LEFT = "┌";
    private static final String TOP_RIGHT = "┐";
    private static final String BOTTOM_LEFT = "└";
    private static final String BOTTOM_RIGHT = "┘";
    private static final String HORIZONTAL = "─";
    private static final String VERTICAL = "│";

    public static void main(String[] args) throws IOException {
        GameState gameState = new GameState();

        displayInstructions();

        while (true) {
            gameState.display();

            int input = System.in.read();

            // Consume any additional characters (like newline)
            while (System.in.available() > 0) {
                System.in.read();
            }

            if (input == 'q' || input == 'Q') {
                System.out.println("Quitting the game. Goodbye!");
                DataManager.getInstance().save(gameState);
                break;
            }

            processInput((char) input, gameState);
        }
    }

    private static void displayInstructions() {
        String message = " Use W/A/S/D to move, Q to quit ";
        int width = message.length() + 2; // Add 2 for left and right padding

        // Top border
        System.out.print(ANSI_GREEN + TOP_LEFT);
        for (int i = 0; i < width; i++) {
            System.out.print(HORIZONTAL);
        }
        System.out.println(TOP_RIGHT);

        // Message
        System.out.println(VERTICAL + " " + message + " " + VERTICAL);

        // Bottom border
        System.out.print(BOTTOM_LEFT);
        for (int i = 0; i < width; i++) {
            System.out.print(HORIZONTAL);
        }
        System.out.println(BOTTOM_RIGHT + ANSI_RESET);
    }

    private static void processInput(char input, GameState gameState) {
        switch (Character.toLowerCase(input)) {
            case 'w':
                System.out.println("Moving up");
                // gameState.movePlayer(0, -1);
                break;
            case 's':
                System.out.println("Moving down");
                // gameState.movePlayer(0, 1);
                break;
            case 'a':
                System.out.println("Moving left");
                // gameState.movePlayer(-1, 0);
                break;
            case 'd':
                System.out.println("Moving right");
                // gameState.movePlayer(1, 0);
                break;
            default:
                // Ignore other inputs
        }
    }
}