package com.joyintech.tams.common.base.entity;

public class ChbSysBatchLogWithBLOBs extends ChbSysBatchLog {
    /**
     * 产品代码列表
     */
    private String prdIdList;

    /**
     * 失败产品列表
     */
    private String failedProdId;

    /**
     * 失败详细信息
     */
    private String failedMessageDetail;

    /**
     * 取得 产品代码列表
     * @return 产品代码列表
     */
    public String getPrdIdList() {
        return prdIdList;
    }

    /**
     * 设置 产品代码列表
     * @param prdIdList 产品代码列表
     */
    public void setPrdIdList(String prdIdList) {
        this.prdIdList = prdIdList == null ? null : prdIdList.trim();
    }

    /**
     * 取得 失败产品列表
     * @return 失败产品列表
     */
    public String getFailedProdId() {
        return failedProdId;
    }

    /**
     * 设置 失败产品列表
     * @param failedProdId 失败产品列表
     */
    public void setFailedProdId(String failedProdId) {
        this.failedProdId = failedProdId == null ? null : failedProdId.trim();
    }

    /**
     * 取得 失败详细信息
     * @return 失败详细信息
     */
    public String getFailedMessageDetail() {
        return failedMessageDetail;
    }

    /**
     * 设置 失败详细信息
     * @param failedMessageDetail 失败详细信息
     */
    public void setFailedMessageDetail(String failedMessageDetail) {
        this.failedMessageDetail = failedMessageDetail == null ? null : failedMessageDetail.trim();
    }
}