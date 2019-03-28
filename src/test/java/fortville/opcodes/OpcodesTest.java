package fortville.opcodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.opcodes.StoreBCDToMemory;

import org.junit.Before;
import org.junit.Test;

public class OpcodesTest {

    private int value;
    private short zero;

    Registers registers;
    Memory memory;
    Display display;

    @Before
    public void initOpcodesTest() {
        // XXX Common setup for all tests.
        value = 42;
        zero = 0;

        registers = new Registers();
        memory = new Memory("input.ch8", registers); // XXX A Test rom would be nice.
        display = new Display();
    }

    @Test
    public void testAddConstToRegister() {
        assertEquals(value, 42);
    }

    @Test
    public void testJumpAddress() {
        assertTrue("XXX Testing", true);
    }

    @Test
    public void testStoreBCDToMemory_SmallByte() {
        byte testValue = 35; // Small value, so always positive.
        short regV1 = 1;
        short addrI = 0x200;

        // Given
        registers.storeI(addrI);
        registers.storeRegister(regV1, testValue);

        assertNotEquals(memory.load(addrI), 0);
        assertNotEquals(memory.load(addrI + 1), 3);
        assertNotEquals(memory.load(addrI + 2), 5);

        // When
        StoreBCDToMemory op = new StoreBCDToMemory();
        op.execute(regV1, zero, zero, memory, display, registers);

        // Then
        assertEquals(memory.load(addrI), 0);
        assertEquals(memory.load(addrI + 1), 3);
        assertEquals(memory.load(addrI + 2), 5);
    }

    @Test
    public void testStoreBCDToMemory_BigByte() {
        byte testValue = (byte)241; // Big value, so could be negative if signed.
        short regV1 = 1;
        short addrI = 0x200;

        // Given
        registers.storeI(addrI);
        registers.storeRegister(regV1, testValue);

        assertNotEquals(memory.load(addrI), 2);
        assertNotEquals(memory.load(addrI + 1), 4);
        assertNotEquals(memory.load(addrI + 2), 1);

        // When
        StoreBCDToMemory op = new StoreBCDToMemory();
        op.execute(regV1, zero, zero, memory, display, registers);

        // Then
        assertEquals(memory.load(addrI), 2);
        assertEquals(memory.load(addrI + 1), 4);
        assertEquals(memory.load(addrI + 2), 1);
    }
}
