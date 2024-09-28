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

    public static final int maxWeaponNum = 3;
    public static final int maxRecoverNum = 5;

    ItemWeapon[] itemWeaponArray = new ItemWeapon[maxWeaponNum];
    ItemRecover[] itemRecoverArray = new ItemRecover[maxRecoverNum];

    public Inventory() {
        if(!DataManager.READ_CONFIG_FROM_FILE){
            generalize();
        }
    }

    public ItemWeapon getWeapon(int index){
        index -= 1;
        if(index < 0){
            System.out.println(ANSI_RED + "invalid index" + ANSI_RESET);
            return null;
        }
        if(index >= maxWeaponNum){
            System.out.println(ANSI_RED + "index out of bounds" + ANSI_RESET);
            return null;
        }
        if(itemWeaponArray[index] == null ){
            System.out.println(ANSI_RED + "this slot is no weapon" + ANSI_RESET);
            return null;
        }
        //update show the new state
        ItemWeapon ret = itemWeaponArray[index];
        showInventory();
        return ret;
    }

    public boolean addWeapon(ItemWeapon itemWeapon){
        for (int i = 0; i < maxWeaponNum; i++) {
            if(itemWeaponArray[i] == null){
                itemWeaponArray[i] = itemWeapon;
                showInventory();
                return true;
            }
        }

        //not find show error
        System.out.println(ANSI_RED + "xxxx no rest slot for storing weapon, please remove some of the weapon use command  (rm w + index)!" + ANSI_RESET);
        return false;
    }

    public ItemWeapon removeWeapon(int index){
        index -= 1;
        if(index < 0){
            System.out.println(ANSI_RED + "invalid index" + ANSI_RESET);
            return null;
        }
        if(index >= maxWeaponNum){
            System.out.println(ANSI_RED + "index out of bounds" + ANSI_RESET);
            return null;
        }
        if(itemWeaponArray[index] == null ){
            System.out.println(ANSI_RED + "this slot is no weapon" + ANSI_RESET);
            return null;
        }
        //update show the new state
        ItemWeapon ret = itemWeaponArray[index];
        itemWeaponArray[index] = null;
        showInventory();
        return ret;
    }

    public boolean addRecover(ItemRecover itemRecover){
        for (int i = 0; i < maxRecoverNum; i++) {
            if(itemRecoverArray[i] == null){
                itemRecoverArray[i] = itemRecover;
                showInventory();
                return true;
            }
        }

        //not find show error
        System.out.println(ANSI_RED + "xxxx no rest slot for storing recover, please remove some of the recover use command (rm r + index)!" + ANSI_RESET);
        return false;
    }

    /**
     * get the recover by index
     * @param index the index of recover in the slot
     * @return the recover instance in the index
     */
    public ItemRecover getRecover(int index){
        index -= 1;

        if(index < 0){
            System.out.println(ANSI_RED + "invalid index (negative)" + ANSI_RESET);
            return null;
        }
        if(index >= maxRecoverNum){
            System.out.println(ANSI_RED + "index out of bounds" + ANSI_RESET);
            return null;
        }
        if(itemRecoverArray[index] == null ){
            System.out.println(ANSI_RED + "this slot is no recover" + ANSI_RESET);
            return null;
        }
        //update show the new state
        ItemRecover ret = itemRecoverArray[index];
        showInventory();
        return ret;
    }

    public ItemRecover removeRecover(int index){
        index -= 1;

        if(index < 0){
            System.out.println(ANSI_RED + "invalid index (negative)" + ANSI_RESET);
            return null;
        }
        if(index >= maxRecoverNum){
            System.out.println(ANSI_RED + "index out of bounds" + ANSI_RESET);
            return null;
        }
        if(itemRecoverArray[index] == null ){
            System.out.println(ANSI_RED + "this slot is no recover" + ANSI_RESET);
            return null;
        }
        //update show the new state
        ItemRecover ret = itemRecoverArray[index];
        itemRecoverArray[index] = null;
        showInventory();
        return ret;
    }

    public void showInventory(){
        System.out.println("Inventory: " + ANSI_YELLOW + "only carry  " + maxWeaponNum + "  weapons and   "+  maxRecoverNum +" recovers!" + " use commend (use index) to use the recover" + ANSI_RESET);

        System.out.print(ANSI_GREEN + "    --Weapons(attack): ");
        for (int i = 0; i < itemWeaponArray.length; i++) {
            ItemWeapon itemWeapon = itemWeaponArray[i];
            String weaponString;
            if(itemWeapon == null){
                weaponString = "    ";
            } else {
                int weaponAttribute = itemWeapon.getAttributes();
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
        for (int i = 0; i < itemRecoverArray.length; i++) {
            ItemRecover itemRecover = itemRecoverArray[i];
            String recoverString;

            if(itemRecover == null){
                recoverString = "    ";
            } else {
                int recoverAttributes = itemRecover.getAttributes();
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
        for (Item item : itemWeaponArray){
            if(item!=null){
                itemArray.add(JsonParser.parseString(item.serialize()));
            }
        }
        for (Item item : itemRecoverArray){
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
                ItemWeapon itemWeapon = new ItemWeapon(new Location(), 0);
                itemWeapon.deserialize(itemElement.toString());
                for (int i = 0; i < maxWeaponNum; i++) {
                    if(itemWeaponArray[i] == null){
                        itemWeaponArray[i] = itemWeapon;
                    }
                }
            } else if(itemObject.get("type").getAsString().equals(ItemType.RECOVER.getName()) ) {
                ItemRecover itemRecover = new ItemRecover(new Location(), 0);
                itemRecover.deserialize(itemElement.toString());
                for (int i = 0; i < maxRecoverNum; i++) {
                    if(itemRecoverArray[i] == null){
                        itemRecoverArray[i] = itemRecover;
                    }
                }
            }
        }
    }

    void generalize(){
        Random random = new Random();
        int max = 100;

        addWeapon(new ItemWeapon(new Location(),random.nextInt(max)+1));
        addWeapon(new ItemWeapon(new Location(),random.nextInt(max)+1));
        addWeapon(new ItemWeapon(new Location(),random.nextInt(max)+1));


        addRecover(new ItemRecover(new Location(),random.nextInt(max)+1));
        addRecover(new ItemRecover(new Location(),random.nextInt(max)+1));
        addRecover(new ItemRecover(new Location(),random.nextInt(max)+1));
    }

    @Override
    public String toString() {
        return  "\n  weaponArray =" + Arrays.toString(itemWeaponArray) +
                "\n  recoverArray=" + Arrays.toString(itemRecoverArray);
    }
}
