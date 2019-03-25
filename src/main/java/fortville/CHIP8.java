package fortville;

import fortville.components.Display;

public class CHIP8 {
    public static void main(String[] args) {
        System.out.println("CHIP-8");

        byte[] sp = new byte[9];
        sp[0] = 0x3C;
        sp[1] = 0x42;
        sp[2] = (byte) 0x81;
        sp[3] = (byte) 0xA5;
        sp[4] = (byte) 0x81;
        sp[5] = (byte) 0xA5;
        sp[6] = (byte) 0x99;
        sp[7] = 0x42;
        sp[8] = 0x3C;

        Display d = new Display();
        d.drawSprite(sp, 0, 0);
    }
}
