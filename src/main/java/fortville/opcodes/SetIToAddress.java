package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * SetIToAddress
 */
public class SetIToAddress implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*Annn - LD I, addr
		Set I = nnn.
		The value of register I is set to nnn.*/
		
		registers.storeI(data1);

		//Do we want incrementPC in opcode execution?
		registers.incrementPC();
    }
}
