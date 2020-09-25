package com.joyintech.tams.common.base.entity;

public class SysBatchUnistartGrpconfig extends SysBatchUnistartGrpconfigKey {
    /**
     * 任务组
     */
    private String batchgrpSeqNo;

    /**
     * 任务组类型(T+0,T+1,日切等)
     */
    private String grpType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 任务组
     * @return 任务组
     */
    public String getBatchgrpSeqNo() {
        return batchgrpSeqNo;
    }

    /**
     * 设置 任务组
     * @param batchgrpSeqNo 任务组
     */
    public void setBatchgrpSeqNo(String batchgrpSeqNo) {
        this.batchgrpSeqNo = batchgrpSeqNo == null ? null : batchgrpSeqNo.trim();
    }

    /**
     * 取得 任务组类型(T+0,T+1,日切等)
     * @return 任务组类型(T+0,T+1,日切等)
     */
    public String getGrpType() {
        return grpType;
    }

    /**
     * 设置 任务组类型(T+0,T+1,日切等)
     * @param grpType 任务组类型(T+0,T+1,日切等)
     */
    public void setGrpType(String grpType) {
        this.grpType = grpType == null ? null : grpType.trim();
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