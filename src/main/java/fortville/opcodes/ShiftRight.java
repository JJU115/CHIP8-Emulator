package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * 8xy6 - SHR Vx {, Vy}
 * Set Vx = Vx SHR 1.
 * If the least-significant bit of Vx is 1,
 * then VF is set to 1, otherwise 0.
 * Then Vx is divided by 2.
 */
public class ShiftRight implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int value = registers.loadRegister(data1);
        if ((value & 0x01) == 1) {
            registers.storeRegister(15, 1);
        } else {
            registers.storeRegister(15, 0);
        }
        value >>= 1;
        value &= 0xFF;
        registers.storeRegister(data1, value);
    }
}
