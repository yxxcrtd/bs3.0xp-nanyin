package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class PrdRiskLevel extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 生成时间
     */
    private Date generateTime;

    /**
     * 金融产品代码
     */
    private String finProductCode;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 风险等级
     */
    private String riskLevel;

    /**
     * 调整后风险等级
     */
    private String ajustRiskLevel;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 生成时间
     * @return 生成时间
     */
    public Date getGenerateTime() {
        return generateTime;
    }

    /**
     * 设置 生成时间
     * @param generateTime 生成时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinProductCode() {
        return finProductCode;
    }

    /**
     * 设置 金融产品代码
     * @param finProductCode 金融产品代码
     */
    public void setFinProductCode(String finProductCode) {
        this.finProductCode = finProductCode == null ? null : finProductCode.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 风险等级
     * @return 风险等级
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置 风险等级
     * @param riskLevel 风险等级
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 取得 调整后风险等级
     * @return 调整后风险等级
     */
    public String getAjustRiskLevel() {
        return ajustRiskLevel;
    }

    /**
     * 设置 调整后风险等级
     * @param ajustRiskLevel 调整后风险等级
     */
    public void setAjustRiskLevel(String ajustRiskLevel) {
        this.ajustRiskLevel = ajustRiskLevel == null ? null : ajustRiskLevel.trim();
    }
}