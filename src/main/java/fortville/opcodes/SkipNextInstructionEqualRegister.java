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
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 5xy0 - SE Vx, Vy
         * Skip next instruction if Vx = Vy.
         * The interpreter compares register Vx to register Vy,
         * and if they are equal, increments the program counter by 2.
         */
        int VX = registers.loadRegister(data1);
        int VY = registers.loadRegister(data2);
        if (VX == VY) {
            registers.incrementPC();
        }
    }
}
