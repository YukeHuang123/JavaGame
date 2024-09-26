package au.edu.anu.comp6120.thu16_a3_d.engine.item;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;
import au.edu.anu.comp6120.thu16_a3_d.engine.IDisplayable;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.Random;

import static au.edu.anu.comp6120.thu16_a3_d.utils.ANSIColors.*;


public class Inventory implements ISerializable,IDisplayable {

    static final int maxWeaponNum = 3;
    static final int maxRecoverNum = 5;

    Weapon[] weaponArray = new Weapon[maxWeaponNum];
    Recover[] recoverArray = new Recover[maxRecoverNum];

    public Inventory() {
        if(!DataManager.READ_CONFIG_FROM_FILE){
            generalize();
        }
    }

    public Weapon getWeapon(int index){
        index -= 1;
        if(index < 0){
            System.out.println(ANSI_RED + "invalid index" + ANSI_RESET);
            return null;
        }
        if(index >= maxWeaponNum){
            System.out.println(ANSI_RED + "index out of bounds" + ANSI_RESET);
            return null;
        }
        if(weaponArray[index] == null ){
            System.out.println(ANSI_RED + "this slot is no weapon" + ANSI_RESET);
            return null;
        }
        //update show the new state
        Weapon ret = weaponArray[index];
        showInventory();
        return ret;
    }

    public boolean addWeapon(Weapon weapon){
        for (int i = 0; i < maxWeaponNum; i++) {
            if(weaponArray[i] == null){
                weaponArray[i] = weapon;
                showInventory();
                return true;
            }
        }

        //not find show error
        System.out.println(ANSI_RED + "xxxx no rest slot for storing weapon, please remove some of the weapon use command  (rm w + index)!" + ANSI_RESET);
        return false;
    }

    public void removeWeapon(int index){
        index -= 1;
        if(index < 0){
            System.out.println(ANSI_RED + "invalid index" + ANSI_RESET);
            return;
        }
        if(index >= maxWeaponNum){
            System.out.println(ANSI_RED + "index out of bounds" + ANSI_RESET);
            return;
        }
        if(weaponArray[index] == null ){
            System.out.println(ANSI_RED + "this slot is no weapon" + ANSI_RESET);
            return;
        }
        //update show the new state
        Weapon ret = weaponArray[index];
        weaponArray[index] = null;
        showInventory();
    }

    public boolean addRecover(Recover recover){
        for (int i = 0; i < maxRecoverNum; i++) {
            if(recoverArray[i] == null){
                recoverArray[i] = recover;
                showInventory();
                return true;
            }
        }

        //not find show error
        System.out.println(ANSI_RED + "xxxx no rest slot for storing recover, please remove some of the recover use command (rm r + index)!" + ANSI_RESET);
        return false;
    }

    public Recover removeRecover(int index){
        index -= 1;

        if(index < 0){
            System.out.println(ANSI_RED + "invalid index (negative)" + ANSI_RESET);
            return null;
        }
        if(index >= maxRecoverNum){
            System.out.println(ANSI_RED + "index out of bounds" + ANSI_RESET);
            return null;
        }
        if(recoverArray[index] == null ){
            System.out.println(ANSI_RED + "this slot is no recover" + ANSI_RESET);
            return null;
        }
        //update show the new state
        Recover ret = recoverArray[index];
        recoverArray[index] = null;
        showInventory();
        return ret;
    }

    public void showInventory(){
        System.out.println("Inventory: " + ANSI_YELLOW + "only carry  " + maxWeaponNum + "  weapons and   "+  maxRecoverNum +" recovers!" + " use commend (use index) to use the recover" + ANSI_RESET);

        System.out.print(ANSI_GREEN + "    --Weapons(attack): ");
        for (int i = 0; i < weaponArray.length; i++) {
            Weapon weapon = weaponArray[i];
            String weaponString;
            if(weapon == null){
                weaponString = "    ";
            } else {
                int weaponAttribute = weapon.getAttributes();
                if(weaponAttribute < 10){
                    weaponString = weaponAttribute + "   ";
                } else if (weaponAttribute <100){
                    weaponString = weaponAttribute + "  ";
                } else if (weaponAttribute < 1000){
                    weaponString = weaponAttribute + " ";
                } else {
                    weaponString = String.valueOf(weaponAttribute);
                }
            }

            System.out.print("[" + (i + 1) + "]" + ":" + weaponString + "   ");
        }


        System.out.println(ANSI_RESET);

        System.out.print(ANSI_GREEN + "    --Recovers ( Hp ): ");
        for (int i = 0; i < recoverArray.length; i++) {
            Recover recover = recoverArray[i];
            String recoverString;

            if(recover == null){
                recoverString = "    ";
            } else {
                int recoverAttributes = recover.getAttributes();
                if(recoverAttributes < 10){
                    recoverString = recoverAttributes + "   ";
                } else if (recoverAttributes < 100){
                    recoverString = recoverAttributes + "  ";
                } else if (recoverAttributes < 1000){
                    recoverString = recoverAttributes + " ";
                } else {
                    recoverString = String.valueOf(recoverAttributes);
                }
            }
            System.out.print("[" + (i + 1) + "]" + ":" + recoverString + "   ");
        }
        System.out.println(ANSI_RESET);

    }

    @Override
    public void display() {
        showInventory();
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();
        JsonArray itemArray = new JsonArray();
        for (Item item : weaponArray){
            if(item!=null){
                itemArray.add(JsonParser.parseString(item.serialize()));
            }
        }
        for (Item item : recoverArray){
            if(item!=null){
                itemArray.add(JsonParser.parseString(item.serialize()));
            }
        }
        jsonObject.add("items", itemArray);
        return jsonObject.toString();
    }

    @Override
    public void deserialize(String data) {
        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();

        // deserialize items
        JsonArray entitiesArray = jsonObject.getAsJsonArray("items");

        for(JsonElement itemElement : entitiesArray){
            JsonObject itemObject = itemElement.getAsJsonObject();
            if(itemObject.get("type").getAsString().equals(ItemType.WEAPON.getName()) ){
                Weapon weapon = new Weapon(new Location(), 0);
                weapon.deserialize(itemElement.toString());
                for (int i = 0; i < maxWeaponNum; i++) {
                    if(weaponArray[i] == null){
                        weaponArray[i] = weapon;
                    }
                }
            } else if(itemObject.get("type").getAsString().equals(ItemType.RECOVER.getName()) ) {
                Recover recover = new Recover(new Location(), 0);
                recover.deserialize(itemElement.toString());
                for (int i = 0; i < maxRecoverNum; i++) {
                    if(recoverArray[i] == null){
                        recoverArray[i] = recover;
                    }
                }
            }
        }
    }

    void generalize(){
        Random random = new Random();
        int max = 100;

        addWeapon(new Weapon(new Location(),random.nextInt(max)+1));
        addWeapon(new Weapon(new Location(),random.nextInt(max)+1));
        addWeapon(new Weapon(new Location(),random.nextInt(max)+1));


        addRecover(new Recover(new Location(),random.nextInt(max)+1));
        addRecover(new Recover(new Location(),random.nextInt(max)+1));
        addRecover(new Recover(new Location(),random.nextInt(max)+1));
    }

    @Override
    public String toString() {
        return  "\n  weaponArray =" + Arrays.toString(weaponArray) +
                "\n  recoverArray=" + Arrays.toString(recoverArray);
    }
}
