package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * Fx1E - ADD I, Vx
 * Set I = I + Vx.
 * The values of I and Vx are added,
 * and the results are stored in I.
 * If the result is greater than 12 bits (i.e., > 0xFFF),
 * VF is set to 1, otherwise 0.
 * Only the lowest 12 bits of the result are kept, and stored in I.
 */
public class AddRegisterToI implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int sum = registers.loadI() + registers.loadRegister(data1);

        // Set overflow flag if overflow.
        if (sum > 0xFFF) {
            registers.storeRegister(15, 1);
        } else {
            registers.storeRegister(15, 0);
        }

        sum &= 0xFFF;
        registers.storeI(sum);
    }
}
