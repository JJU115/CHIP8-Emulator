package fortville;

import fortville.components.Buffer;
import fortville.components.Display;
import fortville.components.ExecuteWriteback;
import fortville.components.FetchDecode;
import fortville.components.Memory;
import fortville.components.Registers;

/**
 * Main architecture class, generates and links the components of the Chip-8 architecture.
 * Dispatches the FetchDecode and ExecuteWriteback threads.
 */
public class CHIP8 {
    public static void main(String[] args) {

        String filename = "pong.ch8";

        if (args.length == 1) {
            filename = args[0]; // Command line rom filename
        }

        Registers registers = new Registers();
        Memory memory = new Memory(filename);
        Display display = new Display();
        Buffer fetchBuffer = new Buffer();
        FetchDecode fetchDecode = new FetchDecode(memory, registers, fetchBuffer);
        ExecuteWriteback executeWriteback = new ExecuteWriteback(memory, registers,
            fetchBuffer, display);

        Thread threadFetchDecode = new Thread(fetchDecode, "Fetch / Decode Thread");
        Thread threadExecuteWriteback = new Thread(executeWriteback, "Execute / Writeback Thread");

        threadFetchDecode.start();
        threadExecuteWriteback.start();
    }
}
