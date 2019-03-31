package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SkipKeyNotEqualRegister
 */
public class SkipKeyNotEqualRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        if (!display.getKeys()[registers.loadRegister(data1)]) {
            registers.incrementPC();
        }
    }
}
