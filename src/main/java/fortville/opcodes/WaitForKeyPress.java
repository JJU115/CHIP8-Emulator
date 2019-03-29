package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Keyboard;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * WaitForKeyPress
 * Wait for a key press, store the value of the key in Vx.
 * All execution stops until a key is pressed, then the value of that key is stored in Vx.
 */
public class WaitForKeyPress implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {
        registers.storeRegister(data1, display.waitForKey());
    }
}
