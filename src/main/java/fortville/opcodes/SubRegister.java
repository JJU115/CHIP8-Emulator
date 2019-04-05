package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 8xy5 - SUB Vx, Vy
 * Set Vx = Vx - Vy, set VF = NOT borrow.
 * If Vx > Vy, then VF is set to 1, otherwise 0.
 * Then Vy is subtracted from Vx, and the results stored in Vx.
 */
public class SubRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int vx = registers.loadRegister(data1);
        int vy = registers.loadRegister(data2);
        if (vx > vy) {
            registers.storeRegister(15, 1);
        } else {
            registers.storeRegister(15, 0);
        }

        vx = (vx - vy) & 0xFF;
        registers.storeRegister(data1, vx);
    }
}
