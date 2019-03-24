package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * CallAddress
 */
public class CallAddress implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*2nnn - CALL addr
		Call subroutine at nnn.
		The interpreter increments the stack pointer, then puts the current PC on the top of the stack.
		The PC is then set to nnn.*/

		registers.storeStack(registers.getPC());
		registers.setPC(data1);
    }
}
