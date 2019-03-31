package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * SetSoundTimer
 */
public class SetSoundTimer implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        /*
         * Fx18 - LD ST, Vx
         * Set sound timer = Vx.
         * ST is set equal to the value of Vx.
         */
        registers.setSoundTimer(registers.loadRegister(data1));
    }
}
