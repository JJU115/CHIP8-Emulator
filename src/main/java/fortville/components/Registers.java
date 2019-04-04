package fortville.components;

public class Registers {

    private int PC; // Program Counter - 16-bit but only 12 bits used
    private int SP; // Stack Pointer - 8-bit
    private int I; // Index Register - 16-bit but only 12 bits used
    private int delayTimer; // 8-bit
    private int soundTimer; // 8-bit

    // 16 8-bit registers (NOTE:V[15] reserved for flag)
    private int[] V = new int[16];

    // 16 16-bit stack locations of return addresses (only 12 bits used)
    private int[] Stack = new int[16];

    public Registers() {
        // Set PC and clear everything else
        PC = 0x200;
        SP = 0;
        I = 0;
        for (int i = 0; i < 16; i++) {
            V[i] = 0;
            Stack[i] = 0;
        }
        delayTimer = 0;
        soundTimer = 0;
    }

    // Indexed from 0-15
    public void storeRegister(int regNum, int data) {
        assert regNum >= 0 && regNum <= 15;
        assert data >= 0 && data <= 0xFF;

        V[regNum] = data;
    }

    public int loadRegister(int regNum) {
        assert regNum >= 0 && regNum <= 15;

        return V[regNum];
    }

    public void storeI(int data) {
        assert data >= 0 && data <= 0xFFF;

        I = data;
    }

    public int loadI() {
        return I;
    }

    public void storeStack(int data) {
        assert data >= 0 && data <= 0xFFF;

        Stack[SP] = data;
        SP++;
    }

    public int loadStack() {
        SP--;
        return Stack[SP];
    }

    public void setPC(int data) {
        assert data >= 0 && data <= 0xFFF;

        PC = data;
    }

    public int getPC() {
        return PC;
    }

    public void incrementPC() {
        PC += 2;
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
