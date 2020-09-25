package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepAssetAstQdiiSec extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea;

    /**
     * 债券代码
     */
    private String repSecId;

    /**
     * 发行机构
     */
    private String repIssueParty;

    /**
     * 发行机构所属行业
     */
    private String repIndustry;

    /**
     * 发行机构所属行业二级分类
     */
    private String repIndustryLevel2;

    /**
     * 起息日
     */
    private Date repVdate;

    /**
     * 到期日
     */
    private Date repMdate;

    /**
     * 期限(月)
     */
    private Integer repTerm;

    /**
     * 发行机构主体信用评级
     */
    private String repPartyGrade;

    /**
     * 债券信用评级
     */
    private String repSecGrade;

    /**
     * 票面利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repPaperRate;

    /**
     * 付息频率
     */
    private Short repIntpayCycle;

    /**
     * 担保情况说明
     */
    private String repGuaranteeCmt;

    /**
     * 是否含权等特殊条款情况说明
     */
    private String repSpecialCmt;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 所属国家或地区 参照数据字典
     * @return 所属国家或地区 参照数据字典
     */
    public String getRepCountryArea() {
        return repCountryArea;
    }

    /**
     * 设置 所属国家或地区 参照数据字典
     * @param repCountryArea 所属国家或地区 参照数据字典
     */
    public void setRepCountryArea(String repCountryArea) {
        this.repCountryArea = repCountryArea == null ? null : repCountryArea.trim();
    }

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getRepSecId() {
        return repSecId;
    }

    /**
     * 设置 债券代码
     * @param repSecId 债券代码
     */
    public void setRepSecId(String repSecId) {
        this.repSecId = repSecId == null ? null : repSecId.trim();
    }

    /**
     * 取得 发行机构
     * @return 发行机构
     */
    public String getRepIssueParty() {
        return repIssueParty;
    }

    /**
     * 设置 发行机构
     * @param repIssueParty 发行机构
     */
    public void setRepIssueParty(String repIssueParty) {
        this.repIssueParty = repIssueParty == null ? null : repIssueParty.trim();
    }

    /**
     * 取得 发行机构所属行业
     * @return 发行机构所属行业
     */
    public String getRepIndustry() {
        return repIndustry;
    }

    /**
     * 设置 发行机构所属行业
     * @param repIndustry 发行机构所属行业
     */
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry == null ? null : repIndustry.trim();
    }

    /**
     * 取得 发行机构所属行业二级分类
     * @return 发行机构所属行业二级分类
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    /**
     * 设置 发行机构所属行业二级分类
     * @param repIndustryLevel2 发行机构所属行业二级分类
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2 == null ? null : repIndustryLevel2.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getRepVdate() {
        return repVdate;
    }

    /**
     * 设置 起息日
     * @param repVdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepVdate(Date repVdate) {
        this.repVdate = repVdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getRepMdate() {
        return repMdate;
    }

    /**
     * 设置 到期日
     * @param repMdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepMdate(Date repMdate) {
        this.repMdate = repMdate;
    }

    /**
     * 取得 期限(月)
     * @return 期限(月)
     */
    public Integer getRepTerm() {
        return repTerm;
    }

    /**
     * 设置 期限(月)
     * @param repTerm 期限(月)
     */
    public void setRepTerm(Integer repTerm) {
        this.repTerm = repTerm;
    }

    /**
     * 取得 发行机构主体信用评级
     * @return 发行机构主体信用评级
     */
    public String getRepPartyGrade() {
        return repPartyGrade;
    }

    /**
     * 设置 发行机构主体信用评级
     * @param repPartyGrade 发行机构主体信用评级
     */
    public void setRepPartyGrade(String repPartyGrade) {
        this.repPartyGrade = repPartyGrade == null ? null : repPartyGrade.trim();
    }

    /**
     * 取得 债券信用评级
     * @return 债券信用评级
     */
    public String getRepSecGrade() {
        return repSecGrade;
    }

    /**
     * 设置 债券信用评级
     * @param repSecGrade 债券信用评级
     */
    public void setRepSecGrade(String repSecGrade) {
        this.repSecGrade = repSecGrade == null ? null : repSecGrade.trim();
    }

    /**
     * 取得 票面利率
     * @return 票面利率
     */
    public BigDecimal getRepPaperRate() {
        return repPaperRate;
    }

    /**
     * 设置 票面利率
     * @param repPaperRate 票面利率
     */
    public void setRepPaperRate(BigDecimal repPaperRate) {
        this.repPaperRate = repPaperRate;
    }

    /**
     * 取得 付息频率
     * @return 付息频率
     */
    public Short getRepIntpayCycle() {
        return repIntpayCycle;
    }

    /**
     * 设置 付息频率
     * @param repIntpayCycle 付息频率
     */
    public void setRepIntpayCycle(Short repIntpayCycle) {
        this.repIntpayCycle = repIntpayCycle;
    }

    /**
     * 取得 担保情况说明
     * @return 担保情况说明
     */
    public String getRepGuaranteeCmt() {
        return repGuaranteeCmt;
    }

    /**
     * 设置 担保情况说明
     * @param repGuaranteeCmt 担保情况说明
     */
    public void setRepGuaranteeCmt(String repGuaranteeCmt) {
        this.repGuaranteeCmt = repGuaranteeCmt == null ? null : repGuaranteeCmt.trim();
    }

    /**
     * 取得 是否含权等特殊条款情况说明
     * @return 是否含权等特殊条款情况说明
     */
    public String getRepSpecialCmt() {
        return repSpecialCmt;
    }

    /**
     * 设置 是否含权等特殊条款情况说明
     * @param repSpecialCmt 是否含权等特殊条款情况说明
     */
    public void setRepSpecialCmt(String repSpecialCmt) {
        this.repSpecialCmt = repSpecialCmt == null ? null : repSpecialCmt.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }
}