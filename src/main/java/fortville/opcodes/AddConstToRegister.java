package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 7xkk - ADD Vx, byte
 * Set Vx = Vx + kk.
 * Adds the value kk to the value of register Vx,
 * then stores the result in Vx.
 * Does not affect VF.
 */
public class AddConstToRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int kk = data2;
        int vx = registers.loadRegister(data1);
        int sum = (vx + kk) & 0xFF;

        registers.storeRegister(data1, sum);
    }
}
