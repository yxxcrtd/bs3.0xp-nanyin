package com.joyintech.service.product.riskLevelRating.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: RiskLevelRating001Dto
 * @description: 产品风险等级评定
 * @author: jyl
 * @create: 2020-01-02 16:44
 **/
public class RiskLevelRating001Dto extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;
    /**
     * 资产风险等级编号
     */
    private String riskLevelNo;
    /**
     * 生成时间
     */
    private Date generateTime;
    /**
     * 金融产品代码
     */
    private String finProductCode;
    /**
     * 产品简称
     */
    private String finprodAbbr;
    /**
     * 分支序号
     */
    private BigDecimal branch;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 调整后风险等级
     */
    private String ajustRiskLevel;
    /**
     * 是否存续
     */
    private String isLast;
    /**
     * 资产
     */
    private List<RiskLevelRating002Dto> selectIem;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public String getFinProductCode() {
        return finProductCode;
    }

    public void setFinProductCode(String finProductCode) {
        this.finProductCode = finProductCode;
    }

    public BigDecimal getBranch() {
        return branch;
    }

    public void setBranch(BigDecimal branch) {
        this.branch = branch;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getAjustRiskLevel() {
        return ajustRiskLevel;
    }

    public void setAjustRiskLevel(String ajustRiskLevel) {
        this.ajustRiskLevel = ajustRiskLevel;
    }

    public String getIsLast() {
        return isLast;
    }

    public void setIsLast(String isLast) {
        this.isLast = isLast;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getRiskLevelNo() {
        return riskLevelNo;
    }

    public void setRiskLevelNo(String riskLevelNo) {
        this.riskLevelNo = riskLevelNo;
    }

    public List<RiskLevelRating002Dto> getSelectIem() {
        return selectIem;
    }

    public void setSelectIem(List<RiskLevelRating002Dto> selectIem) {
        this.selectIem = selectIem;
    }
}
