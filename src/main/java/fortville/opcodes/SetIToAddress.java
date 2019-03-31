package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SetIToAddress
 */
public class SetIToAddress implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * Annn - LD I, addr
         * Set I = nnn.
         * The value of register I is set to nnn.
         */
        registers.storeI(data1 & 0xFFF);
    }
}
