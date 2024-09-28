package au.edu.anu.comp6120.thu16_a3_d;

import au.edu.anu.comp6120.thu16_a3_d.engine.item.ItemRecover;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.ItemType;
import au.edu.anu.comp6120.thu16_a3_d.engine.item.ItemWeapon;
import au.edu.anu.comp6120.thu16_a3_d.utils.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Item class and its subclasses.
 * This class contains tests for serialization and deserialization
 * of ItemWeapon and ItemRecover objects.
 */
public class ItemTest {
    /**
     * Tests the serialization of ItemWeapon and ItemRecover objects.
     * Verifies that the serialized JSON strings contain the expected fields
     * and values for both item types.
     */
    @Test
    public void testSerialize() {
        Location location = new Location(1, 2); // Replace with appropriate parameters for Location
        //test for Weapon
        ItemWeapon itemWeapon = new ItemWeapon(location, 40);
        String json = itemWeapon.serialize();
        System.out.println(json);
        Assertions.assertNotNull(json);
        Assertions.assertEquals(true, json.contains("\"type\": \"weapon\""));
        Assertions.assertEquals(true, json.contains("\"location\":"));
        Assertions.assertEquals(true, json.contains("\"attack\": 40"));


        //test for Recover
        ItemRecover itemREcover = new ItemRecover(location, 50);
        json = itemREcover.serialize();

        Assertions.assertNotNull(json);
        Assertions.assertEquals(true, json.contains("\"type\": \"recover\""));
        Assertions.assertEquals(true, json.contains("\"location\":"));
        Assertions.assertEquals(true, json.contains("\"recover\": 50"));
    }

    /**
     * Tests the deserialization of ItemWeapon and ItemRecover objects.
     * Verifies that the fields of the objects are correctly populated
     * from the provided JSON strings.
     */
    @Test
    public void testDeserialize() {
        //test weapon
        String jsonData = "{\"type\": \"weapon\",\n" +
                "\"location\": \"{\\n  \\\"locationX\\\": 0,\\n  \\\"locationY\\\": 0\n}\",\n" +
                "\"attack\": 50\n" +
                "}";
        ItemWeapon itemWeapon = new ItemWeapon(new Location(0, 0), 0); // Temporary item for deserialization
        itemWeapon.deserialize(jsonData);


        Assertions.assertEquals(ItemType.WEAPON, itemWeapon.getType());
        Assertions.assertEquals(50, itemWeapon.getAttributes());

        //test recover
        jsonData = "{\"type\": \"recover\",\n" +
                "\"location\": \"{\\n  \\\"locationX\\\": 0,\\n  \\\"locationY\\\": 0\n}\",\n" +
                "\"recover\": 5\n" +
                "}";
        ItemRecover itemRecover = new ItemRecover(new Location(0, 0), 0); // Temporary item for deserialization
        itemRecover.deserialize(jsonData);


        Assertions.assertEquals(ItemType.RECOVER, itemRecover.getType());
        Assertions.assertEquals(5, itemRecover.getAttributes());
    }
}
