package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.tams.common.base.entity.RepAssetBond;

import java.math.BigDecimal;

/**资产基本信息_其它资产类信息 dto
 * @author wang
 * @create 2021-01-18 16:42
 */


public class SvrBussRepQDIIBondInfoDto extends RepAssetBond {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 所属国家或地区 参考数据字典：COUNTRY_AREA
     */
    private String countryArea;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产代码
     */
    private String assetId;
    /**
     * 发行机构
     */
    private String issuePartyName;
    /**
     * 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String industry;

    /**
     * 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String industryLevel2;
    /**
     * 期限（月）
     */
    private BigDecimal termMonth;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustryLevel2() {
        return industryLevel2;
    }

    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2;
    }

    /**
     * 发行机构主体信用评级
     */
    private String partyGrade;
    /**
     * 债券信用评级
     */
    private String secCreditGradeType;
    /**
     * 收益率/年利率
     */
    private BigDecimal assetYield;
    /**
     * 付息频率（个月/次）
     */
    private Long intpayCycle;
    /**
     * 担保情况说明
     */
    private String guaranteeCmt;
    /**
     * 担保情况说明
     */
    private String  guaTypeRemark;
    /**
     * 是否含权等特条款情况说明
     */
    private String specialCmt;
    /**
     * 备注
     */
    private String remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getIssuePartyName() {
        return issuePartyName;
    }

    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName;
    }



    public BigDecimal getTermMonth() {
        return termMonth;
    }

    public void setTermMonth(BigDecimal termMonth) {
        this.termMonth = termMonth;
    }

    public String getPartyGrade() {
        return partyGrade;
    }

    public void setPartyGrade(String partyGrade) {
        this.partyGrade = partyGrade;
    }

    public String getSecCreditGradeType() {
        return secCreditGradeType;
    }

    public void setSecCreditGradeType(String secCreditGradeType) {
        this.secCreditGradeType = secCreditGradeType;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }

    public Long getIntpayCycle() {
        return intpayCycle;
    }

    public void setIntpayCycle(Long intpayCycle) {
        this.intpayCycle = intpayCycle;
    }

    public String getGuaranteeCmt() {
        return guaranteeCmt;
    }

    public void setGuaranteeCmt(String guaranteeCmt) {
        this.guaranteeCmt = guaranteeCmt;
    }

    public String getSpecialCmt() {
        return specialCmt;
    }

    public void setSpecialCmt(String specialCmt) {
        this.specialCmt = specialCmt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGuaTypeRemark() {
        return guaTypeRemark;
    }

    public void setGuaTypeRemark(String guaTypeRemark) {
        this.guaTypeRemark = guaTypeRemark;
    }
}
