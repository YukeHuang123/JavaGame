package au.edu.anu.comp6120.thu16_a3_d.data;

/**
 * serialize and deserialize use gson
 * @author Shun Liu (u7797828)
 */
public interface ISerializable {

    /**
     * serialize object to json string
     * @return json string
     */
    String serialize();

    /**
     * deserialize json string to object
     * @param data json string
     */
    void deserialize(String data);
}
