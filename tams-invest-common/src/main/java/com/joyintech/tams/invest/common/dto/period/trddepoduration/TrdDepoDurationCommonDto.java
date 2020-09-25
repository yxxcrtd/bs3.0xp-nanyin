/*
 * 文件名：TrdDepoDurationCommonDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.period.trddepoduration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

/**
 * 存款类资产存续期共通dto
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see TrdDepoDurationCommonDto
 * @since 1.0
 */
public class TrdDepoDurationCommonDto extends TrdFinProductDealTotal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7872212923673516686L;

    /**
     * mode
     */
    private String mode;
    /**
     * 是否结清
     */
    private String isClean;
    /**
     * 实收本息总额
     */
    @NotEmpty(fieldName = "TrdDepoDuration002.tradeAmtArrive", groups = {ValidateGroupEdit.class})
    private BigDecimal tradeAmt;

//    /**
//     * 支付日
//     */
//    private String payDate;

    /**
     * 市场代码
     */
    // @PrimaryKey(PrimaryKey.Field.PK_2)
    @NotEmpty(fieldName = "TrdDepoDuration001.finprodMarketId", groups = {ValidateGroupEdit.class})
    private String finprodMarketId;

    /**
     * 调整后的计息截止日
     */
    private Date adjustDate;

    /**
     * 发行机构代码
     */
    private String repIssuePartyId;

    /**
     * 产品名称
     */
    private String finprodMarketName;

    /**
     * 计息开始日
     */
    private Date vdateProd;

    /**
     * 计息结束日
     */
    @NotEmpty(fieldName = "TrdDepoDuration001.mdateProd", groups = {ValidateGroupEdit.class})
    private Date mdateProd;

    /**
     * 有效状态
     */
    private String statusValid;

    /**
     * 交易编号
     */
    private String busiCode;

    /**
     * 编辑类型
     */
    private String opType;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 原交易编号
     */
    private String pTradeId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 借款利率%
     */
    private BigDecimal loadRate;

    /**
     * 资产推荐费率%
     */
    private BigDecimal yield;

    /**
     * 剩余本金
     */
    private BigDecimal remainPrinAmt;

    /**
     * 理论应计利息
     */
    private BigDecimal theoryInt;

    /**
     * 委托行
     */
    private String entruster;

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 提前支取利率
     */
    private BigDecimal earlyWithdrawalRate;

    /**
     * 提前支取金额
     */
    private BigDecimal earlyWithdrawalAmt;
    /**
     * 是否需要跳过插入流程表数据
     */
    private Boolean needSkipInsertWfl;
    /**
     * 利息信息明细
     * 
     */
    private List<FinAssetIntInfo> finAssetIntInfoList;
    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;
    // 通道存续期 qiangchengxi start
    /**
     * 通道
     */
    private String chlId;

    /**
     * 通道名称
     */
    private String channelName;


    /**
     * 理论还本金额
     */
    private BigDecimal prinAmtTheory;
    /**
     * 子组合代码
     */
    private String actPortfolioId;
    /***add by renping 2019-10-14 存续期添加通道费 begin**/
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
     * 是否是通道
     */
    private boolean channel;
    
    /**
     * 实收本息总额
     */
    @NotEmpty(fieldName = "TrdDepoDuration001.tradeAmt", groups = {ValidateGroupEdit.class})
    private BigDecimal tradeAmtReal;

    /**
     * 支付月，可多选，逗号分隔
     */
    private String payMonth;

    /**
     * 日期值，可多选，逗号分隔
     */
    private String dayValue;

    /**
     * 支付频率
     */
    private String payCycle;

    /**
     * 结息方式,适用于活期存放
     */
    private String intSettType;

    /**
     * 获取 channelFee
     * 
     * @return channelFee.
     */
    public BigDecimal getChannelFee() {
        return channelFee;
    }

    /**
     * 设置 channelFee
     * 
     * @param channelFee channelFee
     */
    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }

    /**
     * 获取 payType
     * 
     * @return payType.
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 payType
     * 
     * @param payType payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取 collectType
     * 
     * @return collectType.
     */
    public String getCollectType() {
        return collectType;
    }

    /**
     * 设置 collectType
     * 
     * @param collectType collectType
     */
    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    /**
     * 获取 tradeAmtReal
     * 
     * @return tradeAmtReal.
     */
    public BigDecimal getTradeAmtReal() {
        return tradeAmtReal;
    }

    /**
     * 设置 tradeAmtReal
     * 
     * @param tradeAmtReal tradeAmtReal
     */
    public void setTradeAmtReal(BigDecimal tradeAmtReal) {
        this.tradeAmtReal = tradeAmtReal;
    }

    /**
     * 获取 channel
     * 
     * @return channel.
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
    /***add by renping  2019-10-14 存续期添加通道费 end**/
    /**
     * 划款指令信息
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 prinAmtTheory
     * @return prinAmtTheory.
     */
    public BigDecimal getPrinAmtTheory() {
        return prinAmtTheory;
    }

    /**
     * 设置 prinAmtTheory
     * @param prinAmtTheory prinAmtTheory
     */
    public void setPrinAmtTheory(BigDecimal prinAmtTheory) {
        this.prinAmtTheory = prinAmtTheory;
    }

    /**
     * 获取 channelName
     *
     * @return channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置 channelName
     *
     * @param channelName channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 获取 chlId
     *
     * @return chlId
     */
    @Override
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 chlId
     *
     * @param chlId chlId
     */
    @Override
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }
    // 通道存续期 qiangchengxi end

    /**
     * 撤销原因
     */
    private String revokeReason;

    /**
     * 获取 pageId
     *
     * @return pageId.
     */
    private String pageId;
    
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
	 * 获取 revokeReason
	 * @return revokeReason.
	 */
	public String getRevokeReason() {
		return revokeReason;
	}

	/**
	 * 设置 revokeReason
	 * @param revokeReason revokeReason
	 */
	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	/**
	 * 获取 needSkipInsertWfl
	 * @return needSkipInsertWfl.
	 */
	public Boolean getNeedSkipInsertWfl() {
		return needSkipInsertWfl;
	}

	/**
	 * 设置 needSkipInsertWfl
	 * @param needSkipInsertWfl needSkipInsertWfl
	 */
	public void setNeedSkipInsertWfl(Boolean needSkipInsertWfl) {
		this.needSkipInsertWfl = needSkipInsertWfl;
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
     * 获取 elementId
     *
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     *
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 statusValid
     *
     * @return statusValid.
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
     * @return busiCode.
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
     * 获取 opType
     *
     * @return opType.
     */
    public String getOpType() {
        return opType;
    }

    /**
     * 设置 opType
     *
     * @param opType opType
     */
    public void setOpType(String opType) {
        this.opType = opType;
    }

    /**
     * 获取 pTradeId
     *
     * @return pTradeId.
     */
    @Override
    public String getpTradeId() {
        return pTradeId;
    }

    /**
     * 设置 pTradeId
     *
     * @param pTradeId pTradeId
     */
    @Override
    public void setpTradeId(String pTradeId) {
        this.pTradeId = pTradeId;
    }

    /**
     * 获取 portfolioName
     *
     * @return portfolioName.
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
     * 获取 assetType
     *
     * @return assetType.
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 assetType
     *
     * @param assetType assetType
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    /**
     * 获取 cashType
     *
     * @return cashType.
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
     * 获取 basis
     *
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     *
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 loadRate
     *
     * @return loadRate.
     */
    public BigDecimal getLoadRate() {
        return loadRate;
    }

    /**
     * 设置 loadRate
     *
     * @param loadRate loadRate
     */
    public void setLoadRate(BigDecimal loadRate) {
        this.loadRate = loadRate;
    }

    /**
     * 获取 yield
     *
     * @return yield.
     */
    @Override
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     *
     * @param yield yield
     */
    @Override
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 feeAmt
     *
     * @return feeAmt.
     */
    @Override
    public BigDecimal getFeeAmt() {
        return super.getFeeAmt();
    }

    /**
     * 获取 remainPrinAmt
     *
     * @return remainPrinAmt.
     */
    public BigDecimal getRemainPrinAmt() {
        return remainPrinAmt;
    }

    /**
     * 设置 remainPrinAmt
     *
     * @param remainPrinAmt remainPrinAmt
     */
    public void setRemainPrinAmt(BigDecimal remainPrinAmt) {
        this.remainPrinAmt = remainPrinAmt;
    }

    /**
     * 获取 theoryInt
     *
     * @return theoryInt.
     */
    public BigDecimal getTheoryInt() {
        return theoryInt;
    }

    /**
     * 设置 theoryInt
     *
     * @param theoryInt theoryInt
     */
    public void setTheoryInt(BigDecimal theoryInt) {
        this.theoryInt = theoryInt;
    }

    /**
     * 获取 entruster
     *
     * @return entruster.
     */
    public String getEntruster() {
        return entruster;
    }

    /**
     * 设置 entruster
     *
     * @param entruster entruster
     */
    public void setEntruster(String entruster) {
        this.entruster = entruster;
    }

    public String getPingAnCodeSin() {
        if (StringUtil.isEmpty(this.cashType)) {
            return DictCodeListExt1.平安业务类型_存款提前支取.getValue();
        }
        else if (StringUtil.contains(this.cashType, DictCodeList.现金流类型_到期还本.getValue())) {
            return DictCodeListExt1.平安业务类型_存款到期.getValue();
        }
        else if (StringUtil.equals(this.cashType, DictCodeList.交易方向_到期.getValue())) {
            return DictCodeListExt1.平安业务类型_存款到期.getValue();
        }
        else if (StringUtil.equals(this.cashType, DictCodeList.交易方向_提前支取.getValue())) {
            return DictCodeListExt1.平安业务类型_存款提前支取.getValue();
        }
        else {
            return DictCodeListExt1.平安业务类型_存款中间付息.getValue();
        }
    }

    @Override
    public String getPs() {
        if (StringUtil.isNotEmpty(super.getPs())) {
            return super.getPs();
        }
        else {
            if (StringUtil.equals(getPingAnCodeSin(),
                DictCodeListExt1.平安业务类型_存款中间付息.getValue())) {
                if(StringUtil.equals(getAssetType(), DictCodeListExt3.存款类资产类型_活期存放.getValue())){
                    return DictCodeList.交易方向_中间付息.getValue();
                }
                return DictCodeList.交易方向_收息.getValue();
            }
            else if (StringUtil.equals(getPingAnCodeSin(),
                DictCodeListExt1.平安业务类型_存款提前支取.getValue())) {
                return DictCodeList.交易方向_提前支取.getValue();
            }
            else {
                return DictCodeList.交易方向_到期.getValue();
            }
        }
    }

    /**
     * 获取 vdateProd
     *
     * @return vdateProd.
     */
    public Date getVdateProd() {
        return vdateProd;
    }

    /**
     * 设置 vdateProd
     *
     * @param vdateProd vdateProd
     */
    public void setVdateProd(Date vdateProd) {
        this.vdateProd = vdateProd;
    }

    /**
     * 获取 mdateProd
     *
     * @return mdateProd.
     */
    public Date getMdateProd() {
        return mdateProd;
    }

    /**
     * 设置 mdateProd
     *
     * @param mdateProd mdateProd
     */
    public void setMdateProd(Date mdateProd) {
        this.mdateProd = mdateProd;
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId.
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
     * @return finprodMarketName.
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
     * 获取 intAmt
     *
     * @return intAmt.
     */
    @Override
    @NotEmpty(fieldName = "TrdDepoDuration001.intAmtReal", groups = {ValidateGroupEdit.class})
    public BigDecimal getIntAmt() {
        return super.getIntAmt();
    }

    /**
     * 获取 earlyWithdrawalRate
     *
     * @return earlyWithdrawalRate.
     */
    public BigDecimal getEarlyWithdrawalRate() {
        return earlyWithdrawalRate;
    }

    /**
     * 设置 earlyWithdrawalRate
     *
     * @param earlyWithdrawalRate earlyWithdrawalRate
     */
    public void setEarlyWithdrawalRate(BigDecimal earlyWithdrawalRate) {
        this.earlyWithdrawalRate = earlyWithdrawalRate;
    }

    /**
     * 获取 earlyWithdrawalAmt
     *
     * @return earlyWithdrawalAmt.
     */
    public BigDecimal getEarlyWithdrawalAmt() {
        return earlyWithdrawalAmt;
    }

    /**
     * 设置 earlyWithdrawalAmt
     *
     * @param earlyWithdrawalAmt earlyWithdrawalAmt
     */
    public void setEarlyWithdrawalAmt(BigDecimal earlyWithdrawalAmt) {
        this.earlyWithdrawalAmt = earlyWithdrawalAmt;
    }

//    /**
//     * 获取 payDate
//     *
//     * @return payDate.
//     */
//    public String getPayDate() {
//        return payDate;
//    }
//
//    /**
//     * 设置 payDate
//     *
//     * @param payDate payDate
//     */
//    public void setPayDate(String payDate) {
//        this.payDate = payDate;
//    }

    /**
     * 获取 tradeAmt
     *
     * @return tradeAmt.
     */
    @Override
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 tradeAmt
     *
     * @param tradeAmt tradeAmt
     */
    @Override
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 tradeDate
     *
     * @return tradeDate.
     */
    @Override
    @NotEmpty(fieldName = "TrdDepoDuration001.fundAtDate", groups = {ValidateGroupEdit.class})
    public Date getTradeDate() {
        return super.getTradeDate();
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
     * 获取 adjustDate
     *
     * @return adjustDate.
     */
    public Date getAdjustDate() {
        return adjustDate;
    }

    /**
     * 设置 adjustDate
     *
     * @param adjustDate adjustDate
     */
    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    /**
     * 获取 repIssuePartyId
     *
     * @return repIssuePartyId.
     */
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    /**
     * 设置 repIssuePartyId
     *
     * @param repIssuePartyId repIssuePartyId
     */
    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId;
    }

    /**
     * 获取 mode
     *
     * @return mode.
     */
    public String getMode() {
        return mode;
    }

    /**
     * 设置 mode
     *
     * @param mode mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getActPortfolioId() {
        return actPortfolioId;
    }

    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public String getDayValue() {
        return dayValue;
    }

    public void setDayValue(String dayValue) {
        this.dayValue = dayValue;
    }

    public String getPayCycle() {
        return payCycle;
    }

    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

    public String getIntSettType() {
        return intSettType;
    }

    public void setIntSettType(String intSettType) {
        this.intSettType = intSettType;
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
     * 获取 原交易编号
     *
     * @return pTradeId 原交易编号
     */
    public String getPTradeId() {
        return this.pTradeId;
    }

    /**
     * 设置 原交易编号
     *
     * @param pTradeId 原交易编号
     */
    public void setPTradeId(String pTradeId) {
        this.pTradeId = pTradeId;
    }
}
