package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * SubtractInvertBorrow
 */
public class SubtractInvertBorrow implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*8xy7 - SUBN Vx, Vy
		Set Vx = Vy - Vx, set VF = NOT borrow.
		If Vy > Vx, then VF is set to 1, otherwise 0. Then Vx is subtracted from Vy, and the results stored in Vx.*/
		int VX = (0xff & (registers.loadRegister(data1)));
		int VY = (0xff & (registers.loadRegister(data2)));
		if(VX < VY){
			registers.storeRegister((short) 15, (byte) 1);
		}else{
			registers.storeRegister((short) 15, (byte) 0);
		}

		VX = ((VY - VX) & 0xff);
		registers.storeRegister(data1, (byte) VX);

		//Do we want incrementPC in opcode execution?
		registers.incrementPC();
    }
}
