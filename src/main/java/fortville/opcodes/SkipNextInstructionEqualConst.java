package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SkipNextInstructionEqualConst
 */
public class SkipNextInstructionEqualConst implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 3xkk - SE Vx, byte
         * Skip next instruction if Vx = kk.
         * The interpreter compares register Vx to kk,
         * and if they are equal,
         * increments the program counter by 2.
         */
        int VX = registers.loadRegister(data1);
        int kk = data2;
        if (VX == kk) {
            registers.incrementPC();
        }
    }
}
