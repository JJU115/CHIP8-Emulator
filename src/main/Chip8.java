package main;
import java.io.File;

import components.Control;
import components.Memory;
import components.Registers;
/**
 * Chip8
 */
public class Chip8 {
    public static void main(String[] args){
        Control control = new Control();
        Memory memory = new Memory(control, new File(args[1]));
        control.setMemory(memory);
        
        Registers register = new Registers();
    }

    
}