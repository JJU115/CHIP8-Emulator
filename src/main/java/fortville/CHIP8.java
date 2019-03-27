package fortville;

import java.io.File;

import fortville.components.Buffer;
import fortville.components.Display;
import fortville.components.ExecuteWriteback;
import fortville.components.FetchDecode;
import fortville.components.Memory;
import fortville.components.Registers;

public class CHIP8 {
    public static void main(String[] args) throws InterruptedException {
        String filename = "roms/input.ch8"; //TODO: Figure out how we want to input file names with gradle
        Registers registers = new Registers();
        Memory memory = new Memory(new File(filename), registers);
        Display display = new Display();
        Buffer fetchBuffer = new Buffer();
        FetchDecode fetchDecode = new FetchDecode(memory, registers, fetchBuffer);
        ExecuteWriteback executeWriteback = new ExecuteWriteback(memory, registers, fetchBuffer, display);

        for (;;) {
            System.out.println("Clock");
            fetchDecode.clock();
            executeWriteback.clock();
            registers.deincrementTimers();
        }
    }
}
