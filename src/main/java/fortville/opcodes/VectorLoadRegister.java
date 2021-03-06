package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * Fx65 - LD Vx, [I]
 * Read registers V0 through Vx from memory starting at location I.
 * The interpreter reads values from memory starting at location I
 * into registers V0 through Vx.
 */
public class VectorLoadRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int vx = data1;
        int addrOfI = registers.loadI();

        for (int regNum = 0; regNum <= vx; regNum++) {
            int data = memory.load(addrOfI + regNum);
            registers.storeRegister(regNum, data);
        }
    }
}
