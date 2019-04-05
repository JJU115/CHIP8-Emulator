package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 8xy4 - ADD Vx, Vy
 * Set Vx = Vx + Vy, set VF = carry.
 * The values of Vx and Vy are added together.
 * If the result is greater than 8 bits (i.e., > 255,)
 * VF is set to 1, otherwise 0.
 * Only the lowest 8 bits of the result are kept, and stored in Vx.
 */
public class AddRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int vx = registers.loadRegister(data1);
        int vy = registers.loadRegister(data2);
        int sum = vx + vy;
        if (sum > 0xFF) {
            registers.storeRegister(15, 1);
        } else {
            registers.storeRegister(15, 0);
        }

        sum &= 0xFF;
        registers.storeRegister(data1, sum);
    }
}
