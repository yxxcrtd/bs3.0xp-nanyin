package com.joyintech.tams.common.base.entity;

public class RskIsupPointDetVal extends RskIsupPointDetValKey {
    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 监督值

     */
    private String supVal;

    /**
     * 计算过程
     */
    private String mathProcess;

    /**
     * 监督状态

     */
    private String supStat;

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    /**
     * 取得 监督值

     * @return 监督值

     */
    public String getSupVal() {
        return supVal;
    }

    /**
     * 设置 监督值

     * @param supVal 监督值

     */
    public void setSupVal(String supVal) {
        this.supVal = supVal == null ? null : supVal.trim();
    }

    /**
     * 取得 计算过程
     * @return 计算过程
     */
    public String getMathProcess() {
        return mathProcess;
    }

    /**
     * 设置 计算过程
     * @param mathProcess 计算过程
     */
    public void setMathProcess(String mathProcess) {
        this.mathProcess = mathProcess == null ? null : mathProcess.trim();
    }

    /**
     * 取得 监督状态

     * @return 监督状态

     */
    public String getSupStat() {
        return supStat;
    }

    /**
     * 设置 监督状态

     * @param supStat 监督状态

     */
    public void setSupStat(String supStat) {
        this.supStat = supStat == null ? null : supStat.trim();
    }
}