/*
 * 文件名：FinProductExpire001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productduration.expire.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.json.BigDecimalSerializer;


/**
 * 产品到期查询
 * 
 * @author zhangliang
 * @version 1.0 2019年1月7日
 * @see FinProductExpire001Dto
 * @since 1.0
 */
public class FinProductExpire001Dto extends FinProduct {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1397731899065665132L;

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
     * 交易类型
     */
    private String dealType;
    
    /**
     * 交易备注
     */
    private String remark;
   /**
     * 到期客户赎回金额
     */
    private BigDecimal mCusRevAmt;
    
    /**
     * 本期客户赎回本金 
     */
    private BigDecimal reduceAmt;
    /**
     * workflowId
     * {@inheritDoc}
     */
    private String workflowId;
    
    public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
     * 获取 effectStatus
     * @return effectStatus.
     */
    public String getEffectStatus() {
        return effectStatus;
    }

    
    /**
     * 设置 effectStatus
     * @param effectStatus effectStatus
     */
    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    
    /**
     * 获取 busiNo
     * @return busiNo.
     */
    public String getBusiNo() {
        return busiNo;
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


    
    public String getDealMode() {
        return dealMode;
    }


    
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }


    
    public Date getPayDate() {
        return payDate;
    }


    
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }


    
    public String getIsLay() {
        return isLay;
    }


    
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }
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


    public String getDealType() {
        return dealType;
    }


    public void setDealType(String dealType) {
        this.dealType = dealType;
    }


    
    /**
     * 获取 mCusRevAmt
     * @return mCusRevAmt.
     */
    public BigDecimal getmCusRevAmt() {
        return mCusRevAmt;
    }


    
    /**
     * 设置 mCusRevAmt
     * @param mCusRevAmt mCusRevAmt
     */
    public void setmCusRevAmt(BigDecimal mCusRevAmt) {
        this.mCusRevAmt = mCusRevAmt;
    }


    
    /**
     * 获取 actIntAmt
     * @return actIntAmt.
     */
    public BigDecimal getActIntAmt() {
        return actIntAmt;
    }


    
    /**
     * 设置 actIntAmt
     * @param actIntAmt actIntAmt
     */
    public void setActIntAmt(BigDecimal actIntAmt) {
        this.actIntAmt = actIntAmt;
    }


    
    /**
     * 获取 workflowId
     * @return workflowId.
     */
    public String getWorkflowId() {
        return workflowId;
    }


    
    /**
     * 设置 workflowId
     * @param workflowId workflowId
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }


    
    /**
     * 获取 reduceAmt
     * @return reduceAmt.
     */
    public BigDecimal getReduceAmt() {
        return reduceAmt;
    }


    
    /**
     * 设置 reduceAmt
     * @param reduceAmt reduceAmt
     */
    public void setReduceAmt(BigDecimal reduceAmt) {
        this.reduceAmt = reduceAmt;
    }


    
}
