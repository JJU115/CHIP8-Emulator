package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * Dxyn - DRW Vx, Vy, nibble
 * Display n-byte sprite starting at
 * memory location I at (Vx, Vy),
 * set VF = collision.
 */
public class Draw implements Opcode {
    @Override
    public void execute(int data1, int data2, int data3,
        Memory memory, Display display, Registers registers) {

        int vx = registers.loadRegister(data1);
        int vy = registers.loadRegister(data2);

        int addrOfI = registers.loadI();
        int numBytes = data3 & 0xFF;
        int[] spriteData = new int[numBytes];

        for (int i = 0; i < numBytes; i++) {
            spriteData[i] = memory.load(addrOfI++);
        }

        int collision = display.drawSprite(spriteData, vx, vy);
        registers.storeRegister(15, collision);
    }
}
