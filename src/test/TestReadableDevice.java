package test;

import interfaces.ReadableDevice;

/**
 * TestReadableDevice
 */
public class TestReadableDevice implements ReadableDevice{
    int i;
    public TestReadableDevice(int i){
        this.i = i;
    }

    @Override
    public int read(int output) {
        return i;
    }

    public void write(int input){
        this.i = input;
    }
}