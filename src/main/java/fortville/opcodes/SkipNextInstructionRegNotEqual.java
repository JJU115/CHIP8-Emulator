package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 9xy0 - SNE Vx, Vy
 * Skip next instruction if Vx != Vy.
 * The values of Vx and Vy are compared, and if they are not equal,
 * the program counter is increased by 2.
 */
public class SkipNextInstructionRegNotEqual implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int vx = registers.loadRegister(data1);
        int vy = registers.loadRegister(data2);
        if (vx != vy) {
            registers.incrementPC();
        }
    }
}
