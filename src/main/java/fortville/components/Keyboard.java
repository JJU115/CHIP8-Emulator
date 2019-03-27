package fortville.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Keyboard 
 * Original key mappings mapped to 
 * 1 | 2 | 3 | 4
 * Q | W | E | R
 * A | S | D | F
 * Z | X | C | V
 */
public class Keyboard extends KeyAdapter {
    boolean[] keys = new boolean[16];

    /**
     * @return the keys
     */
    public boolean[] getKeys() {
        return keys;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
        case 'x':
            keys[0] = true;
            break;
        case '1':
            keys[1] = true;
            break;
        case '2':
            keys[2] = true;
            break;
        case '3':
            keys[3] = true;
            break;
        case 'q':
            keys[4] = true;
            break;
        case 'w':
            keys[5] = true;
            break;
        case 'e':
            keys[6] = true;
            break;
        case 'a':
            keys[7] = true;
            break;
        case 's':
            keys[8] = true;
            break;
        case 'd':
            keys[9] = true;
            break;
        case 'z':
            keys[10] = true;
            break;
        case 'c':
            keys[11] = true;
            break;
        case '4':
            keys[12] = true;
            break;
        case 'r':
            keys[13] = true;
            break;
        case 'f':
            keys[14] = true;
            break;
        case 'v':
            keys[15] = true;
            break;
        default:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
        case 'x':
            keys[0] = false;
            break;
        case '1':
            keys[1] = false;
            break;
        case '2':
            keys[2] = false;
            break;
        case '3':
            keys[3] = false;
            break;
        case 'q':
            keys[4] = false;
            break;
        case 'w':
            keys[5] = false;
            break;
        case 'e':
            keys[6] = false;
            break;
        case 'a':
            keys[7] = false;
            break;
        case 's':
            keys[8] = false;
            break;
        case 'd':
            keys[9] = false;
            break;
        case 'z':
            keys[10] = false;
            break;
        case 'c':
            keys[11] = false;
            break;
        case '4':
            keys[12] = false;
            break;
        case 'r':
            keys[13] = false;
            break;
        case 'f':
            keys[14] = false;
            break;
        case 'v':
            keys[15] = false;
            break;
        default:
        }
    }
}
