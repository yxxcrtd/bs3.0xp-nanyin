package com.joyintech.tams.invest.common.dto.common;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

/**
 * @program: tams-invest-parent
 * @description: 利息明细信息
 * @author: 强成西
 * @create: 2018-10-30 17:06
 **/
public class FinAssetIntInfo extends BaseEntity {
	
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7430650959987621457L;
    /**
     *  金融产品代码
     */
    private  String finprodId;
    /**
     * 现金流代码
     */
    private  String cashId;
    /**
     * 现金流类型
     */
    private  String cashType;
    /**
     * 子组合代码
     */
    private String subPortfolioId;
    /**
     * 子组合名称
     */
    private String subPortfolioName;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 结算金额
     */
    private BigDecimal  settleAmt;
    /**
     * 支付频率
     */
    private String calcRule;

    /**
     * 还本金额
     */
    private BigDecimal returnAmount;

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 借款利率
     */
    private BigDecimal yield;

    /**
     * 借款利率
     */
    private BigDecimal loadRate;

    /**
     * 理论本息总额
     */
    private BigDecimal theoryAmt;

    /**
     * 实收本息总额(债券理论本息总额)
     */
    private BigDecimal fpriceAmt;

    /**
     * 总剩余本金|基金/净值（分红份额）
     */
    private BigDecimal qty;

    /**
     * 税费金额
     */
    private BigDecimal taxAmount;

    /**
     * 计息开始日
     */
    private String vDate;

    /**
     * 计息结束日
     */
    private String mDate;

    /**
     * 付息频率名称
     */
    private String calcRuleString;
    /**
     * 区间票面利率 
     */
    private BigDecimal repPaperRate;
    /**
     * 理论应收利息
     */
    private BigDecimal intAmtTheory;
    /**
     * 实收利息
     */
    private BigDecimal intAmt;
    /**
     * 理论还本金额
     */
    private BigDecimal prinAmtTheory;
    /**
     * 投资目的
     */
    private String invAim;
    /**
     * 实收本息总额（债券）|分红金额/非货币净值(基金)
     */
    private BigDecimal tradeAmt;
    /**
     * 理论应计利息
     */
    private BigDecimal theoryInt;
    /**
     * 剩余本金(存款)
     */
    private BigDecimal remainPrinAmt;
    /**
     * 单位分红净值
     */
    private BigDecimal unitCprice;
    /**
     * 权益登记日份额
     */
    private BigDecimal registShare;
    /**
     * 权益登记日
     */
    private Date registDate;
    /**
     * 交易代码
     */
    private  String tradeId;
    /**
     * 实际交易组合
     */
    private String investYesorno;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 交易本金，债券券面等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;
    /**
     * 权益登记日份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal regDateAmt;
    
    /**
     * 面值，债券单位面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal faceValue; 
    /**
     * 证券账户
     */
    private String secAcctId;

    private String secAcctName;

    private String chIldName;
    /**
     *  登记日期
     */
    private Date regDate;
    /***update zhangweiqi 2019-10-14 存续期添加通道费*/
    private BigDecimal channelFee;
    //通道
    private String chIld;
    //折算前份额
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bconShareAmt;
    //折算后份额
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal aconShareAmt;
    
    /**
     * 收息未支付利息
     * @return
     */
    private BigDecimal chargeUnpayInterest;
    /**
     * 还本未支付利息
     * @return
     */
    private BigDecimal repaymentUnpayInterest;

    /**
     * 实付还本未支付利息
     * @return
     */
    private BigDecimal actrRepaymentUnpayInterest;

    /**
     * 本金总持仓
     * @return
     */
    private BigDecimal capitalHoldPosition;
    
    /**
     * 利息总持仓
     * @return
     */
    private BigDecimal interestHoldPosition;
    
    
    /**
     * 还本未支付总持仓
     * @return
     */
    private BigDecimal repaymentUnpayHoldPosition;
    
    /**
     * 本金总罚息
     * @return
     */
    private BigDecimal capitalPenaltyTotle;
    
    /**
     * 利息总罚息
     * @return
     */
    private BigDecimal interestPenaltyTotle;
    /**
     * 还本未付息总罚息
     * @return
     */
    private BigDecimal repaymentUnpayHoldPenaltyTotle;
    
    
    /**
     * 本金罚息
     * @return
     */
    private BigDecimal capitalPenalty;
    
    /**
     * 利息罚息
     * @return
     */
    private BigDecimal interestPenalty;
    /**
     * 还本未付息罚息
     * @return
     */
    private BigDecimal repaymentUnpayHoldPenalty;
    
    /**
     * 支付日
     */
    private Date payDate;
    
    /**
     * 实收罚息
     */
    private BigDecimal penaltyIntamt;
    
    /**
     * 理论罚息
     */
    private BigDecimal theoryPenalty;
    
    /**
     * 关联母组合信息id（portfolioId+invAim)
     */
    private String portfolioIdInvAim;
    
    /**
     * 实付本金罚息
     */
    private BigDecimal actPenaltyIntBj;
    
    /**
     * 实付利息罚息
     */
    private BigDecimal actPenaltyIntLx;
    
    /**
     * 实付还本未付息罚息
     */
    private BigDecimal actPenaltyIntHbWfx;
    
    /**
     * 日初未支付本金
     * @return
     */
    private BigDecimal earlyPrinAmtTheory;

    /**
     * 日初收息未支付利息
     * @return
     */
    private BigDecimal earlyChargeUnpayInterest;
    /**
     * 日初还本未支付利息
     * @return
     */
    private BigDecimal earlyRepaymentUnpayInterest;


    /**
	 * @return the 收息未支付利息
	 */
	public BigDecimal getChargeUnpayInterest() {
		return chargeUnpayInterest;
	}

	/**
	 * @param chargeUnpayInterest the 收息未支付利息 to set
	 */
	public void setChargeUnpayInterest(BigDecimal chargeUnpayInterest) {
		this.chargeUnpayInterest = chargeUnpayInterest;
	}

	/**
	 * @return the 还本未支付利息
	 */
	public BigDecimal getRepaymentUnpayInterest() {
		return repaymentUnpayInterest;
	}

	/**
	 * @param repaymentUnpayInterest the 还本未支付利息 to set
	 */
	public void setRepaymentUnpayInterest(BigDecimal repaymentUnpayInterest) {
		this.repaymentUnpayInterest = repaymentUnpayInterest;
	}

	/**
	 * @return the 本金总持仓
	 */
	public BigDecimal getCapitalHoldPosition() {
		return capitalHoldPosition;
	}

	/**
	 * @param capitalHoldPosition the 本金总持仓 to set
	 */
	public void setCapitalHoldPosition(BigDecimal capitalHoldPosition) {
		this.capitalHoldPosition = capitalHoldPosition;
	}

	/**
	 * @return the 利息总持仓
	 */
	public BigDecimal getInterestHoldPosition() {
		return interestHoldPosition;
	}

	/**
	 * @param interestHoldPosition the 利息总持仓 to set
	 */
	public void setInterestHoldPosition(BigDecimal interestHoldPosition) {
		this.interestHoldPosition = interestHoldPosition;
	}

	/**
	 * @return the 还本未支付总持仓
	 */
	public BigDecimal getRepaymentUnpayHoldPosition() {
		return repaymentUnpayHoldPosition;
	}

	/**
	 * @param repaymentUnpayHoldPosition the 还本未支付总持仓 to set
	 */
	public void setRepaymentUnpayHoldPosition(BigDecimal repaymentUnpayHoldPosition) {
		this.repaymentUnpayHoldPosition = repaymentUnpayHoldPosition;
	}

	/**
	 * @return the 本金罚息
	 */
	public BigDecimal getCapitalPenalty() {
		return capitalPenalty;
	}

	/**
	 * @param capitalPenalty the 本金罚息 to set
	 */
	public void setCapitalPenalty(BigDecimal capitalPenalty) {
		this.capitalPenalty = capitalPenalty;
	}

	/**
	 * @return the 利息罚息
	 */
	public BigDecimal getInterestPenalty() {
		return interestPenalty;
	}

	/**
	 * @param interestPenalty the 利息罚息 to set
	 */
	public void setInterestPenalty(BigDecimal interestPenalty) {
		this.interestPenalty = interestPenalty;
	}

	/**
	 * @return the 还本未付息罚息
	 */
	public BigDecimal getRepaymentUnpayHoldPenalty() {
		return repaymentUnpayHoldPenalty;
	}

	/**
	 * @param repaymentUnpayHoldPenalty the 还本未付息罚息 to set
	 */
	public void setRepaymentUnpayHoldPenalty(BigDecimal repaymentUnpayHoldPenalty) {
		this.repaymentUnpayHoldPenalty = repaymentUnpayHoldPenalty;
	}
	
	/**
	 * @return the 本金总罚息
	 */
	public BigDecimal getCapitalPenaltyTotle() {
		return capitalPenaltyTotle;
	}

	/**
	 * @param capitalPenaltyTotle the 本金总罚息 to set
	 */
	public void setCapitalPenaltyTotle(BigDecimal capitalPenaltyTotle) {
		this.capitalPenaltyTotle = capitalPenaltyTotle;
	}

	/**
	 * @return the 利息总罚息 
	 */
	public BigDecimal getInterestPenaltyTotle() {
		return interestPenaltyTotle;
	}

	/**
	 * @param interestPenaltyTotle the 利息总罚息  to set
	 */
	public void setInterestPenaltyTotle(BigDecimal interestPenaltyTotle) {
		this.interestPenaltyTotle = interestPenaltyTotle;
	}

	/**
	 * @return the 还本未付息总罚息
	 */
	public BigDecimal getRepaymentUnpayHoldPenaltyTotle() {
		return repaymentUnpayHoldPenaltyTotle;
	}

	/**
	 * @param repaymentUnpayHoldPenaltyTotle the 还本未付息总罚息 to set
	 */
	public void setRepaymentUnpayHoldPenaltyTotle(BigDecimal repaymentUnpayHoldPenaltyTotle) {
		this.repaymentUnpayHoldPenaltyTotle = repaymentUnpayHoldPenaltyTotle;
	}


    public String getChIld() {
        return chIld;
    }

    public void setChIld(String chIld) {
        this.chIld = chIld;
    }

    public BigDecimal getBconShareAmt() {
        return bconShareAmt;
    }

    public void setBconShareAmt(BigDecimal bconShareAmt) {
        this.bconShareAmt = bconShareAmt;
    }

    public BigDecimal getAconShareAmt() {
        return aconShareAmt;
    }

    public void setAconShareAmt(BigDecimal aconShareAmt) {
        this.aconShareAmt = aconShareAmt;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public BigDecimal getRegDateAmt() {
        return regDateAmt;
    }

    public void setRegDateAmt(BigDecimal regDateAmt) {
        this.regDateAmt = regDateAmt;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public String getInvestYesorno() {
        return investYesorno;
    }

    public void setInvestYesorno(String investYesorno) {
        this.investYesorno = investYesorno;
    }

    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getSubPortfolioId() {
        return subPortfolioId;
    }

    public void setSubPortfolioId(String subPortfolioId) {
        this.subPortfolioId = subPortfolioId;
    }

    public String getSubPortfolioName() {
        return subPortfolioName;
    }

    public void setSubPortfolioName(String subPortfolioName) {
        this.subPortfolioName = subPortfolioName;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
	 * 获取 tradeId
	 * @return tradeId.
	 */
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
	 * 获取 unitCprice
	 * @return unitCprice.
	 */
	public BigDecimal getUnitCprice() {
		return unitCprice;
	}

	/**
	 * 设置 unitCprice
	 * @param unitCprice unitCprice
	 */
	public void setUnitCprice(BigDecimal unitCprice) {
		this.unitCprice = unitCprice;
	}

	/**
	 * 获取 registShare
	 * @return registShare.
	 */
	public BigDecimal getRegistShare() {
		return registShare;
	}

	/**
	 * 设置 registShare
	 * @param registShare registShare
	 */
	public void setRegistShare(BigDecimal registShare) {
		this.registShare = registShare;
	}

	/**
	 * 获取 registDate
	 * @return registDate.
	 */
	public Date getRegistDate() {
		return registDate;
	}

	/**
	 * 设置 registDate
	 * @param registDate registDate
	 */
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	/**
	 * 获取 remainPrinAmt
	 * @return remainPrinAmt.
	 */
	public BigDecimal getRemainPrinAmt() {
		return remainPrinAmt;
	}

	/**
	 * 设置 remainPrinAmt
	 * @param remainPrinAmt remainPrinAmt
	 */
	public void setRemainPrinAmt(BigDecimal remainPrinAmt) {
		this.remainPrinAmt = remainPrinAmt;
	}

	/**
	 * 获取 theoryInt
	 * @return theoryInt.
	 */
	public BigDecimal getTheoryInt() {
		return theoryInt;
	}

	/**
	 * 设置 theoryInt
	 * @param theoryInt theoryInt
	 */
	public void setTheoryInt(BigDecimal theoryInt) {
		this.theoryInt = theoryInt;
	}

	/**
	 * 获取 tradeAmt
	 * @return tradeAmt.
	 */
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	/**
	 * 设置 tradeAmt
	 * @param tradeAmt tradeAmt
	 */
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	/**
	 * 获取 invAim
	 * @return invAim.
	 */
	public String getInvAimString() {
	    if ("999999".equals(getInvAim())|| StringUtil.equals(DictCodeListExt1.投资目的_卖空.getValue(),getInvAim())) {
	        return "融入券";
        }
		return SysDatadictUtil.getNameFromDataDict(DictCode.投资目的, getInvAim());
	}
	
	/**
	 * 获取 invAim值（逾期使用）
	 * @return invAim.
	 */
	public String getInvAimStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.投资目的, getInvAim());
	}

	/**
	 * 获取 invAim
	 * @return invAim.
	 */
	public String getInvAim() {
		return invAim;
	}

	/**
	 * 设置 invAim
	 * @param invAim invAim
	 */
	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	/**
     * 获取 prinAmtTheory.
     *
     * @return prinAmtTheory 的值
     */
    public BigDecimal getPrinAmtTheory() {
        return prinAmtTheory;
    }

    /**
     * 设置 prinAmtTheory.
     *
     * <p>You can use getPrinAmtTheory() to get the value of prinAmtTheory</p>
     *
     * @param prinAmtTheory prinAmtTheory
     */
    public void setPrinAmtTheory(BigDecimal prinAmtTheory) {
        this.prinAmtTheory = prinAmtTheory;
    }

    /**
     * 获取 calcRuleString
     *
     * @return calcRuleString
     */
    public String getCalcRuleString() {
        return calcRuleString;
    }

    /**
     * 设置 calcRuleString
     *
     * @param calcRuleString calcRuleString
     */
    public void setCalcRuleString(String calcRuleString) {
        this.calcRuleString = calcRuleString;
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
     * 获取 calcRule
     *
     * @return calcRule
     */
    public String getCalcRule() {
        return calcRule;
    }

    /**
     * 设置 calcRule
     *
     * @param calcRule calcRule
     */
    public void setCalcRule(String calcRule) {
        this.calcRule = calcRule;
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
     * 获取 amount
     *
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 amount
     *
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     * 获取 repPaperRate.
     *
     * @return repPaperRate 的值
     */
    public BigDecimal getRepPaperRate() {
        return repPaperRate;
    }

    /**
     * 设置 repPaperRate.
     *
     * <p>You can use getRepPaperRate() to get the value of repPaperRate</p>
     *
     * @param repPaperRate repPaperRate
     */
    public void setRepPaperRate(BigDecimal repPaperRate) {
        this.repPaperRate = repPaperRate;
    }

    /**
     * 获取 intAmtTheory.
     *
     * @return intAmtTheory 的值
     */
    public BigDecimal getIntAmtTheory() {
        return intAmtTheory;
    }

    /**
     * 设置 intAmtTheory.
     *
     * <p>You can use getIntAmtTheory() to get the value of intAmtTheory</p>
     *
     * @param intAmtTheory intAmtTheory
     */
    public void setIntAmtTheory(BigDecimal intAmtTheory) {
        this.intAmtTheory = intAmtTheory;
    }

    /**
     * 获取 intAmt.
     *
     * @return intAmt 的值
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 intAmt.
     *
     * <p>You can use getIntAmt() to get the value of intAmt</p>
     *
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
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
     * 获取 loadRate
     *
     * @return loadRate
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

	public BigDecimal getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(BigDecimal channelFee) {
		this.channelFee = channelFee;
	}
    public String getSecAcctName() {
        return secAcctName;
    }

    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    public String getChIldName() {
        return chIldName;
    }

    public void setChIldName(String chIldName) {
        this.chIldName = chIldName;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	 * @return the 理论罚息
	 */
	public BigDecimal getTheoryPenalty() {
		return theoryPenalty;
	}

	/**
	 * @param theoryPenalty the 理论罚息 to set
	 */
	public void setTheoryPenalty(BigDecimal theoryPenalty) {
		this.theoryPenalty = theoryPenalty;
	}

	/**
	 * @return the 实收罚息
	 */
	public BigDecimal getPenaltyIntamt() {
		return penaltyIntamt;
	}

	/**
	 * @param realityPenalty the 实收罚息 to set
	 */
	public void setPenaltyIntamt(BigDecimal penaltyIntamt) {
		this.penaltyIntamt = penaltyIntamt;
	}

	public String getPortfolioIdInvAim() {
		return portfolioIdInvAim;
	}

	public void setPortfolioIdInvAim(String portfolioIdInvAim) {
		this.portfolioIdInvAim = portfolioIdInvAim;
	}

	public BigDecimal getActPenaltyIntBj() {
		return actPenaltyIntBj;
	}

	public void setActPenaltyIntBj(BigDecimal actPenaltyIntBj) {
		this.actPenaltyIntBj = actPenaltyIntBj;
	}

	public BigDecimal getActPenaltyIntLx() {
		return actPenaltyIntLx;
	}

	public void setActPenaltyIntLx(BigDecimal actPenaltyIntLx) {
		this.actPenaltyIntLx = actPenaltyIntLx;
	}

	public BigDecimal getActPenaltyIntHbWfx() {
		return actPenaltyIntHbWfx;
	}

	public void setActPenaltyIntHbWfx(BigDecimal actPenaltyIntHbWfx) {
		this.actPenaltyIntHbWfx = actPenaltyIntHbWfx;
	}

    /**
     * 获取 actrRepaymentUnpayInterest
     *
     * @return actrRepaymentUnpayInterest
     */
    public BigDecimal getActrRepaymentUnpayInterest() {
        return actrRepaymentUnpayInterest;
    }
	public BigDecimal getEarlyPrinAmtTheory() {
		return earlyPrinAmtTheory;
	}

	public void setEarlyPrinAmtTheory(BigDecimal earlyPrinAmtTheory) {
		this.earlyPrinAmtTheory = earlyPrinAmtTheory;
	}

	public BigDecimal getEarlyChargeUnpayInterest() {
		return earlyChargeUnpayInterest;
	}

	public void setEarlyChargeUnpayInterest(BigDecimal earlyChargeUnpayInterest) {
		this.earlyChargeUnpayInterest = earlyChargeUnpayInterest;
	}

	public BigDecimal getEarlyRepaymentUnpayInterest() {
		return earlyRepaymentUnpayInterest;
	}

	public void setEarlyRepaymentUnpayInterest(BigDecimal earlyRepaymentUnpayInterest) {
		this.earlyRepaymentUnpayInterest = earlyRepaymentUnpayInterest;
	}


    /**
     * 设置 actrRepaymentUnpayInterest
     *
     * @param actrRepaymentUnpayInterest actrRepaymentUnpayInterest
     */
    public void setActrRepaymentUnpayInterest(BigDecimal actrRepaymentUnpayInterest) {
        this.actrRepaymentUnpayInterest = actrRepaymentUnpayInterest;
    }
}
