package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * Fx15 - LD DT, Vx
 * Set delay timer = Vx.
 * DT is set equal to the value of Vx.
 */
public class SetDelayTimer implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        registers.setDelayTimer(registers.loadRegister(data1));
    }
}
