package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SetRegisterEqualConst
 */
public class SetRegisterEqualConst implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 6xkk - LD Vx, byte
         * Set Vx = kk.
         * The interpreter puts the value kk into register Vx.
         */
        registers.storeRegister(data1, (byte)data2);
    }
}
