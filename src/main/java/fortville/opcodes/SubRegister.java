package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SubRegister
 */
public class SubRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 8xy5 - SUB Vx, Vy
         * Set Vx = Vx - Vy, set VF = NOT borrow.
         * If Vx > Vy, then VF is set to 1, otherwise 0.
         * Then Vy is subtracted from Vx, and the results stored in Vx.
         */
        int VX = registers.loadRegister(data1);
        int VY = registers.loadRegister(data2);
        if (VX > VY) {
            registers.storeRegister(15, 1);
        } else {
            registers.storeRegister(15, 0);
        }

        VX = (VX - VY) & 0xFF;
        registers.storeRegister(data1, VX);
    }
}
