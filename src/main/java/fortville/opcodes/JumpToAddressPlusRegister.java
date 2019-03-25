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
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * Bnnn - JP V0, addr
         * Jump to location nnn + V0.
         * The program counter is set to nnn plus the value of V0.
         */
        registers.setPC((short)(data1 + (0xff & registers.loadRegister((short)0))));
    }
}
