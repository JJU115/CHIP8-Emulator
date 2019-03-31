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
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 9xy0 - SNE Vx, Vy
         * Skip next instruction if Vx != Vy.
         * The values of Vx and Vy are compared, and if they are not equal,
         * the program counter is increased by 2.
         */
        int VX = registers.loadRegister(data1);
        int VY = registers.loadRegister(data2);
        if (VX != VY) {
            registers.incrementPC();
        }
    }
}
