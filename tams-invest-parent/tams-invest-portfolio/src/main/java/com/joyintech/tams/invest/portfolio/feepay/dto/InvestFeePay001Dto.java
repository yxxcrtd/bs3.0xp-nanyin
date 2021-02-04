/*
 * 文件名：InvestFeePay001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.portfolio.feepay.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.feepay.validator.InvestFeePay001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;



/**
 * 投资端费用支付dto
 * 
 * @author malinlin
 * @version 1.0 2019年12月8日
 * @see InvestFeePay001Dto
 * @since 1.0
 */

@ValidLogic(targetClass = InvestFeePay001Validator.class)
public class InvestFeePay001Dto extends BaseEntity implements ProcessEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 7605593327899545754L;

    /**
     * 证券账户
     */
    private String secAcctId;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 状态
     */
    private String effectStatus;
    
    /**
     * 组合代码
     */
    private String ptlId;
    
    /**
     * 市场代码
     */
    private String finprodMarketId;
    
    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 销售部门
     */
    private String saleDepartment;
    
    /**
     * 费用代码
     */
    private String feeId;
    
    /**
     * 费用名称
     */
    private String feeName;
    
    /**
     * 计费基础
     */
    private String basis;
    
    /**
     * 费用类型
     */
    private String feeType;
    
    /**
     * 开始日期
     */
    private Date vdate;
    
    /**
     * 结束日期
     */
    private Date mdate;
    
    /**
     * 支付日
     */
    private Date payDate;
    
    /**
     * 费用金额
     */
    private BigDecimal feeAmt;
    
    /**
     * 现金流代码
     */
    private String cashId;
    
    /**
     * 现金流序号
     */
    private String cashNum;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    
    /**
     * 费用账户
     */
    private String feeAccount;
    
    /**
     * 理财托管户
     */
    private String manageAcctount;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 计提开始日
     */
    private Date positionStartDate;
    
    /**
     * 计提结束日
     */
    private Date positionEndDate;
    
    /**
     * 上次未结清费用
     */
    private BigDecimal unclearAmt;
    /**
     * 产品收益类型
     */
    private String profitType;
    /**
     * 产品处理模式
     */
    private String dealMode;

    //产品费用支付问题 guyunfei 20190923 start
    /**
     * 费用类型
     */
    private String feeType1;
    
    /**
     * 实付费用金额
     */
    private BigDecimal actPayAmt;
    
    public String getFeeType1() {
        return feeType1;
    }

    public void setFeeType1(String feeType1) {
        this.feeType1 = feeType1;
    }
    public String getFeeType1Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.费用品种类型, feeType1);
    }

    //产品费用支付问题 guyunfei 20190923 end

    public String getEffectStatus() {
        return effectStatus;
    }
    
    /**
     * 获取 secAcctId
     * @return secAcctId.
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 secAcctId
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getEffectStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态, effectStatus);
    }
    
    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    //@PrimaryKey(Field.PK_1)
    public String getPtlId() {
        return ptlId;
    }

    
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    public String getFinprodId() {
        return finprodId;
    }

    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    public String getSaleDepartment() {
        return saleDepartment;
    }

    
    public String getSaleDepartmentStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售部门, saleDepartment);
    }

    
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    //@PrimaryKey(Field.PK_2)
    public String getFeeId() {
        return feeId;
    }

    
    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getFeeName() {
        return feeName;
    }

    
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    
    
    
    /**
     * 获取 feeType
     * @return feeType.
     */
//    @NotEmpty(fieldName = "FinProductFeePay002.feeType")
    @PrimaryKey(Field.PK_1)
    public String getFeeType() {
        return feeType;
    }

    
    /**
     * 设置 feeType
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * 获取 vdate
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    
    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    
    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    public String getMdateStr() {
        return DateUtil.format(mdate);
    }
    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public Date getPayDate() {
        return payDate;
    }
    
    public String getPayDateStr() {
        return DateUtil.format(payDate);
    }

    
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }


    
    /**
     * 获取 feeAmt
     * @return feeAmt.
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }
    
    public String getFeeAmtStr(){
        return feeAmt != null ? new DecimalFormat(",###.00").format(feeAmt) : null;
    }


    
    /**
     * 设置 feeAmt
     * @param feeAmt feeAmt
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    
    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    
    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    
    /**
     * 获取 cashNum
     * @return cashNum.
     */
    public String getCashNum() {
        return cashNum;
    }

    
    /**
     * 设置 cashNum
     * @param cashNum cashNum
     */
    public void setCashNum(String cashNum) {
        this.cashNum = cashNum;
    }

    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    
    /**
     * 获取 tradeId
     * @return tradeId.
     */
    @PrimaryKey(Field.PK_2)
    public String getTradeId() {
        return tradeId;
    }

    
    /**
     * 设置 tradeId
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getFeeAccount() {
		return feeAccount;
	}

	public void setFeeAccount(String feeAccount) {
		this.feeAccount = feeAccount;
	}

	public String getManageAcctount() {
		return manageAcctount;
	}

	public void setManageAcctount(String manageAcctount) {
		this.manageAcctount = manageAcctount;
	}

	public String getCcy() {
		return ccy;
	}
	
	public String getCcyStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
	}
	
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public Date getPositionStartDate() {
		return positionStartDate;
	}
	public String getPositionStartDateStr() {
        return DateUtil.format(positionStartDate);
    }
	public void setPositionStartDate(Date positionStartDate) {
		this.positionStartDate = positionStartDate;
	}

	public Date getPositionEndDate() {
		return positionEndDate;
	}
	public String getPositionEndDateStr() {
        return DateUtil.format(positionEndDate);
    }
	public void setPositionEndDate(Date positionEndDate) {
		this.positionEndDate = positionEndDate;
	}

	public BigDecimal getUnclearAmt() {
		return unclearAmt;
	}

	public void setUnclearAmt(BigDecimal unclearAmt) {
		this.unclearAmt = unclearAmt;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

    /**
     * 获取 actPayAmt
     * @return actPayAmt.
     */
    public BigDecimal getActPayAmt() {
        return actPayAmt;
    }
    
    /**
     * 设置 actPayAmt
     * @param actPayAmt actPayAmt
     */
    public void setActPayAmt(BigDecimal actPayAmt) {
        this.actPayAmt = actPayAmt;
    }

}
