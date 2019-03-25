package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * OrRegister
 */
public class OrRegister implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 8xy1 - OR Vx, Vy
         * Set Vx = Vx OR Vy.
         * Performs a bitwise OR on the values of Vx and Vy,
         * then stores the result in Vx.
         * A bitwise OR compares the corrseponding bits from two values,
         * and if either bit is 1, then the same bit in the result is also 1.
         * Otherwise, it is 0.
         */
        int result = (registers.loadRegister(data1) | registers.loadRegister(data2));
        registers.storeRegister(data1, (byte)result);

        // XXX Do we want incrementPC in opcode execution?
        registers.incrementPC();
    }
}
