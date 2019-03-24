package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * JumpAddress
 */
public class JumpAddress implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*1nnn - JP addr
        Jump to location nnn.
        The interpreter sets the program counter to nnn.*/
        
        registers.setPC(data1);
    }
}
