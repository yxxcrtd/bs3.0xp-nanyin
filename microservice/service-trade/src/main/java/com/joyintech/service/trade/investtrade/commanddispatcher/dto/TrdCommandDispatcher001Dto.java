/*
 * 文件名：TrdCommandDispatcher001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.investtrade.commanddispatcher.dto;


import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.EtrEntrust;

/**
 * 指令分发dto
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年9月12日
 * @see TrdCommandDispatcher001Dto
 * @since 1.0
 */
public class TrdCommandDispatcher001Dto extends EtrEntrust{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 组合 组合名称
	 */
	private String portfolioName;
	/**
	 * 标的类金融产品  市场代码
	 */
	private String finprodMarketId;
	/**
	 * 委托指令分发 指定交易员
	 */
	private String trader;
 	/**
 	 * 委托操作类型
 	 */
 	private String delegateOperationType;
 	/**
 	 * 交易员列表
 	 */
 	private String dealers;		
 	
 	/**
 	 * 交易员资产类型数量
 	 */
 	private int count;
 	/**
     * 用户名
     */
    private String userName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 角色岗位
     */
    private String rolePos;
    /**
     * 完成额度
     */
 	private BigDecimal finishAmt;
 	/**
 	 * 金融产品简称
 	 */
 	private String finprodAbbr;
 	/**
 	 * 回购/拆借方向
 	 */
 	private String busiPs;
	/**
	 * 投标标位
	 */
	private String bidPrice;
	/**
	 * 投标券面
	 */
	private BigDecimal bidPrinAmt;
	/**
	 * 投标日期
	 */
	private Date bidDate;
	/**
	 * 投标日期
	 */
	private String bussBidDate;
	
	/**
	 * 交易方向（画面端用）
	 */
	private String otherPs;

	private String distributeRule;//分发规则
	private String distributeParameter;//分发参数

	public String getDistributeRule() {
		return distributeRule;
	}

	public void setDistributeRule(String distributeRule) {
		this.distributeRule = distributeRule;
	}

	public String getDistributeParameter() {
		return distributeParameter;
	}

	public void setDistributeParameter(String distributeParameter) {
		this.distributeParameter = distributeParameter;
	}

	/**
 	 * 获取交易员资产类型数量
 	 * @return
 	 */
 	public int getCount() {
		return count;
	}
 	/**
 	 * 设置交易员资产类型数量
 	 * @param count
 	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
 	 * 委托关闭方式（数据字典）								
 	 */
 	private String delegateCloseWay;
 	/**
 	 * 获取交易员列表
 	 * @return
 	 */
	public String getDealers() {
		return dealers;
	}
	/**
	 * 设置交易员列表
	 * @param dealers
	 */
	public void setDealers(String dealers) {
		this.dealers = dealers;
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
     * 获取 trader
     * @return trader.
     */
	public String getTrader() {
		return trader;
	}
	/**
     * 设置 trader
     * @param trader trader
     */
	public void setTrader(String trader) {
		this.trader = trader;
	}

	/**
     * 获取 userName
     * @return userName.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置 trader
     * @param trader trader
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取 realName
     * @return realName.
     */
    public String getRealName() {
        return realName;
    }
    /**
     * 设置 realName
     * @param realName realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }
    /**
     * 获取 trader
     * @return trader.
     */
    public String getRolePos() {
        return rolePos;
    }
    /**
     * 设置 rolePos
     * @param rolePos rolePos
     */
    public void setRolePos(String rolePos) {
        this.rolePos = rolePos;
    }
    /**
     * 获取 delegateOperationType
     * @return delegateOperationType.
     */
    public String getDelegateOperationType() {
        return delegateOperationType;
    }
    /**
     * 设置 delegateOperationType
     * @param delegateOperationType delegateOperationType
     */
    public void setDelegateOperationType(String delegateOperationType) {
        this.delegateOperationType = delegateOperationType;
    }
    /**
     * 获取 getDelegateCloseWay
     * @return getDelegateCloseWay.
     */
    public String getDelegateCloseWay() {
        return delegateCloseWay;
    }
    /**
     * 设置 delegateCloseWay
     * @param delegateCloseWay delegateCloseWay
     */
    public void setDelegateCloseWay(String delegateCloseWay) {
        this.delegateCloseWay = delegateCloseWay;
    }
    /**
     * 获取 finishAmt
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }
    /**
     * 设置 finishAmt
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }
    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    /**
     * 获取 busiPs
     * @return busiPs.
     */
    public String getBusiPs() {
        return busiPs;
    }
    /**
     * 设置 busiPs
     * @param busiPs busiPs
     */
    public void setBusiPs(String busiPs) {
        this.busiPs = busiPs;
    }

	public String getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

	public BigDecimal getBidPrinAmt() {
		return bidPrinAmt;
	}

	public void setBidPrinAmt(BigDecimal bidPrinAmt) {
		this.bidPrinAmt = bidPrinAmt;
	}

	public Date getBidDate() {
		return bidDate;
	}

	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}

	public String getBussBidDate() {
		return bussBidDate;
	}

	public void setBussBidDate(String bussBidDate) {
		this.bussBidDate = bussBidDate;
	}
	public String getOtherPs() {
		return otherPs;
	}
	public void setOtherPs(String otherPs) {
		this.otherPs = otherPs;
	}
}
