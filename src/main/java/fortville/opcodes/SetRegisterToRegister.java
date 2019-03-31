package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SetRegisterToRegister
 */
public class SetRegisterToRegister implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * 8xy0 - LD Vx, Vy
         * Set Vx = Vy.
         * Stores the value of register Vy in register Vx.
         */
        registers.storeRegister(data1, registers.loadRegister(data2));
    }
}
