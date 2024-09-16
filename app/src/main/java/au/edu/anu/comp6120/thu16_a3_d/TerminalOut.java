package au.edu.anu.comp6120.thu16_a3_d;

import java.util.Scanner;

/**
 * call all resources in the engine and use the string command to realize the process of the game by using the
 * terminal
 */
public class TerminalOut {
    static String JSON_FILE_PATH = "?????????????????????????????????????????????????????S";
    GameEngine gameEngine;

    boolean isUsingInventory = false;
    boolean isPicking = false;
    boolean isFighting = false;
    boolean isDropping = false;
    boolean isRecurring = false;

    boolean notNeedRecurring = false;

    public TerminalOut() {
        System.out.println("Please input \"start\" to start the game.");
    }

    void inputCommand(String inputCommand){
        if(isUsingInventory){
            useInventory(inputCommand);
            return;
        }
        if(isPicking){
            pick(inputCommand);
            //add
            return;
        }
        if(isDropping){
            drop(inputCommand);
            //remove
            return;
        }
        if(isFighting){
            combat(inputCommand);
            return;
        }
        if(isRecurring){
            resurrect(inputCommand);
            return;
        }

        //main command
        switch (inputCommand){
            case "start" -> start();
            case "W", "A", "S", "D" -> move(inputCommand);
            case "drop" ->drop(inputCommand);
            case "use" -> useInventory(inputCommand);
        }
    }

    void start(){
        Scanner scanner = new Scanner(JSON_FILE_PATH);
        StringBuilder builder = new StringBuilder();
        while(scanner.hasNextLine()){
            builder.append(scanner.nextLine());
        }
        gameEngine = new GameEngine(builder.toString());

        showBasicState();
    }

    void move(String direction){
        String errorString = gameEngine.move(Direction.stringToDirection(direction));
        if(errorString != null){
            System.out.println(errorString);
            return;
        }

        String s;
        s = gameEngine.getMeetEnemyString();
        if(s!=null){
            System.out.println("Enemy: Do you want fight with me?");
            System.out.println("input yes or no");
            isFighting = true;
            return;
        }
        s = gameEngine.getMeetBonusString();
        if(s!=null){
            System.out.println("System: You got " + s);
            System.out.println("System: Do you want to pick it up?");
            System.out.println("input yes or no");
            isPicking = true;
            return;
        }

        showBasicState();
    }

    void combat(String combatStringFlag){
        if(!isFighting){
            return;
        }

        isFighting = false;
        if (combatStringFlag.equals("yes")) {
            gameEngine.combat();
        }
        if (gameEngine.idPlayerDead()) {
            //dead
            if (gameEngine.canResurrect()) {
                System.out.println("System: You have died, do you want to resurrect?");
                System.out.println("input yes or no");
                isRecurring = true;
            }
            else {
                System.out.println("you cannot resurrect");
            }
        } else {
            showBasicState();
        }


    }
    void pick(String pickStringFlag){
        if(!isPicking){
            return;
        }
        isPicking = false;
        boolean pickFlag = false;
        if(pickStringFlag.equals("yes")){
            pickFlag = true;
        }
        gameEngine.pickBonus(pickFlag);
        showBasicState();
    }
    void drop(String dropStringFlag){
        if(isDropping){
            isDropping = false;
            int index = Integer.parseInt(dropStringFlag);
            String errorMessage = gameEngine.drop(index);
            if(errorMessage!=null){
                System.out.println(errorMessage);
                return;
            }
            showBasicState();
        }
        else {
            isDropping = true;
            System.out.println("input the index that you want to drop");
        }
    }

    void useInventory(String dropStringFlag){
        if (isUsingInventory) {
            isUsingInventory = false;
            int index = Integer.parseInt(dropStringFlag);
            String errorMessage = gameEngine.useInventory(index);
            if (errorMessage != null) {
                System.out.println(errorMessage);
                return;
            }
            showBasicState();
        }
        else {
            isUsingInventory = true;
        }
    }

    void resurrect(String resurrectStringFlag){
        if(!isRecurring){
            return;
        }
        isRecurring = false;
        if(resurrectStringFlag.equals("yes")){
            gameEngine.resurrect();
        }
        else {
            notNeedRecurring = true;
        }
    }

    void showBasicState(){
        gameEngine.showMap();
        gameEngine.showPlayer();
        System.out.println("input W A S D to move, input drop to drop the inventory");
    }

    boolean needExit(){
        if(gameEngine.judgeWin()){
            System.out.println("You have won the game!");
            return true;
        } else if(gameEngine.judgeLoss()){
            System.out.println("You have lost the game!");
            return true;
        } else if (notNeedRecurring) {
            return false;
        } else {
            return false;
        }
    }
}
