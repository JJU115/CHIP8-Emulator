public class TestRegisters{

	public static void main(String args []){
		
		Registers Reg = new Registers();
		
		System.out.println("REGISTER CLASS TESTING");
		System.out.println();
		
		//PC Testing
		System.out.println("PC = " + Reg.getPC());
		System.out.println("Incrementing the PC");
		Reg.incrementPC();
		System.out.println("PC = " +Reg.getPC());
		System.out.println("Set PC to 0x400");
		Reg.setPC((short) 0x400);
		System.out.println("PC = " +Reg.getPC());
		System.out.println();
		
		//Reg Testing
		System.out.println("Storing byte 0x17 in register 4");
		Reg.storeRegister(4, (byte) 0x17);
		System.out.println("Register 4 = "+Reg.loadRegister(4));
		System.out.println();
		System.out.println("Store byte 0x317 in register I");
		Reg.storeI((short) 0x317);
		System.out.println("I = "+Reg.loadI());
		System.out.println();
		
		//Stack Testing
		System.out.println("Load 3 values onto the stack");
		System.out.println("Load 1");
		Reg.storeStack((short) 1);
		System.out.println("Load 2");
		Reg.storeStack((short) 2);
		System.out.println("Load 3");
		Reg.storeStack((short) 3);
		System.out.println("Pop 3 values from the stack");
		System.out.println(Reg.loadStack());
		System.out.println(Reg.loadStack());
		System.out.println(Reg.loadStack());
		System.out.println();
		
		//Timer Testing
		System.out.println("Set Delay Timer to 60");
		Reg.setDelayTimer((byte) 60);
		System.out.println("Delay Timer = " + Reg.getDelayTimer());
		System.out.println();
		System.out.println("Set Sound Timer to 127");
		Reg.setSoundTimer((byte) 127);
		System.out.println("Sound Timer = " + Reg.getSoundTimer());
	}
}