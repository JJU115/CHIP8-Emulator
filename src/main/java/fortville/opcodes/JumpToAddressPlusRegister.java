package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * JumpToAddressPlusRegister
 */
public class JumpToAddressPlusRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * Bnnn - JP V0, addr
         * Jump to location nnn + V0.
         * The program counter is set to nnn plus the value of V0.
         * The PC is 16-bit, but only 12 bits are used since
         * RAM is 4096 bytes, so truncate any overflow.
         */
        registers.setPC((data1 + registers.loadRegister(0)) & 0xFFF);
    }
}
