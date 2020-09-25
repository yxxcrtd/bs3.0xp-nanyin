package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class PrdResearchScheme extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 生成时间
     */
    private Date generateTime;

    /**
     * 产品设计编号
     */
    private String prdDesignId;

    /**
     * 产品可行性分析结果
     */
    private String feasibilityResult;

    /**
     * 产品风险等级评定结果
     */
    private String prdRiskLevel;

    /**
     * 是否通过,通过，不通过，条件通过
     */
    private String schemeResulr;

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
     * 取得 产品设计编号
     * @return 产品设计编号
     */
    public String getPrdDesignId() {
        return prdDesignId;
    }

    /**
     * 设置 产品设计编号
     * @param prdDesignId 产品设计编号
     */
    public void setPrdDesignId(String prdDesignId) {
        this.prdDesignId = prdDesignId == null ? null : prdDesignId.trim();
    }

    /**
     * 取得 产品可行性分析结果
     * @return 产品可行性分析结果
     */
    public String getFeasibilityResult() {
        return feasibilityResult;
    }

    /**
     * 设置 产品可行性分析结果
     * @param feasibilityResult 产品可行性分析结果
     */
    public void setFeasibilityResult(String feasibilityResult) {
        this.feasibilityResult = feasibilityResult == null ? null : feasibilityResult.trim();
    }

    /**
     * 取得 产品风险等级评定结果
     * @return 产品风险等级评定结果
     */
    public String getPrdRiskLevel() {
        return prdRiskLevel;
    }

    /**
     * 设置 产品风险等级评定结果
     * @param prdRiskLevel 产品风险等级评定结果
     */
    public void setPrdRiskLevel(String prdRiskLevel) {
        this.prdRiskLevel = prdRiskLevel == null ? null : prdRiskLevel.trim();
    }

    /**
     * 取得 是否通过,通过，不通过，条件通过
     * @return 是否通过,通过，不通过，条件通过
     */
    public String getSchemeResulr() {
        return schemeResulr;
    }

    /**
     * 设置 是否通过,通过，不通过，条件通过
     * @param schemeResulr 是否通过,通过，不通过，条件通过
     */
    public void setSchemeResulr(String schemeResulr) {
        this.schemeResulr = schemeResulr == null ? null : schemeResulr.trim();
    }
}