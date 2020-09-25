/*
 * 文件名：BussSettleOrderConditionDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.order.dto;

public class BussSettleOrderConditionDto {

    /**
     * 业务类型，债券交易、产品交易，估值核算专用
     */
    private String busiType;

    /**
     * 金融产品类型（投管分类）
     */
    private String finprodType2;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 证券托管户ID
     */
    private String secManageAcctId;

    /**
     * 交易平台
     */
    private String tradePlat;
    /**
     * 交易方式
     * add by wangpeng 20190724
     */
    private String dealMode;

    /**
     * 拆借类型
     */
    private String bankLendingType;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 是否保本
     */
    private String isGuarantee;
    /**
     * 交易市场，一级市场、二级市场
     */
    private String tradeMarketMode;

    /**
     * 收支方向
     * add by wangpeng 20191111
     */
    private String ieFlag;


    /**
     * 获取 finprodType2
     *
     * @return finprodType2
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 finprodType2
     *
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    /**
     * 获取交易对手
     *
     * @return 交易对手
     */
    public String getCounterId() {
		return counterId;
	}

    /**
     * 获取交易对手
     *
     * @return counterId 交易对手
     */
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}

	/**
     * 获取 ps
     *
     * @return ps
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     *
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 tradeMarket
     *
     * @return tradeMarket
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 tradeMarket
     *
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 secManageAcctId
     *
     * @return secManageAcctId
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 secManageAcctId
     *
     * @param secManageAcctId secManageAcctId
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    /**
     * 获取 tradePlat
     *
     * @return tradePlat
     */
    public String getTradePlat() {
        return tradePlat;
    }

    /**
     * 设置 tradePlat
     *
     * @param tradePlat tradePlat
     */
    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    /**
     * 获取 bankLendingType
     *
     * @return bankLendingType
     */
    public String getBankLendingType() {
        return bankLendingType;
    }

    /**
     * 设置 bankLendingType
     *
     * @param bankLendingType bankLendingType
     */
    public void setBankLendingType(String bankLendingType) {
        this.bankLendingType = bankLendingType;
    }

    /**
     * 获取 profitType
     *
     * @return profitType
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 isGuarantee
     *
     * @return isGuarantee
     */
    public String getIsGuarantee() {
        return isGuarantee;
    }

    /**
     * 获取 tradeMarketMode
     *
     * @return tradeMarketMode
     */
    public String getTradeMarketMode() {
        return tradeMarketMode;
    }

    /**
     * 设置 tradeMarketMode
     *
     * @param tradeMarketMode tradeMarketMode
     */
    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode;
    }

    /**
     * 设置 isGuarantee
     *
     * @param isGuarantee isGuarantee
     */
    public void setIsGuarantee(String isGuarantee) {
        this.isGuarantee = isGuarantee;
    }

    @Override
    public String toString() {
        return "BussSettleOrderConditionDto{" + "finprodType2='" + finprodType2 + '\'' + ", ps='" + ps + '\'' + ", tradeMarket='"
               + tradeMarket + '\'' + ", secManageAcctId='" + secManageAcctId + '\'' + ", tradePlat='" + tradePlat + '\''
               + ", bankLendingType='" + bankLendingType + '\'' + ", profitType='" + profitType + '\'' + ", isGuarantee='"
               + isGuarantee + '\'' + '}';
    }

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

    /**
     * 获取 busiType
     *
     * @return busiType
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 busiType
     *
     * @param busiType busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

	public String getIeFlag() {
		return ieFlag;
	}

	public void setIeFlag(String ieFlag) {
		this.ieFlag = ieFlag;
	}
}
