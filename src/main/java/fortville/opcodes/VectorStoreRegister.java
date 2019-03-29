package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * VectorStoreRegister
 */
public class VectorStoreRegister implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * Fx55 - LD [I], Vx
         * Store registers V0 through Vx in memory starting at location I.
         * The interpreter copies the values of registers V0 through Vx
         * into memory, starting at the address in I.
         */
        short VX = data1;
        short addrOfI = registers.loadI(); // Get memory address from I.

        for (short regNum = 0; regNum <= VX; regNum++) {
            byte data = registers.loadRegister(regNum);
            memory.store(data, addrOfI + regNum);
        }
    }
}
