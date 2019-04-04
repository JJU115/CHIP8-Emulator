package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * Ex9E - SKP Vx
 * Skip next instruction if key with the value of Vx is pressed.
 * Checks the keyboard, and if the key corresponding to the value of Vx is currently in the down position, PC is increased by 2.
 */
public class SkipKeyEqualRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        if (display.getKeys()[registers.loadRegister(data1)]) {
            registers.incrementPC();
        }
    }
}
