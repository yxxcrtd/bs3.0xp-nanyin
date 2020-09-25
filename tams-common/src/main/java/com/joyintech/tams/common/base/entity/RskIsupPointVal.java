package com.joyintech.tams.common.base.entity;

public class RskIsupPointVal extends RskIsupPointValKey {
    /**
     * 监督值

     */
    private String supVal;

    /**
     * 监控状态
     */
    private String supStat;

    /**
     * 联合监控状态
     */
    private String supUniteStat;

    /**
     * 预警条件
     */
    private String warnCond;

    /**
     * 审批条件
     */
    private String approveCond;

    /**
     * 违规条件
     */
    private String foulCond;

    /**
     * 连续违规天数
     */
    private Long foulDays;

    /**
     * 计算过程
     */
    private String mathProcess;

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
     * 取得 监控状态
     * @return 监控状态
     */
    public String getSupStat() {
        return supStat;
    }

    /**
     * 设置 监控状态
     * @param supStat 监控状态
     */
    public void setSupStat(String supStat) {
        this.supStat = supStat == null ? null : supStat.trim();
    }

    /**
     * 取得 联合监控状态
     * @return 联合监控状态
     */
    public String getSupUniteStat() {
        return supUniteStat;
    }

    /**
     * 设置 联合监控状态
     * @param supUniteStat 联合监控状态
     */
    public void setSupUniteStat(String supUniteStat) {
        this.supUniteStat = supUniteStat == null ? null : supUniteStat.trim();
    }

    /**
     * 取得 预警条件
     * @return 预警条件
     */
    public String getWarnCond() {
        return warnCond;
    }

    /**
     * 设置 预警条件
     * @param warnCond 预警条件
     */
    public void setWarnCond(String warnCond) {
        this.warnCond = warnCond == null ? null : warnCond.trim();
    }

    /**
     * 取得 审批条件
     * @return 审批条件
     */
    public String getApproveCond() {
        return approveCond;
    }

    /**
     * 设置 审批条件
     * @param approveCond 审批条件
     */
    public void setApproveCond(String approveCond) {
        this.approveCond = approveCond == null ? null : approveCond.trim();
    }

    /**
     * 取得 违规条件
     * @return 违规条件
     */
    public String getFoulCond() {
        return foulCond;
    }

    /**
     * 设置 违规条件
     * @param foulCond 违规条件
     */
    public void setFoulCond(String foulCond) {
        this.foulCond = foulCond == null ? null : foulCond.trim();
    }

    /**
     * 取得 连续违规天数
     * @return 连续违规天数
     */
    public Long getFoulDays() {
        return foulDays;
    }

    /**
     * 设置 连续违规天数
     * @param foulDays 连续违规天数
     */
    public void setFoulDays(Long foulDays) {
        this.foulDays = foulDays;
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
}