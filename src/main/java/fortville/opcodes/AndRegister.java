package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * AndRegister
 */
public class AndRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 8xy2 - AND Vx, Vy
         * Set Vx = Vx AND Vy.
         * Performs a bitwise AND on the values of Vx and Vy,
         * then stores the result in Vx.
         * A bitwise AND compares the corrseponding bits from two values,
         * and if both bits are 1, then the same bit in the result is also 1.
         * Otherwise, it is 0.
         */
        int VX = registers.loadRegister(data1);
        int VY = registers.loadRegister(data2);
        int result = VX & VY;
        registers.storeRegister(data1, result);
    }
}
