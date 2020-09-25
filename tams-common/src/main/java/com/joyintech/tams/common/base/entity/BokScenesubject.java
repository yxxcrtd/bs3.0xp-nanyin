package com.joyintech.tams.common.base.entity;

public class BokScenesubject extends BokScenesubjectKey {
    /**
     * 金额元素
     */
    private String formEle;

    /**
     * 账务明细元素
     */
    private String bokDetailId;

    /**
     * 记账网点
     */
    private String accountNet;

    /**
     * 取得 金额元素
     * @return 金额元素
     */
    public String getFormEle() {
        return formEle;
    }

    /**
     * 设置 金额元素
     * @param formEle 金额元素
     */
    public void setFormEle(String formEle) {
        this.formEle = formEle == null ? null : formEle.trim();
    }

    /**
     * 取得 账务明细元素
     * @return 账务明细元素
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细元素
     * @param bokDetailId 账务明细元素
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
    }

    /**
     * 取得 记账网点
     * @return 记账网点
     */
    public String getAccountNet() {
        return accountNet;
    }

    /**
     * 设置 记账网点
     * @param accountNet 记账网点
     */
    public void setAccountNet(String accountNet) {
        this.accountNet = accountNet == null ? null : accountNet.trim();
    }
}