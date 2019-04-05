package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 8xy7 - SUBN Vx, Vy
 * Set Vx = Vy - Vx, set VF = NOT borrow.
 * If Vy > Vx, then VF is set to 1, otherwise 0.
 * Then Vx is subtracted from Vy, and the results stored in Vx.
 */
public class SubtractInvertBorrow implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int vx = registers.loadRegister(data1);
        int vy = registers.loadRegister(data2);
        if (vx < vy) {
            registers.storeRegister(15, 1);
        } else {
            registers.storeRegister(15, 0);
        }

        vx = (vy - vx) & 0xFF;
        registers.storeRegister(data1, vx);
    }
}
