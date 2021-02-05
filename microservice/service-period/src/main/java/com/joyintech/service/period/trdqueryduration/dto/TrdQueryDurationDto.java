/*
 * 文件名：TrdInvestDealQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdqueryduration.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurDbInertDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 交易查询dto
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2021年2月3日
 * @see TrdQueryDurationDto
 * @since 1.0
 */
public class TrdQueryDurationDto extends BaseEntity{
	    /**
		 * <意义，目的和功能，以及可能被用到的地方>
		 */
		private static final long serialVersionUID = -3387164315079376712L;

		/**
		 * 操作类型
		 */
		private String operateType;
		/**
		 * 是否结清
		 */
		private String isClean;

		/**
		 * 金融产品代码
		 */
		private String finprodId;

		/**
		 * 有效状态
		 */
		private String statusValid;

		/**
		 * 交易编号
		 */
		private String busiCode;

		/**
		 * 现金流类型
		 */
		private String cashType;

		/**
		 * 投资目的
		 */
		private String invAim;

		/**
		 * 根据投资目的排序的券面
		 */
		private String amountList;

		/**
		 * 平安业务类型
		 */
		private String paBussType;

		/**
		 * 组合代码
		 */
		private String portfolioId;

		/**
		 * 组合名称
		 */
		private String portfolioName;

		/**
		 * 证券托管户
		 */
		private String secManageAcctId;
		// <!-- 通道新增 qiangchengxi 2018-03-30 start -->

		/**
		 * 划款指令
		 */
		private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

		/**
		 * 是否是通道
		 */
		private boolean channel;

		/**
		 * 证券托管户名称/通道名称
		 */
		private String secAcctName;
		// <!-- 通道新增 qiangchengxi 2018-03-30 end -->

		/**
		 * 市场代码
		 */
		private String finprodMarketId;

		/**
		 * 产品代码
		 */
		private String finprodMarketName;

		/**
		 * 产品起息日
		 */
		private String vDateWhole;

		/**
		 * 产品到期日
		 */
		private String mDateWhole;

		/**
		 * 计息开始日
		 */
		private String vDate;

		/**
		 * 计息结束日
		 */
		private String mDate;

		/**
		 * 是否需要跳过插入流程表数据
		 */
		private Boolean needSkipInsertWfl;

		/**
		 * 资金到账日
		 */
		private String tradeDate;

		/**
		 * 券面总额
		 */
		private BigDecimal qty;

		/**
		 * 全价总额 理论本息总额
		 */
		private BigDecimal fpriceAmt;

		/**
		 * 到账金额
		 */
		private BigDecimal tradeAmt;

		/**
		 * 到账金额2
		 */
		private BigDecimal tradeAmt2;

		/**
		 * 实收利息总额
		 */
		private BigDecimal intAmt;

		/**
		 * 还本金额
		 */
		private BigDecimal prinAmt;

		/**
		 * 理论应计利息
		 */
		private BigDecimal intAmtTheory;

		/**
		 * 理论还本金额
		 */
		private BigDecimal prinAmtTheory;

		/**
		 * 现金流计划表.计息本金 单位本金,
		 */
		private BigDecimal intPrinAmt;

		/**
		 * 单位应计利息,
		 */
		private BigDecimal unitIntAmt;

		/**
		 * 区间票面利率
		 */
		private BigDecimal yield;

		/**
		 * 支付日
		 */
		private String payDate;

		/**
		 * 现金流代码（多条时，以<code>,</code>分割）
		 */
		private String cashId;

		/**
		 * 币种
		 */
		private String ccy;

		/**
		 * 交易备注
		 */
		private String remark;

		/**
		 * 撤销理由
		 */
		private String revokeReason;

		private String issuePartyId;

		/**
		 * 交易场所
		 */
		private String tradeMarket;
		/**
		 * 利息信息明细
		 *
		 */
		private List<FinAssetIntInfo> finAssetIntInfoList;
		/**
		 * 所有相关表，贯穿整个流程
		 */
		private BaseTotalTradeInfoDto baseTotalTradeInfoDto;
		/**
		 * dto
		 */
		private TrdBondDurDbInertDto trdBondDurDbInertDto;


		/***update zhangweiqi 2019-10-14 存续期添加通道费 begin**/
		/**
		 * 通道费
		 */
		private BigDecimal channelFee;

		/**
		 * 通道性质
		 */
		private String payType;

		/**
		 * 通道收取方式
		 */
		private String collectType;

		/**
		 * 实收本息总额
		 */

		private BigDecimal tradeAmtReal;
		/***update zhangweiqi 2019-10-14 存续期添加通道费 end**/

		//add by lishuping 20191030 start
		/**
		 * 资产简称
		 */
		private String finprodAbbr;
		//多级法人改造 add by caiqun 20200615
		/**
		 *
		 * 所属机构代码
		 */
		private String institutionCode;

		/**
		 *
		 * 所属机构名称
		 */
		private String institutionName;
		//多级法人改造 add by caiqun 20200615

		/**
		 * finprodAbbr
		 * @return finprodAbbr
		 */
		public String getFinprodAbbr() {
			return finprodAbbr;
		}

		/**
		 * finprodAbbr
		 * @param finprodAbbr finprodAbbr
		 */
		public void setFinprodAbbr(String finprodAbbr) {
			this.finprodAbbr = finprodAbbr;
		}
		//add by lishuping 20191030 end

		/**
		 * 获取 baseTotalTradeInfoDto
		 * @return baseTotalTradeInfoDto.
		 */
		public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
			return baseTotalTradeInfoDto;
		}

		/**
		 * 设置 baseTotalTradeInfoDto
		 * @param baseTotalTradeInfoDto baseTotalTradeInfoDto
		 */
		public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
			this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
		}

		/**
		 * 获取 finAssetIntInfoList
		 * @return finAssetIntInfoList.
		 */
		public List<FinAssetIntInfo> getFinAssetIntInfoList() {
			return finAssetIntInfoList;
		}

		/**
		 * 设置 finAssetIntInfoList
		 * @param finAssetIntInfoList finAssetIntInfoList
		 */
		public void setFinAssetIntInfoList(List<FinAssetIntInfo> finAssetIntInfoList) {
			this.finAssetIntInfoList = finAssetIntInfoList;
		}

		/**
		 * 获取 issuePartyId
		 *
		 * @return issuePartyId
		 */
		public String getIssuePartyId() {
			return issuePartyId;
		}

		/**
		 * 设置 issuePartyId
		 *
		 * @param issuePartyId issuePartyId
		 */
		public void setIssuePartyId(String issuePartyId) {
			this.issuePartyId = issuePartyId;
		}

		/**
		 * 获取 trdBondDurDbInertDto
		 *
		 * @return trdBondDurDbInertDto
		 */
		public TrdBondDurDbInertDto getTrdBondDurDbInertDto() {
			return trdBondDurDbInertDto;
		}

		/**
		 * 设置 trdBondDurDbInertDto
		 *
		 * @param trdBondDurDbInertDto trdBondDurDbInertDto
		 */
		public void setTrdBondDurDbInertDto(TrdBondDurDbInertDto trdBondDurDbInertDto) {
			this.trdBondDurDbInertDto = trdBondDurDbInertDto;
		}

		/**
		 * 获取 revokeReason
		 *
		 * @return revokeReason
		 */
		public String getRevokeReason() {
			return revokeReason;
		}

		/**
		 * 设置 revokeReason
		 *
		 * @param revokeReason revokeReason
		 */
		public void setRevokeReason(String revokeReason) {
			this.revokeReason = revokeReason;
		}

		/**
		 * 获取 statusValid
		 *
		 * @return statusValid
		 */
		public String getStatusValid() {
			return statusValid;
		}

		/**
		 * 设置 statusValid
		 *
		 * @param statusValid statusValid
		 */
		public void setStatusValid(String statusValid) {
			this.statusValid = statusValid;
		}

		/**
		 * 获取 busiCode
		 *
		 * @return busiCode
		 */
		public String getBusiCode() {
			return busiCode;
		}

		/**
		 * 设置 busiCode
		 *
		 * @param busiCode busiCode
		 */
		public void setBusiCode(String busiCode) {
			this.busiCode = busiCode;
		}

		/**
		 * 获取 cashType
		 *
		 * @return cashType
		 */
		public String getCashType() {
			return cashType;
		}

		/**
		 * 设置 cashType
		 *
		 * @param cashType cashType
		 */
		public void setCashType(String cashType) {
			this.cashType = cashType;
		}

		/**
		 * 获取 portfolioId
		 *
		 * @return portfolioId
		 */
		public String getPortfolioId() {
			return portfolioId;
		}

		/**
		 * 设置 portfolioId
		 *
		 * @param portfolioId portfolioId
		 */
		public void setPortfolioId(String portfolioId) {
			this.portfolioId = portfolioId;
		}

		/**
		 * 获取 portfolioName
		 *
		 * @return portfolioName
		 */
		public String getPortfolioName() {
			return portfolioName;
		}

		/**
		 * 设置 portfolioName
		 *
		 * @param portfolioName portfolioName
		 */
		public void setPortfolioName(String portfolioName) {
			this.portfolioName = portfolioName;
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
		 * 获取 finprodMarketId
		 *
		 * @return finprodMarketId
		 */
		public String getFinprodMarketId() {
			return finprodMarketId;
		}

		/**
		 * 设置 finprodMarketId
		 *
		 * @param finprodMarketId finprodMarketId
		 */
		public void setFinprodMarketId(String finprodMarketId) {
			this.finprodMarketId = finprodMarketId;
		}

		/**
		 * 获取 finprodMarketName
		 *
		 * @return finprodMarketName
		 */
		public String getFinprodMarketName() {
			return finprodMarketName;
		}

		/**
		 * 设置 finprodMarketName
		 *
		 * @param finprodMarketName finprodMarketName
		 */
		public void setFinprodMarketName(String finprodMarketName) {
			this.finprodMarketName = finprodMarketName;
		}

		/**
		 * 获取 vDate
		 *
		 * @return vDate
		 */
		public String getvDate() {
			return vDate;
		}

		/**
		 * 设置 vDate
		 *
		 * @param vDate vDate
		 */
		public void setvDate(String vDate) {
			this.vDate = vDate;
		}

		/**
		 * 获取 mDate
		 *
		 * @return mDate
		 */
		public String getmDate() {
			return mDate;
		}

		/**
		 * 设置 mDate
		 *
		 * @param mDate mDate
		 */
		public void setmDate(String mDate) {
			this.mDate = mDate;
		}

		/**
		 * 获取 tradeDate
		 *
		 * @return tradeDate
		 */
		public String getTradeDate() {
			return tradeDate;
		}

		/**
		 * 设置 tradeDate
		 *
		 * @param tradeDate tradeDate
		 */
		public void setTradeDate(String tradeDate) {
			this.tradeDate = tradeDate;
		}

		/**
		 * 获取 qty
		 *
		 * @return qty
		 */
		public BigDecimal getQty() {
			return qty;
		}

		/**
		 * 设置 qty
		 *
		 * @param qty qty
		 */
		public void setQty(BigDecimal qty) {
			this.qty = qty;
		}

		/**
		 * 获取 fpriceAmt
		 *
		 * @return fpriceAmt
		 */
		public BigDecimal getFpriceAmt() {
			return fpriceAmt;
		}

		/**
		 * 设置 fpriceAmt
		 *
		 * @param fpriceAmt fpriceAmt
		 */
		public void setFpriceAmt(BigDecimal fpriceAmt) {
			this.fpriceAmt = fpriceAmt;
		}

		/**
		 * 获取 tradeAmt
		 *
		 * @return tradeAmt
		 */
		public BigDecimal getTradeAmt() {
			return tradeAmt;
		}

		/**
		 * 设置 tradeAmt
		 *
		 * @param tradeAmt tradeAmt
		 */
		public void setTradeAmt(BigDecimal tradeAmt) {
			this.tradeAmt = tradeAmt;
		}

		/**
		 * 获取 intAmt
		 *
		 * @return intAmt
		 */
		public BigDecimal getIntAmt() {
			return intAmt;
		}

		/**
		 * 设置 intAmt
		 *
		 * @param intAmt intAmt
		 */
		public void setIntAmt(BigDecimal intAmt) {
			this.intAmt = intAmt;
		}

		/**
		 * 获取 prinAmt
		 *
		 * @return prinAmt
		 */
		public BigDecimal getPrinAmt() {
			return prinAmt;
		}

		/**
		 * 设置 prinAmt
		 *
		 * @param prinAmt prinAmt
		 */
		public void setPrinAmt(BigDecimal prinAmt) {
			this.prinAmt = prinAmt;
		}

		/**
		 * 获取 intPrinAmt
		 *
		 * @return intPrinAmt
		 */
		public BigDecimal getIntPrinAmt() {
			return intPrinAmt;
		}

		/**
		 * 设置 intPrinAmt
		 *
		 * @param intPrinAmt intPrinAmt
		 */
		public void setIntPrinAmt(BigDecimal intPrinAmt) {
			this.intPrinAmt = intPrinAmt;
		}

		/**
		 * 获取 remark
		 *
		 * @return remark
		 */
		public String getRemark() {
			return remark;
		}

		/**
		 * 设置 remark
		 *
		 * @param remark remark
		 */
		public void setRemark(String remark) {
			this.remark = remark;
		}

		/**
		 * 获取 vDateWhole
		 *
		 * @return vDateWhole
		 */
		public String getvDateWhole() {
			return vDateWhole;
		}

		/**
		 * 设置 vDateWhole
		 *
		 * @param vDateWhole vDateWhole
		 */
		public void setvDateWhole(String vDateWhole) {
			this.vDateWhole = vDateWhole;
		}

		/**
		 * 获取 mDateWhole
		 *
		 * @return mDateWhole
		 */
		public String getmDateWhole() {
			return mDateWhole;
		}

		/**
		 * 设置 mDateWhole
		 *
		 * @param mDateWhole mDateWhole
		 */
		public void setmDateWhole(String mDateWhole) {
			this.mDateWhole = mDateWhole;
		}

		/**
		 * 获取 payDate
		 *
		 * @return payDate
		 */
		public String getPayDate() {
			return payDate;
		}

		/**
		 * 设置 payDate
		 *
		 * @param payDate payDate
		 */
		public void setPayDate(String payDate) {
			this.payDate = payDate;
		}

		/**
		 * 获取 unitIntAmt
		 *
		 * @return unitIntAmt
		 */
		public BigDecimal getUnitIntAmt() {
			return unitIntAmt;
		}

		/**
		 * 设置 unitIntAmt
		 *
		 * @param unitIntAmt unitIntAmt
		 */
		public void setUnitIntAmt(BigDecimal unitIntAmt) {
			this.unitIntAmt = unitIntAmt;
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
		 * 获取 yield
		 *
		 * @return yield
		 */
		public BigDecimal getYield() {
			return yield;
		}

		/**
		 * 设置 yield
		 *
		 * @param yield yield
		 */
		public void setYield(BigDecimal yield) {
			this.yield = yield;
		}

		/**
		 * 获取 prinAmtTheory
		 *
		 * @return prinAmtTheory
		 */
		public BigDecimal getPrinAmtTheory() {
			return prinAmtTheory;
		}

		/**
		 * 设置 prinAmtTheory
		 *
		 * @param prinAmtTheory prinAmtTheory
		 */
		public void setPrinAmtTheory(BigDecimal prinAmtTheory) {
			this.prinAmtTheory = prinAmtTheory;
		}

		/**
		 * 获取 operateType
		 *
		 * @return operateType
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
		 * 获取 intAmtTheory
		 *
		 * @return intAmtTheory
		 */
		public BigDecimal getIntAmtTheory() {
			return intAmtTheory;
		}

		/**
		 * 设置 intAmtTheory
		 *
		 * @param intAmtTheory intAmtTheory
		 */
		public void setIntAmtTheory(BigDecimal intAmtTheory) {
			this.intAmtTheory = intAmtTheory;
		}

		/**
		 * 获取 cashId
		 *
		 * @return cashId
		 */
		public String getCashId() {
			return cashId;
		}

		/**
		 * 设置 cashId
		 *
		 * @param cashId cashId
		 */
		public void setCashId(String cashId) {
			this.cashId = cashId;
		}

		/**
		 * 获取 finprodId
		 *
		 * @return finprodId
		 */
		public String getFinprodId() {
			return finprodId;
		}

		/**
		 * 设置 finprodId
		 *
		 * @param finprodId finprodId
		 */
		public void setFinprodId(String finprodId) {
			this.finprodId = finprodId;
		}

		/**
		 * 获取 ccy
		 *
		 * @return ccy
		 */
		public String getCcy() {
			return ccy;
		}

		/**
		 * 设置 ccy
		 *
		 * @param ccy ccy
		 */
		public void setCcy(String ccy) {
			this.ccy = ccy;
		}

		/**
		 * 获取 paBussType
		 *
		 * @return paBussType
		 */
		public String getPaBussType() {
			return paBussType;
		}

		/**
		 * 设置 paBussType
		 *
		 * @param paBussType paBussType
		 */
		public void setPaBussType(String paBussType) {
			this.paBussType = paBussType;
		}

		/**
		 * 获取 needSkipInsertWfl
		 *
		 * @return needSkipInsertWfl
		 */
		public Boolean getNeedSkipInsertWfl() {
			return needSkipInsertWfl;
		}

		/**
		 * 设置 needSkipInsertWfl
		 *
		 * @param needSkipInsertWfl needSkipInsertWfl
		 */
		public void setNeedSkipInsertWfl(Boolean needSkipInsertWfl) {
			this.needSkipInsertWfl = needSkipInsertWfl;
		}

		/**
		 * 获取 invAim
		 *
		 * @return invAim
		 */
		public String getInvAim() {
			if (invAim == null || "".equals(invAim))
				return "";
			return invAim;
		}

		/**
		 * 设置 invAim
		 *
		 * @param invAim invAim
		 */
		public void setInvAim(String invAim) {
			this.invAim = invAim;
		}

		/**
		 * 获取 amountList
		 *
		 * @return amountList
		 */
		public String getAmountList() {
			if (amountList == null || "".equals(amountList))
				return "";
			return amountList;
		}

		/**
		 * 设置 amountList
		 *
		 * @param amountList amountList
		 */
		public void setAmountList(String amountList) {
			this.amountList = amountList;
		}

		/**
		 * 获取 secAcctName
		 *
		 * @return secAcctName
		 */
		public String getSecAcctName() {
			return secAcctName;
		}

		/**
		 * 设置 secAcctName
		 *
		 * @param secAcctName secAcctName
		 */
		public void setSecAcctName(String secAcctName) {
			this.secAcctName = secAcctName;
		}

		/**
		 * 获取 channel
		 *
		 * @return channel
		 */
		public boolean isChannel() {
			return channel;
		}

		/**
		 * 设置 channel
		 *
		 * @param channel channel
		 */
		public void setChannel(boolean channel) {
			this.channel = channel;
		}

		/**
		 * 获取 bussTrdTransferOrder
		 *
		 * @return bussTrdTransferOrder
		 */
		public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
			return bussTrdTransferOrder;
		}

		/**
		 * 设置 bussTrdTransferOrder
		 *
		 * @param bussTrdTransferOrder bussTrdTransferOrder
		 */
		public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
			this.bussTrdTransferOrder = bussTrdTransferOrder;
		}

		public BigDecimal getChannelFee() {
			return channelFee;
		}

		public void setChannelFee(BigDecimal channelFee) {
			this.channelFee = channelFee;
		}

		public String getPayType() {
			return payType;
		}

		public void setPayType(String payType) {
			this.payType = payType;
		}

		public String getCollectType() {
			return collectType;
		}

		public void setCollectType(String collectType) {
			this.collectType = collectType;
		}

		public BigDecimal getTradeAmtReal() {
			return tradeAmtReal;
		}

		public void setTradeAmtReal(BigDecimal tradeAmtReal) {
			this.tradeAmtReal = tradeAmtReal;
		}

		/**
		 * 获取 是否结清
		 *
		 * @return isClean 是否结清
		 */
		public String getIsClean() {
			return this.isClean;
		}

		/**
		 * 设置 是否结清
		 *
		 * @param isClean 是否结清
		 */
		public void setIsClean(String isClean) {
			this.isClean = isClean;
		}

		/**
		 * 获取 产品起息日
		 *
		 * @return vDateWhole 产品起息日
		 */
		public String getVDateWhole() {
			return this.vDateWhole;
		}

		/**
		 * 设置 产品起息日
		 *
		 * @param vDateWhole 产品起息日
		 */
		public void setVDateWhole(String vDateWhole) {
			this.vDateWhole = vDateWhole;
		}

		/**
		 * 获取 产品到期日
		 *
		 * @return mDateWhole 产品到期日
		 */
		public String getMDateWhole() {
			return this.mDateWhole;
		}

		/**
		 * 设置 产品到期日
		 *
		 * @param mDateWhole 产品到期日
		 */
		public void setMDateWhole(String mDateWhole) {
			this.mDateWhole = mDateWhole;
		}

		/**
		 * 获取 计息开始日
		 *
		 * @return vDate 计息开始日
		 */
		public String getVDate() {
			return this.vDate;
		}

		/**
		 * 设置 计息开始日
		 *
		 * @param vDate 计息开始日
		 */
		public void setVDate(String vDate) {
			this.vDate = vDate;
		}

		/**
		 * 获取 计息结束日
		 *
		 * @return mDate 计息结束日
		 */
		public String getMDate() {
			return this.mDate;
		}

		/**
		 * 设置 计息结束日
		 *
		 * @param mDate 计息结束日
		 */
		public void setMDate(String mDate) {
			this.mDate = mDate;
		}

		public String getInstitutionCode() {
			return institutionCode;
		}

		public void setInstitutionCode(String institutionCode) {
			this.institutionCode = institutionCode;
		}

		public String getInstitutionName() {
			return institutionName;
		}

		public void setInstitutionName(String institutionName) {
			this.institutionName = institutionName;
		}

		/**
		 * 平安资产类型
		 */
		private String finprodType2;

		/**
		 * 到账金额
		 */
		private BigDecimal shareAmt;

		public String getFinprodType2() {
			return finprodType2;
		}

		public void setFinprodType2(String finprodType2) {
			this.finprodType2 = finprodType2;
		}

		public BigDecimal getShareAmt() {
			return shareAmt;
		}

		public void setShareAmt(BigDecimal shareAmt) {
			this.shareAmt = shareAmt;
		}

		public BigDecimal getTradeAmt2() {
			return tradeAmt2;
		}

		public void setTradeAmt2(BigDecimal tradeAmt2) {
			this.tradeAmt2 = tradeAmt2;
		}
}
