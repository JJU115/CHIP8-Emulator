package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * ReturnSubroutine
 */
public class ReturnSubroutine implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*00EE - RET
		Return from a subroutine.
		The interpreter sets the program counter to the address at the top of the stack, then subtracts 1 from the stack pointer.*/
		registers.setPC(registers.loadStack());
    }
}
