package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * ShiftRight
 */
public class ShiftRight implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*8xy6 - SHR Vx {, Vy}
		Set Vx = Vx SHR 1.
		If the least-significant bit of Vx is 1, then VF is set to 1, otherwise 0. Then Vx is divided by 2.*/
		int value = registers.loadRegister(data1);
		if((value & 0x01)==1){
			registers.storeRegister((short) 15, (byte) 1);
		}else{
			registers.storeRegister((short) 15, (byte) 0);
		}
		value = value >> 1;
		registers.storeRegister(data1, (byte) value);

		//Do we want incrementPC in opcode execution?
		registers.incrementPC();
    }
}
