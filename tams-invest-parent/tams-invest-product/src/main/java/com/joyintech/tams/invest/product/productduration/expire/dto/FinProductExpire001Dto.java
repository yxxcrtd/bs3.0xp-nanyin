/*
 * 文件名：FinProductExpire001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.expire.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.productduration.expire.validator.FinProductExpire001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;


/**
 * 产品到期查询
 * 
 * @author zhangliang
 * @version 1.0 2019年1月7日
 * @see FinProductExpire001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = FinProductExpire001Validator.class)
public class FinProductExpire001Dto extends FinProduct {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1397731899065665132L;
    
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 有效状态
     */
    private String effectStatus;
    
    /**
     * 业务编号
     */
    private String busiNo;
    
    /**
     * 组合代码
     */
    private String ptlId;
    
    /**
     * 销售部门
     */
    private String saleDepartment;
    
    /**
     * 子产品号
     */
    private String branch;
    
    /**
     * 付息起始日
     */
    private Date startDate;
    
    /**
     * 付息截止日
     */
    private Date endDate;
    
    /**
     * 交易类型
     */
    private String dealType;
    
    /**
     * 本金金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamt;
    
    /**
     * 实付收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actYield;
    
    /**
     * 实付客户收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actCusProfit;
    
    /**
     * 实付销售费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actSaleFee;
    
    /**
     * 是收托管费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actTrustFee;
    
    /**
     * 处理模式
     */
    private String dealMode;
    
    /**
     * 支付日
     */
    private Date payDate;
    
    /**
     * 是否分层
     */
    private String isLay;

    
    /**
     * 获取 effectStatus
     * @return effectStatus.
     */
    public String getEffectStatus() {
        return effectStatus;
    }
    
    public String getEffectStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态, effectStatus);
    }

    
    /**
     * 设置 effectStatus
     * @param effectStatus effectStatus
     */
    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }
    
    /**
     * 获取 busiNo
     * @return busiNo.
     */
    @PrimaryKey(Field.PK_2)
    public String getBusiNo() {
        return this.busiNo;
    }
    
    

    
    /**
     * 设置 busiNo
     * @param busiNo busiNo
     */
    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    
    /**
     * 获取 ptlId
     * @return ptlId.
     */
    public String getPtlId() {
        return ptlId;
    }

    
    /**
     * 设置 ptlId
     * @param ptlId ptlId
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    
    /**
     * 获取 saleDepartment
     * @return saleDepartment.
     */
    public String getSaleDepartment() {
        return saleDepartment;
    }

    
    public String getSaleDepartmentStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售部门, saleDepartment);
    }

    
    /**
     * 设置 saleDepartment
     * @param saleDepartment saleDepartment
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    
    /**
     * 获取 branch
     * @return branch.
     */
    public String getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    
    /**
     * 获取 startDate
     * @return startDate.
     */
    public Date getStartDate() {
        return startDate;
    }
    
    public String getStartDateStr(){
        return DateUtil.format(startDate);
    }

    
    /**
     * 设置 startDate
     * @param startDate startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    
    /**
     * 获取 endDate
     * @return endDate.
     */
    public Date getEndDate() {
        return endDate;
    }
    
    public String getEndDateStr(){
        return DateUtil.format(endDate);
    }

    
    /**
     * 设置 endDate
     * @param endDate endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    /**
     * 获取 prinamt
     * @return prinamt.
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    
    /**
     * 设置 prinamt
     * @param prinamt prinamt
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    
    /**
     * 获取 actYield
     * @return actYield.
     */
    public BigDecimal getActYield() {
        return actYield;
    }

    
    /**
     * 设置 actYield
     * @param actYield actYield
     */
    public void setActYield(BigDecimal actYield) {
        this.actYield = actYield;
    }

    
    /**
     * 获取 actCusProfit
     * @return actCusProfit.
     */
    public BigDecimal getActCusProfit() {
        return actCusProfit;
    }

    
    /**
     * 设置 actCusProfit
     * @param actCusProfit actCusProfit
     */
    public void setActCusProfit(BigDecimal actCusProfit) {
        this.actCusProfit = actCusProfit;
    }

    
    /**
     * 获取 actSaleFee
     * @return actSaleFee.
     */
    public BigDecimal getActSaleFee() {
        return actSaleFee;
    }

    
    /**
     * 设置 actSaleFee
     * @param actSaleFee actSaleFee
     */
    public void setActSaleFee(BigDecimal actSaleFee) {
        this.actSaleFee = actSaleFee;
    }

    
    /**
     * 获取 actTrustFee
     * @return actTrustFee.
     */
    public BigDecimal getActTrustFee() {
        return actTrustFee;
    }

    
    /**
     * 设置 actTrustFee
     * @param actTrustFee actTrustFee
     */
    public void setActTrustFee(BigDecimal actTrustFee) {
        this.actTrustFee = actTrustFee;
    }
    
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
    }

    
    /**
     * 获取 dealMode
     * @return dealMode.
     */
    public String getDealMode() {
        return dealMode;
    }

    
    /**
     * 设置 dealMode
     * @param dealMode dealMode
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    
    /**
     * 获取 payDate
     * @return payDate.
     */
    public Date getPayDate() {
        return payDate;
    }
    
    /**
     * 获取 payDate
     * @return payDate.
     */
    public String getPayDateStr() {
        return DateUtil.format(payDate);
    }

    
    /**
     * 设置 payDate
     * @param payDate payDate
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    
    /**
     * 获取 isLay
     * @return isLay.
     */
    public String getIsLay() {
        return isLay;
    }

    
    /**
     * 设置 isLay
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
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
     * 产品款型
     */
    private String prodType;
    /**
     * 计息本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPrinAmt;
    public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public BigDecimal getTdyPrinAmt() {
		return tdyPrinAmt;
	}

	public void setTdyPrinAmt(BigDecimal tdyPrinAmt) {
		this.tdyPrinAmt = tdyPrinAmt;
	}

	public BigDecimal getIntAmt() {
		return intAmt;
	}

	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	public BigDecimal getActIntAmt() {
		return actIntAmt;
	}

	public void setActIntAmt(BigDecimal actIntAmt) {
		this.actIntAmt = actIntAmt;
	}

	/**
     * 应付利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;
    /**
     * 实付利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actIntAmt;
    //add by yejiejie 2019-2-21 start
    //减少客户数
    private BigDecimal customersReduceNum;

	public BigDecimal getCustomersReduceNum() {
		return customersReduceNum;
	}

	public void setCustomersReduceNum(BigDecimal customersReduceNum) {
		this.customersReduceNum = customersReduceNum;
	}
	//add by yejiejie 2019-2-21 end

    
    
    public String getDealTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealType);
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    //add by wm begin 2019-05-07 解决付息开始、结束日导出为空问题
    /**
     * 获取 vdate
     * @return vdate.
     */
    public String getVdateStr() {
        return DateUtil.format(super.getVdate());
    }
    /**
     * 获取 mdate
     * @return mdate.
     */
    public String getMdateStr() {
        return DateUtil.format(super.getMdate());
    }
  //add by wm end 2019-05-07 解决付息开始、结束日导出为空问题
}
