package fortville.components;


/*
    Memory.java - Data/Instruction memory for simulated CHIP-8 Architecture
*/


// XXX import fortville.interfaces.ReadableDevice;
import fortville.components.Registers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;


public class Memory {
    private byte[] memory;
    private byte dataResult;
    private int targetAddress;
    ReadableDevice control;
    Registers registers;


    public Memory(ReadableDevice control, File input) {
        this.control = control;
        memory = new byte[4096];
        
        FileInputStream fileReader;
        try {
            fileReader = new FileInputStream(input);
            int read = fileReader.read(memory, 0x200, 3583);
            System.out.println("Read " + read + " bytes from " + input.getName());

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: The input file could not be found.");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("ERROR: an I/O error occurred.");
            System.exit(-1);
        }
    }


    public void clock() {
        switch (control.read(0)){
            case 1:
                store(dataResult, targetAddress);
                break;
            case 2:
                dataResult = load(targetAddress);
                break;
        }    
    }


    public byte load(int address) {
        return memory[address];
    }
    public int loadInstruction(){
        int instruction = memory[registers.getPC()];
        instruction = instruction << 8;
        instruction = instruction | (int) memory[registers.getPC() +1];
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
