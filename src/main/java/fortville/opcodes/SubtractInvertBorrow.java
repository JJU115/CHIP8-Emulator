package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SubtractInvertBorrow
 */
public class SubtractInvertBorrow implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 8xy7 - SUBN Vx, Vy
         * Set Vx = Vy - Vx, set VF = NOT borrow.
         * If Vy > Vx, then VF is set to 1, otherwise 0.
         * Then Vx is subtracted from Vy, and the results stored in Vx.
         */
        int VX = registers.loadRegister(data1);
        int VY = registers.loadRegister(data2);
        if (VX < VY) {
            registers.storeRegister(15, 1);
        } else {
            registers.storeRegister(15, 0);
        }

        VX = (VY - VX) & 0xFF;
        registers.storeRegister(data1, VX);
    }
}
