/*
 * 文件名：FinProductBuild001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productelement.build.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.FinProduct;

public class FinProductBuild001Dto extends FinProduct {

    /**
     * 
     */
    private static final long serialVersionUID = -2665841947732793407L;

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

    
    /**
     * 新发行状态爱
     */
    private String newIssueStatus;
   
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
     * 产品设计系列编号
     */
    private String prdDesignId;
    /**
     * 产品设计系列名称
     */
    private String prdDesignName;
    
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

    /**
     * 募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;
    
    /**
     * 追加金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal  lowestAmt;
    
    /**
     * 目标客户一级分类
     */
    private String targetCustomer1;
    /**
     * 目标客户二级分类
     */
    private String targetCustomer2;
    /********* add by zhangweiqi 主产品需求添加 end**************/
    
    /** add by 发送接口状态   lizhi 2019-6-19 start**/
    // 发送状态
    private String sendStatus ;
    // 发送状态备注
    private String sendRemark ;
    /*浮动管理费利率 add by lpl 2019-12-28 begin*/
    private String floatFeeRate;
    /*浮动管理费利率 add by lpl 2019-12-28 end*/
    
    public String getFloatFeeRate() {
		return floatFeeRate;
	}

	public void setFloatFeeRate(String floatFeeRate) {
		this.floatFeeRate = floatFeeRate;
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
    /**
     * 到期金额
     */
    private String mCusRevAmt;
    
    private String investmentCycle;
    
    private String termType;
    
    public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}

	public String getmCusRevAmt() {
		return mCusRevAmt;
	}

	public void setmCusRevAmt(String mCusRevAmt) {
		this.mCusRevAmt = mCusRevAmt;
	}

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

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public String getRepInvestorType() {
        return repInvestorType;
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

    /**
     * @return the issueStatus
     */
    public String getIssueStatus() {
        return issueStatus;
    }

    /**
     * @param issueStatus the issueStatus to set
     */
    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    
    public String getIssueStatusRemark() {
        return issueStatusRemark;
    }

    
    public void setIssueStatusRemark(String issueStatusRemark) {
        this.issueStatusRemark = issueStatusRemark;
    }

    
    public String getNewIssueStatus() {
        return newIssueStatus;
    }

    
    public void setNewIssueStatus(String newIssueStatus) {
        this.newIssueStatus = newIssueStatus;
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

	public BigDecimal getIssueAmt() {
		return issueAmt;
	}

	public void setIssueAmt(BigDecimal issueAmt) {
		this.issueAmt = issueAmt;
	}

	public BigDecimal getLowestAmt() {
		return lowestAmt;
	}

	public void setLowestAmt(BigDecimal lowestAmt) {
		this.lowestAmt = lowestAmt;
	}

	public String getTargetCustomer1() {
		return targetCustomer1;
	}

	public void setTargetCustomer1(String targetCustomer1) {
		this.targetCustomer1 = targetCustomer1;
	}

	public String getTargetCustomer2() {
		return targetCustomer2;
	}

	public void setTargetCustomer2(String targetCustomer2) {
		this.targetCustomer2 = targetCustomer2;
	}
//	期次开始日
    private Date vdateStr;
    
    //期次结束日
    private Date vdateEnd;




	public Date getVdateStr() {
		return vdateStr;
	}

	public void setVdateStr(Date vdateStr) {
		this.vdateStr = vdateStr;
	}

	public Date getVdateEnd() {
		return vdateEnd;
	}

	public void setVdateEnd(Date vdateEnd) {
		this.vdateEnd = vdateEnd;
	}

	  /**
     * 是否周期型
     */
    private String isCycle;


    public String getIsCycle() {
		return isCycle;
	}

	public void setIsCycle(String isCycle) {
		this.isCycle = isCycle;
	}
	public Date getOpenVdate() {
		return openVdate;
	}
	

	public void setOpenVdate(Date openVdate) {
		this.openVdate = openVdate;
	}

	public Date getOpenEdate() {
		return openEdate;
	}

	public void setOpenEdate(Date openEdate) {
		this.openEdate = openEdate;
	}

	private Date openVdate;
	
	private Date openEdate;
	

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

	public String getPrdDesignId() {
		return prdDesignId;
	}

	public void setPrdDesignId(String prdDesignId) {
		this.prdDesignId = prdDesignId;
	}

	public String getPrdDesignName() {
		return prdDesignName;
	}

	public void setPrdDesignName(String prdDesignName) {
		this.prdDesignName = prdDesignName;
	}
    
    
}
