package fortville.components;

import fortville.interfaces.Opcode;

/**
 * ExecuteWriteback
 */
public class ExecuteWriteback {

    Memory memory;
    Registers registers;
    Buffer fetchDecodeBuffer;
    Display display;

    public ExecuteWriteback(Memory memory, Registers registers,
        Buffer fetchBuffer, Display display) {

        this.memory = memory;
        this.registers = registers;
        this.fetchDecodeBuffer = fetchBuffer;
        this.display = display;
    }

    public void clock() {
        Opcode opcode = fetchDecodeBuffer.getOpcode();
        short data1 = fetchDecodeBuffer.getData1();
        short data2 = fetchDecodeBuffer.getData2();
        short data3 = fetchDecodeBuffer.getData3();
        opcode.execute(data1, data2, data3, memory, display, registers);
    }
}
