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
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 8xy5 - SUB Vx, Vy
         * Set Vx = Vx - Vy, set VF = NOT borrow.
         * If Vx > Vy, then VF is set to 1, otherwise 0.
         * Then Vy is subtracted from Vx, and the results stored in Vx.
         */
        int VX = (0xff & (registers.loadRegister(data1)));
        int VY = (0xff & (registers.loadRegister(data2)));
        if (VX > VY) {
            registers.storeRegister((short)15, (byte)1);
        } else {
            registers.storeRegister((short)15, (byte)0);
        }

        VX = ((VX - VY) & 0xff);
        registers.storeRegister(data1, (byte)VX);
    }
}
