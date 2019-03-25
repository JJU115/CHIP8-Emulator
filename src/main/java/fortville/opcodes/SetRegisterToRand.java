package fortville.opcodes;

import fortville.interfaces.Opcode;
import fortville.components.Memory;
import fortville.components.Display;
import fortville.components.Registers;

import java.util.Random;

/**
 * SetRegisterToRand
 */
public class SetRegisterToRand implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*Cxkk - RND Vx, byte
		Set Vx = random byte AND kk.
		The interpreter generates a random number from 0 to 255, which is then ANDed with the value kk.
		The results are stored in Vx. See instruction 8xy2 for more information on AND.*/
		Random rand = new Random();
		registers.storeRegister(data1, (byte) ((rand.nextInt(256)) & data2));

		//Do we want incrementPC in opcode execution?
		registers.incrementPC();
    }
}
