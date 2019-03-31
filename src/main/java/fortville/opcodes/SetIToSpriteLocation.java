package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SetIToSpriteLocation
 * Fx29 - LD F, Vx
 * Set I = location of sprite for digit Vx.
 */
public class SetIToSpriteLocation implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        registers.storeI((5 * registers.loadRegister(data1)) & 0xFFF);
    }
}
