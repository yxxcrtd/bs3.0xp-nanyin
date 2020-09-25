package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepOtherInvestTarget extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 行内资产类别
     */
    private String repBankAssetType;

    /**
     * 行内资产类别说明
     */
    private String repBankAssetTyprExplain;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMoney;

    /**
     * 起息日
     */
    private Date repValueDate;

    /**
     * 到期日
     */
    private Date repExpiringDate;

    /**
     * 项目期限
     */
    private Integer repProjectDuration;

    /**
     * 所属国家或地区
     */
    private String repCountryArea;

    /**
     * 是否有预期收益率
     */
    private String repContainExpectedYield2;

    /**
     * 项目收益率(利率)%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repProjectYieldRate;

    /**
     * 付息频率(个月/次)
     */
    private Short repIntpayCycle;

    /**
     * 融资人
     */
    private String repFinancier;

    /**
     * 融资人组织机构（社会信用）代码
     */
    private String repFinancierOrgCode;

    /**
     * 外部评级机构及对融资人评级
     */
    private String repFcrGradeOut;

    /**
     * 融资人类型（按规模划分）
     */
    private String repFinancierType1;

    /**
     * 融资人类型（按经济类型划分）
     */
    private String repFinancierType2;

    /**
     * 融资人类型（按技术领域划分）
     */
    private String repFinancierType3;

    /**
     * 融资项目
     */
    private String repFinanceProj;

    /**
     * 融资人所属行业
     */
    private String repIndustry1;

    /**
     * 融资人所属行业二级分类
     */
    private String repIndustryLevel2;

    /**
     * 融资项目所属行业
     */
    private String repProIndustry;

    /**
     * 融资项目所属行业二级分类
     */
    private String repProIndustryLevel2;

    /**
     * 是否属于重点监控行业和领域
     */
    private String repIndustryFlag;

    /**
     * 重点监控行业和领域类别
     */
    private String repMonitType;

    /**
     * 重点监控行业和领域类别说明
     */
    private String repMonTypeComm;

    /**
     * 资产外部评级
     */
    private String repOutGrade;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 资产登记日
     */
    private Date repAssetRegDate;
    
    /**
     * 是否为债转股   中债新增字段 20200407
     */
    private String repDeptEquity;

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
     * 取得 行内资产类别
     * @return 行内资产类别
     */
    public String getRepBankAssetType() {
        return repBankAssetType;
    }

    /**
     * 设置 行内资产类别
     * @param repBankAssetType 行内资产类别
     */
    public void setRepBankAssetType(String repBankAssetType) {
        this.repBankAssetType = repBankAssetType == null ? null : repBankAssetType.trim();
    }

    /**
     * 取得 行内资产类别说明
     * @return 行内资产类别说明
     */
    public String getRepBankAssetTyprExplain() {
        return repBankAssetTyprExplain;
    }

    /**
     * 设置 行内资产类别说明
     * @param repBankAssetTyprExplain 行内资产类别说明
     */
    public void setRepBankAssetTyprExplain(String repBankAssetTyprExplain) {
        this.repBankAssetTyprExplain = repBankAssetTyprExplain == null ? null : repBankAssetTyprExplain.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getRepMoney() {
        return repMoney;
    }

    /**
     * 设置 金额
     * @param repMoney 金额
     */
    public void setRepMoney(BigDecimal repMoney) {
        this.repMoney = repMoney;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getRepValueDate() {
        return repValueDate;
    }

    /**
     * 设置 起息日
     * @param repValueDate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepValueDate(Date repValueDate) {
        this.repValueDate = repValueDate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getRepExpiringDate() {
        return repExpiringDate;
    }

    /**
     * 设置 到期日
     * @param repExpiringDate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepExpiringDate(Date repExpiringDate) {
        this.repExpiringDate = repExpiringDate;
    }

    /**
     * 取得 项目期限
     * @return 项目期限
     */
    public Integer getRepProjectDuration() {
        return repProjectDuration;
    }

    /**
     * 设置 项目期限
     * @param repProjectDuration 项目期限
     */
    public void setRepProjectDuration(Integer repProjectDuration) {
        this.repProjectDuration = repProjectDuration;
    }

    /**
     * 取得 所属国家或地区
     * @return 所属国家或地区
     */
    public String getRepCountryArea() {
        return repCountryArea;
    }

    /**
     * 设置 所属国家或地区
     * @param repCountryArea 所属国家或地区
     */
    public void setRepCountryArea(String repCountryArea) {
        this.repCountryArea = repCountryArea == null ? null : repCountryArea.trim();
    }

    /**
     * 取得 是否有预期收益率
     * @return 是否有预期收益率
     */
    public String getRepContainExpectedYield2() {
        return repContainExpectedYield2;
    }

    /**
     * 设置 是否有预期收益率
     * @param repContainExpectedYield2 是否有预期收益率
     */
    public void setRepContainExpectedYield2(String repContainExpectedYield2) {
        this.repContainExpectedYield2 = repContainExpectedYield2 == null ? null : repContainExpectedYield2.trim();
    }

    /**
     * 取得 项目收益率(利率)%
     * @return 项目收益率(利率)%
     */
    public BigDecimal getRepProjectYieldRate() {
        return repProjectYieldRate;
    }

    /**
     * 设置 项目收益率(利率)%
     * @param repProjectYieldRate 项目收益率(利率)%
     */
    public void setRepProjectYieldRate(BigDecimal repProjectYieldRate) {
        this.repProjectYieldRate = repProjectYieldRate;
    }

    /**
     * 取得 付息频率(个月/次)
     * @return 付息频率(个月/次)
     */
    public Short getRepIntpayCycle() {
        return repIntpayCycle;
    }

    /**
     * 设置 付息频率(个月/次)
     * @param repIntpayCycle 付息频率(个月/次)
     */
    public void setRepIntpayCycle(Short repIntpayCycle) {
        this.repIntpayCycle = repIntpayCycle;
    }

    /**
     * 取得 融资人
     * @return 融资人
     */
    public String getRepFinancier() {
        return repFinancier;
    }

    /**
     * 设置 融资人
     * @param repFinancier 融资人
     */
    public void setRepFinancier(String repFinancier) {
        this.repFinancier = repFinancier == null ? null : repFinancier.trim();
    }

    /**
     * 取得 融资人组织机构（社会信用）代码
     * @return 融资人组织机构（社会信用）代码
     */
    public String getRepFinancierOrgCode() {
        return repFinancierOrgCode;
    }

    /**
     * 设置 融资人组织机构（社会信用）代码
     * @param repFinancierOrgCode 融资人组织机构（社会信用）代码
     */
    public void setRepFinancierOrgCode(String repFinancierOrgCode) {
        this.repFinancierOrgCode = repFinancierOrgCode == null ? null : repFinancierOrgCode.trim();
    }

    /**
     * 取得 外部评级机构及对融资人评级
     * @return 外部评级机构及对融资人评级
     */
    public String getRepFcrGradeOut() {
        return repFcrGradeOut;
    }

    /**
     * 设置 外部评级机构及对融资人评级
     * @param repFcrGradeOut 外部评级机构及对融资人评级
     */
    public void setRepFcrGradeOut(String repFcrGradeOut) {
        this.repFcrGradeOut = repFcrGradeOut == null ? null : repFcrGradeOut.trim();
    }

    /**
     * 取得 融资人类型（按规模划分）
     * @return 融资人类型（按规模划分）
     */
    public String getRepFinancierType1() {
        return repFinancierType1;
    }

    /**
     * 设置 融资人类型（按规模划分）
     * @param repFinancierType1 融资人类型（按规模划分）
     */
    public void setRepFinancierType1(String repFinancierType1) {
        this.repFinancierType1 = repFinancierType1 == null ? null : repFinancierType1.trim();
    }

    /**
     * 取得 融资人类型（按经济类型划分）
     * @return 融资人类型（按经济类型划分）
     */
    public String getRepFinancierType2() {
        return repFinancierType2;
    }

    /**
     * 设置 融资人类型（按经济类型划分）
     * @param repFinancierType2 融资人类型（按经济类型划分）
     */
    public void setRepFinancierType2(String repFinancierType2) {
        this.repFinancierType2 = repFinancierType2 == null ? null : repFinancierType2.trim();
    }

    /**
     * 取得 融资人类型（按技术领域划分）
     * @return 融资人类型（按技术领域划分）
     */
    public String getRepFinancierType3() {
        return repFinancierType3;
    }

    /**
     * 设置 融资人类型（按技术领域划分）
     * @param repFinancierType3 融资人类型（按技术领域划分）
     */
    public void setRepFinancierType3(String repFinancierType3) {
        this.repFinancierType3 = repFinancierType3 == null ? null : repFinancierType3.trim();
    }

    /**
     * 取得 融资项目
     * @return 融资项目
     */
    public String getRepFinanceProj() {
        return repFinanceProj;
    }

    /**
     * 设置 融资项目
     * @param repFinanceProj 融资项目
     */
    public void setRepFinanceProj(String repFinanceProj) {
        this.repFinanceProj = repFinanceProj == null ? null : repFinanceProj.trim();
    }

    /**
     * 取得 融资人所属行业
     * @return 融资人所属行业
     */
    public String getRepIndustry1() {
        return repIndustry1;
    }

    /**
     * 设置 融资人所属行业
     * @param repIndustry1 融资人所属行业
     */
    public void setRepIndustry1(String repIndustry1) {
        this.repIndustry1 = repIndustry1 == null ? null : repIndustry1.trim();
    }

    /**
     * 取得 融资人所属行业二级分类
     * @return 融资人所属行业二级分类
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    /**
     * 设置 融资人所属行业二级分类
     * @param repIndustryLevel2 融资人所属行业二级分类
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2 == null ? null : repIndustryLevel2.trim();
    }

    /**
     * 取得 融资项目所属行业
     * @return 融资项目所属行业
     */
    public String getRepProIndustry() {
        return repProIndustry;
    }

    /**
     * 设置 融资项目所属行业
     * @param repProIndustry 融资项目所属行业
     */
    public void setRepProIndustry(String repProIndustry) {
        this.repProIndustry = repProIndustry == null ? null : repProIndustry.trim();
    }

    /**
     * 取得 融资项目所属行业二级分类
     * @return 融资项目所属行业二级分类
     */
    public String getRepProIndustryLevel2() {
        return repProIndustryLevel2;
    }

    /**
     * 设置 融资项目所属行业二级分类
     * @param repProIndustryLevel2 融资项目所属行业二级分类
     */
    public void setRepProIndustryLevel2(String repProIndustryLevel2) {
        this.repProIndustryLevel2 = repProIndustryLevel2 == null ? null : repProIndustryLevel2.trim();
    }

    /**
     * 取得 是否属于重点监控行业和领域
     * @return 是否属于重点监控行业和领域
     */
    public String getRepIndustryFlag() {
        return repIndustryFlag;
    }

    /**
     * 设置 是否属于重点监控行业和领域
     * @param repIndustryFlag 是否属于重点监控行业和领域
     */
    public void setRepIndustryFlag(String repIndustryFlag) {
        this.repIndustryFlag = repIndustryFlag == null ? null : repIndustryFlag.trim();
    }

    /**
     * 取得 重点监控行业和领域类别
     * @return 重点监控行业和领域类别
     */
    public String getRepMonitType() {
        return repMonitType;
    }

    /**
     * 设置 重点监控行业和领域类别
     * @param repMonitType 重点监控行业和领域类别
     */
    public void setRepMonitType(String repMonitType) {
        this.repMonitType = repMonitType == null ? null : repMonitType.trim();
    }

    /**
     * 取得 重点监控行业和领域类别说明
     * @return 重点监控行业和领域类别说明
     */
    public String getRepMonTypeComm() {
        return repMonTypeComm;
    }

    /**
     * 设置 重点监控行业和领域类别说明
     * @param repMonTypeComm 重点监控行业和领域类别说明
     */
    public void setRepMonTypeComm(String repMonTypeComm) {
        this.repMonTypeComm = repMonTypeComm == null ? null : repMonTypeComm.trim();
    }

    /**
     * 取得 资产外部评级
     * @return 资产外部评级
     */
    public String getRepOutGrade() {
        return repOutGrade;
    }

    /**
     * 设置 资产外部评级
     * @param repOutGrade 资产外部评级
     */
    public void setRepOutGrade(String repOutGrade) {
        this.repOutGrade = repOutGrade == null ? null : repOutGrade.trim();
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

    /**
     * 取得 资产登记日
     * @return 资产登记日
     */
    public Date getRepAssetRegDate() {
        return repAssetRegDate;
    }

    /**
     * 设置 资产登记日
     * @param repAssetRegDate 资产登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepAssetRegDate(Date repAssetRegDate) {
        this.repAssetRegDate = repAssetRegDate;
    }

	/**
	 * @return the repDeptEquity
	 */
	public String getRepDeptEquity() {
		return repDeptEquity;
	}

	/**
	 * @param repDeptEquity the repDeptEquity to set
	 */
	public void setRepDeptEquity(String repDeptEquity) {
		this.repDeptEquity = repDeptEquity;
	}
}