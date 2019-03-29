package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SkipNextInstructionEqualRegister
 */
public class SkipNextInstructionEqualRegister implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 5xy0 - SE Vx, Vy
         * Skip next instruction if Vx = Vy.
         * The interpreter compares register Vx to register Vy,
         * and if they are equal, increments the program counter by 2.
         */
        Byte a = registers.loadRegister(data1);
        Byte b = registers.loadRegister(data2);
        if (a == b) {
            registers.incrementPC();
        }
    }
}
