public class Registers{
	
	private short PC;	//ProgramCounter
	private byte SP;	//StackPointer
	private short I;	//Generally used to store memory addresses
	private byte DelayTimer;
	private byte SoundTimer;
	
	
	private byte[] V = new byte[16]; 		//16 8-bit registers (NOTE:V[15] reserved for flag) 
	private short[] Stack = new short[16];	//16 16-bit stack locations
	
	
	public Registers(){
		//Set PC and clear everything else
		PC = 0x200;
		SP = 0;
		I = 0;
		for(int i = 0; i<16;i++){
			V[i]=0;
			Stack[i] = 0;
		}
		DelayTimer = 0;
		SoundTimer = 0;
	}
	
	//indexed from 0-15
	public void storeRegister(int regNum, byte data){
		V[regNum] = data;
	}
	
	public byte loadRegister(int regNum){
		return V[regNum];
	}
	
	public void storeI(short data){
		I = data;
	}
	
	public short loadI(){
		return I;
	}
	
	public void storeStack(short data){
		Stack[SP] = data;
		SP++;
	}
	
	public short loadStack(){
		SP--;
		return Stack[SP];
	}
	
	public short getPC(){
		return PC;
	}
	
	public void setPC(short setData){
		PC = setData;
	}
	
	public void incrementPC(){
		PC += 1;
	}
	
	public void setDelayTimer(byte data){
		DelayTimer = data;
	}
	
	public byte getDelayTimer(){
		return DelayTimer;
	}
	
	public void setSoundTimer(byte data){
		SoundTimer = data;
	}
	
	public byte getSoundTimer(){
		return SoundTimer;
	}
}