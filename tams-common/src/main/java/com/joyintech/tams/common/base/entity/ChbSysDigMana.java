package com.joyintech.tams.common.base.entity;

public class ChbSysDigMana extends ChbSysDigManaKey {
    /**
     * 输入位数
     */
    private Short inputDig;

    /**
     * 输出位数
     */
    private Short outputDig;

    /**
     * 取得 输入位数
     * @return 输入位数
     */
    public Short getInputDig() {
        return inputDig;
    }

    /**
     * 设置 输入位数
     * @param inputDig 输入位数
     */
    public void setInputDig(Short inputDig) {
        this.inputDig = inputDig;
    }

    /**
     * 取得 输出位数
     * @return 输出位数
     */
    public Short getOutputDig() {
        return outputDig;
    }

    /**
     * 设置 输出位数
     * @param outputDig 输出位数
     */
    public void setOutputDig(Short outputDig) {
        this.outputDig = outputDig;
    }
}