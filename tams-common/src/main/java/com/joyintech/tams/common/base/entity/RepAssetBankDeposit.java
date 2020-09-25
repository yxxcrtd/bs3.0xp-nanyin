package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

import java.math.BigDecimal;
import java.util.Date;

public class RepAssetBankDeposit extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 资金存入银行
     */
    private String repBankName;

    /**
     * 存款金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repPrinAmt;

    /**
     * 起息日
     */
    private Date repVdate;

    /**
     * 到期日
     */
    private Date repMdate;

    /**
     * 年利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRateYear;

    /**
     * 计息基础
     */
    private String repBasis;

    /**
     * 是否为结构性存款
     */
    private String repIsStruct;
    
    /**
     * 存款类型
     */
    private String repDepoType;

    /**
     * 挂钩标的类别
     */
    private String repTargetType;

    /**
     * 结构性存款挂钩标的
     */
    private String repTarget;

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
     * 取得 资金存入银行
     * @return 资金存入银行
     */
    public String getRepBankName() {
        return repBankName;
    }

    /**
     * 设置 资金存入银行
     * @param repBankName 资金存入银行
     */
    public void setRepBankName(String repBankName) {
        this.repBankName = repBankName == null ? null : repBankName.trim();
    }

    /**
     * 取得 存款金额
     * @return 存款金额
     */
    public BigDecimal getRepPrinAmt() {
        return repPrinAmt;
    }

    /**
     * 设置 存款金额
     * @param repPrinAmt 存款金额
     */
    public void setRepPrinAmt(BigDecimal repPrinAmt) {
        this.repPrinAmt = repPrinAmt;
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
     * 取得 年利率
     * @return 年利率
     */
    public BigDecimal getRepRateYear() {
        return repRateYear;
    }

    /**
     * 设置 年利率
     * @param repRateYear 年利率
     */
    public void setRepRateYear(BigDecimal repRateYear) {
        this.repRateYear = repRateYear;
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getRepBasis() {
        return repBasis;
    }

    /**
     * 设置 计息基础
     * @param repBasis 计息基础
     */
    public void setRepBasis(String repBasis) {
        this.repBasis = repBasis == null ? null : repBasis.trim();
    }

    /**
     * 取得 是否为结构性存款
     * @return 是否为结构性存款
     */
    public String getRepIsStruct() {
        return repIsStruct;
    }

    /**
     * 设置 是否为结构性存款
     * @param repIsStruct 是否为结构性存款
     */
    public void setRepIsStruct(String repIsStruct) {
        this.repIsStruct = repIsStruct == null ? null : repIsStruct.trim();
    }

    /**
	 * @return the repDepoType
	 */
	public String getRepDepoType() {
		return repDepoType;
	}

	/**
	 * @param repDepoType the repDepoType to set
	 */
	public void setRepDepoType(String repDepoType) {
		this.repDepoType = repDepoType;
	}

	/**
     * 取得 挂钩标的类别
     * @return 挂钩标的类别
     */
    public String getRepTargetType() {
        return repTargetType;
    }

    /**
     * 设置 挂钩标的类别
     * @param repTargetType 挂钩标的类别
     */
    public void setRepTargetType(String repTargetType) {
        this.repTargetType = repTargetType == null ? null : repTargetType.trim();
    }

    /**
     * 取得 结构性存款挂钩标的
     * @return 结构性存款挂钩标的
     */
    @Length(fieldName = "BussRepDepositInfo.repTarget", max = 256, groups = {ValidateGroupEdit.class})
    public String getRepTarget() {
        return repTarget;
    }

    /**
     * 设置 结构性存款挂钩标的
     * @param repTarget 结构性存款挂钩标的
     */
    public void setRepTarget(String repTarget) {
        this.repTarget = repTarget == null ? null : repTarget.trim();
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