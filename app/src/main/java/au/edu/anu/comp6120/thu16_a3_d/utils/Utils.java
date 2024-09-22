package au.edu.anu.comp6120.thu16_a3_d.utils;


import java.util.UUID;

public class Utils {

    public static boolean isValidUUID(String str){
        try {
            UUID uuid = UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

}
