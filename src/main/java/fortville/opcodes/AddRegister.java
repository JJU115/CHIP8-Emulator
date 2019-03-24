package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * addRegister
 */
public class AddRegister implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*8xy4 - ADD Vx, Vy
		Set Vx = Vx + Vy, set VF = carry.
		The values of Vx and Vy are added together. If the result is greater than 8 bits (i.e., > 255,) 
		VF is set to 1, otherwise 0. Only the lowest 8 bits of the result are kept, and stored in Vx.*/
		int VX = (registers.loadRegister(data1) & 0xff);
		int VY = (registers.loadRegister(data2) & 0xff);
		int sum = VX + VY;

		if(sum>=256){
			registers.storeRegister((short) 15, (byte) 1);
			sum -= 256;
		}else{
			registers.storeRegister((short) 15, (byte) 0);
		}
		registers.storeRegister(data1, (byte) sum);

		//Do we want incrementPC in opcode execution?
		registers.incrementPC();
    }
}
