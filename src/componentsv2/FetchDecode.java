package componentsv2;

import components.Memory;
import interfacesv2.opcode;
import opcodes.ClearDisplayOpcode;

/**
 * FetchDecode
 */
public class FetchDecode {

    Memory memory;
    Registers registers;
    Buffer fetchDecodeBuffer;

    public FetchDecode(Memory memory, Registers registers, Buffer fetcBuffer){
        this.memory = memory;
        this.registers = registers;
        this.fetchDecodeBuffer = fetcBuffer;
    }

    public void clock(){
        int opcode = memory.loadInstruction();
        byte firstNum = instruction & 0xF000;
        byte lastNum = instruction & 0x000F;
        switch(firstNum){
            case 0:
                switch(lastNum){
                    case 0x0:
                        fetchDecodeBuffer.setOpcode(new ClearDisplayOpcode());
                        break;
                    case 0xE:
                }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:  
                switch(lastNum){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 0xE:
                }
            case 9:
            case 0xA:
            case 0xB:
            case 0xC:
            case 0xD:
            case 0xE:
                switch(lastNum){
                    case 1:
                    case 0xE:
                }
            case 0xF:
                short lastBytes = instruction & 0x00FF;
                switch(lastBytes){
                    case 0x07:
                    case 0x0A:
                    case 0x15:
                    case 0x18:
                    case 0x1E:
                    case 0x29:
                    case 0x33:
                    case 0x55:
                    case 0x65:
                }
        }
    }
}