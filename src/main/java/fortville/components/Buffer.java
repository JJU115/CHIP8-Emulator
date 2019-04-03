package fortville.components;

import fortville.interfaces.Opcode;

/**
 * Buffer
 */
public class Buffer {
    Opcode opcode;
    int prediction;
    int data1;
    int data2;
    int data3;
    boolean full;
    boolean branch;
    boolean valid;

    public Buffer() {
        data1 = 0;
        data2 = 0;
        data3 = 0;
        prediction = 0;
        branch = false;
        valid = true;
    }

    /**
     * @param valid the valid to set
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * @return the valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @return the prediction
     */
    public int getPrediction() {
        return prediction;
    }

    /**
     * @param prediction the prediction to set
     */
    public void setPrediction(int prediction) {
        this.prediction = prediction;
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

    /**
     * @return the full
     */
    public boolean isFull() {
        return full;
    }

    /**
     * @param t the value to set full to
     */
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
