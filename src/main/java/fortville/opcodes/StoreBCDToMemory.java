package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * StoreBCDToMemory
 */
public class StoreBCDToMemory implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * Fx33 - LD B, Vx
         * Store BCD representation of Vx in memory locations I, I+1, and I+2.
         * The interpreter takes the decimal value of Vx,
         * and places the hundreds digit in memory at location in I,
         * the tens digit at location I+1,
         * and the ones digit at location I+2.
         */
        // Read value from Vx. It is in data1. Watch out for signed bytes.
        int bcd = Byte.toUnsignedInt(registers.loadRegister(data1));

        byte hundreds = (byte)(bcd / 100);
        bcd %= 100;
        byte tens = (byte)(bcd / 10);
        bcd %= 10;
        byte ones = (byte)bcd;

        short addrOfI = registers.loadI(); // Get memory address from I.
        memory.store(hundreds, addrOfI);
        memory.store(tens, addrOfI + 1);
        memory.store(ones, addrOfI + 2);
    }
}
