package fortville.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Simulates the Chip-8's 64x32 display, also provides access to the keyboard listener
 */
public class Display {

    private JFrame display;
    private DrawPanel panel;
    private Keyboard keyboard;
    private int[][] setPixels;

    class DrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 1290, 680);
            g.setColor(Color.WHITE);

            for (int i = 0; i < 64; i++) {
                for (int j = 0; j < 32; j++) {
                    if (setPixels[i][j] == 1) {
                        g.fillRect(i * 20, j * 20, 20, 20);
                    }
                }
            }
        }
    }

    public Display() {
        display = new JFrame("CHIP-8");
        setPixels = new int[64][32];
        panel = new DrawPanel();
        keyboard = new Keyboard();

        display.addKeyListener(keyboard);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setSize(new Dimension(1290, 680));
        display.setLocation(0, 0);
        display.setResizable(false);
        display.add(panel);
        display.setVisible(true);
    }


    public int drawSprite(int[] sprite, int x, int y) {

        int collision = 0;
        int num;
        int xco;
        int yco;

        for (int i = 0; i < sprite.length; i++) {
            for (int j = 0; j < 8; j++) {
                num = ((int)Math.pow(2, 7 - j) & sprite[i]) >> 7 - j;
                xco = (x + j) % 64;
                yco = (y + i) % 32;

                if (collision == 0) {
                    if ((setPixels[xco][yco] & num) > 0) {
                        collision = 1;
                    }
                }

                setPixels[xco][yco] ^= num;
            }
        }

        panel.repaint();
        return collision;
    }

    public void clear() {
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 32; j++) {
                setPixels[i][j] = 0;
            }
        }
        panel.repaint();
    }

    public boolean[] getKeys() {
        return keyboard.getKeys();
    }

    public int waitForKey() {
        return keyboard.waitForKey();
    }
}
