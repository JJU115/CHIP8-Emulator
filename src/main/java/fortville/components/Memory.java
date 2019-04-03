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
    private int[] memory;

    public Memory(String filename, Registers registers) {
        byte[] byteMemory = new byte[4096]; // Temporary byte array for file reads.
        memory = new int[4096];
        BufferedInputStream fileReader;
        try {
            InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
            if (input != null) {
                fileReader = new BufferedInputStream(input);
            } else {
                fileReader = new BufferedInputStream(new FileInputStream(filename));
            }

            int read = 0;
            try {
                read = fileReader.read(byteMemory, 0x200, 3583);
            } catch (IOException e) {
                System.err.println("ERROR: an I/O error occurred in file read.");
                System.exit(-1);
            } finally {
                fileReader.close();
                System.out.println("Read " + read + " bytes from " + filename);
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: The input file could not be found.");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("ERROR: an I/O error occurred.");
            System.exit(-1);
        }

        // Copy byte array to int array that holds values no bigger than a byte.
        for (int i = 0; i < 4096; i++) {
            memory[i] = Byte.toUnsignedInt(byteMemory[i]);
        }

        /*
         * CHIP-8 Programs can refer to groups of sprites representing
         * hexadecimal digits 0 to F.
         * Digit sprites are 5 bytes long and pre-stored in the interpreter
         * area of memory (0x000 - 0x1FF).
         * The start of each byte for digit n will be at memory[n * 5].
         */

        //0
        memory[0] = memory[4] = 0xF0;
        memory[1] = memory[2] = memory[3] = 0x90;

        //1
        memory[5] = memory[7] = memory[8] = 0x20;
        memory[6] = 0x60;
        memory[9] = 0x70;

        //2
        memory[10] = memory[12] = memory[14] = 0xF0;
        memory[11] = 0x10;
        memory[13] = 0x80;

        //3
        memory[15] = memory[17] = memory[19] = 0xF0;
        memory[16] = memory[18] = 0x10;

        //4
        memory[20] = memory[21] = 0x90;
        memory[22] = 0xF0;
        memory[23] = memory[24] = 0x10;

        //5
        memory[25] = memory[27] = memory[29] = 0xF0;
        memory[26] = 0x80;
        memory[28] = 0x10;

        //6
        memory[30] = memory[32] = memory[34] = 0xF0;
        memory[31] = 0x80;
        memory[33] = 0x90;

        //7
        memory[35] = 0xF0;
        memory[36] = 0x10;
        memory[37] = 0x20;
        memory[38] = memory[39] = 0x40;

        //8
        memory[40] = memory[42] = memory[44] = 0xF0;
        memory[41] = memory[43] = 0x90;

        //9
        memory[45] = memory[47] = memory[49] = 0xF0;
        memory[46] = 0x90;
        memory[48] = 0x10;

        //A
        memory[50] = memory[52] = 0xF0;
        memory[51] = memory[53] = memory[54] = 0x90;

        //B
        memory[55] = memory[57] = memory[59] = 0xE0;
        memory[56] = memory[58] = 0x90;

        //C
        memory[60] = memory[64] = 0xF0;
        memory[61] = memory[62] = memory[63] = 0x80;

        //D
        memory[65] = memory[69] = 0xE0;
        memory[66] = memory[67] = memory[68] = 0x90;

        //E
        memory[70] = memory[72] = memory[74] = 0xF0;
        memory[71] = memory[73] = 0x80;

        //F
        memory[75] = memory[77] = 0xF0;
        memory[76] = memory[78] = memory[79] = 0x80;
    }

    public int load(int address) {
        // XXX Add assertion that address is only 12-bits in size.
        return memory[address & 0xFFF];
    }

    public int loadInstruction(int address) {
        // XXX Add assertion that address is only 12-bits in size.
        int instruction = memory[address & 0xFFF];
        instruction <<= 8;
        instruction |= memory[(address + 1) & 0xFFF];
        instruction &= 0xFFFF; // Instructions are 16 bits.
        return instruction;
    }

    public void store(int data, int address) {
        // XXX Add assertion that data is only 8-bits in size.
        // XXX Add assertion that address is only 12-bits in size.
        memory[address & 0xFFF] = data & 0xFF;
    }
}
