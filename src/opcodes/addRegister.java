package opcodes;

import interfacesv2.opcode;
/**
 * addRegister
 */
public class addRegister implements opcode{

    
    @Override
    public void execute(short data1, short data2, short data3){
        register.get(data1) + data2
        reg
    }
}