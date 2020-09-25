/*
 * 文件名：BussRepFinProductInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.report.prod.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Email;
import com.joyintech.framework.validation.annotation.IDCard;
import com.joyintech.framework.validation.annotation.OfficePhone;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.report.prod.validator.BussRepFinProductInfoValidator;

/**
 * 产品中债报备信息
 * 
 * @author 张亮
 * @version 1.0 2018年9月21日
 * @see BussRepFinProductInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussRepFinProductInfoValidator.class)
public class BussRepFinProductInfoDto extends RepProdDeclare {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3615036090702766860L;
    
    /**
     * 主画面传入的pageId
     */
    private String pageId;
    
    // 基础信息传入的属性 start
    /**
     * 行内标识码/理财产品代码
     */
    private String repBankProdCode;
    /**
     * 募集起始日
     */
    private Date repRaiseVdate;
    /**
     * 募集结束日
     */
    private Date repRaiseEnddate;
    /**
     * 产品起始日期
     */
    private Date repPrdVdate;
    /**
     * 产品终止日期
     */
    private Date repPrdMdate;
    /**
     * 预计客户最高年收益率
     */
    private BigDecimal repExpMaxYield;
    /**
     * 预计客户最低年收益率
     */
    private BigDecimal repExpMinYield;
    /**
     * 节假日是否开放
     */
    private String repHolidayOnFlag;
    /**
     * 系统参数，是否合并
     */
    private String isMerger;
    
//    @NotEmpty(fieldName = "BussRepFinProductInfo.repInvestorType")
    public String getRepInvestorType() {
        return super.getRepInvestorType();
    }
    
    public String getRepBankProdCode() {
        return repBankProdCode;
    }

    
    public void setRepBankProdCode(String repBankProdCode) {
        this.repBankProdCode = repBankProdCode;
    }

    
    public Date getRepRaiseVdate() {
        return repRaiseVdate;
    }

    
    public void setRepRaiseVdate(Date repRaiseVdate) {
        this.repRaiseVdate = repRaiseVdate;
    }

    
    public Date getRepRaiseEnddate() {
        return repRaiseEnddate;
    }

    
    public void setRepRaiseEnddate(Date repRaiseEnddate) {
        this.repRaiseEnddate = repRaiseEnddate;
    }

    
    public Date getRepPrdVdate() {
        return repPrdVdate;
    }

    
    public void setRepPrdVdate(Date repPrdVdate) {
        this.repPrdVdate = repPrdVdate;
    }

    
    public Date getRepPrdMdate() {
        return repPrdMdate;
    }

    
    public void setRepPrdMdate(Date repPrdMdate) {
        this.repPrdMdate = repPrdMdate;
    }

    
    public BigDecimal getRepExpMaxYield() {
        return repExpMaxYield;
    }

    
    public void setRepExpMaxYield(BigDecimal repExpMaxYield) {
        this.repExpMaxYield = repExpMaxYield;
    }

    
    public BigDecimal getRepExpMinYield() {
        return repExpMinYield;
    }

    
    public void setRepExpMinYield(BigDecimal repExpMinYield) {
        this.repExpMinYield = repExpMinYield;
    }

    
    public String getRepHolidayOnFlag() {
        return repHolidayOnFlag;
    }

    
    public void setRepHolidayOnFlag(String repHolidayOnFlag) {
        this.repHolidayOnFlag = repHolidayOnFlag;
    }

    // 基础信息传入的属性 end

    /**
     * 平均预计客户年收益率
     */
    private BigDecimal repExpAvgYield;

    /**
     * 收益率分档情况说明
     */
    private String repYieldComment;

    /**
     * 规律开放周期
     */
    private String repOpenCircle;

    /**
     * 其他规律开放周期
     */
    private Integer repOpenCircleDays;

    /**
     * 无规律开放说明
     */
    private String repRuleComment;

    /**
     * 首次开放周期起始日
     */
    private Date repFirstOpenDay;

    /**
     * 平均开放次数（年化）
     */
    private BigDecimal repAvgOpenCount;

    /**
     * 开放期业务
     */
    private String repOpenBuss;

    /**
     * 开放期业务说明
     */
    private String repOpenBussRemark;

    /**
     * 开放类型(验证使用)
     */
    private String openType;

    /**
     * 开放模式(验证使用)
     */
    private String openMode;
    
    /**
     * 是否为结构化（分级）产品
     */
    private String repIsConstracte;
    
    /**
     * 层级类别
     */
    private String repGradeType;
    

    /**
     * 开放模式
     */
    private String repOpenMode;
    
    /**
     * 业绩比较基准说明
     * 中债新增字段 20200401
     */
    private String repYieldRemark;
    

    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    @Rate(fieldName = "BussRepFinProductInfo.repExpAvgYield", maxDecBit = 5)
    public BigDecimal getRepExpAvgYield() {
        return repExpAvgYield;
    }

    public void setRepExpAvgYield(BigDecimal repExpAvgYield) {
        this.repExpAvgYield = repExpAvgYield;
    }

    public String getRepYieldComment() {
        return repYieldComment;
    }

    public void setRepYieldComment(String repYieldComment) {
        this.repYieldComment = repYieldComment;
    }

    public String getRepOpenCircle() {
        return repOpenCircle;
    }

    public void setRepOpenCircle(String repOpenCircle) {
        this.repOpenCircle = repOpenCircle;
    }

    public Integer getRepOpenCircleDays() {
        return repOpenCircleDays;
    }

    public void setRepOpenCircleDays(Integer repOpenCircleDays) {
        this.repOpenCircleDays = repOpenCircleDays;
    }

    public String getRepRuleComment() {
        return repRuleComment;
    }

    public void setRepRuleComment(String repRuleComment) {
        this.repRuleComment = repRuleComment;
    }

    public Date getRepFirstOpenDay() {
        return repFirstOpenDay;
    }

    public void setRepFirstOpenDay(Date repFirstOpenDay) {
        this.repFirstOpenDay = repFirstOpenDay;
    }

    @Amount(fieldName = "BussRepFinProductInfo.repAvgOpenCount", maxIntBit = 3)
    public BigDecimal getRepAvgOpenCount() {
        return repAvgOpenCount;
    }

    public void setRepAvgOpenCount(BigDecimal repAvgOpenCount) {
        this.repAvgOpenCount = repAvgOpenCount;
    }

    public String getRepOpenBuss() {
        return repOpenBuss;
    }

    public void setRepOpenBuss(String repOpenBuss) {
        this.repOpenBuss = repOpenBuss;
    }

    public String getRepOpenBussRemark() {
        return repOpenBussRemark;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getOpenMode() {
        return openMode;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    public void setRepOpenBussRemark(String repOpenBussRemark) {
        this.repOpenBussRemark = repOpenBussRemark;
    }

    @Override
    @IDCard(fieldName = "BussRepFinProductInfo.repApproverId")
    public String getRepApproverId() {
        return super.getRepApproverId();
    }

    @Override
    @IDCard(fieldName = "BussRepFinProductInfo.repDesignerId")
    public String getRepDesignerId() {
        return super.getRepDesignerId();
    }

    @Override
    @IDCard(fieldName = "BussRepFinProductInfo.repManagerId")
    public String getRepManagerId() {
        return super.getRepManagerId();
    }

    @Override
    @OfficePhone(fieldName = "BussRepFinProductInfo.repLiaisonTel")
    public String getRepLiaisonTel() {
        return super.getRepLiaisonTel();
    }

    @Override
    //去除标签交易，在BussRepFinProductInfoValidator中校验
//    @MobilePhone(fieldName = "BussRepFinProductInfo.repLiaisonMobile")
    public String getRepLiaisonMobile() {
        return super.getRepLiaisonMobile();
    }

    @Override
    @Email(fieldName = "BussRepFinProductInfo.repLiaisonEmail")
    public String getRepLiaisonEmail() {
        return super.getRepLiaisonEmail();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Rate(fieldName = "BussRepFinProductInfo.repHighYield", maxDecBit = 5)
    public BigDecimal getRepHighYield() {
        return super.getRepHighYield();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Rate(fieldName = "BussRepFinProductInfo.repLowYield", maxDecBit = 5)
    public BigDecimal getRepLowYield() {
        return super.getRepLowYield();
    }

    @Override
    @Amount(fieldName = "BussRepFinProductInfo.repSaleAmtStr", maxIntBit = 13)
    public BigDecimal getRepSaleAmtStr() {
        return super.getRepSaleAmtStr();
    }

    @Override
//    @Amount(fieldName = "BussRepFinProductInfo.repRaiseAmtPlan", maxIntBit = 13)
    public BigDecimal getRepRaiseAmtPlan() {
        return super.getRepRaiseAmtPlan();
    }
    
    
    /**
     * 产品起息日
     */
    private Date prodVdate;
    
    /**
     * 产品到期日
     */
    private Date prodMdate;
    
    /**
     * 开放类型
     */
    private String operationType;
    
    /**
     * 开放周期
     */
    private String openCycle;
    
    /**
     * 开放周期单位
     */
    private String openCycleUnit;

    
    /**
     * 获取 prodVdate
     * @return prodVdate.
     */
    public Date getProdVdate() {
        return prodVdate;
    }


    
    /**
     * 设置 prodVdate
     * @param prodVdate prodVdate
     */
    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
    }


    
    /**
     * 获取 prodMdate
     * @return prodMdate.
     */
    public Date getProdMdate() {
        return prodMdate;
    }


    
    /**
     * 设置 prodMdate
     * @param prodMdate prodMdate
     */
    public void setProdMdate(Date prodMdate) {
        this.prodMdate = prodMdate;
    }


    
    /**
     * 获取 operationType
     * @return operationType.
     */
    public String getOperationType() {
        return operationType;
    }


    
    /**
     * 设置 operationType
     * @param operationType operationType
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }


    
    
    /**
     * 获取 openCycle
     * @return openCycle.
     */
    public String getOpenCycle() {
        return openCycle;
    }


    
    /**
     * 设置 openCycle
     * @param openCycle openCycle
     */
    public void setOpenCycle(String openCycle) {
        this.openCycle = openCycle;
    }


    /**
     * 获取 openCycleUnit
     * @return openCycleUnit.
     */
    public String getOpenCycleUnit() {
        return openCycleUnit;
    }


    
    /**
     * 设置 openCycleUnit
     * @param openCycleUnit openCycleUnit
     */
    public void setOpenCycleUnit(String openCycleUnit) {
        this.openCycleUnit = openCycleUnit;
    }
    

	public String getRepIsConstracte() {
		return repIsConstracte;
	}


	public void setRepIsConstracte(String repIsConstracte) {
		this.repIsConstracte = repIsConstracte;
	}


	public String getRepGradeType() {
		return repGradeType;
	}


	public void setRepGradeType(String repGradeType) {
		this.repGradeType = repGradeType;
	}


	public String getRepOpenMode() {
		return repOpenMode;
	}


	public void setRepOpenMode(String repOpenMode) {
		this.repOpenMode = repOpenMode;
	}

	public String getIsMerger() {
		return isMerger;
	}

	public void setIsMerger(String isMerger) {
		this.isMerger = isMerger;
	}

	/**
	 * @return the repYieldRemark
	 */
	public String getRepYieldRemark() {
		return repYieldRemark;
	}

	/**
	 * @param repYieldRemark the repYieldRemark to set
	 */
	public void setRepYieldRemark(String repYieldRemark) {
		this.repYieldRemark = repYieldRemark;
	}

    
}
