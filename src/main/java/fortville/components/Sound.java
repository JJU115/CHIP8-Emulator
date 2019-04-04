package fortville.components;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

/**
 * Plays a tone for one clock cycle in a thread
 */
public class Sound implements Runnable {

    public static float SAMPLE_RATE = 8000f;

    /**
     * Tone code borrowed from
     * https://stackoverflow.com/questions/3780406/how-to-play-a-sound-alert-in-a-java-application/6700039#6700039
     */
    public void tone(int hz, int msecs, double vol) {
        byte[] buf = new byte[1];
        AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
        try {
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();
            for (int i = 0; i < msecs * 8; i++) {
                double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
                sdl.write(buf, 0, 1);
            }
            sdl.drain();
            sdl.stop();
            sdl.close();
        } catch (Exception e) {
            System.err.println("ERROR: Tone output not found.");
        }
    }

    @Override
    public void run() {
        tone(400, 1, 1.0);
    }
}
