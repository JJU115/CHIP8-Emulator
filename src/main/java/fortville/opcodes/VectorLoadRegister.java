package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * VectorLoadRegister
 */
public class VectorLoadRegister implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * Fx65 - LD Vx, [I]
         * Read registers V0 through Vx from memory starting at location I.
         * The interpreter reads values from memory starting at location I
         * into registers V0 through Vx.
         */
        short VX = data1;
        short addrOfI = registers.loadI(); // Get memory address from I.

        for (short regNum = 0; regNum <= VX; regNum++) {
            byte data = memory.load(addrOfI + regNum);
            registers.storeRegister(regNum, data);
        }
    }
}
