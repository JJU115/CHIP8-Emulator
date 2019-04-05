package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 00EE - RET
 * Return from a subroutine.
 * The interpreter sets the program counter to the
 * address at the top of the stack,
 * then subtracts 1 from the stack pointer.
 */
public class ReturnSubroutine implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        registers.setPC(registers.loadStack());
    }
}
