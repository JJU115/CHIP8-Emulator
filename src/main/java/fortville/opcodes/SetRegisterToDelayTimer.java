package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SetRegisterToDelayTimer
 */
public class SetRegisterToDelayTimer implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * Fx07 - LD Vx, DT
         * Set Vx = delay timer value.
         * The value of DT is placed into Vx.
         */
        registers.storeRegister(data1, registers.getDelayTimer());

        // XXX Do we want incrementPC in opcode execution?
        registers.incrementPC();
    }
}
