package fortville.components;

import fortville.components.Memory;
import fortville.interfaces.Opcode;
import fortville.opcodes.AddConstToRegister;
import fortville.opcodes.AddRegister;
import fortville.opcodes.AddRegisterToI;
import fortville.opcodes.AndRegister;
import fortville.opcodes.CallAddress;
import fortville.opcodes.ClearDisplay;
import fortville.opcodes.Draw;
import fortville.opcodes.JumpAddress;
import fortville.opcodes.JumpToAddressPlusRegister;
import fortville.opcodes.OrRegister;
import fortville.opcodes.ReturnSubroutine;
import fortville.opcodes.SetDelayTimer;
import fortville.opcodes.SetIToAddress;
import fortville.opcodes.SetIToSpriteLocation;
import fortville.opcodes.SetRegisterEqualConst;
import fortville.opcodes.SetRegisterToDelayTimer;
import fortville.opcodes.SetRegisterToRand;
import fortville.opcodes.SetRegisterToRegister;
import fortville.opcodes.SetSoundTimer;
import fortville.opcodes.ShiftLeft;
import fortville.opcodes.ShiftRight;
import fortville.opcodes.SkipKeyEqualRegister;
import fortville.opcodes.SkipKeyNotEqualRegister;
import fortville.opcodes.SkipNextInstructionEqualConst;
import fortville.opcodes.SkipNextInstructionEqualRegister;
import fortville.opcodes.SkipNextInstructionNotEqualConst;
import fortville.opcodes.SkipNextInstructionRegNotEqual;
import fortville.opcodes.StoreBCDToMemory;
import fortville.opcodes.SubRegister;
import fortville.opcodes.SubtractInvertBorrow;
import fortville.opcodes.VectorLoadRegister;
import fortville.opcodes.VectorStoreRegister;
import fortville.opcodes.WaitForKeyPress;
import fortville.opcodes.XORRegister;

import java.util.HashMap;

/**
 * FetchDecode
 */
public class FetchDecode implements Runnable {
    HashMap<Integer, Integer> branchMap = new HashMap<>();
    Memory memory;
    Registers registers;
    Buffer fetchDecodeBuffer;

    public FetchDecode(Memory memory, Registers registers, Buffer fetchBuffer) {
        this.memory = memory;
        this.registers = registers;
        this.fetchDecodeBuffer = fetchBuffer;
    }

    public void run() {
        while (true) {
            while (fetchDecodeBuffer.isFull() || fetchDecodeBuffer.isBranch()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("ERROR: Fetch/Decode Interruption");
                    System.exit(-1);
                }
            }
            clock();
            registers.deincrementTimers();
        }
    }

    public void clock() {
        int opcode = memory.loadInstruction(registers.getPC());
        int firstNum = (opcode & 0xF000) >> 12;
        int lastNum = opcode & 0x000F;
        int lastBytes = opcode & 0x00FF;

        switch (firstNum) {
        case 0:
            switch (lastBytes) {
            case 0xE0:
                fetchDecodeBuffer.setOpcode(new ClearDisplay());
                break;
            case 0xEE:
                fetchDecodeBuffer.setOpcode(new ReturnSubroutine());
                fetchDecodeBuffer.setBranch(true);
                break;
            default:
                fetchDecodeBuffer.setOpcode(null);
                break;
            }
            break;
        case 1:
            fetchDecodeBuffer.setOpcode(new JumpAddress());
            fetchDecodeBuffer.setData1(opcode & 0x0FFF);
            fetchDecodeBuffer.setBranch(true);
            break;
        case 2:
            fetchDecodeBuffer.setOpcode(new CallAddress());
            fetchDecodeBuffer.setData1(opcode & 0x0FFF);
            fetchDecodeBuffer.setBranch(true);
            break;
        case 3:
            fetchDecodeBuffer.setOpcode(new SkipNextInstructionEqualConst());
            fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
            fetchDecodeBuffer.setData2(opcode & 0x00FF);
            fetchDecodeBuffer.setBranch(true);
            break;
        case 4:
            fetchDecodeBuffer.setOpcode(new SkipNextInstructionNotEqualConst());
            fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
            fetchDecodeBuffer.setData2(opcode & 0x00FF);
            fetchDecodeBuffer.setBranch(true);
            break;
        case 5:
            fetchDecodeBuffer.setOpcode(new SkipNextInstructionEqualRegister());
            fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
            fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
            fetchDecodeBuffer.setBranch(true);
            break;
        case 6:
            fetchDecodeBuffer.setOpcode(new SetRegisterEqualConst());
            fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
            fetchDecodeBuffer.setData2(opcode & 0x00FF);
            break;
        case 7:
            fetchDecodeBuffer.setOpcode(new AddConstToRegister());
            fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
            fetchDecodeBuffer.setData2(opcode & 0x00FF);
            break;
        case 8:
            switch (lastNum) {
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
            default:
                // XXX Error handling.
            }
            break;
        case 9:
            fetchDecodeBuffer.setOpcode(new SkipNextInstructionRegNotEqual());
            fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
            fetchDecodeBuffer.setData2((opcode & 0x00F0) >> 4);
            fetchDecodeBuffer.setBranch(true);
            break;
        case 0xA:
            fetchDecodeBuffer.setOpcode(new SetIToAddress());
            fetchDecodeBuffer.setData1(opcode & 0x0FFF);
            break;
        case 0xB:
            fetchDecodeBuffer.setOpcode(new JumpToAddressPlusRegister());
            fetchDecodeBuffer.setData1(opcode & 0x0FFF);
            fetchDecodeBuffer.setBranch(true);
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
            switch (lastNum) {
            case 0x1:
                fetchDecodeBuffer.setOpcode(new SkipKeyNotEqualRegister());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                fetchDecodeBuffer.setBranch(true);
                break;
            case 0xE:
                fetchDecodeBuffer.setOpcode(new SkipKeyEqualRegister());
                fetchDecodeBuffer.setData1((opcode & 0x0F00) >> 8);
                fetchDecodeBuffer.setBranch(true);
                break;
            default:
                // XXX Error handling.
            }
            break;
        case 0xF:
            switch (lastBytes) {
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
            default:
                // XXX Error handling.
            }
            break;
        default:
            // XXX Error handling.
        }
        fetchDecodeBuffer.setFull(true);
        registers.incrementPC();
    }
}
