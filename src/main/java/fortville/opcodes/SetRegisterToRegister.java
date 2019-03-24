package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * SetRegisterToRegister
 */
public class SetRegisterToRegister implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*8xy0 - LD Vx, Vy
		Set Vx = Vy.
		Stores the value of register Vy in register Vx.*/
		registers.storeRegister(data1, registers.loadRegister(data2));

		//Do we want incrementPC in opcode execution?
		registers.incrementPC();
    }
}
