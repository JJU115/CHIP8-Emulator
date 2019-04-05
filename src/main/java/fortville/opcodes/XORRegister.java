package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 8xy3 - XOR Vx, Vy
 * Set Vx = Vx XOR Vy.
 * Performs a bitwise exclusive OR on the values of Vx and Vy,
 * then stores the result in Vx.
 * An exclusive OR compares the corrseponding bits from two values,
 * and if the bits are not both the same,
 * then the corresponding bit in the result is set to 1.
 * Otherwise, it is 0.
 */
public class XORRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        registers.storeRegister(data1,
            registers.loadRegister(data1) ^ registers.loadRegister(data2));
    }
}
