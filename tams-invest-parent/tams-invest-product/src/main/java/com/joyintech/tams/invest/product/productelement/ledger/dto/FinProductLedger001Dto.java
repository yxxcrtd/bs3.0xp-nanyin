/*
 * 文件名：FinProductLedger001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.ledger.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild001Dto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品新建查询dto
 * 
 * @author zhangliang
 * @version 1.0 2018年10月13日
 * @see FinProductLedger001Dto
 * @since 1.0
 */
@PageId(value = "FinProductLedger001")
public class FinProductLedger001Dto extends FinProduct implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6378395144237248960L;

    private long remainDays;
    
    private String prodManager;
	
	private String riskLevel;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal raiseAmtPlan;
	
	private String payType;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal subDealAmt;	
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal prodUnitValue;	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal prodAssetAmt;	
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal subDealNum;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal trustFee;	
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal saleFee;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal fixedFee;	
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal floatDiffFee;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal othSaleFee;	
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal vatFee;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal floatFee;	
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal othFee;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal midBusFee;	
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal monthPubFee;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal monthDueFee;

	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal tdyUnitValue;

	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal yield7;

	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal tdyAssetAmt;
	
	private String floatFeeProportion;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal feeFixedRate;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal trustFeeRate;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal customerRealRate;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal customerProfit;
	
	private String durationflag;
	
	private String payCycle;
	
	private String investmentCycle;
	
	private String rateRepYieldStr;

	/**
	 * 销售手续费率
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal saleProcessRate;

	public BigDecimal getSaleProcessRate() {
		return saleProcessRate;
	}

	public void setSaleProcessRate(BigDecimal saleProcessRate) {
		this.saleProcessRate = saleProcessRate;
	}
	
	public String getRateRepYieldStr() {
		return rateRepYieldStr;
	}

	public void setRateRepYieldStr(String rateRepYieldStr) {
		this.rateRepYieldStr = rateRepYieldStr;
	}
	
	public BigDecimal getProdUnitValue() {
		return prodUnitValue;
	}

	public void setProdUnitValue(BigDecimal prodUnitValue) {
		this.prodUnitValue = prodUnitValue;
	}

	public BigDecimal getProdAssetAmt() {
		return prodAssetAmt;
	}

	public void setProdAssetAmt(BigDecimal prodAssetAmt) {
		this.prodAssetAmt = prodAssetAmt;
	}

	public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal rateRepYield;
		
	public BigDecimal getRateRepYield() {
		return rateRepYield;
	}

	public void setRateRepYield(BigDecimal rateRepYield) {
		this.rateRepYield = rateRepYield;
	}

	public String getDurationflag() {
		return durationflag;
	}

	public void setDurationflag(String durationflag) {
		this.durationflag = durationflag;
	}

	public String getPayCycle() {
		return payCycle;
	}

	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}

	public String getFloatFeeProportion() {
		return floatFeeProportion;
	}

	public void setFloatFeeProportion(String floatFeeProportion) {
		this.floatFeeProportion = floatFeeProportion;
	}

	public BigDecimal getFeeFixedRate() {
		return feeFixedRate;
	}

	public void setFeeFixedRate(BigDecimal feeFixedRate) {
		this.feeFixedRate = feeFixedRate;
	}

	public BigDecimal getTrustFeeRate() {
		return trustFeeRate;
	}

	public void setTrustFeeRate(BigDecimal trustFeeRate) {
		this.trustFeeRate = trustFeeRate;
	}

	public BigDecimal getCustomerRealRate() {
		return customerRealRate;
	}

	public void setCustomerRealRate(BigDecimal customerRealRate) {
		this.customerRealRate = customerRealRate;
	}

	public BigDecimal getCustomerProfit() {
		return customerProfit;
	}

	public void setCustomerProfit(BigDecimal customerProfit) {
		this.customerProfit = customerProfit;
	}

	public String getPayTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.分红方式, this.getPayType());
	}

	public String getCycs() {
		return SysDataUtil.getNameFromDataDict(DictCode.频率, this.getPayCycle());
	}

	public BigDecimal getTdyUnitValue() {
		return tdyUnitValue;
	}

	public void setTdyUnitValue(BigDecimal tdyUnitValue) {
		this.tdyUnitValue = tdyUnitValue;
	}

	public BigDecimal getYield7() {
		return yield7;
	}

	public void setYield7(BigDecimal yield7) {
		this.yield7 = yield7;
	}

	public BigDecimal getTdyAssetAmt() {
		return tdyAssetAmt;
	}

	public void setTdyAssetAmt(BigDecimal tdyAssetAmt) {
		this.tdyAssetAmt = tdyAssetAmt;
	}

	public BigDecimal getSubDealAmt() {
		return subDealAmt;
	}

	public void setSubDealAmt(BigDecimal subDealAmt) {
		this.subDealAmt = subDealAmt;
	}

	public BigDecimal getSubDealNum() {
		return subDealNum;
	}

	public void setSubDealNum(BigDecimal subDealNum) {
		this.subDealNum = subDealNum;
	}

	public BigDecimal getTrustFee() {
		return trustFee;
	}

	public void setTrustFee(BigDecimal trustFee) {
		this.trustFee = trustFee;
	}

	public BigDecimal getSaleFee() {
		return saleFee;
	}

	public void setSaleFee(BigDecimal saleFee) {
		this.saleFee = saleFee;
	}

	public BigDecimal getFixedFee() {
		return fixedFee;
	}

	public void setFixedFee(BigDecimal fixedFee) {
		this.fixedFee = fixedFee;
	}

	public BigDecimal getFloatDiffFee() {
		return floatDiffFee;
	}

	public void setFloatDiffFee(BigDecimal floatDiffFee) {
		this.floatDiffFee = floatDiffFee;
	}

	public BigDecimal getOthSaleFee() {
		return othSaleFee;
	}

	public void setOthSaleFee(BigDecimal othSaleFee) {
		this.othSaleFee = othSaleFee;
	}

	public BigDecimal getVatFee() {
		return vatFee;
	}

	public void setVatFee(BigDecimal vatFee) {
		this.vatFee = vatFee;
	}

	public BigDecimal getFloatFee() {
		return floatFee;
	}

	public void setFloatFee(BigDecimal floatFee) {
		this.floatFee = floatFee;
	}

	public BigDecimal getOthFee() {
		return othFee;
	}

	public void setOthFee(BigDecimal othFee) {
		this.othFee = othFee;
	}

	public BigDecimal getMidBusFee() {
		return midBusFee;
	}

	public void setMidBusFee(BigDecimal midBusFee) {
		this.midBusFee = midBusFee;
	}

	public BigDecimal getMonthPubFee() {
		return monthPubFee;
	}

	public void setMonthPubFee(BigDecimal monthPubFee) {
		this.monthPubFee = monthPubFee;
	}

	public BigDecimal getMonthDueFee() {
		return monthDueFee;
	}

	public void setMonthDueFee(BigDecimal monthDueFee) {
		this.monthDueFee = monthDueFee;
	}
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 画面Id
     */
    private String pageId;
    
    /**
     * 按钮元素Id
     */
    private String elementId;
    
    /**
     * 有效状态
     */
    private String effectStatus;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 处理模式
     */
    private String dealMode;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 生效利率
     */
    private BigDecimal yield;

    /**
     * 投资者类型
     */
    private String repInvestorType;
    
    /**
     * 目标客户一级分类 add by  libaisong  列表导出项展示
     */
    private String targetCustomer1;
    
    /**
     * 目标客户二级分类 add by  libaisong
     */
    private String targetCustomer2;

    /** add by 发送接口状态   lizhi 2019-6-19 start**/
    // 发送状态
    private String sendStatus ;
    // 发送状态备注
    private String sendRemark ;
    //期次管理方式
    private String preview;

    
    public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getRaiseAmtPlan() {
		return raiseAmtPlan;
	}

	public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
		this.raiseAmtPlan = raiseAmtPlan;
	}

	public long getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(long remainDays) {
		this.remainDays = remainDays;
	}

	public String getProdManager() {
		return prodManager;
	}

	public void setProdManager(String prodManager) {
		this.prodManager = prodManager;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }

    /** add by 发送接口状态   lizhi 2019-6-19 end**/
    
    
    public String getTargetCustomer1() {
		return targetCustomer1;
	}
    
    public String getTargetCustomer1Str() {
        return /*SysDataUtil.getNameFromDataDict(DictCode.目标客户一级分类, targetCustomer1)*/"";
    }


	public void setTargetCustomer1(String targetCustomer1) {
		this.targetCustomer1 = targetCustomer1;
	}

	public String getTargetCustomer2() {
		return targetCustomer2;
	}
	
	public String getTargetCustomer2Str() {
        return /*SysDataUtil.getNameFromDataDict(DictCode.目标客户二级分类, targetCustomer2)*/"";
    }
	public void setTargetCustomer2(String targetCustomer2) {
		this.targetCustomer2 = targetCustomer2;
	}


    /**
     * 募集开始日
     */
    private Date firstSaleVdate;

    /**
     * 募集结束日
     */
    private Date firstSaleMdate;

    /**
     * 认购起点
     */
    private BigDecimal toffStart;

    /**
     * 募集上限
     */
    private BigDecimal saleMax;

    /**
     * 募集下限
     */
    private BigDecimal saleMin;

    /**
     * 发行状态
     */
    private String issueStatus;
    
    /**
     * 发行状态备注
     */
    private String issueStatusRemark;
    
 /********* add by zhangweiqi 主产品需求添加 begin**************/
    
    /**
     * 产品登记编码
     */
    private String prodRegistCode;
    
    /**
     * 产品系列
     */
    private String prodSeries;
    
     /**
     * 产品分类
     */
    private String prodClass;
    
    /**
     * 产品收益标识
     */
    private String profitFlag;
    
    /**
     * 产品风险等级
     */
    private String repRiskLevel;
    
    /**
     * 是否支持代销
     */
    private String consignmentFlag;
    
    /**
     * 业绩基准利率类型
     */
    private String baseRateType;
    
    /**
     * 投资性质
     */
    private String investNature;
    
    
    
    /**
     * 销售部门
     */
    private String saleDepartment;
    
    /**
     * 销售渠道，多选逗号分隔，柜面、网银、直销银行、手机银行、其他电子渠道，可多选
     */
    private String saleChannel;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /********* add by zhangweiqi 主产品需求添加 end**************/
    

    public String getEffectStatus() {
        return effectStatus;
    }

    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getDealMode() {
        return dealMode;
    }
    
    public String getDealModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.处理模式, dealMode);
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public String getRateType() {
        return rateType;
    }

    public String getRateTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.利率类型, rateType);
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getYield() {
        //return ArithmeticUtil.multiply(yield, new BigDecimal(100));
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public String getRepInvestorType() {
        return repInvestorType;
    }

    public String getRepInvestorTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.目标客户类型, repInvestorType);
    }

    public void setRepInvestorType(String repInvestorType) {
        this.repInvestorType = repInvestorType;
    }

    public Date getFirstSaleVdate() {
        return firstSaleVdate;
    }

    public void setFirstSaleVdate(Date firstSaleVdate) {
        this.firstSaleVdate = firstSaleVdate;
    }

    public Date getFirstSaleMdate() {
        return firstSaleMdate;
    }

    public void setFirstSaleMdate(Date firstSaleMdate) {
        this.firstSaleMdate = firstSaleMdate;
    }

    public BigDecimal getToffStart() {
        return toffStart;
    }

    public void setToffStart(BigDecimal toffStart) {
        this.toffStart = toffStart;
    }

    public BigDecimal getSaleMax() {
        return saleMax;
    }

    public void setSaleMax(BigDecimal saleMax) {
        this.saleMax = saleMax;
    }

    public BigDecimal getSaleMin() {
        return saleMin;
    }

    public void setSaleMin(BigDecimal saleMin) {
        this.saleMin = saleMin;
    }

    public String getProfitTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.收益类型, super.getProfitType());
    }

    public String getIssueTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.募集方式, super.getIssueType());
    }

    /**
     * 起息日
     * 
     * @return 起息日
     * @see
     * @since 1.0
     */
    public String getVdateStr() {
        return DateUtil.format(super.getVdate());
    }

    /**
     * 到期日
     * 
     * @return 到期日
     * @see
     * @since 1.0
     */
    public String getMdateStr() {
        return DateUtil.format(super.getMdate());
    }

    /**
     * 募集开始日
     * 
     * @return 募集开始日
     * @see
     * @since 1.0
     */
    public String getFirstSaleVdateStr() {
        return DateUtil.format(this.getFirstSaleVdate());
    }

    /**
     * 募集结束日
     * 
     * @return 募集结束日
     * @see
     * @since 1.0
     */
    public String getFirstSaleMdateStr() {
        return DateUtil.format(this.getFirstSaleMdate());
    }

    public String getIssueStatus() {
        return issueStatus;
    }
    
    public String getIssueStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.发行状态, this.getIssueStatus());
    }

    public String getSendStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.发送状态, this.getSendStatus());
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
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
     * {@inheritDoc}
     */
    @Override
    public String getFinprodId() {
        return super.getFinprodId();
    }
    
    /**
     * {@inheritDoc}
     */
    @PrimaryKey(Field.PK_2)
    public String getFinprodMarketId() {
        return super.getFinprodMarketId();
    }

    
    /**
     * 获取 issueStatusRemark
     * @return issueStatusRemark.
     */
    public String getIssueStatusRemark() {
        return issueStatusRemark;
    }

    
    /**
     * 设置 issueStatusRemark
     * @param issueStatusRemark issueStatusRemark
     */
    public void setIssueStatusRemark(String issueStatusRemark) {
        this.issueStatusRemark = issueStatusRemark;
    }

	public String getProdRegistCode() {
		return prodRegistCode;
	}

	public void setProdRegistCode(String prodRegistCode) {
		this.prodRegistCode = prodRegistCode;
	}

	public String getProdSeries() {
		return prodSeries;
	}

	public void setProdSeries(String prodSeries) {
		this.prodSeries = prodSeries;
	}

	public String getProdClass() {
		return prodClass;
	}

	public void setProdClass(String prodClass) {
		this.prodClass = prodClass;
	}

	public String getProfitFlag() {
		return profitFlag;
	}

	public void setProfitFlag(String profitFlag) {
		this.profitFlag = profitFlag;
	}

	public String getRepRiskLevel() {
		return repRiskLevel;
	}

	public void setRepRiskLevel(String repRiskLevel) {
		this.repRiskLevel = repRiskLevel;
	}

	public String getConsignmentFlag() {
		return consignmentFlag;
	}

	public void setConsignmentFlag(String consignmentFlag) {
		this.consignmentFlag = consignmentFlag;
	}

	public String getBaseRateType() {
		return baseRateType;
	}

	public void setBaseRateType(String baseRateType) {
		this.baseRateType = baseRateType;
	}

	public String getInvestNature() {
		return investNature;
	}

	public void setInvestNature(String investNature) {
		this.investNature = investNature;
	}

	

	public String getSaleDepartment() {
		return saleDepartment;
	}

	public void setSaleDepartment(String saleDepartment) {
		this.saleDepartment = saleDepartment;
	}

	public String getSaleChannel() {
		return saleChannel;
	}

	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	 public String getProfitFlagStr() {
	        return SysDataUtil.getNameFromDataDict(DictCode.产品收益标识, this.getProfitFlag());
	    }
	 public String getRepRiskLevelStr(){
		 return SysDataUtil.getNameFromDataDict(DictCode.产品风险等级, this.getRepRiskLevel());
	 }
	 public String getConsignmentFlagStr(){
		 return SysDataUtil.getNameFromDataDict(DictCode.是否, this.getConsignmentFlag());
	 }
	 
	 public String getBaseRateTypeStr(){
		 return SysDataUtil.getNameFromDataDict(DictCode.利率类型, this.getBaseRateType());
	 }
	 
	public String getInvestNatureStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.投资性质, this.getInvestNature());
	}
	
	public String getSaleDepartmentStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.销售部门, this.getSaleDepartment());
	}
	
   public String getSaleChannelStr(){
	   return SysDataUtil.getNameFromDataDict(DictCode.销售渠道, this.getSaleChannel());
   }
   public String getOperationTypeStr(){
	   return SysDataUtil.getNameFromDataDict(DictCode.开放类型, this.getOperationType());
	   
   }
   public String getCcyStr(){
	   return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
	   
   }
   //add by yejiejie 2019-3-12 start
   public String getProdSeriesStr(){
       if (StringUtil.isEmpty(this.getProdSeries())) {
           return StringUtil.EMPTY;
       }
	   return SysDataUtil.getNameFromDataDict(DictCode.产品系列, this.getProdSeries());
   }
   //add by yejiejie 2019-3-12 end

   //add by zhangJin 20191017 start
   /**
    * 业绩基准利率Str
    */
   private String rateStr;

   /**
    * 获取 rateStr
    * @return rateStr.
    */
   public String getRateStr() {
       return rateStr;
   }

   /**
    * 设置 rateStr
    * @param rateStr rateStr
    */
   public void setRateStr(String rateStr) {
       this.rateStr = rateStr;
   }
   //add by zhangJin 20191017 end
   //业绩比较基准（%）（浮动管理费） add by cxq 20191227
   private String rateYieldFloat;

   public String getRateYieldFloat() {
	   return rateYieldFloat;
   }

   public void setRateYieldFloat(String rateYieldFloat) {
	   this.rateYieldFloat = rateYieldFloat;
   }
}
