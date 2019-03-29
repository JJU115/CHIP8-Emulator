package fortville;

import fortville.components.Buffer;
import fortville.components.Display;
import fortville.components.ExecuteWriteback;
import fortville.components.FetchDecode;
import fortville.components.Memory;
import fortville.components.Registers;

public class CHIP8 {
    public static void main(String[] args) throws InterruptedException {

        String filename = "input.ch8";  // XXX Need default rom

        if (args.length == 1) {
            filename = args[0];         // Command line rom filename
        }

        Registers registers = new Registers();
        Memory memory = new Memory(filename, registers);
        Display display = new Display();
        Buffer fetchBuffer = new Buffer();
        FetchDecode fetchDecode = new FetchDecode(memory, registers, fetchBuffer);
        ExecuteWriteback executeWriteback = new ExecuteWriteback(memory, registers,
            fetchBuffer, display);

        for (;;) {
            //System.out.println("Clock");
            fetchDecode.clock();
            executeWriteback.clock();
            registers.deincrementTimers();
            Thread.sleep(1);
        }
    }
}
