package fortville.components;

import fortville.interfaces.Opcode;

/**
 * Buffer
 */
public class Buffer {
    Opcode opcode;
    int data1;
    int data2;
    int data3;

    public Buffer() {
        data1 = 0;
        data2 = 0;
        data3 = 0;
    }

    /**
     * @param data1 the data1 to set
     */
    public void setData1(int data1) {
        this.data1 = data1;
    }

    /**
     * @param data2 the data2 to set
     */
    public void setData2(int data2) {
        this.data2 = data2;
    }

    /**
     * @param data3 the data3 to set
     */
    public void setData3(int data3) {
        this.data3 = data3;
    }

    /**
     * @param opcode the opcode to set
     */
    public void setOpcode(Opcode opcode) {
        this.opcode = opcode;
    }

    /**
     * @return the data1
     */
    public int getData1() {
        return data1;
    }

    /**
     * @return the data2
     */
    public int getData2() {
        return data2;
    }

    /**
     * @return the data3
     */
    public int getData3() {
        return data3;
    }

    /**
     * @return the opcode
     */
    public Opcode getOpcode() {
        return opcode;
    }
}
