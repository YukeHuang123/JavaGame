package au.edu.anu.comp6120.thu16_a3_d;

/**
 * different type of recover material, can add different HP
 */
public enum RecoverMaterial {
    M1(10),
    M2(20),
    M3(30),
    M4(40),
    M5(50);

    final int recoverHp;

    RecoverMaterial(int recoverHp) {
        this.recoverHp = recoverHp;
    }

    static public RecoverMaterial stringToRecoverMaterial(String string) {
        switch (string) {
            case "M1" -> { return M1; }
            case "M2" -> { return M2; }
            case "M3" -> { return M3; }
            case "M4" -> { return M4; }
            case "M5" -> { return M5; }
            default -> {return M1;}
        }
    }

    @Override
    public String toString() {
        return "RecoverMaterial{" +
                "recoverHp=" + recoverHp +
                '}';
    }
}
