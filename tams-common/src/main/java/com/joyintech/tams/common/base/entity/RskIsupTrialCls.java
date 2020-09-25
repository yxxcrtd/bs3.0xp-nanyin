package com.joyintech.tams.common.base.entity;

public class RskIsupTrialCls extends RskIsupTrialClsKey {
    /**
     * 阻挡类型，硬控、软控
     */
    private String stopType;

    /**
     * 试算结论，通过、预警、审批
     */
    private String conclusion;

    /**
     * 取得 阻挡类型，硬控、软控
     * @return 阻挡类型，硬控、软控
     */
    public String getStopType() {
        return stopType;
    }

    /**
     * 设置 阻挡类型，硬控、软控
     * @param stopType 阻挡类型，硬控、软控
     */
    public void setStopType(String stopType) {
        this.stopType = stopType == null ? null : stopType.trim();
    }

    /**
     * 取得 试算结论，通过、预警、审批
     * @return 试算结论，通过、预警、审批
     */
    public String getConclusion() {
        return conclusion;
    }

    /**
     * 设置 试算结论，通过、预警、审批
     * @param conclusion 试算结论，通过、预警、审批
     */
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion == null ? null : conclusion.trim();
    }
}