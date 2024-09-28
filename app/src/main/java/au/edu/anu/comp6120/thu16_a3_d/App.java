/*
 * This source file was generated by the Gradle 'init' task
 */
package au.edu.anu.comp6120.thu16_a3_d;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.engine.GameState;
import au.edu.anu.comp6120.thu16_a3_d.engine.GameStatus;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.ItemType;

import java.io.IOException;
import java.util.Scanner;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.*;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    // Unicode box drawing characters
    private static final String TOP_LEFT = "+";
    private static final String TOP_RIGHT = "+";
    private static final String BOTTOM_LEFT = "+";
    private static final String BOTTOM_RIGHT = "+";
    private static final String HORIZONTAL = "-";
    private static final String VERTICAL = "|";

    public static void main(String[] args) throws IOException {
        GameState gameState = (DataManager.READ_CONFIG_FROM_FILE) ? (DataManager.getInstance().load()) : new GameState();

        displayInstructions();

        Scanner scanner = new Scanner(System.in);
        while (true) {

            gameState.display();
            //the state
            System.out.println(ANSI_RED + gameState.getGameStatus().toString() + ANSI_RESET);
            //TODO: this is for test out
            //System.out.println(gameState);

            String input = scanner.nextLine();

            if (input.equals("q") || input.equals("Q")) {
                System.out.println("Quitting the game. Goodbye!");
                DataManager.getInstance().save(gameState);
                break;
            }

            processInput(input, gameState);

            if(gameState.getGameStatus() == GameStatus.LOSS) {
                System.out.println(ANSI_RED + "You lost!" + ANSI_RESET);
                gameState.display();
                showLoss();
                break;
            }
            if(gameState.getGameStatus() == GameStatus.WIN) {
                System.out.println(ANSI_GREEN + "You win!" + ANSI_RESET);
                gameState.display();
                showWin();
                break;
            }
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

    private static void processInput(String input, GameState gameState) {
        GameStatus gameStateType = gameState.getGameStatus();

        switch (gameStateType) {
            case READY_MOVE -> moveProcess(input, gameState);
            case FIND_BONUS -> bonusProcess(input, gameState);
            case MEET_NPC ->  meetNPCProcess(input, gameState);
            case FIGHTING -> fightProcess(input, gameState);
        }

        inventoryProcess(input, gameState);
    }

    private static void moveProcess(String input, GameState gameState){
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

    private static void bonusProcess(String input, GameState gameState){
        if(input.equals("y") || input.equals("Y")){
            System.out.println("fetch bonus");
            gameState.fetchBonus();
        } else if(input.equals("n") || input.equals("N")){
            System.out.println("drop bonus");
            gameState.dropBonus();
        }
    }

    private static void meetNPCProcess(String input, GameState gameState){
        if(input.equals("y") || input.equals("Y")){
            System.out.println("fight NPC");
            gameState.chooseToFightNPC();
        } else if(input.equals("n") || input.equals("N")){
            System.out.println("run away");
            gameState.chooseToRunAwayNPC();
        }

    }

    private static void fightProcess(String input, GameState gameState){
        //fight choose weapon
        if(input.length() == 1){
            if(!Character.isDigit(input.charAt(0))){
                return;
            }
            int weaponIndex = Character.getNumericValue(input.charAt(0));
            gameState.fightNPC(weaponIndex);
        }
    }

    private static void inventoryProcess(String input, GameState gameState){
        if(input.length()!=6 && input.length()!=5){
            return;
        }

        if(input.substring(0,4).equalsIgnoreCase("rm w")){
            int index  = input.charAt(5) - '0';
            gameState.removeInventory(ItemType.WEAPON,index);
        } else if (input.substring(0,4).equalsIgnoreCase("rm r")) {
            int index  = input.charAt(5) - '0';
            gameState.removeInventory(ItemType.RECOVER,index);
        } else if (input.substring(0,3).equalsIgnoreCase("use")) {
            int index = input.charAt(4) - '0';
            gameState.userRecover(index);
        }
    }

    private static void showWin(){
        System.out.println(ANSI_GREEN);
        System.out.println("*-----------**************----------*");
        System.out.println("|\\                                 /|");
        System.out.println("| \\ _________ YOU WIN!!!! ________/ |");
        System.out.println("|                                   |");
        System.out.println("*-----------**************----------*");
        System.out.println(ANSI_RESET);
    }

    private static void showLoss(){
        System.out.println(ANSI_RED);
        System.out.println("*-----------**************----------*");
        System.out.println("|                                   |");
        System.out.println("| __________ YOU LOSS!!! __________ |");
        System.out.println("|/                                 \\|");
        System.out.println("*-----------**************----------*");
        System.out.println(ANSI_RESET);
    }

}