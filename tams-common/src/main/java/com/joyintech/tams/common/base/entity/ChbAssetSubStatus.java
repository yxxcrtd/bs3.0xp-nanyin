package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbAssetSubStatus extends BaseEntity {
    /**
     * 业务表主键
     */
    private String registSeqno;

    /**
     * 机构编码
     */
    private String institutionCode;

    /**
     * 报送状态 01 未报送/02 手工处理/03 接口报送
     */
    private String sendStatus;

    /**
     * 报送反馈状态
     */
    private String submitFeedbackStatus;

    /**
     * 资产归属 : 01-资产基本信息 , 02-资产估值信息
     */
    private String assetType;

    /**
     * 取得 业务表主键
     * @return 业务表主键
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 业务表主键
     * @param registSeqno 业务表主键
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 取得 机构编码
     * @return 机构编码
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 机构编码
     * @param institutionCode 机构编码
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    /**
     * 取得 报送状态 01 未报送/02 手工处理/03 接口报送
     * @return 报送状态 01 未报送/02 手工处理/03 接口报送
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态 01 未报送/02 手工处理/03 接口报送
     * @param sendStatus 报送状态 01 未报送/02 手工处理/03 接口报送
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 报送反馈状态
     * @return 报送反馈状态
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 设置 报送反馈状态
     * @param submitFeedbackStatus 报送反馈状态
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }

    /**
     * 取得 资产归属 : 01-资产基本信息 , 02-资产估值信息
     * @return 资产归属 : 01-资产基本信息 , 02-资产估值信息
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产归属 : 01-资产基本信息 , 02-资产估值信息
     * @param assetType 资产归属 : 01-资产基本信息 , 02-资产估值信息
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }
}