package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class FinProductTypeConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 配置类型，投管分类、风险分类等
     */
    private String configType;

    /**
     * 输入项1
     */
    private String inputValue1;

    /**
     * 输入项2
     */
    private String inputValue2;

    /**
     * 输入项3
     */
    private String inputValue3;

    /**
     * 输入项4
     */
    private String inputValue4;

    /**
     * 输入项5
     */
    private String inputValue5;

    /**
     * 输出项1
     */
    private String outputValue1;

    /**
     * 输出项2
     */
    private String outputValue2;

    /**
     * 输出项3
     */
    private String outputValue3;

    /**
     * 输出项4
     */
    private String outputValue4;

    /**
     * 输出项5
     */
    private String outputValue5;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 配置类型，投管分类、风险分类等
     * @return 配置类型，投管分类、风险分类等
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * 设置 配置类型，投管分类、风险分类等
     * @param configType 配置类型，投管分类、风险分类等
     */
    public void setConfigType(String configType) {
        this.configType = configType == null ? null : configType.trim();
    }

    /**
     * 取得 输入项1
     * @return 输入项1
     */
    public String getInputValue1() {
        return inputValue1;
    }

    /**
     * 设置 输入项1
     * @param inputValue1 输入项1
     */
    public void setInputValue1(String inputValue1) {
        this.inputValue1 = inputValue1 == null ? null : inputValue1.trim();
    }

    /**
     * 取得 输入项2
     * @return 输入项2
     */
    public String getInputValue2() {
        return inputValue2;
    }

    /**
     * 设置 输入项2
     * @param inputValue2 输入项2
     */
    public void setInputValue2(String inputValue2) {
        this.inputValue2 = inputValue2 == null ? null : inputValue2.trim();
    }

    /**
     * 取得 输入项3
     * @return 输入项3
     */
    public String getInputValue3() {
        return inputValue3;
    }

    /**
     * 设置 输入项3
     * @param inputValue3 输入项3
     */
    public void setInputValue3(String inputValue3) {
        this.inputValue3 = inputValue3 == null ? null : inputValue3.trim();
    }

    /**
     * 取得 输入项4
     * @return 输入项4
     */
    public String getInputValue4() {
        return inputValue4;
    }

    /**
     * 设置 输入项4
     * @param inputValue4 输入项4
     */
    public void setInputValue4(String inputValue4) {
        this.inputValue4 = inputValue4 == null ? null : inputValue4.trim();
    }

    /**
     * 取得 输入项5
     * @return 输入项5
     */
    public String getInputValue5() {
        return inputValue5;
    }

    /**
     * 设置 输入项5
     * @param inputValue5 输入项5
     */
    public void setInputValue5(String inputValue5) {
        this.inputValue5 = inputValue5 == null ? null : inputValue5.trim();
    }

    /**
     * 取得 输出项1
     * @return 输出项1
     */
    public String getOutputValue1() {
        return outputValue1;
    }

    /**
     * 设置 输出项1
     * @param outputValue1 输出项1
     */
    public void setOutputValue1(String outputValue1) {
        this.outputValue1 = outputValue1 == null ? null : outputValue1.trim();
    }

    /**
     * 取得 输出项2
     * @return 输出项2
     */
    public String getOutputValue2() {
        return outputValue2;
    }

    /**
     * 设置 输出项2
     * @param outputValue2 输出项2
     */
    public void setOutputValue2(String outputValue2) {
        this.outputValue2 = outputValue2 == null ? null : outputValue2.trim();
    }

    /**
     * 取得 输出项3
     * @return 输出项3
     */
    public String getOutputValue3() {
        return outputValue3;
    }

    /**
     * 设置 输出项3
     * @param outputValue3 输出项3
     */
    public void setOutputValue3(String outputValue3) {
        this.outputValue3 = outputValue3 == null ? null : outputValue3.trim();
    }

    /**
     * 取得 输出项4
     * @return 输出项4
     */
    public String getOutputValue4() {
        return outputValue4;
    }

    /**
     * 设置 输出项4
     * @param outputValue4 输出项4
     */
    public void setOutputValue4(String outputValue4) {
        this.outputValue4 = outputValue4 == null ? null : outputValue4.trim();
    }

    /**
     * 取得 输出项5
     * @return 输出项5
     */
    public String getOutputValue5() {
        return outputValue5;
    }

    /**
     * 设置 输出项5
     * @param outputValue5 输出项5
     */
    public void setOutputValue5(String outputValue5) {
        this.outputValue5 = outputValue5 == null ? null : outputValue5.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}