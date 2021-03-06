package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * Fx55 - LD [I], Vx
 * Store registers V0 through Vx in memory starting at location I.
 * The interpreter copies the values of registers V0 through Vx
 * into memory, starting at the address in I.
 */
public class VectorStoreRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int vx = data1;
        int addrOfI = registers.loadI();

        for (int regNum = 0; regNum <= vx; regNum++) {
            int data = registers.loadRegister(regNum);
            memory.store(data, addrOfI + regNum);
        }
    }
}
