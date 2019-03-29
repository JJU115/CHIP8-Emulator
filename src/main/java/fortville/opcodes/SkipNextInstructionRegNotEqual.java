package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SkipNextInstructionRegNotEqual
 */
public class SkipNextInstructionRegNotEqual implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 9xy0 - SNE Vx, Vy
         * Skip next instruction if Vx != Vy.
         * The values of Vx and Vy are compared, and if they are not equal,
         * the program counter is increased by 2.
         */
        Byte a = registers.loadRegister(data1);
        Byte b = registers.loadRegister(data2);
        if (a != b) {
            registers.incrementPC();
        }
    }
}
