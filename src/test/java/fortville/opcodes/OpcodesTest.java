package fortville.opcodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.opcodes.StoreBCDToMemory;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;

public class OpcodesTest {

    private int zero;

    Registers registers;
    Memory memory;
    Display display;

    @Before
    public void initOpcodesTest() {
        // Common setup for all tests.
        zero = 0;

        registers = new Registers();
        memory = new Memory("input.ch8", registers); // XXX A Test rom would be nice.
        display = new Display();
    }

    @Test
    public void testStoreBCDToMemory_BigByte() {
        int testValue = 241; // Big value, so could be negative if signed.
        int regV1 = 1;
        int addrI = 0x200;

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

    @Test
    public void testStoreBCDToMemory_SmallByte() {
        int testValue = 35; // Small value, so always positive.
        int regV1 = 1;
        int addrI = 0x200;

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
    public void testVectorLoadRegister_V0() {
        int initValue = 42;
        int testValue;
        int regVX = 0;
        int addrI = 0x200;

        // Given
        registers.storeI(addrI); // Set register I to addrI.
        testValue = memory.load(registers.loadI()); // Save value from [addrI].
        registers.storeRegister(regVX, initValue); // Store init value.

        assertEquals(registers.loadRegister(regVX), initValue);
        assertNotEquals(registers.loadRegister(regVX), testValue);

        // When
        VectorLoadRegister op = new VectorLoadRegister();
        op.execute(regVX, zero, zero, memory, display, registers);

        // Then
        assertEquals(registers.loadRegister(regVX), testValue);
    }

    @Test
    public void testVectorLoadRegister_V4() {
        int initValue = 42;
        int regVX = 4;
        int addrI = 0x200;

        // Given
        registers.storeI(addrI); // Set register I to addrI.
        for (int regNum = 0; regNum <= regVX; regNum++) {
            registers.storeRegister(regNum, initValue); // Store init values.
        }

        for (int regNum = 0; regNum <= regVX; regNum++) { // Check init values.
            assertEquals(registers.loadRegister(regNum), initValue);
            assertNotEquals(registers.loadRegister(regNum), memory.load(addrI + regNum));
        }

        // When
        VectorLoadRegister op = new VectorLoadRegister();
        op.execute(regVX, zero, zero, memory, display, registers);

        // Then
        for (int regNum = 0; regNum <= regVX; regNum++) {
            assertEquals(registers.loadRegister(regNum), memory.load(addrI + regNum));
        }
    }

    @Test
    public void testVectorStoreRegister_V0() {
        int initValue = 42;
        int testValue;
        int regVX = 0;
        int addrI = 0x200;

        // Given
        registers.storeI(addrI); // Set register I to addrI.
        registers.storeRegister(regVX, initValue); // Store init value.
        testValue = memory.load(registers.loadI()); // Save value from [addrI].

        assertEquals(registers.loadRegister(regVX), initValue);
        assertEquals(memory.load(registers.loadI()), testValue);

        // When
        VectorStoreRegister op = new VectorStoreRegister();
        op.execute(regVX, zero, zero, memory, display, registers);

        // Then
        assertNotEquals(memory.load(registers.loadI()), testValue);
        assertEquals(memory.load(registers.loadI()), initValue);
    }

    @Test
    public void testVectorStoreRegister_V4() {
        int initValue = 42;
        int regVX = 4;
        int addrI = 0x200;

        // Given
        registers.storeI(addrI); // Set register I to addrI.
        for (int regNum = 0; regNum <= regVX; regNum++) {
            memory.store(initValue, addrI + regNum); // Store init values to memory.
            registers.storeRegister(regNum, new Random().nextInt(256));
        }

        for (int regNum = 0; regNum <= regVX; regNum++) { // Check init values.
            assertEquals(memory.load(addrI + regNum), initValue);
            assertNotEquals(memory.load(addrI + regNum), registers.loadRegister(regNum));
        }

        // When
        VectorStoreRegister op = new VectorStoreRegister();
        op.execute(regVX, zero, zero, memory, display, registers);

        // Then
        for (int regNum = 0; regNum <= regVX; regNum++) {
            assertEquals(memory.load(addrI + regNum), registers.loadRegister(regNum));
        }
    }
}
