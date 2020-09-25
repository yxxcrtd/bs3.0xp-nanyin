/*
 * 文件名：PayTheVatQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chufengjie
 * 修改时间：2019年02月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.paythevatquery.dto;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.joyintech.framework.annotation.PageId;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;


/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
@PageId("PayTheVatQuery003")
public class PayTheVatQuery001Dto extends BaseEntity implements ProcessEntity {

	private static final long serialVersionUID = 1L;

	/** 产品代码 */
	@NotBlank(message = "交易编号不能为空！")
	/** 交易编号 */
	private String tradeId;
	/**总汇交易*/
	@PrimaryKey(value = Field.PK_1)
	private String totleTradeId;
	/** 缴税/退税开始日 */
	private String vDate;
	/** 缴税/退税结束日 */
	private String mDate;
	/** 缴税/退税日期 */
	private String seitleDate;
	/** 利息收入 */
	private BigDecimal interestIncome;
	/** 红利收入 */
	private BigDecimal dividendIncome;
	/** 价差收入 */
	private BigDecimal difbetIncome;
	/** 增值税 */
	private BigDecimal appreciationTax;
	/** 城建税附加 */
	private BigDecimal constructionTax;
	/** 教育税附加 */
	private BigDecimal educationTax;
	/** 地方教育费附加 */
	private BigDecimal placeeducationTax;
    /**
     * 水利基金附加税
     */
    private BigDecimal waterTrafficTax;
	/** 增值税附加 */
	private BigDecimal additionalTax;
	/** 缴税/退税 */
	private String taxidenType;
	/** 金融产品代码 */
	private String finprodId;
	/**金融产品简称*/
	private String finprodAbbr;
	/** 交易币种 */
	private String ccy;
	/** 投资组合代码 */
	private String portfolioId;
	/** 投资组合代码 */
	private String portfolioName;
	/** 交易状态 */
	private String tradeStatus;
	/**公开市场代码*/
	private String finprodMarketId;
	/**
     * 指令类型
     */
    private String orderType;
    /**税费总额*/
    private BigDecimal taxTotal;
    /**收款账户*/
    private String actName;
    
    private String remark;
    
    /**是否显示水利基金 **/
    private boolean isShowWaterTrafficTax;
    
	/**
	 * 操作类型
	 */
	private String operateType;
	/**
	 * 增值税品种
	 */
	private String taxClass;
	/**
	 * 通道ID
	 */
	private String channelId;
	/**
	 * 通道名称
	 */
	private String channelName;
	
    /**
     * 组合收益类型
     */
    private String profitType;
    
    /**
     * 通道的划款方式
     */
    private String payType;
    
    /**
     * 交易方向(风险接口需要)
     */
    private String ps;
    
    /**
     * List<BussTrdTransferOrderDto>
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    
    /**
     * 获取 ps
     * @return ps.
     */
    public String getPs() {
        return StringUtil.isNotEmpty(this.ps)?this.ps:this.taxidenType;
    }

    /**
     * 设置 ps
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }
    
    
    
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getvDate() {
		return vDate;
	}
	public void setvDate(String vDate) {
		this.vDate = vDate;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getSeitleDate() {
		return seitleDate;
	}
	public void setSeitleDate(String seitleDate) {
		this.seitleDate = seitleDate;
	}
	public BigDecimal getInterestIncome() {
		return interestIncome;
	}
	public void setInterestIncome(BigDecimal interestIncome) {
		this.interestIncome = interestIncome;
	}
	public BigDecimal getDividendIncome() {
		return dividendIncome;
	}
	public void setDividendIncome(BigDecimal dividendIncome) {
		this.dividendIncome = dividendIncome;
	}
	public BigDecimal getDifbetIncome() {
		return difbetIncome;
	}
	public void setDifbetIncome(BigDecimal difbetIncome) {
		this.difbetIncome = difbetIncome;
	}
	public BigDecimal getAppreciationTax() {
		return appreciationTax;
	}
	public void setAppreciationTax(BigDecimal appreciationTax) {
		this.appreciationTax = appreciationTax;
	}
	public BigDecimal getConstructionTax() {
		return constructionTax;
	}
	public void setConstructionTax(BigDecimal constructionTax) {
		this.constructionTax = constructionTax;
	}
	public BigDecimal getEducationTax() {
		return educationTax;
	}
	public void setEducationTax(BigDecimal educationTax) {
		this.educationTax = educationTax;
	}
	public BigDecimal getPlaceeducationTax() {
		return placeeducationTax;
	}
	public void setPlaceeducationTax(BigDecimal placeeducationTax) {
		this.placeeducationTax = placeeducationTax;
	}
	public BigDecimal getAdditionalTax() {
		return additionalTax;
	}
	public void setAdditionalTax(BigDecimal additionalTax) {
		this.additionalTax = additionalTax;
	}
	public String getTaxidenType() {
		return taxidenType;
	}
	public String getTaxidenTypeStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向,  this.getTaxidenType());
	}
	public void setTaxidenType(String taxidenType) {
		this.taxidenType = taxidenType;
	}
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public String getFinprodAbbr() {
		return finprodAbbr;
	}
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}
	public String getCcy() {
		return ccy;
	}
	public String getCcyStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCode.币种,  this.getCcy());
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public String getTradeStatusStr(){
		return SysDataUtil.getNameFromDataDict(BussinessDictCode.有效状态,  this.getTradeStatus());
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public BigDecimal getTaxTotal() {
		return taxTotal;
	}
	public void setTaxTotal(BigDecimal taxTotal) {
		this.taxTotal = taxTotal;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getTotleTradeId() {
		return totleTradeId;
	}
	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
    
    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }
    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
    /**
     * 获取 waterTrafficTax
     * @return waterTrafficTax.
     */
    public BigDecimal getWaterTrafficTax() {
        return waterTrafficTax;
    }
    /**
     * 设置 waterTrafficTax
     * @param waterTrafficTax waterTrafficTax
     */
    public void setWaterTrafficTax(BigDecimal waterTrafficTax) {
        this.waterTrafficTax = waterTrafficTax;
    }
    /**
     * 获取 isShowWaterTrafficTax
     * @return isShowWaterTrafficTax.
     */
    public boolean isShowWaterTrafficTax() {
        return isShowWaterTrafficTax;
    }
    /**
     * 设置 isShowWaterTrafficTax
     * @param isShowWaterTrafficTax isShowWaterTrafficTax
     */
    public void setShowWaterTrafficTax(boolean isShowWaterTrafficTax) {
        this.isShowWaterTrafficTax = isShowWaterTrafficTax;
    }
    
    /**
     * 获取 channelId
     * @return channelId.
     */
    public String getChannelId() {
        return channelId;
    }
    
    /**
     * 设置 channelId
     * @param channelId channelId
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    
    /**
     * 获取 channelName
     * @return channelName.
     */
    public String getChannelName() {
        return channelName;
    }
    
    /**
     * 设置 channelName
     * @param channelName channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    /**
     * 获取 taxClass
     * @return taxClass.
     */
    public String getTaxClass() {
        return taxClass;
    }
    /**
     * 设置 taxClass
     * @param taxClass taxClass
     */
    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public String getTaxClassStr() {
        return  SysDataUtil.getNameFromDataDict(DictCode.增值税品种,  this.getTaxClass());
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
     * 获取 payType
     * @return payType.
     */
    public String getPayType() {
        return payType;
    }
    /**
     * 设置 payType
     * @param payType payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
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
}
