package opcodes;

/**
 * AddRegisterToI
 */
public class AddRegisterToI implements Opcode{
    @Override
    public void execute(short data1, short data2, short data3, Memory memory, Display display, Registers registers){
        /*Fx1E - ADD I, Vx
		Set I = I + Vx.
		The values of I and Vx are added, and the results are stored in I.*/ 
		int sum = registers.loadI() + registers.loadRegister(data1);

		//Do we need to set overflow flag if overflow?
		if(sum>0xfff){
			registers.storeRegister((short) 15, (byte) 1);
		}else{
			registers.storeRegister((short) 15, (byte) 0);
		}

		sum = (sum & 0xfff);
		registers.storeI((short) sum);
    }
}