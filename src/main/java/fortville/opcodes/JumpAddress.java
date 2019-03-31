package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * JumpAddress
 */
public class JumpAddress implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 1nnn - JP addr
         * Jump to location nnn.
         * The interpreter sets the program counter to nnn.
         */
        registers.setPC(data1 & 0xFFF);
    }
}
