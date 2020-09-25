package com.joyintech.tams.invest.trade.buyoutrepo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Pattern;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.trade.dto.BussTrdAvailableCashQuery;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.buyoutrepo.validator.TrdOutRightRepoTrade002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 买断式回购交易新建
 * @author zhangliang
 *
 */
@PageId(value = "TrdOutRightRepoTrade002")
@ValidLogic(targetClass = TrdOutRightRepoTrade002DtoValidator.class)
public class TrdOutRightRepoTrade002Dto extends BaseEntity implements ProcessEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1806018338501643163L;
	
	/**
	 * 元素Id
	 */
	private String elementId;
	
	/**
	 * 操作类型
	 */
	private String operateType;
	
	/**
	 * 是否保本
	 */
	private String isGuarantee;
	
	/**
	 * 撤销原因
	 */
	private String revokeReason;
	
	/**
	 * 交易编号
	 */
	@PrimaryKey(PrimaryKey.Field.PK_1)
	private String tradeId;

	/**
	 * 委托代码
	 */
	private String entrustId;

	/**
	 * 组合代码
	 */
//	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.pltId")
	private String portfolioId;

	/**
	 * 实体组合
	 */
	private String actPortfolioId;

	/**
	 * 成交编号
	 */
	@Pattern(fieldName = "TrdOutRightRepoTrade002.bussId", regx = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+)[a-zA-Z0-9]+$", message = "FW_E_0002")
	private String dealBussId;

	/**
	 * 交易日期
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.dealDate")
	private Date tradeDate;

	/**
	 * 交易市场
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.tradePlace")
	private String tradeMarket;

	/**
	 * 交易方向
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.ps")
	private String ps;

	/**
	 * 标的资产代码
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.finprodMarketId")
	private String finprodId;

	/**
	 * 清算速度
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.settleSpeed")
	private String settleSpeed;

	/**
	 * 期限品种
	 */
	private String termType;
	/**
	 * 回购期限
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.repoterm")
	private Long repoterm;
	/**
	 * 计息基础
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.basis")
	private String basis;
	/**
	 * 起息日
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.vdate")
	private Date vdate;
	/**
	 * 到期日
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.mdate")
	private Date mdate;
	/**
	 * 实际占款天数
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.actAmtDays")
	private String actAmtDays;
	/**
	 * 资金可用日
	 */
	private Date amtAvaDate;
	/**
	 * 交易日历
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.dealCalId")
	private String dealCalId;
	/**
	 * 证券账户
	 */
	private String secAcctId;
	/**
	 * 通道
	 */
//	@NotEmpty(fieldName = "invest.channelId")
	private String chlAgrtId;

	// <!-- 通道交易 新增 强成西 2019/03/26 start -->
	/**
	 * 通道
	 */
	private String chlId;

	/**
	 * 通道费
	 */
	private BigDecimal channelFee;

	/**
	 * 交易金额
	 */
	private BigDecimal tradeAmtAndFee;

	/**
	 * 通道性质
	 */
	private String payType;

    // ---add by 王国显 2019/07/19---start---------
    /**
     * 结算费
     */
    private BigDecimal settleAmt;
	/**
	 * 结算服务费费率
	 */
	private BigDecimal settleAmtRate;

    /**
     * 交易手续费
     */
    private BigDecimal procedureAmt;
	/**
	 * 交易手续费费率
	 */
	private BigDecimal procedureAmtRate;

	/**
	 * 佣金
	 */
	private BigDecimal commissionAmt;

	/**
	 * 佣金费率
	 */
	private BigDecimal commissionAmtRate;

    /**
     * 交易费用LIST
     */
    private List<TrdTradeFeeDetail> tradeFeeList;

    // ---add by 王国显 2019/07/19---end---------

	/**
	 * 计算模式
	 */
	private String calcModel;
	
	private RiskCreditListCommonDto riskCreditListCommonDto;
	

	/**
	 * 获取 payType
	 *
	 * @return payType
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
	 * 获取 channelFee
	 *
	 * @return channelFee
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
	 * 获取 tradeAmtAndFee
	 *
	 * @return tradeAmtAndFee
	 */
	public BigDecimal getTradeAmtAndFee() {
		return tradeAmtAndFee;
	}

	/**
	 * 设置 tradeAmtAndFee
	 *
	 * @param tradeAmtAndFee tradeAmtAndFee
	 */
	public void setTradeAmtAndFee(BigDecimal tradeAmtAndFee) {
		this.tradeAmtAndFee = tradeAmtAndFee;
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

	// <!-- 通道交易 新增 强成西 2019/03/26 end -->

	/**
	 * 组合可用持仓和组合可用现金实体类
	 */
	private BussTrdAvailableCashQuery bussTrdOutRightRepoTrade;

	/**
	 * 币种
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.amtInfo.ccy")
	private String ccy;

	/**
	 * 券面总额
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.amtInfo.bondTotalAmt")
	@Amount(fieldName = "TrdOutRightRepoTrade002.amtInfo.bondTotalAmt")
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal secTotalPrin;

	/**
	 * 回购利率
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.amtInfo.repoRate")
	@Rate(fieldName = "TrdOutRightRepoTrade002.amtInfo.repoRate")
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal repoRate;

	/**
	 * 首次净价
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.amtInfo.firstCleanPrice")
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fCprice;

	/**
	 * 首次净价区间
	 */
	private BigDecimal fCprice2;

	/**
	 * 首次应计利息
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fIntAmt;

	/**
	 * 首次全价
	 */
	private BigDecimal fFprice;

	/**
	 * 首次收益率
	 */
	@Rate(fieldName = "TrdOutRightRepoTrade002.amtInfo.firstYield")
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fYield;

	/**
	 * 首次结算金额
	 */
	@Amount(fieldName = "TrdOutRightRepoTrade002.amtInfo.firstSettleAmt")
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fSettleAmt;

	/**
	 * 首次结算方式
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.amtInfo.firstSettleType")
	private String fSettleMethod;

	/**
	 * 到期净价
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.amtInfo.matCleanPrice")
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mCprice;

	/**
	 * 到期应计利息
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mIntAmt;

	/**
	 * 到期全价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mFprice;

	/**
	 * 到期收益率%
	 */
	@Rate(fieldName = "TrdOutRightRepoTrade002.amtInfo.matYield")
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mYield;

	/**
	 * 到期结算金额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	@Amount(fieldName = "TrdOutRightRepoTrade002.amtInfo.matSettleAmt")
	private BigDecimal mSettleAmt;

	/**
	 * 到期结算方式
	 */
	@NotEmpty(fieldName = "TrdOutRightRepoTrade002.amtInfo.matSettleType")
	private String mSettleMethod;

	/**
	 * 人员信息
	 */
	@Valid
	private transient TrdInvestPersonInfo002Dto trdOutRightRepoPersonInfo;

	/**
	 * 划款指令
	 */
	private transient List<BussTrdTransferOrderDto> bussTrdTransferOrder;

	/**
	 * 备注
	 */
	@Length(fieldName = "TrdOutRightRepoTrade002.remark", max = 1000)
	private String remark;

	/**
	 * 附件上传
	 */
	private transient List<UploadFile> uploadFileList;

    /**
     * 风险试算实体类
     */
    private transient List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 委托信息
     */
    private TrdOutRightRepoTrade003Dto entrustInfo;
    

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getDealBussId() {
		return dealBussId;
	}

	public void setDealBussId(String dealBussId) {
		this.dealBussId = dealBussId;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getSettleSpeed() {
		return settleSpeed;
	}

	public void setSettleSpeed(String settleSpeed) {
		this.settleSpeed = settleSpeed;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public Long getRepoterm() {
		return repoterm;
	}

	public void setRepoterm(Long repoterm) {
		this.repoterm = repoterm;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public String getActAmtDays() {
		return actAmtDays;
	}

	public void setActAmtDays(String actAmtDays) {
		this.actAmtDays = actAmtDays;
	}

	public Date getAmtAvaDate() {
		return amtAvaDate;
	}

	public void setAmtAvaDate(Date amtAvaDate) {
		this.amtAvaDate = amtAvaDate;
	}

	public String getDealCalId() {
		return dealCalId;
	}

	public void setDealCalId(String dealCalId) {
		this.dealCalId = dealCalId;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	public String getChlAgrtId() {
		return chlAgrtId;
	}

	public void setChlAgrtId(String chlAgrtId) {
		this.chlAgrtId = chlAgrtId;
	}

	public BussTrdAvailableCashQuery getBussTrdOutRightRepoTrade() {
		return bussTrdOutRightRepoTrade;
	}

	public void setBussTrdOutRightRepoTrade(BussTrdAvailableCashQuery bussTrdOutRightRepoTrade) {
		this.bussTrdOutRightRepoTrade = bussTrdOutRightRepoTrade;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getSecTotalPrin() {
		return secTotalPrin;
	}

	public void setSecTotalPrin(BigDecimal secTotalPrin) {
		this.secTotalPrin = secTotalPrin;
	}

	public BigDecimal getRepoRate() {
		return repoRate;
	}

	public void setRepoRate(BigDecimal repoRate) {
		this.repoRate = repoRate;
	}

	public BigDecimal getfCprice() {
		return fCprice;
	}

	public void setfCprice(BigDecimal fCprice) {
		this.fCprice = fCprice;
	}

	public BigDecimal getfCprice2() {
		return fCprice2;
	}

	public void setfCprice2(BigDecimal fCprice2) {
		this.fCprice2 = fCprice2;
	}

	public BigDecimal getfIntAmt() {
		return fIntAmt;
	}

	public void setfIntAmt(BigDecimal fIntAmt) {
		this.fIntAmt = fIntAmt;
	}

	public BigDecimal getfFprice() {
		return fFprice;
	}

	public void setfFprice(BigDecimal fFprice) {
		this.fFprice = fFprice;
	}

	public BigDecimal getfYield() {
		return fYield;
	}

	public void setfYield(BigDecimal fYield) {
		this.fYield = fYield;
	}

	public BigDecimal getfSettleAmt() {
		return fSettleAmt;
	}

	public void setfSettleAmt(BigDecimal fSettleAmt) {
		this.fSettleAmt = fSettleAmt;
	}

	public String getfSettleMethod() {
		return fSettleMethod;
	}

	public void setfSettleMethod(String fSettleMethod) {
		this.fSettleMethod = fSettleMethod;
	}

	public BigDecimal getmCprice() {
		return mCprice;
	}

	public void setmCprice(BigDecimal mCprice) {
		this.mCprice = mCprice;
	}

	public BigDecimal getmIntAmt() {
		return mIntAmt;
	}

	public void setmIntAmt(BigDecimal mIntAmt) {
		this.mIntAmt = mIntAmt;
	}

	public BigDecimal getmFprice() {
		return mFprice;
	}

	public void setmFprice(BigDecimal mFprice) {
		this.mFprice = mFprice;
	}

	public BigDecimal getmYield() {
		return mYield;
	}

	public void setmYield(BigDecimal mYield) {
		this.mYield = mYield;
	}

	public BigDecimal getmSettleAmt() {
		return mSettleAmt;
	}

	public void setmSettleAmt(BigDecimal mSettleAmt) {
		this.mSettleAmt = mSettleAmt;
	}

	public String getmSettleMethod() {
		return mSettleMethod;
	}

	public void setmSettleMethod(String mSettleMethod) {
		this.mSettleMethod = mSettleMethod;
	}

	public TrdInvestPersonInfo002Dto getTrdOutRightRepoPersonInfo() {
		return trdOutRightRepoPersonInfo;
	}

	public void setTrdOutRightRepoPersonInfo(TrdInvestPersonInfo002Dto trdOutRightRepoPersonInfo) {
		this.trdOutRightRepoPersonInfo = trdOutRightRepoPersonInfo;
	}

	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}

	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}

	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}

	public List<RiskTrialRetDto> getRiskTrialRetDto() {
		return riskTrialRetDto;
	}

	public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
		this.riskTrialRetDto = riskTrialRetDto;
	}
	

	public TrdOutRightRepoTrade003Dto getEntrustInfo() {
		return entrustInfo;
	}

	public void setEntrustInfo(TrdOutRightRepoTrade003Dto entrustInfo) {
		this.entrustInfo = entrustInfo;
	}

	@PrimaryKey(Field.PK_1)
	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	@PrimaryKey(Field.PK_2)
	public String getBusiType(){
		return DictCodeList.共通业务类型_质押式回购交易.getValue();
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getIsGuarantee() {
		return isGuarantee;
	}

	public void setIsGuarantee(String isGuarantee) {
		this.isGuarantee = isGuarantee;
	}

	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}
	
    /**
     * 获取结算费
     * 
     * @return 结算费
     * @see
     * @since 1.0
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    /**
     * 设置结算费
     * 
     * @param settleAmt 结算费
     * @see
     * @since 1.0
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    /**
     * 获取交易手续费
     * 
     * @return 交易手续费
     * @see
     * @since 1.0
     */
    public BigDecimal getProcedureAmt() {
        return procedureAmt;
    }

    /**
     * 设置交易手续费
     * 
     * @param procedureAmt 交易手续费
     * @see
     * @since 1.0
     */
    public void setProcedureAmt(BigDecimal procedureAmt) {
        this.procedureAmt = procedureAmt;
    }

	public BigDecimal getSettleAmtRate() {
		return settleAmtRate;
	}

	public void setSettleAmtRate(BigDecimal settleAmtRate) {
		this.settleAmtRate = settleAmtRate;
	}

	public BigDecimal getProcedureAmtRate() {
		return procedureAmtRate;
	}

	public void setProcedureAmtRate(BigDecimal procedureAmtRate) {
		this.procedureAmtRate = procedureAmtRate;
	}

	public BigDecimal getCommissionAmt() {
		return commissionAmt;
	}

	public void setCommissionAmt(BigDecimal commissionAmt) {
		this.commissionAmt = commissionAmt;
	}

	public BigDecimal getCommissionAmtRate() {
		return commissionAmtRate;
	}

	public void setCommissionAmtRate(BigDecimal commissionAmtRate) {
		this.commissionAmtRate = commissionAmtRate;
	}

	/**
     * 获取交易费用LIST
     * 
     * @return 交易费用LIST
     * @see
     * @since 1.0
     */
    public List<TrdTradeFeeDetail> getTradeFeeList() {
        return tradeFeeList;
    }

    /**
     * 设置交易费用LIST
     * 
     * @param tradeFeeList 交易费用LIST
     * @see
     * @since 1.0
     */
    public void setTradeFeeList(List<TrdTradeFeeDetail> tradeFeeList) {
        this.tradeFeeList = tradeFeeList;
    }

	/**
	 * 获取 actPortfolioId
	 *
	 * @return actPortfolioId
	 */
	public String getActPortfolioId() {
		return actPortfolioId;
	}

	/**
	 * 设置 actPortfolioId
	 *
	 * @param actPortfolioId actPortfolioId
	 */
	public void setActPortfolioId(String actPortfolioId) {
		this.actPortfolioId = actPortfolioId;
	}
	
    /**
      * 组合分配
      */
	private List<TrdOutRightRepoTradePtl> trdOutRightRepoTradePtl;

    
    /**
     * 获取 trdOutRightRepoTradePtl
     * @return trdOutRightRepoTradePtl.
     */
    public List<TrdOutRightRepoTradePtl> getTrdOutRightRepoTradePtl() {
        return trdOutRightRepoTradePtl;
    }

    
    /**
     * 设置 trdOutRightRepoTradePtl
     * @param trdOutRightRepoTradePtl trdOutRightRepoTradePtl
     */
    public void setTrdOutRightRepoTradePtl(List<TrdOutRightRepoTradePtl> trdOutRightRepoTradePtl) {
        this.trdOutRightRepoTradePtl = trdOutRightRepoTradePtl;
    }

	public String getCalcModel() {
		return calcModel;
	}

	public void setCalcModel(String calcModel) {
		this.calcModel = calcModel;
	}

    public RiskCreditListCommonDto getRiskCreditListCommonDto() {
        return riskCreditListCommonDto;
    }

    public void setRiskCreditListCommonDto(RiskCreditListCommonDto riskCreditListCommonDto) {
        this.riskCreditListCommonDto = riskCreditListCommonDto;
    }
}
