package fortville.interfaces;

import fortville.components.Memory;
import fortville.components.Registers;
import fortville.components.Display;

/**
 * Opcode
 */
public interface Opcode {
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers);
}
