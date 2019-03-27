package fortville.opcodes;

import fortville.components.Display;
import fortville.components.Memory;
import fortville.components.Registers;
import fortville.interfaces.Opcode;

/**
 * Draw
 * Dxyn - DRW Vx, Vy, nibble
 * Display n-byte sprite starting at memory location I at (Vx, Vy), set VF = collision.
 */
public class Draw implements Opcode {
    @Override
    public void execute(short data1, short data2, short data3,
        Memory memory, Display display, Registers registers) {

        short memI = registers.loadI();
        byte[] spriteData = new byte[data3];

        for (int i = 0; i < data3; i++) {
            spriteData[i] = memory.load(memI++);
        }

        registers.storeRegister((short) 15, 
            display.drawSprite(spriteData, registers.loadRegister(data1), registers.loadRegister(data2)));
            
    }
}
