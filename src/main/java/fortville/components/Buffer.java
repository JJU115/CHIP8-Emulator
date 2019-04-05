package fortville.components;

import fortville.interfaces.Opcode;

/**
 * Buffer to store data passed between fetch/decode and execute/writeback.
 */
public class Buffer {
    Opcode opcode;
    int prediction;
    int data1;
    int data2;
    int data3;
    boolean full;
    boolean branch;

    public Buffer() {
        data1 = 0;
        data2 = 0;
        data3 = 0;
        prediction = 0;
        branch = false;
    }

    public int getPrediction() {
        return prediction;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }

    public void setData1(int data1) {
        this.data1 = data1;
    }

    public void setData2(int data2) {
        this.data2 = data2;
    }

    public void setData3(int data3) {
        this.data3 = data3;
    }

    public void setOpcode(Opcode opcode) {
        this.opcode = opcode;
    }

    public int getData1() {
        return data1;
    }

    public int getData2() {
        return data2;
    }

    public int getData3() {
        return data3;
    }

    public Opcode getOpcode() {
        return opcode;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean t) {
        full = t;
    }

    public void setBranch(boolean t) {
        branch = t;
    }

    public boolean isBranch() {
        return branch;
    }
}
