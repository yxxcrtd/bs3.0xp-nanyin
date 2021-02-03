package com.joyintech.tams.invest.common.dto.period.trdprojduration;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.common.FinAssetInfo;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

/**
 * @program: component-base
 * @description: 计划类资产利率型DTO
 * @author: 强成西
 * @create: 2018-10-10 16:00
 **/
public class TrdProjectDurationDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3097694272636813875L;

    /**
     * 操作类型
     */
    private String operateType;

    private String issuePartyId;
    /**
     * 业务编号
     */
    private String busiCode;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 项目类一级类型
     */
    private String type6;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 通道名称
     */
    private String chlName;

    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 资产名称
     */
    private String finprodMarketName;

    /**
     * 合作资产
     */
    private String chlFinprodId;

    public String getChlFinprodId() {
        return StringUtil.isEmpty(chlName) ? chlFinprodId : chlName;
    }

    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId;
    }

    /**
     * 交易备注
     */
    private String remark;

    /**
     * 撤销理由
     */
    private String revokeReason;

    /**
     * 总剩余本金
     */
    private BigDecimal qty;

    /**
     * 借款利率
     */
    private BigDecimal yield;

    /**
     * 利息调整
     */
    private BigDecimal intAdjustment;

    /**
     * 还本金额
     */
    private BigDecimal returnAmount;

    /**
     * 理论利息
     */
    private BigDecimal intAmtTheory;

    /**
     * 实付利息
     */
    private BigDecimal intAmt;

    /**
     * 到账金额
     */
    private BigDecimal arrivalAmt;

    /**
     * 资金到账日
     */
    private String arrivalDate;

    /**
     * 计息开始日
     */
    private String vDate;

    /**
     * 计息结束日
     */
    private String mDate;

    /**
     * 理论支付日
     */
    private String interestPayDay;

    /**
     * 实收本息总额
     */
    private BigDecimal fpriceAmt;

    /**
     * 理论本息总额
     */
    private BigDecimal theoryAmt;

    /**
     * 税费金额
     */
    private BigDecimal taxAmount;

    /**
     * 有效状态code
     */
    private String statusValid;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 类型code
     */
    private String cashType;

    /**
     * 平安业务类型
     */
    private String paBussType;

    /**
     * 是否需要跳过插入流程表数据
     */
    private Boolean needSkipInsertWfl;

    /**
     * 资产信息
     */
    private FinAssetInfo finAssetInfo;

    /**
     * 利息明细
     */
    private List<FinAssetIntInfo> finAssetIntInfoList;

    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;
    /**
     * 划款指令信息
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
  /**
   * 通道划款方式
  */
    private String collectType;
    
    /**
     * 通道费
     * @return
     */
    private BigDecimal channelFee;
    
    /**
     * 是否是通道
     */
    private boolean channel;

    /**
     * 是否还本不带息
     */
    private boolean intAmtUnpayFlag;
    /**
     * 是否结清
     */
    @NotEmpty(fieldName = "TrdPlanInterestDuration002.isClean", groups = {ValidateGroupEdit.class})
    private String isClean;

    /**
     * 累计还本未付利息
     * @return
     */
    private BigDecimal payUnpaidintAmt;


    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
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
     * 获取 finAssetInfo
     *
     * @return finAssetInfo
     */
    public FinAssetInfo getFinAssetInfo() {
        return finAssetInfo;
    }

    /**
     * 获取 theoryAmt
     *
     * @return theoryAmt
     */
    public BigDecimal getTheoryAmt() {
        return theoryAmt;
    }

    /**
     * 设置 theoryAmt
     *
     * @param theoryAmt theoryAmt
     */
    public void setTheoryAmt(BigDecimal theoryAmt) {
        this.theoryAmt = theoryAmt;
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
     * 设置 finAssetInfo
     *
     * @param finAssetInfo finAssetInfo
     */
    public void setFinAssetInfo(FinAssetInfo finAssetInfo) {
        this.finAssetInfo = finAssetInfo;
    }

    /**
     * 获取 finAssetIntInfoList
     *
     * @return finAssetIntInfoList
     */
    public List<FinAssetIntInfo> getFinAssetIntInfoList() {
        return finAssetIntInfoList;
    }

    /**
     * 设置 finAssetIntInfoList
     *
     * @param finAssetIntInfoList finAssetIntInfoList
     */
    public void setFinAssetIntInfoList(List<FinAssetIntInfo> finAssetIntInfoList) {
        this.finAssetIntInfoList = finAssetIntInfoList;
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
     * 获取 returnAmount
     *
     * @return returnAmount
     */
    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    /**
     * 设置 returnAmount
     *
     * @param returnAmount returnAmount
     */
    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
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
     * 获取 arrivalAmt
     *
     * @return arrivalAmt
     */
    public BigDecimal getArrivalAmt() {
        return arrivalAmt;
    }

    /**
     * 设置 arrivalAmt
     *
     * @param arrivalAmt arrivalAmt
     */
    public void setArrivalAmt(BigDecimal arrivalAmt) {
        this.arrivalAmt = arrivalAmt;
    }

    /**
     * 获取 intAdjustment
     *
     * @return intAdjustment
     */
    public BigDecimal getIntAdjustment() {
        return intAdjustment;
    }

    /**
     * 设置 intAdjustment
     *
     * @param intAdjustment intAdjustment
     */
    public void setIntAdjustment(BigDecimal intAdjustment) {
        this.intAdjustment = intAdjustment;
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
     * 获取 arrivalDate
     *
     * @return arrivalDate
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     * 设置 arrivalDate
     *
     * @param arrivalDate arrivalDate
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * 获取 interestPayDay
     *
     * @return interestPayDay
     */
    public String getInterestPayDay() {
        return interestPayDay;
    }

    /**
     * 设置 interestPayDay
     *
     * @param interestPayDay interestPayDay
     */
    public void setInterestPayDay(String interestPayDay) {
        this.interestPayDay = interestPayDay;
    }

    /**
     * 获取 taxAmount
     *
     * @return taxAmount
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * 设置 taxAmount
     *
     * @param taxAmount taxAmount
     */
    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
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
     * 获取 baseTotalTradeInfoDto
     *
     * @return baseTotalTradeInfoDto
     */
    public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
        return baseTotalTradeInfoDto;
    }

    /**
     * 设置 baseTotalTradeInfoDto
     *
     * @param baseTotalTradeInfoDto baseTotalTradeInfoDto
     */
    public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
        this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
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
     * 获取 type6
     *
     * @return type6
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 type6
     *
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    /**
     * 获取 chlId
     *
     * @return chlId
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 chlId
     *
     * @param chlId chlId
     */
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    /**
     * 获取 chlName
     *
     * @return chlName
     */
    public String getChlName() {
        return chlName;
    }

    /**
     * 设置 chlName
     *
     * @param chlName chlName
     */
    public void setChlName(String chlName) {
        this.chlName = chlName;
    }

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

	public BigDecimal getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(BigDecimal channelFee) {
		this.channelFee = channelFee;
	}

	public boolean isChannel() {
		return channel;
	}

	public void setChannel(boolean channel) {
		this.channel = channel;
	}

    /**
     * 获取 isClean
     *
     * @return isClean
     */
    public String getIsClean() {
        return isClean;
    }

    /**
     * 设置 isClean
     *
     * @param isClean isClean
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean;
    }

    /**
     * 获取 payUnpaidintAmt
     *
     * @return payUnpaidintAmt
     */
    public BigDecimal getPayUnpaidintAmt() {
        return payUnpaidintAmt;
    }

    /**
     * 设置 payUnpaidintAmt
     *
     * @param payUnpaidintAmt payUnpaidintAmt
     */
    public void setPayUnpaidintAmt(BigDecimal payUnpaidintAmt) {
        this.payUnpaidintAmt = payUnpaidintAmt;
    }

    /**
     * 获取 intAmtUnpayFlag
     *
     * @return intAmtUnpayFlag
     */
    public boolean isIntAmtUnpayFlag() {
        return intAmtUnpayFlag;
    }

    /**
     * 设置 intAmtUnpayFlag
     *
     * @param intAmtUnpayFlag intAmtUnpayFlag
     */
    public void setIntAmtUnpayFlag(boolean intAmtUnpayFlag) {
        this.intAmtUnpayFlag = intAmtUnpayFlag;
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
}
