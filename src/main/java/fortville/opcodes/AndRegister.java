package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

/**
 * AndRegister
 */
public class AndRegister implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*8xy2 - AND Vx, Vy
		Set Vx = Vx AND Vy.
		Performs a bitwise AND on the values of Vx and Vy, then stores the result in Vx.
		A bitwise AND compares the corrseponding bits from two values, and if both bits are 1,
		then the same bit in the result is also 1. Otherwise, it is 0.*/

		byte VX = registers.loadRegister(data1);
		byte VY = registers.loadRegister(data2);
		int result = (VX & VY);
		registers.storeRegister(data1, (byte) result);

		//Do we want incrementPC in opcode execution?
		registers.incrementPC();
    }
}
