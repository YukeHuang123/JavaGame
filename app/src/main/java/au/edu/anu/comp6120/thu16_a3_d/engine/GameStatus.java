package au.edu.anu.comp6120.thu16_a3_d.engine;

public enum GameStatus {
    READY_MOVE,
    FIND_BONUS,
    MEET_NPC,
    FIGHTING,
    WIN,
    LOSS;

    @Override
    public String toString() {
        switch (this){
            case READY_MOVE -> {return "Ready Move press W / A / S / D to move";}
            case FIND_BONUS -> {return "Find Bonus press Y or N to take or drop";}
            case MEET_NPC -> {return "Find NPC press Y or N to fight or run away";}
            case FIGHTING -> {return "In Fighting with NPC, choose the weapon (press the index) every time to fight";}
            default -> {return this.toString();}
        }

    }
}
