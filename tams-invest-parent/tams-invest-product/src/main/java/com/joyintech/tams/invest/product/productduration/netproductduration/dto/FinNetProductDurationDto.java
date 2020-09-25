/*
 * 文件名：FinNetProductDurationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.netproductduration.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.validator.FinNetProductDurationDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zqz
 * @version 1.0 2019年1月15日
 * @see FinNetProductDurationDto
 * @since 1.0
 */
@PageId("FinNetProductDuration")
@ValidLogic(targetClass = FinNetProductDurationDtoValidator.class, groups = {ValidateGroupEdit.class,
		ValidateGroupDelete.class})
public class FinNetProductDurationDto extends BaseEntity implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5480204273162551173L;
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 撤销原因
     */
    private String revokeReason;
    /**
     * 界面类型
     */
    private String pageModel;
    /**
     * 画面元素
     */
    private String elementId;
    /**
     * 收益类型：01 净值  03 货币
     */
    private String profitType;
    /**
     * 是否周期型产品 Y/N
     */
    private String isCycle;
    /**
     * 运作方式，开放式 01、封闭式 02
     */
    private String operationType;
    /**
     * 交易编号
     */
    private String tradeId;
    /**
     * 产品组合代码
     */
    private String portfolioId;
    
    /**
     * 产品代码+产品类型
     */
    private String finprodId;
    /**
     * 产品代码
     */
    private String finprodMarketId;
    
    /**
     * 分支序号
     */
    private Long branch;
    
    /**
     * 交易类型 
     */
    private String dealType;
    /**
     * 交易方向（风险接口需要）
     */
    private String ps;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 产品起息日
     */
    private Date vDate;
    /**
     * 产品到期日
     */
    private Date mDate;
    /**
     * 交易日历
     */
    private String calendarId;
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    /**
     * 期限类型
     */
    private String termType;
    /**
     * 期限(天)
     */
    private Long term;
    /**
     * 	期次管理方式	debug
     */
    private String maneWay;
    
    /*认购要素 start*/
    /**
     * 确认日
     */
    private Date subConfirmDate;
    /**
     * 交易金额
     */
    private BigDecimal subDealAmt;
    /**
     * 交易份额
     */
    private BigDecimal subDealNum;
    /**
     * 认购费
     */
    private BigDecimal subFee;
    /**
     * 期次代码
     */
    private String subPeriodId;
    /**
     * 投资周期
     */
    private String subTermDays;
    /**
     * 期次结束日
     */
    private Date subCycleEndDate;
    /**
     * 期次支付日
     */
    private Date subCyclePayDate;
    /**
     * 期次分支序号
     */
    private Long subPeriodBranch;
    /**
     * 交易备注
     */
    private String subRemark;
    /*认购要素 end*/
    
    /*申购要素 start*/
    /**
     * 申购确认日
     */
    private Date purConfirmDate;
    /**
     * 净值日
     */
    private Date purNetValueDate;
    /**
     * 单位净值
     */
    private BigDecimal purUnitNetValue;
    /**
     * 申购金额
     */
    private BigDecimal purDealAmt;
    /**
     * 申购份额
     */
    private BigDecimal purDealNum;
    /**
     * 申购费
     */
    private BigDecimal purFee;
    /**
     * 期次代码
     */
    private String purPeriodId;
    /**
     * 投资周期
     */
    private String purTermDays;
    /**
     * 期次结束日
     */
    private Date purCycleEndDate;
    /**
     * 期次支付日
     */
    private Date purCyclePayDate;
    /**
     * 期次分支序号
     */
    private Long purPeriodBranch;
    /**
     * 交易备注
     */
    private String purRemark;
    /*申购要素 end*/
    
    /*赎回要素 包含 赎回、提前赎回、违约赎回 start*/
    /**
     * 确认日
     */
    private Date redConfirmDate;
    /**
     * 支付日
     */
    private Date redPayDate;
    /**
     * 净值日
     */
    private Date redNetValueDate;
    /**
     * 单位净值
     */
    private BigDecimal redUnitNetValue;
    /**
     * 交易金额
     */
    private BigDecimal redDealAmt;
    /**
     * 交易份额
     */
    private BigDecimal redDealNum;
    /**
     * 赎回费(产品留存)
     */
    private BigDecimal redFeeLc;
    /**
     * 净金额
     */    
    private BigDecimal redTotalNetValue;
    
    /**
     * 赎回费
     */
    private BigDecimal redFee;
    /**
     * 申购费(后端)
     */
    private BigDecimal redPurFeeHd;
    /**
     * 赎回收益
     */
    private BigDecimal redProfit;
    /**
     * 赎回成本
     */
    private BigDecimal redCost;
    
    /**
     * 违约金
     */
    private BigDecimal redLiquidatedDamages;
    /**
     * 备注
     */
    private String redRemark;
    /**
     * 赎回期次代码
     */
    private String redPeriodId;
    /**
     * 赎回期次开始日
     */
    private Date redPeriodStartDate;
    /**
     * 赎回期次结束日
     */
    private Date redPeriodEndDate;
    /**
     * 赎回浮动管理费
     */
    private BigDecimal redManaFreeVal;
    /**
     * 期次交易信息
     */
    private List<FinNetProductDurationDto> redCycleFinNetProductDurationDtoList;
    /*赎回要素 包含 赎回、提前赎回、违约赎回 end*/
    
    /*到期、提前终止要素 start*/
    /**
     * 确认日
     */
    private Date expireConfirmDate;
    /**
     * 支付日
     */
    private Date expirePayDate;
    /**
     * 净值日
     */
    private Date expireNetValueDate;
    /**
     * 单位净值
     */
    private BigDecimal expireUnitNetValue;
    /**
     * 交易金额
     */
    private BigDecimal expireDealAmt;
    /**
     * 交易份额
     */
    private BigDecimal expireDealNum;
    /**
     * 到期收益
     */
    private BigDecimal expireProfit;
    /**
     * 到期成本
     */
    private BigDecimal expireCost;
    /**
     * 备注
     */
    private String expireRemark;
    /**
     * 到期浮动管理费
     */
    private BigDecimal expireManaFreeVal;
    /**
     * 期次交易信息
     */
    private List<FinNetProductDurationDto> expireCycleFinNetProductDurationDtoList;
    /*到期、提前终止要素 end*/
    
    /*强增要素 start*/
    /**
     * 确认日
     */
    private Date addReduceConfirmDate;
    /**
     * 净值日
     */
    private Date addReduceNetValueDate;
    /**
     * 单位净值
     */
    private BigDecimal addReduceUnitNetValue;
    /**
     * 交易金额
     */
    private BigDecimal addReduceDealAmt;
    /**
     * 交易份额
     */
    private BigDecimal addReduceDealNum;
    /**
     * 备注
     */
    private String addReduceRemark;
    /**
     * 期次交易信息
     */
    private List<FinNetProductDurationDto> addReduceCycleFinNetProductDurationDtoList;
    /*强增要素 end*/
    
    //分层信息list
  	private List<FinNetProductDurationLayerDto> finNetProductDurationLayerList;
    /*货币产品 认申购 赎回 到期 强增强减 分红start*/
    /*认购要素 start*/
    /**
     * 确认日
     */
    private Date subConfirmDate03;
    /**
     * 交易金额
     */
    private BigDecimal subDealAmt03;
    /**
     * 认购费
     */
    private BigDecimal subFee03;
    /**
     * 交易备注
     */
    private String subRemark03;
    /*认购要素 end*/
    
    /*申购要素 start*/
    /**
     * 申购确认日
     */
    private Date purConfirmDate03;
    /**
     * 申购金额
     */
    private BigDecimal purDealAmt03;
    /**
     * 申购费
     */
    private BigDecimal purFee03;
    /**
     * 交易备注
     */
    private String purRemark03;
    /*申购要素 end*/
    
    /*赎回要素 包含 赎回、提前赎回 start*/
    /**
     * 确认日
     */
    private Date redConfirmDate03;
    /**
     * 本金支付日
     */
    private Date redPrinPayDate03;
    /**
     * 收益支付日
     */
    private Date redProfitPayDate03;
    /**
     * 本金金额
     */
    private BigDecimal redPrinAmt03;
    /**
     * 收益金额
     */
    private BigDecimal redProfit03;
    
    /**
     * 交易金额
     */
    private BigDecimal redDealAmt03;
    /**
     * 赎回费(产品留存)
     */
    private BigDecimal redFeeLc03;
    /**
     * 违约金
     */
    private BigDecimal redLiquidatedDamages03;
    /**
     * 净金额
     */    
    private BigDecimal redTotalNetValue03;
    
    /**
     * 赎回费
     */
    private BigDecimal redFee03;
    /**
     * 申购费(后端)
     */
    private BigDecimal redPurFeeHd03;
    
    /**
     * 备注
     */
    private String redRemark03;
    /*赎回要素 包含 赎回、提前赎回 end*/
    
    /*到期、提前终止要素 start*/
    /**
     * 确认日
     */
    private Date expireConfirmDate03;
    /**
     * 本金支付日
     */
    private Date expirePrinPayDate03;
    /**
     * 收益支付日
     */
    private Date expireProfitPayDate03;
    /**
     * 本金金额
     */
    private BigDecimal expirePrinAmt03;
    /**
     * 收益金额
     */
    private BigDecimal expireProfit03;
    /**
     * 交易金额
     */
    private BigDecimal expireDealAmt03;
    /**
     * 备注
     */
    private String expireRemark03;
    /*到期、提前终止要素 end*/
    
    /*强增、强减要素 start*/
    /**
     * 确认日
     */
    private Date addReduceConfirmDate03;
    /**
     * 交易金额
     */
    private BigDecimal addReduceDealAmt03;
    /**
     * 交易份额
     */
    private BigDecimal addReduceDealNum03;
    /**
     * 备注
     */
    private String addReduceRemark03;
    /*强增、强减要素 end*/
    
    /*分红要素 start*/
    /**
     * 计息结束日
     */
    private Date bonusCashEndDate03;
    /**
     * 红利下发日
     */
    private Date bonusCashBonusDate03;
    /**
     * 分红总金额
     */
    private BigDecimal bonusCashBonusAmt03;
    /**
     * 备注
     */
    private String bonusCashRemark03;
    /**
     * 红利再投份额-交易份额
     */
    private BigDecimal bonusShareAmtNum03;
    /*分红要素 end*/
    
    /*红利再投要素 start*/
    /**
     * 计息结束日
     */
    private Date bonusShareEndDate03;
    /**
     * 再投确认日
     */
    private Date bonusShareConfirmDate03;
    /**
     * 红利再投金额
     */
    private BigDecimal bonusShareAmt03;
    /**
     * 分红后总份额
     */
    private BigDecimal bonusShareAfterShareNum03;
    /**
     * 红利下发前总份额
     */
    private BigDecimal bonusShareAfterShareNumBf;
    /**
     * 备注
     */
    private String bonusShareRemark03;
    
    private Date ndate;
    /**
     * 产品处理模式
     */
    private String dealMode;
    
    public Date getNdate() {
		return ndate;
	}

	public void setNdate(Date ndate) {
		this.ndate = ndate;
	}

	/*红利再投要素 end*/
    /*货币产品 认申购 赎回 到期 强增强减 分红start*/
    /**
     * {@inheritDoc}
     */
//    @Override
//    public void setWorkflowId(String workflowId) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setStatus(String status) {
//        // TODO Auto-generated method stub
//        
//    }

    
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

    
    public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	/**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    
    /**
     * 获取 isCycle
     * @return isCycle.
     */
    public String getIsCycle() {
        return isCycle;
    }

    
    /**
     * 设置 isCycle
     * @param isCycle isCycle
     */
    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle;
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
     * 获取 tradeId
     * @return tradeId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
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

    
    
    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 finprodId
     * @return finprodId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    
    /**
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 dealType
     * @return dealType.
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getDealType() {
        return dealType;
    }

    
    /**
     * 设置 dealType
     * @param dealType dealType
     */
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    
    /**
     * 获取 vDate
     * @return vDate.
     */
    public Date getvDate() {
        return vDate;
    }

    
    /**
     * 设置 vDate
     * @param vDate vDate
     */
    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    
    /**
     * 获取 mDate
     * @return mDate.
     */
    public Date getmDate() {
        return mDate;
    }

    
    /**
     * 设置 mDate
     * @param mDate mDate
     */
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    
    
    public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	/**
     * 获取 bussTrdTransferOrder
     * @return bussTrdTransferOrder.
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    
    /**
     * 设置 bussTrdTransferOrder
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }
    /**
     * 获取期限类型
     * @param getTermType
     */
    public String getTermType() {
		return termType;
	}
    /**
     * 设置期限类型
     * @param termType
     */
	public void setTermType(String termType) {
		this.termType = termType;
	}

	/**
     * 获取期限
     * @param getTerm
     */
    public Long getTerm() {
		return term;
	}
    /**
     * 设备期限
     * @param Long term
     */
	public void setTerm(Long term) {
		this.term = term;
	}
	/**
     * 获取期次管理方式
     * @param getManeWay
     */
	public String getManeWay() {
		return maneWay;
	}
	/**
     * 设置期次管理方式
     * @param Long maneWay
     */
	public void setManeWay(String maneWay) {
		this.maneWay = maneWay;
	}

	/**
     * 获取 subConfirmDate
     * @return subConfirmDate.
     */
    public Date getSubConfirmDate() {
        return subConfirmDate;
    }

    
    /**
     * 设置 subConfirmDate
     * @param subConfirmDate subConfirmDate
     */
    public void setSubConfirmDate(Date subConfirmDate) {
        this.subConfirmDate = subConfirmDate;
    }

    
    /**
     * 获取 subDealAmt
     * @return subDealAmt.
     */
    public BigDecimal getSubDealAmt() {
        return subDealAmt;
    }

    
    /**
     * 设置 subDealAmt
     * @param subDealAmt subDealAmt
     */
    public void setSubDealAmt(BigDecimal subDealAmt) {
        this.subDealAmt = subDealAmt;
    }

    
    /**
     * 获取 subDealNum
     * @return subDealNum.
     */
    public BigDecimal getSubDealNum() {
        return subDealNum;
    }

    
    /**
     * 设置 subDealNum
     * @param subDealNum subDealNum
     */
    public void setSubDealNum(BigDecimal subDealNum) {
        this.subDealNum = subDealNum;
    }

    
    /**
     * 获取 subFee
     * @return subFee.
     */
    public BigDecimal getSubFee() {
        return subFee;
    }

    
    /**
     * 设置 subFee
     * @param subFee subFee
     */
    public void setSubFee(BigDecimal subFee) {
        this.subFee = subFee;
    }
    
	public String getSubPeriodId() {
		return subPeriodId;
	}

	public void setSubPeriodId(String subPeriodId) {
		this.subPeriodId = subPeriodId;
	}

	public String getSubTermDays() {
		return subTermDays;
	}

	public void setSubTermDays(String subTermDays) {
		this.subTermDays = subTermDays;
	}

	/**
     * 获取 subCycleEndDate
     * @return subCycleEndDate.
     */
    public Date getSubCycleEndDate() {
        return subCycleEndDate;
    }

    
    /**
     * 设置 subCycleEndDate
     * @param subCycleEndDate subCycleEndDate
     */
    public void setSubCycleEndDate(Date subCycleEndDate) {
        this.subCycleEndDate = subCycleEndDate;
    }

    
    /**
     * 获取 subCyclePayDate
     * @return subCyclePayDate.
     */
    public Date getSubCyclePayDate() {
        return subCyclePayDate;
    }

    
    /**
     * 设置 subCyclePayDate
     * @param subCyclePayDate subCyclePayDate
     */
    public void setSubCyclePayDate(Date subCyclePayDate) {
        this.subCyclePayDate = subCyclePayDate;
    }

    /**
     * 获取subCyclePayDate(认购期次分支序号)
     * @param 
     */
    public Long getSubPeriodBranch() {
		return subPeriodBranch;
	}
    
    /**
     * 设置 subCyclePayDate
     * @param subPeriodBranch
     */
	public void setSubPeriodBranch(Long subPeriodBranch) {
		this.subPeriodBranch = subPeriodBranch;
	}

	/**
     * 获取 subRemark
     * @return subRemark.
     */
    public String getSubRemark() {
        return subRemark;
    }

    
    /**
     * 设置 subRemark
     * @param subRemark subRemark
     */
    public void setSubRemark(String subRemark) {
        this.subRemark = subRemark;
    }

    
    /**
     * 获取 purConfirmDate
     * @return purConfirmDate.
     */
    public Date getPurConfirmDate() {
        return purConfirmDate;
    }

    
    /**
     * 设置 purConfirmDate
     * @param purConfirmDate purConfirmDate
     */
    public void setPurConfirmDate(Date purConfirmDate) {
        this.purConfirmDate = purConfirmDate;
    }

    
    /**
     * 获取 purNetValueDate
     * @return purNetValueDate.
     */
    public Date getPurNetValueDate() {
        return purNetValueDate;
    }

    
    /**
     * 设置 purNetValueDate
     * @param purNetValueDate purNetValueDate
     */
    public void setPurNetValueDate(Date purNetValueDate) {
        this.purNetValueDate = purNetValueDate;
    }

    
    /**
     * 获取 purUnitNetValue
     * @return purUnitNetValue.
     */
    public BigDecimal getPurUnitNetValue() {
        return purUnitNetValue;
    }

    
    /**
     * 设置 purUnitNetValue
     * @param purUnitNetValue purUnitNetValue
     */
    public void setPurUnitNetValue(BigDecimal purUnitNetValue) {
        this.purUnitNetValue = purUnitNetValue;
    }

    
    /**
     * 获取 purDealAmt
     * @return purDealAmt.
     */
    public BigDecimal getPurDealAmt() {
        return purDealAmt;
    }

    
    /**
     * 设置 purDealAmt
     * @param purDealAmt purDealAmt
     */
    public void setPurDealAmt(BigDecimal purDealAmt) {
        this.purDealAmt = purDealAmt;
    }

    
    /**
     * 获取 purDealNum
     * @return purDealNum.
     */
    public BigDecimal getPurDealNum() {
        return purDealNum;
    }

    
    /**
     * 设置 purDealNum
     * @param purDealNum purDealNum
     */
    public void setPurDealNum(BigDecimal purDealNum) {
        this.purDealNum = purDealNum;
    }

    
    /**
     * 获取 purFee
     * @return purFee.
     */
    public BigDecimal getPurFee() {
        return purFee;
    }

    
    /**
     * 设置 purFee
     * @param purFee purFee
     */
    public void setPurFee(BigDecimal purFee) {
        this.purFee = purFee;
    }

	public String getPurPeriodId() {
		return purPeriodId;
	}

	public void setPurPeriodId(String purPeriodId) {
		this.purPeriodId = purPeriodId;
	}

	public String getPurTermDays() {
		return purTermDays;
	}

	public void setPurTermDays(String purTermDays) {
		this.purTermDays = purTermDays;
	}

	/**
     * 获取 purCycleEndDate
     * @return purCycleEndDate.
     */
    public Date getPurCycleEndDate() {
        return purCycleEndDate;
    }

    
    /**
     * 设置 purCycleEndDate
     * @param purCycleEndDate purCycleEndDate
     */
    public void setPurCycleEndDate(Date purCycleEndDate) {
        this.purCycleEndDate = purCycleEndDate;
    }

    
    /**
     * 获取 purCyclePayDate
     * @return purCyclePayDate.
     */
    public Date getPurCyclePayDate() {
        return purCyclePayDate;
    }

    
    /**
     * 设置 purCyclePayDate
     * @param purCyclePayDate purCyclePayDate
     */
    public void setPurCyclePayDate(Date purCyclePayDate) {
        this.purCyclePayDate = purCyclePayDate;
    }

    /**
     * 获取 purCyclePayDate（申购期次分支序号）
     * @return purCyclePayDate.
     */
    public Long getPurPeriodBranch() {
		return purPeriodBranch;
	}
    
    /**
     * 设置 purPeriodBranch
     * @param purPeriodBranch purPeriodBranch
     */
	public void setPurPeriodBranch(Long purPeriodBranch) {
		this.purPeriodBranch = purPeriodBranch;
	}

	/**
     * 获取 purRemark
     * @return purRemark.
     */
    public String getPurRemark() {
        return purRemark;
    }

    
    /**
     * 设置 purRemark
     * @param purRemark purRemark
     */
    public void setPurRemark(String purRemark) {
        this.purRemark = purRemark;
    }

    
    /**
     * 获取 redConfirmDate
     * @return redConfirmDate.
     */
    public Date getRedConfirmDate() {
        return redConfirmDate;
    }

    
    /**
     * 设置 redConfirmDate
     * @param redConfirmDate redConfirmDate
     */
    public void setRedConfirmDate(Date redConfirmDate) {
        this.redConfirmDate = redConfirmDate;
    }

    
    /**
     * 获取 redPayDate
     * @return redPayDate.
     */
    public Date getRedPayDate() {
        return redPayDate;
    }

    
    /**
     * 设置 redPayDate
     * @param redPayDate redPayDate
     */
    public void setRedPayDate(Date redPayDate) {
        this.redPayDate = redPayDate;
    }

    
    /**
     * 获取 redNetValueDate
     * @return redNetValueDate.
     */
    public Date getRedNetValueDate() {
        return redNetValueDate;
    }

    
    /**
     * 设置 redNetValueDate
     * @param redNetValueDate redNetValueDate
     */
    public void setRedNetValueDate(Date redNetValueDate) {
        this.redNetValueDate = redNetValueDate;
    }

    
    /**
     * 获取 redUnitNetValue
     * @return redUnitNetValue.
     */
    public BigDecimal getRedUnitNetValue() {
        return redUnitNetValue;
    }

    
    /**
     * 设置 redUnitNetValue
     * @param redUnitNetValue redUnitNetValue
     */
    public void setRedUnitNetValue(BigDecimal redUnitNetValue) {
        this.redUnitNetValue = redUnitNetValue;
    }

    
    /**
     * 获取 redDealAmt
     * @return redDealAmt.
     */
    public BigDecimal getRedDealAmt() {
        return redDealAmt;
    }

    
    /**
     * 设置 redDealAmt
     * @param redDealAmt redDealAmt
     */
    public void setRedDealAmt(BigDecimal redDealAmt) {
        this.redDealAmt = redDealAmt;
    }

    
    /**
     * 获取 redDealNum
     * @return redDealNum.
     */
    public BigDecimal getRedDealNum() {
        return redDealNum;
    }

    
    /**
     * 设置 redDealNum
     * @param redDealNum redDealNum
     */
    public void setRedDealNum(BigDecimal redDealNum) {
        this.redDealNum = redDealNum;
    }

    
    /**
     * 获取 redFeeLc
     * @return redFeeLc.
     */
    public BigDecimal getRedFeeLc() {
        return redFeeLc;
    }

    
    /**
     * 设置 redFeeLc
     * @param redFeeLc redFeeLc
     */
    public void setRedFeeLc(BigDecimal redFeeLc) {
        this.redFeeLc = redFeeLc;
    }

    
    /**
     * 获取 redTotalNetValue
     * @return redTotalNetValue.
     */
    public BigDecimal getRedTotalNetValue() {
        return redTotalNetValue;
    }

    
    /**
     * 设置 redTotalNetValue
     * @param redTotalNetValue redTotalNetValue
     */
    public void setRedTotalNetValue(BigDecimal redTotalNetValue) {
        this.redTotalNetValue = redTotalNetValue;
    }

    
    /**
     * 获取 redFee
     * @return redFee.
     */
    public BigDecimal getRedFee() {
        return redFee;
    }

    
    /**
     * 设置 redFee
     * @param redFee redFee
     */
    public void setRedFee(BigDecimal redFee) {
        this.redFee = redFee;
    }

    
    /**
     * 获取 redPurFeeHd
     * @return redPurFeeHd.
     */
    public BigDecimal getRedPurFeeHd() {
        return redPurFeeHd;
    }

    
    /**
     * 设置 redPurFeeHd
     * @param redPurFeeHd redPurFeeHd
     */
    public void setRedPurFeeHd(BigDecimal redPurFeeHd) {
        this.redPurFeeHd = redPurFeeHd;
    }

    
    /**
     * 获取 redProfit
     * @return redProfit.
     */
    public BigDecimal getRedProfit() {
        return redProfit;
    }

    
    /**
     * 设置 redProfit
     * @param redProfit redProfit
     */
    public void setRedProfit(BigDecimal redProfit) {
        this.redProfit = redProfit;
    }

    
    /**
     * 获取 redCost
     * @return redCost.
     */
    public BigDecimal getRedCost() {
        return redCost;
    }

    
    /**
     * 设置 redCost
     * @param redCost redCost
     */
    public void setRedCost(BigDecimal redCost) {
        this.redCost = redCost;
    }

    
    public BigDecimal getRedLiquidatedDamages() {
		return redLiquidatedDamages;
	}

	public void setRedLiquidatedDamages(BigDecimal redLiquidatedDamages) {
		this.redLiquidatedDamages = redLiquidatedDamages;
	}

	/**
     * 获取 redRemark
     * @return redRemark.
     */
    public String getRedRemark() {
        return redRemark;
    }

    
    /**
     * 设置 redRemark
     * @param redRemark redRemark
     */
    public void setRedRemark(String redRemark) {
        this.redRemark = redRemark;
    }
    /**
     * 获取赎回期次代码
     * @param 
     */
    public String getRedPeriodId() {
		return redPeriodId;
	}
    /**
     * 设置赎回期次代码
     * @param String redPeriodId
     */
	public void setRedPeriodId(String redPeriodId) {
		this.redPeriodId = redPeriodId;
	}
	/**
     * 获取赎回期次开始日
     * @param 
     */
	public Date getRedPeriodStartDate() {
		return redPeriodStartDate;
	}
	/**
     * 设置赎回期次开始日
     * @param Date redPeriodStartDate
     */
	public void setRedPeriodStartDate(Date redPeriodStartDate) {
		this.redPeriodStartDate = redPeriodStartDate;
	}
	/**
     * 获取赎回期次结束日
     * @param 
     */
	public Date getRedPeriodEndDate() {
		return redPeriodEndDate;
	}
	/**
     * 设置赎回期次结束日
     * @param Date redPeriodEndDate
     */
	public void setRedPeriodEndDate(Date redPeriodEndDate) {
		this.redPeriodEndDate = redPeriodEndDate;
	}
	/**
     * 获取赎回期次管理费
     * @param 
     */
	public BigDecimal getRedManaFreeVal() {
		return redManaFreeVal;
	}
	/**
     * 设置赎回期次管理费
     * @param BigDecimal redManaFreeVal
     */
	public void setRedManaFreeVal(BigDecimal redManaFreeVal) {
		this.redManaFreeVal = redManaFreeVal;
	}
	/**
     * 获取 redCycleFinNetProductDurationDtoList
     * @return redCycleFinNetProductDurationDtoList.
     */
    public List<FinNetProductDurationDto> getRedCycleFinNetProductDurationDtoList() {
        return redCycleFinNetProductDurationDtoList;
    }

    
    /**
     * 设置 redCycleFinNetProductDurationDtoList
     * @param redCycleFinNetProductDurationDtoList redCycleFinNetProductDurationDtoList
     */
    public void setRedCycleFinNetProductDurationDtoList(List<FinNetProductDurationDto> redCycleFinNetProductDurationDtoList) {
        this.redCycleFinNetProductDurationDtoList = redCycleFinNetProductDurationDtoList;
    }

    /**
     * 获取 expireConfirmDate
     * @return expireConfirmDate.
     */
    public Date getExpireConfirmDate() {
        return expireConfirmDate;
    }

    
    /**
     * 设置 expireConfirmDate
     * @param expireConfirmDate expireConfirmDate
     */
    public void setExpireConfirmDate(Date expireConfirmDate) {
        this.expireConfirmDate = expireConfirmDate;
    }

    
    /**
     * 获取 expirePayDate
     * @return expirePayDate.
     */
    public Date getExpirePayDate() {
        return expirePayDate;
    }

    
    /**
     * 设置 expirePayDate
     * @param expirePayDate expirePayDate
     */
    public void setExpirePayDate(Date expirePayDate) {
        this.expirePayDate = expirePayDate;
    }

    
    /**
     * 获取 expireNetValueDate
     * @return expireNetValueDate.
     */
    public Date getExpireNetValueDate() {
        return expireNetValueDate;
    }

    
    /**
     * 设置 expireNetValueDate
     * @param expireNetValueDate expireNetValueDate
     */
    public void setExpireNetValueDate(Date expireNetValueDate) {
        this.expireNetValueDate = expireNetValueDate;
    }

    
    /**
     * 获取 expireUnitNetValue
     * @return expireUnitNetValue.
     */
    public BigDecimal getExpireUnitNetValue() {
        return expireUnitNetValue;
    }

    
    /**
     * 设置 expireUnitNetValue
     * @param expireUnitNetValue expireUnitNetValue
     */
    public void setExpireUnitNetValue(BigDecimal expireUnitNetValue) {
        this.expireUnitNetValue = expireUnitNetValue;
    }

    
    /**
     * 获取 expireDealAmt
     * @return expireDealAmt.
     */
    public BigDecimal getExpireDealAmt() {
        return expireDealAmt;
    }

    
    /**
     * 设置 expireDealAmt
     * @param expireDealAmt expireDealAmt
     */
    public void setExpireDealAmt(BigDecimal expireDealAmt) {
        this.expireDealAmt = expireDealAmt;
    }

    
    /**
     * 获取 expireDealNum
     * @return expireDealNum.
     */
    public BigDecimal getExpireDealNum() {
        return expireDealNum;
    }

    
    /**
     * 设置 expireDealNum
     * @param expireDealNum expireDealNum
     */
    public void setExpireDealNum(BigDecimal expireDealNum) {
        this.expireDealNum = expireDealNum;
    }

    
    /**
     * 获取 expireProfit
     * @return expireProfit.
     */
    public BigDecimal getExpireProfit() {
        return expireProfit;
    }

    
    /**
     * 设置 expireProfit
     * @param expireProfit expireProfit
     */
    public void setExpireProfit(BigDecimal expireProfit) {
        this.expireProfit = expireProfit;
    }

    
    /**
     * 获取 expireCost
     * @return expireCost.
     */
    public BigDecimal getExpireCost() {
        return expireCost;
    }

    
    /**
     * 设置 expireCost
     * @param expireCost expireCost
     */
    public void setExpireCost(BigDecimal expireCost) {
        this.expireCost = expireCost;
    }
    
    /**
     * 获取 expireRemark
     * @return expireRemark.
     */
    public String getExpireRemark() {
        return expireRemark;
    }
    
    /**
     * 设置 expireRemark
     * @param expireRemark expireRemark
     */
    public void setExpireRemark(String expireRemark) {
        this.expireRemark = expireRemark;
    }
    /**
     * 获取到期浮动管理费
     * @return expireRemark.
     */
    
    public BigDecimal getExpireManaFreeVal() {
		return expireManaFreeVal;
	}
    /**
     * 设置到期浮动管理费
     * @param expireRemark expireRemark
     */
	public void setExpireManaFreeVal(BigDecimal expireManaFreeVal) {
		this.expireManaFreeVal = expireManaFreeVal;
	}

	/**
     * 获取 expireCycleFinNetProductDurationDtoList
     * @return expireCycleFinNetProductDurationDtoList.
     */
    public List<FinNetProductDurationDto> getExpireCycleFinNetProductDurationDtoList() {
        return expireCycleFinNetProductDurationDtoList;
    }

    
    /**
     * 设置 expireCycleFinNetProductDurationDtoList
     * @param expireCycleFinNetProductDurationDtoList expireCycleFinNetProductDurationDtoList
     */
    public void setExpireCycleFinNetProductDurationDtoList(List<FinNetProductDurationDto> expireCycleFinNetProductDurationDtoList) {
        this.expireCycleFinNetProductDurationDtoList = expireCycleFinNetProductDurationDtoList;
    }

    /**
     * 获取 addReduceConfirmDate
     * @return addReduceConfirmDate.
     */
    public Date getAddReduceConfirmDate() {
        return addReduceConfirmDate;
    }

    
    /**
     * 设置 addReduceConfirmDate
     * @param addReduceConfirmDate addReduceConfirmDate
     */
    public void setAddReduceConfirmDate(Date addReduceConfirmDate) {
        this.addReduceConfirmDate = addReduceConfirmDate;
    }

    
    /**
     * 获取 addReduceNetValueDate
     * @return addReduceNetValueDate.
     */
    public Date getAddReduceNetValueDate() {
        return addReduceNetValueDate;
    }

    
    /**
     * 设置 addReduceNetValueDate
     * @param addReduceNetValueDate addReduceNetValueDate
     */
    public void setAddReduceNetValueDate(Date addReduceNetValueDate) {
        this.addReduceNetValueDate = addReduceNetValueDate;
    }

    
    /**
     * 获取 addReduceUnitNetValue
     * @return addReduceUnitNetValue.
     */
    public BigDecimal getAddReduceUnitNetValue() {
        return addReduceUnitNetValue;
    }

    
    /**
     * 设置 addReduceUnitNetValue
     * @param addReduceUnitNetValue addReduceUnitNetValue
     */
    public void setAddReduceUnitNetValue(BigDecimal addReduceUnitNetValue) {
        this.addReduceUnitNetValue = addReduceUnitNetValue;
    }

    
    /**
     * 获取 addReduceDealAmt
     * @return addReduceDealAmt.
     */
    public BigDecimal getAddReduceDealAmt() {
        return addReduceDealAmt;
    }

    
    /**
     * 设置 addReduceDealAmt
     * @param addReduceDealAmt addReduceDealAmt
     */
    public void setAddReduceDealAmt(BigDecimal addReduceDealAmt) {
        this.addReduceDealAmt = addReduceDealAmt;
    }

    
    /**
     * 获取 addReduceDealNum
     * @return addReduceDealNum.
     */
    public BigDecimal getAddReduceDealNum() {
        return addReduceDealNum;
    }

    
    /**
     * 设置 addReduceDealNum
     * @param addReduceDealNum addReduceDealNum
     */
    public void setAddReduceDealNum(BigDecimal addReduceDealNum) {
        this.addReduceDealNum = addReduceDealNum;
    }

    
    /**
     * 获取 addReduceRemark
     * @return addReduceRemark.
     */
    public String getAddReduceRemark() {
        return addReduceRemark;
    }

    
    /**
     * 设置 addReduceRemark
     * @param addReduceRemark addReduceRemark
     */
    public void setAddReduceRemark(String addReduceRemark) {
        this.addReduceRemark = addReduceRemark;
    }

    
    /**
     * 获取 addReduceCycleFinNetProductDurationDtoList
     * @return addReduceCycleFinNetProductDurationDtoList.
     */
    public List<FinNetProductDurationDto> getAddReduceCycleFinNetProductDurationDtoList() {
        return addReduceCycleFinNetProductDurationDtoList;
    }

    
    /**
     * 设置 addReduceCycleFinNetProductDurationDtoList
     * @param addReduceCycleFinNetProductDurationDtoList addReduceCycleFinNetProductDurationDtoList
     */
    public void setAddReduceCycleFinNetProductDurationDtoList(List<FinNetProductDurationDto> addReduceCycleFinNetProductDurationDtoList) {
        this.addReduceCycleFinNetProductDurationDtoList = addReduceCycleFinNetProductDurationDtoList;
    }

	public Date getSubConfirmDate03() {
		return subConfirmDate03;
	}

	public void setSubConfirmDate03(Date subConfirmDate03) {
		this.subConfirmDate03 = subConfirmDate03;
	}

	public BigDecimal getSubDealAmt03() {
		return subDealAmt03;
	}

	public void setSubDealAmt03(BigDecimal subDealAmt03) {
		this.subDealAmt03 = subDealAmt03;
	}

	public BigDecimal getSubFee03() {
		return subFee03;
	}

	public void setSubFee03(BigDecimal subFee03) {
		this.subFee03 = subFee03;
	}

	public String getSubRemark03() {
		return subRemark03;
	}

	public void setSubRemark03(String subRemark03) {
		this.subRemark03 = subRemark03;
	}

	public Date getPurConfirmDate03() {
		return purConfirmDate03;
	}

	public void setPurConfirmDate03(Date purConfirmDate03) {
		this.purConfirmDate03 = purConfirmDate03;
	}

	public BigDecimal getPurDealAmt03() {
		return purDealAmt03;
	}

	public void setPurDealAmt03(BigDecimal purDealAmt03) {
		this.purDealAmt03 = purDealAmt03;
	}

	public BigDecimal getPurFee03() {
		return purFee03;
	}

	public void setPurFee03(BigDecimal purFee03) {
		this.purFee03 = purFee03;
	}

	public String getPurRemark03() {
		return purRemark03;
	}

	public void setPurRemark03(String purRemark03) {
		this.purRemark03 = purRemark03;
	}

	public Date getRedConfirmDate03() {
		return redConfirmDate03;
	}

	public void setRedConfirmDate03(Date redConfirmDate03) {
		this.redConfirmDate03 = redConfirmDate03;
	}

	public Date getRedPrinPayDate03() {
		return redPrinPayDate03;
	}

	public void setRedPrinPayDate03(Date redPrinPayDate03) {
		this.redPrinPayDate03 = redPrinPayDate03;
	}

	public Date getRedProfitPayDate03() {
		return redProfitPayDate03;
	}

	public void setRedProfitPayDate03(Date redProfitPayDate03) {
		this.redProfitPayDate03 = redProfitPayDate03;
	}

	public BigDecimal getRedPrinAmt03() {
		return redPrinAmt03;
	}

	public void setRedPrinAmt03(BigDecimal redPrinAmt03) {
		this.redPrinAmt03 = redPrinAmt03;
	}

	public BigDecimal getRedProfit03() {
		return redProfit03;
	}

	public void setRedProfit03(BigDecimal redProfit03) {
		this.redProfit03 = redProfit03;
	}

	public BigDecimal getRedDealAmt03() {
		return redDealAmt03;
	}

	public void setRedDealAmt03(BigDecimal redDealAmt03) {
		this.redDealAmt03 = redDealAmt03;
	}

	public BigDecimal getRedFeeLc03() {
		return redFeeLc03;
	}

	public void setRedFeeLc03(BigDecimal redFeeLc03) {
		this.redFeeLc03 = redFeeLc03;
	}

	public BigDecimal getRedLiquidatedDamages03() {
		return redLiquidatedDamages03;
	}

	public void setRedLiquidatedDamages03(BigDecimal redLiquidatedDamages03) {
		this.redLiquidatedDamages03 = redLiquidatedDamages03;
	}

	public BigDecimal getRedTotalNetValue03() {
		return redTotalNetValue03;
	}

	public void setRedTotalNetValue03(BigDecimal redTotalNetValue03) {
		this.redTotalNetValue03 = redTotalNetValue03;
	}

	public BigDecimal getRedFee03() {
		return redFee03;
	}

	public void setRedFee03(BigDecimal redFee03) {
		this.redFee03 = redFee03;
	}

	public BigDecimal getRedPurFeeHd03() {
		return redPurFeeHd03;
	}

	public void setRedPurFeeHd03(BigDecimal redPurFeeHd03) {
		this.redPurFeeHd03 = redPurFeeHd03;
	}

	public String getRedRemark03() {
		return redRemark03;
	}

	public void setRedRemark03(String redRemark03) {
		this.redRemark03 = redRemark03;
	}

	public Date getExpireConfirmDate03() {
		return expireConfirmDate03;
	}

	public void setExpireConfirmDate03(Date expireConfirmDate03) {
		this.expireConfirmDate03 = expireConfirmDate03;
	}

	public Date getExpirePrinPayDate03() {
		return expirePrinPayDate03;
	}

	public void setExpirePrinPayDate03(Date expirePrinPayDate03) {
		this.expirePrinPayDate03 = expirePrinPayDate03;
	}

	public Date getExpireProfitPayDate03() {
		return expireProfitPayDate03;
	}

	public void setExpireProfitPayDate03(Date expireProfitPayDate03) {
		this.expireProfitPayDate03 = expireProfitPayDate03;
	}

	public BigDecimal getExpirePrinAmt03() {
		return expirePrinAmt03;
	}

	public void setExpirePrinAmt03(BigDecimal expirePrinAmt03) {
		this.expirePrinAmt03 = expirePrinAmt03;
	}

	public BigDecimal getExpireProfit03() {
		return expireProfit03;
	}

	public void setExpireProfit03(BigDecimal expireProfit03) {
		this.expireProfit03 = expireProfit03;
	}

	public BigDecimal getExpireDealAmt03() {
		return expireDealAmt03;
	}

	public void setExpireDealAmt03(BigDecimal expireDealAmt03) {
		this.expireDealAmt03 = expireDealAmt03;
	}

	public String getExpireRemark03() {
		return expireRemark03;
	}

	public void setExpireRemark03(String expireRemark03) {
		this.expireRemark03 = expireRemark03;
	}

	public Date getAddReduceConfirmDate03() {
		return addReduceConfirmDate03;
	}

	public void setAddReduceConfirmDate03(Date addReduceConfirmDate03) {
		this.addReduceConfirmDate03 = addReduceConfirmDate03;
	}

	public BigDecimal getAddReduceDealAmt03() {
		return addReduceDealAmt03;
	}

	public void setAddReduceDealAmt03(BigDecimal addReduceDealAmt03) {
		this.addReduceDealAmt03 = addReduceDealAmt03;
	}

	public BigDecimal getAddReduceDealNum03() {
		return addReduceDealNum03;
	}

	public void setAddReduceDealNum03(BigDecimal addReduceDealNum03) {
		this.addReduceDealNum03 = addReduceDealNum03;
	}

	public String getAddReduceRemark03() {
		return addReduceRemark03;
	}

	public void setAddReduceRemark03(String addReduceRemark03) {
		this.addReduceRemark03 = addReduceRemark03;
	}

	public Date getBonusCashEndDate03() {
		return bonusCashEndDate03;
	}

	public void setBonusCashEndDate03(Date bonusCashEndDate03) {
		this.bonusCashEndDate03 = bonusCashEndDate03;
	}

	public Date getBonusCashBonusDate03() {
		return bonusCashBonusDate03;
	}

	public void setBonusCashBonusDate03(Date bonusCashBonusDate03) {
		this.bonusCashBonusDate03 = bonusCashBonusDate03;
	}

	public BigDecimal getBonusCashBonusAmt03() {
		return bonusCashBonusAmt03;
	}

	public void setBonusCashBonusAmt03(BigDecimal bonusCashBonusAmt03) {
		this.bonusCashBonusAmt03 = bonusCashBonusAmt03;
	}

	public String getBonusCashRemark03() {
		return bonusCashRemark03;
	}

	public void setBonusCashRemark03(String bonusCashRemark03) {
		this.bonusCashRemark03 = bonusCashRemark03;
	}

	public Date getBonusShareEndDate03() {
		return bonusShareEndDate03;
	}

	public void setBonusShareEndDate03(Date bonusShareEndDate03) {
		this.bonusShareEndDate03 = bonusShareEndDate03;
	}

	public Date getBonusShareConfirmDate03() {
		return bonusShareConfirmDate03;
	}

	public void setBonusShareConfirmDate03(Date bonusShareConfirmDate03) {
		this.bonusShareConfirmDate03 = bonusShareConfirmDate03;
	}

	public BigDecimal getBonusShareAmt03() {
		return bonusShareAmt03;
	}

	public void setBonusShareAmt03(BigDecimal bonusShareAmt03) {
		this.bonusShareAmt03 = bonusShareAmt03;
	}

	public BigDecimal getBonusShareAfterShareNum03() {
		return bonusShareAfterShareNum03;
	}

	public void setBonusShareAfterShareNum03(BigDecimal bonusShareAfterShareNum03) {
		this.bonusShareAfterShareNum03 = bonusShareAfterShareNum03;
	}

	public String getBonusShareRemark03() {
		return bonusShareRemark03;
	}

	public void setBonusShareRemark03(String bonusShareRemark03) {
		this.bonusShareRemark03 = bonusShareRemark03;
	}
	//add by yejiejie 2019-2-26 start
	//期初客户数
	private BigDecimal customersBeginNum;

	public BigDecimal getCustomersBeginNum() {
		return customersBeginNum;
	}

	public void setCustomersBeginNum(BigDecimal customersBeginNum) {
		this.customersBeginNum = customersBeginNum;
	}

	//增加客户数
	private BigDecimal customersAddNum;

	public BigDecimal getCustomersAddNum() {
		return customersAddNum;
	}

	public void setCustomersAddNum(BigDecimal customersAddNum) {
		this.customersAddNum = customersAddNum;
	}

	//减少客户数
	private BigDecimal customersReduceNum;

	public BigDecimal getCustomersReduceNum() {
		return customersReduceNum;
	}

	public void setCustomersReduceNum(BigDecimal customersReduceNum) {
		this.customersReduceNum = customersReduceNum;
	}
	//add by yejiejie 2019-2-26 end
	//产品简称
	private String prodAbbr;
	//产品名称
	private String prodName;
	public String getProdAbbr() {
		return prodAbbr;
	}

	public void setProdAbbr(String prodAbbr) {
		this.prodAbbr = prodAbbr;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public BigDecimal getBonusShareAfterShareNumBf() {
		return bonusShareAfterShareNumBf;
	}

	public void setBonusShareAfterShareNumBf(BigDecimal bonusShareAfterShareNumBf) {
		this.bonusShareAfterShareNumBf = bonusShareAfterShareNumBf;
	}

	public List<FinNetProductDurationLayerDto> getFinNetProductDurationLayerList() {
		return finNetProductDurationLayerList;
	}

	public void setFinNetProductDurationLayerList(List<FinNetProductDurationLayerDto> finNetProductDurationLayerList) {
		this.finNetProductDurationLayerList = finNetProductDurationLayerList;
	}

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	public BigDecimal getBonusShareAmtNum03() {
		return bonusShareAmtNum03;
	}

	public void setBonusShareAmtNum03(BigDecimal bonusShareAmtNum03) {
		this.bonusShareAmtNum03 = bonusShareAmtNum03;
	}
	  /**
     * 获取 ps
     * @return ps.
     */
    public String getPs() {
        return StringUtil.isNotEmpty(ps)?this.ps:this.getDealType();
    }

    /**
     * 设置 ps
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }
	
}
