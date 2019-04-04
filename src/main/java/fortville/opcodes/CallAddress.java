package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 2nnn - CALL addr
 * Call subroutine at nnn.
 * The interpreter increments the stack pointer,
 * then puts the current PC on the top of the stack.
 * The PC is then set to nnn.
 */
public class CallAddress implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        registers.storeStack(registers.getPC());
        registers.setPC(data1);
    }
}
