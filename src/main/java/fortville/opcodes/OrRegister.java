package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 8xy1 - OR Vx, Vy
 * Set Vx = Vx OR Vy.
 * Performs a bitwise OR on the values of Vx and Vy,
 * then stores the result in Vx.
 * A bitwise OR compares the corrseponding bits from two values,
 * and if either bit is 1, then the same bit in the result is also 1.
 * Otherwise, it is 0.
 */
public class OrRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        
        int VX = registers.loadRegister(data1);
        int VY = registers.loadRegister(data2);
        int result = VX | VY;
        registers.storeRegister(data1, result);
    }
}
