package test;

import components.ALU;
import test.TestReadableDevice;

public class Tester {

    public static void main(String[] args) {
        TestReadableDevice register = new TestReadableDevice(3);
        TestReadableDevice mux = new TestReadableDevice(2);
        TestReadableDevice control = new TestReadableDevice(1);

        ALU alu = new ALU(register,mux,control);

        alu.clock();

        System.out.println(alu.read(0));

        register.write(5);
        alu.clock();
        System.out.println(alu.read(0));
    } 
}