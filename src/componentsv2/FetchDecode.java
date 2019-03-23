package componentsv2;

import components.Memory;
import interfacesv2.opcode;
import opcodes.AddConstToRegister;
import opcodes.CallAddress;
import opcodes.ClearDisplayOpcode;
import opcodes.JumpAddress;
import opcodes.ReturnSubroutine;
import opcodes.SetRegisterEqualConst;
import opcodes.SetRegisterToRegister;
import opcodes.SkipNextInstructionEqualConst;
import opcodes.SkipNextInstructionEqualRegister;
import opcodes.SkipNextInstructionNotEqualConst;

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
                        fetchDecodeBuffer.setOpcode(new ReturnSubroutine());
                        break;
                }
            break;
            case 1:
                fetchDecodeBuffer.setOpcode(new JumpAddress());
                fetchDecodeBuffer.setData1(opcode & 0x0FFF);
                break;
            case 2:
                fetchDecodeBuffer.setOpcode(new CallAddress());
                fetchDecodeBuffer.setData1(opcode & 0x0FFF);
                break;
            case 3:
                fetchDecodeBuffer.setOpcode(new SkipNextInstructionEqualConst());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >>8);
                fetchDecodeBuffer.setData2(opcode & 0x00FF);
                break;
            case 4:
                fetchDecodeBuffer.setOpcode(new SkipNextInstructionNotEqualConst());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >>8);
                fetchDecodeBuffer.setData2(opcode & 0x00FF);
                break;
            case 5:
                fetchDecodeBuffer.setOpcode(new SkipNextInstructionEqualRegister());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                break;
            case 6:
                fetchDecodeBuffer.setOpcode(new SetRegisterEqualConst());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >>8);
                fetchDecodeBuffer.setData2(opcode & 0x00FF);
                break;
            case 7:
                fetchDecodeBuffer.setOpcode(new AddConstToRegister());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >>8);
                fetchDecodeBuffer.setData2(opcode & 0x00FF);
                break;
            case 8:  
                switch(lastNum){
                    case 0:
                        fetchDecodeBuffer.setOpcode(new SetRegisterToRegister());
                        
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