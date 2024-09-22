package au.edu.anu.comp6120.thu16_a3_d;

import au.edu.anu.comp6120.thu16_a3_d.utils.Utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonHelperTest {
    @Test
    void testIsValidUUID() {


        assertTrue(Utils.isValidUUID("87b218d7-2e3e-412d-bb96-dcec1d45d42d"));
        assertFalse(Utils.isValidUUID("-41d4-a716-44665544000"));
    }
}
