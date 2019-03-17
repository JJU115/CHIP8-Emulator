package components;

import interfaces.ReadableDevice;

/**
 * ALU Component
 */
public class ALU implements ReadableDevice{
    private int result;
    ReadableDevice mux;
    ReadableDevice registers;
    ReadableDevice control;

    public ALU(ReadableDevice mux, ReadableDevice registers, ReadableDevice control){
        this.mux = mux;
        this.registers = registers;
        this.control = control; 
    }

    public void clock(){
        switch (control.read(0)){
            case 1:
                result = registers.read(0) + mux.read(0);
                break;
            case 2:
                result = registers.read(0) - mux.read(0);
                break;
            case 3:
                result = registers.read(0) & mux.read(0);
                break;
            case 4:
                result = registers.read(0) | mux.read(0);
                break;
            case 5:
                result = registers.read(0) ^ mux.read(0);
                break;
            case 6:
                result = registers.read(0) << 2;
                break;
            case 7:
                result = registers.read(0) >> 2;
                break;
            default:
        }
    }

    @Override
    public int read(int output) {
        return result;
    }
    
}