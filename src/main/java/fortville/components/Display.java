package fortville.components;

import java.awt.*;
import javax.swing.*;

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
        display.setLocation(610, 290);
        display.setResizable(false);
        display.add(panel);
        display.setVisible(true);
    }

    public void setPixel(int x, int y, int p) {
        setPixels[x][y] = setPixels[x][y] ^ p;
        panel.repaint();
    }

    public int drawSprite(int[] sprite, int x, int y) {

        int collision = 0;
        for (int i = 0; i < sprite.length; i++) {
            for (int j = 0; j < 8; j++) {
                if (collision == 0) {
                    if ((setPixels[(x + j) % 64][(y + i) % 32]
                          & ((int)Math.pow(2, 7 - j) & sprite[i]) >> 7 - j) > 0) {
                        collision = 1;
                    }
                }

                setPixel((x + j) % 64, (y + i) % 32,
                    ((int)Math.pow(2, 7 - j) & sprite[i]) >> 7 - j);
            }
        }

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
