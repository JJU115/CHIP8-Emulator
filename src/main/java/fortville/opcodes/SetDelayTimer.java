package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * SetDelayTimer
 */
public class SetDelayTimer implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*Fx15 - LD DT, Vx
		Set delay timer = Vx.
		DT is set equal to the value of Vx.*/
		registers.setDelayTimer(registers.loadRegister(data1));

		//Do we want incrementPC in opcode execution?
		registers.incrementPC();
    }
}
