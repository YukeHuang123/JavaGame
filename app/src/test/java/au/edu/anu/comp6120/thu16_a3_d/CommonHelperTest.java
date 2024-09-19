package au.edu.anu.comp6120.thu16_a3_d;

import au.edu.anu.comp6120.thu16_a3_d.utils.CommonHelper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonHelperTest {
    @Test
    void testIsValidUUID() {

        assertTrue(CommonHelper.isValidUUID("87b218d7-2e3e-412d-bb96-dcec1d45d42d"));
        assertFalse(CommonHelper.isValidUUID("-41d4-a716-44665544000"));
    }
}
