package interfacesv2;

import components.Memory;
import components.Registers;
import componentsv2.Display;

/**
 * opcode
 */
public interface opcode {
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers);
}