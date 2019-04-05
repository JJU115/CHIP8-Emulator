package fortville.components;

/**
 * Simulates the registers found in the original Chip-8 architecture.
 */
public class Registers {

    private int regPC; // Program Counter - 16-bit but only 12 bits used
    private int regSP; // Stack Pointer - 8-bit
    private int regI; // Index Register - 16-bit but only 12 bits used
    private int delayTimer; // 8-bit
    private int soundTimer; // 8-bit

    // 16 8-bit registers (NOTE:regV[15] reserved for flag)
    private int[] regV = new int[16];

    // 16 16-bit stack locations of return addresses (only 12 bits used)
    private int[] stack = new int[16];

    public Registers() {
        // Set regPC and clear everything else
        regPC = 0x200;
        regSP = 0;
        regI = 0;
        for (int i = 0; i < 16; i++) {
            regV[i] = 0;
            stack[i] = 0;
        }
        delayTimer = 0;
        soundTimer = 0;
    }

    // Indexed from 0-15
    public void storeRegister(int regNum, int data) {
        assert regNum >= 0 && regNum <= 15;
        assert data >= 0 && data <= 0xFF;

        regV[regNum] = data;
    }

    public int loadRegister(int regNum) {
        assert regNum >= 0 && regNum <= 15;

        return regV[regNum];
    }

    public void storeI(int data) {
        assert data >= 0 && data <= 0xFFF;

        regI = data;
    }

    public int loadI() {
        return regI;
    }

    public void storeStack(int data) {
        assert data >= 0 && data <= 0xFFF;

        stack[regSP] = data;
        regSP++;
    }

    public int loadStack() {
        regSP--;
        return stack[regSP];
    }

    public void setPC(int data) {
        assert data >= 0 && data <= 0xFFF;

        regPC = data;
    }

    public int getPC() {
        return regPC;
    }

    public void incrementPC() {
        regPC += 2;
    }

    public void setDelayTimer(int data) {
        assert data >= 0 && data <= 0xFF;

        delayTimer = data;
    }

    public int getDelayTimer() {
        return delayTimer;
    }

    public void setSoundTimer(int data) {
        assert data >= 0 && data <= 0xFF;

        soundTimer = data;
    }

    public int getSoundTimer() {
        return soundTimer;
    }

    public void deincrementTimers() {
        if (soundTimer > 0) {
            Thread thread = new Thread(new Sound());
            thread.start();
            soundTimer--;
        }
        if (delayTimer > 0) {
            delayTimer--;
        }
    }
}
