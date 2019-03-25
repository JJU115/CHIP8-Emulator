package fortville.components;

public class Registers {

    private short PC;           // Program Counter
    private byte SP;            // Stack Pointer
    private short I;            // Generally used to store memory addresses
    private byte delayTimer;
    private byte soundTimer;

    // 16 8-bit registers (NOTE:V[15] reserved for flag)
    private byte[] V = new byte[16];

    private short[] Stack = new short[16];      // 16 16-bit stack locations

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
    public void storeRegister(short regNum, byte data) {
        V[regNum] = data;
    }

    public byte loadRegister(short regNum) {
        return V[regNum];
    }

    public void storeI(short data) {
        I = data;
    }

    public short loadI() {
        return I;
    }

    public void storeStack(short data) {
        Stack[SP] = data;
        SP++;
    }

    public short loadStack() {
        SP--;
        return Stack[SP];
    }

    public short getPC() {
        return PC;
    }

    public void setPC(short setData) {
        PC = setData;
    }

    public void incrementPC() {
        PC += 2;
    }

    public void setDelayTimer(byte data) {
        delayTimer = data;
    }

    public byte getDelayTimer() {
        return delayTimer;
    }

    public void setSoundTimer(byte data) {
        soundTimer = data;
    }

    public byte getSoundTimer() {
        return soundTimer;
    }
}
