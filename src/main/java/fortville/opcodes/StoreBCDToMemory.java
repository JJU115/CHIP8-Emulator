package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * Fx33 - LD B, Vx
 * Store BCD representation of Vx in memory locations I, I+1, and I+2.
 * The interpreter takes the decimal value of Vx,
 * and places the hundreds digit in memory at location in I,
 * the tens digit at location I+1,
 * and the ones digit at location I+2.
 */
public class StoreBCDToMemory implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int bcd = registers.loadRegister(data1);

        int hundreds = bcd / 100;
        bcd %= 100;
        int tens = bcd / 10;
        bcd %= 10;
        int ones = bcd;

        int addrOfI = registers.loadI();
        memory.store(hundreds, addrOfI);
        memory.store(tens, addrOfI + 1);
        memory.store(ones, addrOfI + 2);
    }
}
