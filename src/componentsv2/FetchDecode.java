package componentsv2;

import components.Memory;

/**
 * FetchDecode
 */
public class FetchDecode {

    Memory memory;
    Registers registers;
    public FetchDecode(Memory memory, Registers registers){
        this.memory = memory;
        this.registers = registers;
    }
}