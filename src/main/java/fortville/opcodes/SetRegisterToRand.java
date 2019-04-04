package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

import java.util.Random;

/**
 * Cxkk - RND Vx, byte
 * Set Vx = random byte AND kk.
 * The interpreter generates a random number from 0 to 255,
 * which is then ANDed with the value kk.
 * The results are stored in Vx.
 * See instruction 8xy2 for more information on AND.
 */
public class SetRegisterToRand implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {
        
        Random rand = new Random();
        registers.storeRegister(data1, rand.nextInt(256) & data2);
    }
}
