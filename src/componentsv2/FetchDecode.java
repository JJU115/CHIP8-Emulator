package componentsv2;

import components.Memory;
import interfacesv2.opcode;
import opcodes.AddConstToRegister;
import opcodes.AddRegister;
import opcodes.AddRegisterToI;
import opcodes.AndRegister;
import opcodes.CallAddress;
import opcodes.ClearDisplayOpcode;
import opcodes.Draw;
import opcodes.JumpAddress;
import opcodes.JumpToAddressPlusRegister;
import opcodes.OrRegister;
import opcodes.ReturnSubroutine;
import opcodes.SetIToAddress;
import opcodes.SetRegisterEqualConst;
import opcodes.SetRegisterToDelayTimer;
import opcodes.SetRegisterToRand;
import opcodes.SetRegisterToRegister;
import opcodes.SetSoundTimer;
import opcodes.ShiftLeft;
import opcodes.ShiftRight;
import opcodes.SkipKeyEqualRegister;
import opcodes.SkipKeyNotEqualRegister;
import opcodes.SkipNextInstructionEqualConst;
import opcodes.SkipNextInstructionEqualRegister;
import opcodes.SkipNextInstructionNotEqualConst;
import opcodes.SkipNextInstructionRegNotEqual;
import opcodes.StoreBCDToMemory;
import opcodes.SubRegister;
import opcodes.SubtractInvertBorrow;
import opcodes.VectorLoadRegister;
import opcodes.VectorStoreRegister;
import opcodes.WaitForKeyPress;
import opcodes.XORRegister;

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
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                        break;
                    case 1:
                        fetchDecodeBuffer.setOpcode(new OrRegister());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                        break;
                    case 2:
                        fetchDecodeBuffer.setOpcode(new AndRegister());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                        break;   
                    case 3:
                        fetchDecodeBuffer.setOpcode(new XORRegister());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                        break;
                    case 4:
                        fetchDecodeBuffer.setOpcode(new AddRegister());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                        break;
                    case 5:
                        fetchDecodeBuffer.setOpcode(new SubRegister());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                        break;
                    case 6:
                        fetchDecodeBuffer.setOpcode(new ShiftRight());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                        break;
                    case 7:
                        fetchDecodeBuffer.setOpcode(new SubtractInvertBorrow());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                        break;
                    case 0xE:
                        fetchDecodeBuffer.setOpcode(new ShiftLeft());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                        break;
                }
            case 9:
                fetchDecodeBuffer.setOpcode(new SkipNextInstructionRegNotEqual());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                break;
            case 0xA:
                fetchDecodeBuffer.setOpcode(new SetIToAddress());
                fetchDecodeBuffer.setData1(opcode & 0x0FFF);
                break;
            case 0xB:
                fetchDecodeBuffer.setOpcode(new JumpToAddressPlusRegister());
                fetchDecodeBuffer.setData1(opcode & 0x0FFF);
                break;
            case 0xC:
                fetchDecodeBuffer.setOpcode(new SetRegisterToRand());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                fetchDecodeBuffer.setData2(opcode & 0x00FF);
                break;
            case 0xD:
                fetchDecodeBuffer.setOpcode(new Draw());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
                fetchDecodeBuffer.setData3(opcode & 0x000F);
                break;
            case 0xE:
                switch(lastNum){
                    case 0x1:
                        fetchDecodeBuffer.setOpcode(new SkipKeyNotEqualRegister());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                    case 0xE:
                        fetchDecodeBuffer.setOpcode(new SkipKeyEqualRegister());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                }
            break;
            case 0xF:
                short lastBytes = instruction & 0x00FF;
                switch(lastBytes){
                    case 0x07:
                        fetchDecodeBuffer.setOpcode(new SetRegisterToDelayTimer());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                    case 0x0A:
                        fetchDecodeBuffer.setOpcode(new WaitForKeyPress());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                    case 0x15:
                        fetchDecodeBuffer.setOpcode(new SetDelayTimer());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                    case 0x18:
                        fetchDecodeBuffer.setOpcode(new SetSoundTimer());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                    case 0x1E:
                        fetchDecodeBuffer.setOpcode(new AddRegisterToI());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                    case 0x29:
                        fetchDecodeBuffer.setOpcode(new SetIToSpriteLocation());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                    case 0x33:
                        fetchDecodeBuffer.setOpcode(new StoreBCDToMemory());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                    case 0x55:
                        fetchDecodeBuffer.setOpcode(new VectorStoreRegister());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                    case 0x65:
                        fetchDecodeBuffer.setOpcode(new VectorLoadRegister());
                        fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                        break;
                }
            break;
        }
    }
}