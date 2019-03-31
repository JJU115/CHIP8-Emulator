package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Keyboard;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * WaitForKeyPress
 */
public class WaitForKeyPress implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * Fx0A - LD Vx, K
         * Wait for a key press, store the value of the key in Vx.
         * All execution stops until a key is pressed,
         * then the value of that key is stored in Vx.
         */
        registers.storeRegister(data1, display.waitForKey());
    }
}
