package fortville.interfaces;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;

/**
 * Interface that all opcode operations implement.
 */
public interface Opcode {
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers);
}
