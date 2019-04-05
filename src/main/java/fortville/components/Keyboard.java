package fortville.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Simulates the keyboard found on the original hardware that runs CHIP-8.
 * Maps the orignal Chip-8 keyboard
 * 1 | 2 | 3 | C
 * 4 | 5 | 6 | D
 * 7 | 8 | 9 | E
 * A | 0 | B | F
 * to the new keymapping
 * 1 | 2 | 3 | 4
 * Q | W | E | R
 * A | S | D | F
 * Z | X | C | V
 */
public class Keyboard extends KeyAdapter {

    boolean[] keys = new boolean[16];
    boolean keyDown = false;
    int lastKey;

    public boolean[] getKeys() {
        return keys;
    }

    public int waitForKey() {
        keyDown = false;
        while (!keyDown) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.err.println("ERROR: Keyboard Interruption");
                System.exit(-1);
            }
        }
        return lastKey;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyDown = true;
        switch (e.getKeyCode()) {
        case KeyEvent.VK_X:
            keys[0] = true;
            lastKey = 0;
            break;
        case KeyEvent.VK_1:
            keys[1] = true;
            lastKey = 1;
            break;
        case KeyEvent.VK_2:
            keys[2] = true;
            lastKey = 2;
            break;
        case KeyEvent.VK_3:
            keys[3] = true;
            lastKey = 3;
            break;
        case KeyEvent.VK_Q:
            keys[4] = true;
            lastKey = 4;
            break;
        case KeyEvent.VK_W:
            keys[5] = true;
            lastKey = 5;
            break;
        case KeyEvent.VK_E:
            keys[6] = true;
            lastKey = 6;
            break;
        case KeyEvent.VK_A:
            keys[7] = true;
            lastKey = 7;
            break;
        case KeyEvent.VK_S:
            keys[8] = true;
            lastKey = 8;
            break;
        case KeyEvent.VK_D:
            keys[9] = true;
            lastKey = 9;
            break;
        case KeyEvent.VK_Z:
            keys[10] = true;
            lastKey = 10;
            break;
        case KeyEvent.VK_C:
            keys[11] = true;
            lastKey = 11;
            break;
        case KeyEvent.VK_4:
            keys[12] = true;
            lastKey = 12;
            break;
        case KeyEvent.VK_R:
            keys[13] = true;
            lastKey = 13;
            break;
        case KeyEvent.VK_F:
            keys[14] = true;
            lastKey = 14;
            break;
        case KeyEvent.VK_V:
            keys[15] = true;
            lastKey = 15;
            break;
        default:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_X:
            keys[0] = false;
            break;
        case KeyEvent.VK_1:
            keys[1] = false;
            break;
        case KeyEvent.VK_2:
            keys[2] = false;
            break;
        case KeyEvent.VK_3:
            keys[3] = false;
            break;
        case KeyEvent.VK_Q:
            keys[4] = false;
            break;
        case KeyEvent.VK_W:
            keys[5] = false;
            break;
        case KeyEvent.VK_E:
            keys[6] = false;
            break;
        case KeyEvent.VK_A:
            keys[7] = false;
            break;
        case KeyEvent.VK_S:
            keys[8] = false;
            break;
        case KeyEvent.VK_D:
            keys[9] = false;
            break;
        case KeyEvent.VK_Z:
            keys[10] = false;
            break;
        case KeyEvent.VK_C:
            keys[11] = false;
            break;
        case KeyEvent.VK_4:
            keys[12] = false;
            break;
        case KeyEvent.VK_R:
            keys[13] = false;
            break;
        case KeyEvent.VK_F:
            keys[14] = false;
            break;
        case KeyEvent.VK_V:
            keys[15] = false;
            break;
        default:
        }
    }
}
