package au.edu.anu.comp6120.thu16_a3_d.utils;


import java.util.UUID;

public class Utils {

    public static double calculateDistance(Location loc1, Location loc2) {
        int deltaX = loc2.getLocationX() - loc1.getLocationX();
        int deltaY = loc2.getLocationY() - loc1.getLocationY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

}
