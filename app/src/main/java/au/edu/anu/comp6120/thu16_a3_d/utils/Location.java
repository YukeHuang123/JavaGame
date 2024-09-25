package au.edu.anu.comp6120.thu16_a3_d.utils;

import au.edu.anu.comp6120.thu16_a3_d.data.DataManager;
import au.edu.anu.comp6120.thu16_a3_d.data.ISerializable;

/**
 * Location class
 */
public class Location implements ISerializable {

    private int locationX;
    private int locationY;

    public Location() {
        this.locationX = -1;
        this.locationY = -1;
    }

    public Location(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public boolean hasSetLocation(){
        return locationX != -1 && locationY != -1;
    }

    @Override
    public String serialize() {
        return DataManager.GSON.toJson(this);
    }

    @Override
    public void deserialize(String data) {
        Location location = DataManager.GSON.fromJson(data, Location.class);
        this.locationX = location.locationX;
        this.locationY = location.locationY;
    }

    @Override
    public String toString() {
        return "[" + locationX +","+ locationY +']';
    }
}
