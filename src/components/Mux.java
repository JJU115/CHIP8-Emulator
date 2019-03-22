package components;

import interfaces.ReadableDevice;

public class Mux implements ReadableDevice{
    private ReadableDevice control;
    private int controlSignal;
    private ReadableDevice input1;
    private int readSig1;
    private ReadableDevice input2;
    private int readSig2;

    public Mux(ReadableDevice control, int controlSignal, ReadableDevice input1, ReadableDevice input2, int readSig1, int readSig2){
        this.control = control;
        this.controlSignal = controlSignal;
        this.input1 = input1;
        this.input2 = input2;
        this.readSig1 = readSig1;
        this.readSig2 = readSig2;
    }

    @Override
    public int read(int input){
        if(control.read(controlSignal)){
            return input1.read(readSig1);
        }
        else{
            return input2.read(readSig2);
        }
    }
    
}