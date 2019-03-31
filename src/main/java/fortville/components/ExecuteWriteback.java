package fortville.components;

import fortville.interfaces.Opcode;

/**
 * ExecuteWriteback
 */
public class ExecuteWriteback implements Runnable {

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

    public void run() {
        while (true) {
            while (!fetchDecodeBuffer.isFull()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("ERROR: Execute/Writeback Interruption");
                    System.exit(-1);
                }    
            } 
            System.out.println("Execute/Writeback"); 
            clock();
        }     
    }

    public void clock() {
        Opcode opcode = fetchDecodeBuffer.getOpcode();
        int data1 = fetchDecodeBuffer.getData1();
        int data2 = fetchDecodeBuffer.getData2();
        int data3 = fetchDecodeBuffer.getData3();
        fetchDecodeBuffer.setFull(false);
        opcode.execute(data1, data2, data3, memory, display, registers);
    }
}
