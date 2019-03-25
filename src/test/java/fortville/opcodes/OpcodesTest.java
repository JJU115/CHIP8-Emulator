package fortville.opcodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OpcodesTest {

    private int value;

    @Before
    public void initOpcodesTest() {
        // XXX Common setup for all tests.
        value = 42;
    }

    @Test
    public void testAddConstToRegister() {
        assertEquals(value, 42);
    }

    @Test
    public void testJumpAddress() {
        assertTrue("XXX Testing", true);
    }
}
