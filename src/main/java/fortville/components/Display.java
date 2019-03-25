package fortville.components;

import java.awt.*;
import javax.swing.*;

public class Display {

    private JFrame display;
    private DrawPanel panel;
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


    public int drawSprite(byte[] sprite, int x, int y) {

        int collision = 0;
        for (int i = 0; i < sprite.length; i++) {
            for (int j = 0; j < 8; j++) {
                if (collision == 0) {
                    if ((setPixels[x + j][y + i]
                          & ((int)Math.pow(2, 7 - j) & sprite[i]) >> 7 - j) > 0) {
                        collision = 1;
                    }
                }

                setPixel(x + j, y + i, ((int)Math.pow(2, 7 - j) & sprite[i]) >> 7 - j);
            }
        }

        return collision;
    }


    // Main method for testing purposes only
    public static void main(String[] args) {
        byte[] sp = new byte[9];
        sp[0] = 0x3C;
        sp[1] = 0x42;
        sp[2] = (byte)0x81;
        sp[3] = (byte)0xA5;
        sp[4] = (byte)0x81;
        sp[5] = (byte)0xA5;
        sp[6] = (byte)0x99;
        sp[7] = 0x42;
        sp[8] = 0x3C;

        Display d = new Display();
        d.drawSprite(sp, 0, 0);
    }
}
