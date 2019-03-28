package fortville.components;

/*
    Memory.java - Data/Instruction memory for simulated CHIP-8 Architecture
*/

import fortville.components.Registers;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Memory {
    private byte[] memory;
    private byte dataResult;
    private int targetAddress;
    Registers registers;

    public Memory(String filename, Registers registers) {
        this.registers = registers;
        memory = new byte[4096];
        BufferedInputStream fileReader;
        try {
            InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
            if (input != null) {
                fileReader = new BufferedInputStream(input);
            } else {
                fileReader = new BufferedInputStream(new FileInputStream(filename));
            }
            int read = fileReader.read(memory, 0x200, 3583);
            System.out.println("Read " + read + " bytes from " + filename);
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: The input file could not be found.");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("ERROR: an I/O error occurred.");
            System.exit(-1);
        }
        
        
        /*
         * CHIP-8 Programs can refer to groups of sprites representing
         * hexadecimal digits 0 to F.
         * Digit sprites are 5 bytes long and pre-stored in the interpreter
         * area of memory (0x000 - 0x1FF).
         * The start of each byte for digit n will be at memory[n * 5].
         */
    
        //0
        memory[0] = memory[4] = (byte)0xF0;
        memory[1] = memory[2] = memory[3] = (byte)0x90;
        
        //1
        memory[5] = memory[7] = memory[8] = (byte)0x20;
        memory[6] = (byte)0x60;
        memory[9] = (byte)0x70;

        //2
        memory[10] = memory[12] = memory[14] = (byte)0xF0;
        memory[11] = (byte)0x10;
        memory[13] = (byte)0x80;

        //3
        memory[15] = memory[17] = memory[19] = (byte)0xF0;
        memory[16] = memory[18] = (byte)0x10;

        //4
        memory[20] = memory[21] = (byte)0x90;
        memory[22] = (byte)0xF0;
        memory[23] = memory[24] = (byte)0x10;

        //5
        memory[25] = memory[27] = memory[29] = (byte)0xF0;
        memory[26] = (byte)0x80;
        memory[28] = (byte)0x10;

        //6
        memory[30] = memory[32] = memory[34] = (byte)0xF0;
        memory[31] = (byte)0x80;
        memory[33] = (byte)0x90;

        //7
        memory[35] = (byte)0xF0;
        memory[36] = (byte)0x10;
        memory[37] = (byte)0x20;
        memory[38] = memory[39] = (byte)0x40;

        //8
        memory[40] = memory[42] = memory[44] = (byte)0xF0;
        memory[41] = memory[43] = (byte)0x90;

        //9
        memory[45] = memory[47] = memory[49] = (byte)0xF0;
        memory[46] = (byte)0x90;
        memory[48] = (byte)0x10;

        //A
        memory[50] = memory[52] = (byte)0xF0;
        memory[51] = memory[53] = memory[54] = (byte)0x90;

        //B
        memory[55] = memory[57] = memory[59] = (byte)0xE0;
        memory[56] = memory[58] = (byte)0x90;

        //C
        memory[60] = memory[64] = (byte)0xF0;
        memory[61] = memory[62] = memory[63] = (byte)0x80;

        //D
        memory[65] = memory[69] = (byte)0xE0;
        memory[66] = memory[67] = memory[68] = (byte)0x90;

        //E
        memory[70] = memory[72] = memory[74] = (byte)0xF0;
        memory[71] = memory[73] = (byte)0x80;

        //F
        memory[75] = memory[77] = (byte)0xF0;
        memory[76] = memory[78] = memory[79] = (byte)0x80;
    }

    public byte load(int address) {
        return memory[address];
    }

    public short loadInstruction() {
        short instruction = memory[registers.getPC()];
        instruction <<= 8;
        instruction |= memory[registers.getPC() + 1];
        return instruction;
    }

    public void store(byte data, int address) {
        memory[address] = data;
    }

    public void setAddress(int addr) {
        targetAddress = addr;
    }

    public int read(int output) {
        return dataResult;
    }
}
