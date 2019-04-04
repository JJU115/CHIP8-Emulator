package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 6xkk - LD Vx, byte
 * Set Vx = kk.
 * The interpreter puts the value kk into register Vx.
 */
public class SetRegisterEqualConst implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
       
        registers.storeRegister(data1, data2);
    }
}
