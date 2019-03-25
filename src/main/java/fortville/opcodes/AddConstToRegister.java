package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * AddConstToRegister
 */
public class AddConstToRegister implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 7xkk - ADD Vx, byte
         * Set Vx = Vx + kk.
         * Adds the value kk to the value of register Vx,
         * then stores the result in Vx.
         */
        int kk = data2;
        int VX = registers.loadRegister(data1);
        int sum = VX + kk;

        if (sum >= 256) {
            sum -= 256;
        }
        registers.storeRegister(data1, (byte)sum);

        // XXX Do we want incrementPC in opcode execution?
        registers.incrementPC();
    }
}
