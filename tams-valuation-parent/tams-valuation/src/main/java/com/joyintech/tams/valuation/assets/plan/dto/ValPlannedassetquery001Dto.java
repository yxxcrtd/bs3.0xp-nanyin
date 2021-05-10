/*
 * 文件名：ValPlannedassetquery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.plan.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;

import java.math.BigDecimal;

/**
 * 计划类资产查询dto
 * 
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValPlannedassetquery001Dto
 * @since 1.0
 */
public class ValPlannedassetquery001Dto extends FinProduct{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
   
    /**
     * 资产类型
     */
    private String assetTypeCode;
    /**
     * 资产类型
     */
    private String assetType;
    /**
     * 起息日
     */
    private String vdate1; 
    /**
     * 到期日
     */
    private String mdate1;
    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 收益类型名称
     */
    private String profitTypeCode;
    /**
     * 是否按日复利
     */
    private String compoundint;
    /**
     * 本金收益标志
     */
    private String priproflag;
    /**
     * 募集方式
     */
    private String raisetype;
    /**
     * 公开市场代码
     */
    private String opmarketid;
    /**
     * 是否非标
     */
    private String isNonstandard;
    /**
     * 是否有预期收益
     */
    private String isExpectedprofit;
    /**
     * 永续
     */
    private String susYorN;
    /**
     * 还本方式
     */
    private String retype;
    /**
     * 计息营业日准则
     */
    private String jxyyrzz;
//    /**
//     * 付息营业日准则
//     */
//    private String fxyyrzz;
    /**
     * 付息截止日营业日准则
     */
    private String endTdayRule;

    /**
     * 付息支付日营业日准则
     */
    private String payTdayRule;
    /**
     * 付息日期
     */
    private String fxpayDate;
    /**
     * 分红日期
     */
    private String fhpayDate;
    /**
     * 当前报价
     */
    private BigDecimal currentquotation;
   
    /**
     * 计息基础   
     */
    private String basic;
    
    /**
     * 当前利率
     */
    private String rate;
   
    /**
     * 发行价
     */
    private BigDecimal price;
    /**
     * 发行总额
     */
    private BigDecimal sumprice;
    /**
     * 交易场所
     */
    private String tradingplace;
    /**
     * 交易日历
     */
    private String tradingtime;
    /**
     * 到期赎回价
     */
    private String dqshj;
//    /**
//     * 营业日准则
//     */
//    private String yyrzz;
    /**
     * 付息频率
     */
    private String paycycle;
    /**
     *利息分配方式
     */
    private String inttype;
    /**
     * 利率类型
     */
    private String ratetype;
    /**
     * 初始利率
     */
    private BigDecimal ratestr;
    /**
     * 推算方式
     */
    private String calcrule;
    /**
     * 首次付息日
     */
    private String fpayDate;
    /**
     * 币种名称
     */
    private String ccyName;
    /**
     * 境内外名称
     */
    private String blnAreaName;
    /**
     * 交易方式
     */
    private String transactiontype;
    /**
     * 生效日
     */
    private String efdate;
    /**
     * 利率
     */
    private BigDecimal rate2;
    /**
     * 计息开始日
     */
    private String jxdatestr;
    /**
     * 计息结束日
     */
    private String jxdateend;
    /**
     * 支付日
     */
    private String paydate;
    /**
     * 百元应计利息
     */
    private BigDecimal byrate;
    /**
     * 计息开始日
     */
    private String jsdatestr;
    /**
     * 计息截止日
     */
    private String jsdateend;
    /**
     * 提前还本金额
     */
    private BigDecimal tqrmon;
    /**
     * 按期还本金额
     */
    private BigDecimal aqrmon;
    /**
     * 是否带息
     */
    private String israte;
    /**
     * 应计利息
     */
    private BigDecimal yjrte;


    /**
     * 是否其他债券
     */
    private String isQtzq;

    /**
     * 是否其他债券 显示名称
     */
    private String isQtzqFmt;
    
    /**
     * 获取 price
     * @return price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置 price
     * @param price price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    /**
     * 获取 tradingplace
     * @return tradingplace.
     */
    public String getTradingplace() {
        return tradingplace;
    }
  
    /**
     * 设置 tradingplace
     * @param tradingplace tradingplace
     */
    public void setTradingplace(String tradingplace) {
        this.tradingplace = tradingplace;
    }
      
    /**
     * 获取 rate
     * @return rate.
     */
    public String getRate() {
        return rate;
    }
    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(String rate) {
        this.rate = rate;
    }
    
     /**
     * 获取 inttype
     * @return inttype.
     */
    public String getInttype() {
        return inttype;
    }


    
    /**
     * 设置 inttype
     * @param inttype inttype
     */
    public void setInttype(String inttype) {
        this.inttype = inttype;
    }


    
    /**
     * 获取 ratetype
     * @return ratetype.
     */
    public String getRatetype() {
        return ratetype;
    }


    
    /**
     * 设置 ratetype
     * @param ratetype ratetype
     */
    public void setRatetype(String ratetype) {
        this.ratetype = ratetype;
    }

    /**
     * 获取 vdate1
     * @return vdate1.
     */
    public String getVdate1() {
        return vdate1;
    }


    
    /**
     * 设置 vdate1
     * @param vdate1 vdate1
     */
    public void setVdate1(String vdate1) {
        this.vdate1 = vdate1;
    }


    
    /**
     * 获取 mdate1
     * @return mdate1.
     */
    public String getMdate1() {
        return mdate1;
    }


    
    /**
     * 设置 mdate1
     * @param mdate1 mdate1
     */
    public void setMdate1(String mdate1) {
        this.mdate1 = mdate1;
    }


    
    /**
     * 获取 calcrule
     * @return calcrule.
     */
    public String getCalcrule() {
        return calcrule;
    }


    
    /**
     * 设置 calcrule
     * @param calcrule calcrule
     */
    public void setCalcrule(String calcrule) {
        this.calcrule = calcrule;
    }


    
    /**
     * 获取 ratestr
     * @return ratestr.
     */
    public BigDecimal getRatestr() {
        return ratestr;
    }


    
    /**
     * 设置 ratestr
     * @param ratestr ratestr
     */
    public void setRatestr(BigDecimal ratestr) {
        this.ratestr = ratestr;
    }

    /**
     * 获取 sumprice
     * @return sumprice.
     */
    public BigDecimal getSumprice() {
        return sumprice;
    }

    /**
     * 设置 sumprice
     * @param sumprice sumprice
     */
    public void setSumprice(BigDecimal sumprice) {
        this.sumprice = sumprice;
    }
    

    /**
     * 获取 tradingtime
     * @return tradingtime.
     */
    public String getTradingtime() {
        return tradingtime;
    }

    /**
     * 设置 tradingtime
     * @param tradingtime tradingtime
     */
    public void setTradingtime(String tradingtime) {
        this.tradingtime = tradingtime;
    }
    /**
     * 获取 dqshj
     * @return dqshj.
     */
    public String getDqshj() {
        return dqshj;
    }

    
    /**
     * 设置 dqshj
     * @param dqshj dqshj
     */
    public void setDqshj(String dqshj) {
        this.dqshj = dqshj;
    }

    
//    /**
//     * 获取 yyrzz
//     * @return yyrzz.
//     */
//    public String getYyrzz() {
//        return yyrzz;
//    }
//
//    
//    /**
//     * 设置 yyrzz
//     * @param yyrzz yyrzz
//     */
//    public void setYyrzz(String yyrzz) {
//        this.yyrzz = yyrzz;
//    }

    
    /**
     * 获取 paycycle
     * @return paycycle.
     */
    public String getPaycycle() {
        return paycycle;
    }

    
    /**
     * 设置 paycycle
     * @param paycycle paycycle
     */
    public void setPaycycle(String paycycle) {
        this.paycycle = paycycle;
    }

    
    /**
     * 获取 fpayDate
     * @return fpayDate.
     */
    public String getFpayDate() {
        return fpayDate;
    }

    
    /**
     * 设置 fpayDate
     * @param fpayDate fpayDate
     */
    public void setFpayDate(String fpayDate) {
        this.fpayDate = fpayDate;
    }

    
    /**
     * 获取 basic
     * @return basic.
     */
    public String getBasic() {
        return basic;
    }

    
    /**
     * 设置 basic
     * @param basic basic
     */
    public void setBasic(String basic) {
        this.basic = basic;
    }

    
    /**
     * 获取 assetType
     * @return assetType.
     */
    public String getAssetTypeFt() {
//    	return SysDataUtil.getNameFromDataDict(DictCode.计划类资产类型, this.assetType);
    	
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.计划类资产类型, assetType))?
            SysDataUtil.getNameFromDataDict(DictCode.计划类资产类型, assetType):assetType;
    }

    
    /**
     * 设置 assetType
     * @param assetType assetType
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitTypeFt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType);
        
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType))?
            SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType):this.profitType;
    }

    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }



    
    /**
     * 获取 currentquotation
     * @return currentquotation.
     */
    public BigDecimal getCurrentquotation() {
        return currentquotation;
    }

    
    /**
     * 设置 currentquotation
     * @param currentquotation currentquotation
     */
    public void setCurrentquotation(BigDecimal currentquotation) {
        this.currentquotation = currentquotation;
    }

    /**
     * 获取 compoundint
     * @return compoundint.
     */
    public String getCompoundint() {
        return compoundint;
    }

    
    /**
     * 设置 compoundint
     * @param compoundint compoundint
     */
    public void setCompoundint(String compoundint) {
        this.compoundint = compoundint;
    }

    
    /**
     * 获取 priproflag
     * @return priproflag.
     */
    public String getPriproflag() {
        return priproflag;
    }

    
    /**
     * 设置 priproflag
     * @param priproflag priproflag
     */
    public void setPriproflag(String priproflag) {
        this.priproflag = priproflag;
    }

    
    /**
     * 获取 raisetype
     * @return raisetype.
     */
    public String getRaisetype() {
        return raisetype;
    }

    
    /**
     * 设置 raisetype
     * @param raisetype raisetype
     */
    public void setRaisetype(String raisetype) {
        this.raisetype = raisetype;
    }

    
    /**
     * 获取 opmarketid
     * @return opmarketid.
     */
    public String getOpmarketid() {
        return opmarketid;
    }

    
    /**
     * 设置 opmarketid
     * @param opmarketid opmarketid
     */
    public void setOpmarketid(String opmarketid) {
        this.opmarketid = opmarketid;
    }

    
    /**
     * 获取 isNonstandard
     * @return isNonstandard.
     */
    public String getIsNonstandard() {
        return isNonstandard;
    }

    
    /**
     * 设置 isNonstandard
     * @param isNonstandard isNonstandard
     */
    public void setIsNonstandard(String isNonstandard) {
        this.isNonstandard = isNonstandard;
    }

    
    /**
     * 获取 isExpectedprofit
     * @return isExpectedprofit.
     */
    public String getIsExpectedprofit() {
        return isExpectedprofit;
    }

    
    /**
     * 设置 isExpectedprofit
     * @param isExpectedprofit isExpectedprofit
     */
    public void setIsExpectedprofit(String isExpectedprofit) {
        this.isExpectedprofit = isExpectedprofit;
    }

    
    
    /**
     * 获取 susYorN
     * @return susYorN.
     */
    public String getSusYorN() {
        return susYorN;
    }

    
    /**
     * 设置 susYorN
     * @param susYorN susYorN
     */
    public void setSusYorN(String susYorN) {
        this.susYorN = susYorN;
    }

    /**
     * 获取 retype
     * @return retype.
     */
    public String getRetype() {
        return retype;
    }

    
    /**
     * 设置 retype
     * @param retype retype
     */
    public void setRetype(String retype) {
        this.retype = retype;
    }

    
    /**
     * 获取 jxyyrzz
     * @return jxyyrzz.
     */
    public String getJxyyrzz() {
        return jxyyrzz;
    }

    
    /**
     * 设置 jxyyrzz
     * @param jxyyrzz jxyyrzz
     */
    public void setJxyyrzz(String jxyyrzz) {
        this.jxyyrzz = jxyyrzz;
    }

    
//    /**
//     * 获取 fxyyrzz
//     * @return fxyyrzz.
//     */
//    public String getFxyyrzz() {
//        return fxyyrzz;
//    }
//
//    
//    /**
//     * 设置 fxyyrzz
//     * @param fxyyrzz fxyyrzz
//     */
//    public void setFxyyrzz(String fxyyrzz) {
//        this.fxyyrzz = fxyyrzz;
//    }

    
    
    /**
     * 获取 fxpayDate
     * @return fxpayDate.
     */
    public String getFxpayDate() {
        return fxpayDate;
    }

    
    public String getEndTdayRule() {
		return endTdayRule;
	}

	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}

	public String getPayTdayRule() {
		return payTdayRule;
	}

	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}

	/**
     * 设置 fxpayDate
     * @param fxpayDate fxpayDate
     */
    public void setFxpayDate(String fxpayDate) {
        this.fxpayDate = fxpayDate;
    }

 
    
    /**
     * 获取 fhpayDate
     * @return fhpayDate.
     */
    public String getFhpayDate() {
        return fhpayDate;
    }

    
    /**
     * 设置 fhpayDate
     * @param fhpayDate fhpayDate
     */
    public void setFhpayDate(String fhpayDate) {
        this.fhpayDate = fhpayDate;
    }

    /**
     * 获取 profitTypeCode
     * @return profitTypeCode.
     */
    public String getProfitTypeCode() {
        return profitTypeCode;
    }

    
    /**
     * 设置 profitTypeCode
     * @param profitTypeCode profitTypeCode
     */
    public void setProfitTypeCode(String profitTypeCode) {
        this.profitTypeCode = profitTypeCode;
    }

    /**
     * 获取 ccyName
     * @return ccyName.
     */
    public String getCcyNameFt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
        
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy()))?
            SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy()):this.getCcy();
    }

    
    /**
     * 设置 ccyName
     * @param ccyName ccyName
     */
    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }

    
    /**
     * 获取 blnAreaName
     * @return blnAreaName.
     */
    public String getBlnAreaNameFt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.境内外, this.getBlnArea());
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.境内外, this.getBlnArea()))?
            SysDataUtil.getNameFromDataDict(DictCode.境内外, this.getBlnArea()):this.getBlnArea();
    }

    
    /**
     * 设置 blnAreaName
     * @param blnAreaName blnAreaName
     */
    public void setBlnAreaName(String blnAreaName) {
        this.blnAreaName = blnAreaName;
    }

    
    /**
     * 获取 transactiontype
     * @return transactiontype.
     */
    public String getTransactiontype() {
        return transactiontype;
    }

    
    /**
     * 设置 transactiontype
     * @param transactiontype transactiontype
     */
    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }

    
    /**
     * 获取 efdate
     * @return efdate.
     */
    public String getEfdate() {
        return efdate;
    }

    
    /**
     * 设置 efdate
     * @param efdate efdate
     */
    public void setEfdate(String efdate) {
        this.efdate = efdate;
    }

    
    /**
     * 获取 rate2
     * @return rate2.
     */
    public BigDecimal getRate2() {
        return rate2;
    }

    
    /**
     * 设置 rate2
     * @param rate2 rate2
     */
    public void setRate2(BigDecimal rate2) {
        this.rate2 = rate2;
    }

    /**
     * 获取 jxdatestr
     * @return jxdatestr.
     */
    public String getJxdatestr() {
        return jxdatestr;
    }

    
    /**
     * 设置 jxdatestr
     * @param jxdatestr jxdatestr
     */
    public void setJxdatestr(String jxdatestr) {
        this.jxdatestr = jxdatestr;
    }

    
    /**
     * 获取 jxdateend
     * @return jxdateend.
     */
    public String getJxdateend() {
        return jxdateend;
    }

    
    /**
     * 设置 jxdateend
     * @param jxdateend jxdateend
     */
    public void setJxdateend(String jxdateend) {
        this.jxdateend = jxdateend;
    }

    
    /**
     * 获取 paydate
     * @return paydate.
     */
    public String getPaydate() {
        return paydate;
    }

    
    /**
     * 设置 paydate
     * @param paydate paydate
     */
    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    
    
    /**
     * 获取 byrate
     * @return byrate.
     */
    public BigDecimal getByrate() {
        return byrate;
    }

    
    /**
     * 设置 byrate
     * @param byrate byrate
     */
    public void setByrate(BigDecimal byrate) {
        this.byrate = byrate;
    }

    
    /**
     * 获取 jsdatestr
     * @return jsdatestr.
     */
    public String getJsdatestr() {
        return jsdatestr;
    }

    
    /**
     * 设置 jsdatestr
     * @param jsdatestr jsdatestr
     */
    public void setJsdatestr(String jsdatestr) {
        this.jsdatestr = jsdatestr;
    }

    /**
     * 获取 jsdateend
     * @return jsdateend.
     */
    public String getJsdateend() {
        return jsdateend;
    }

    
    /**
     * 设置 jsdateend
     * @param jsdateend jsdateend
     */
    public void setJsdateend(String jsdateend) {
        this.jsdateend = jsdateend;
    }

    
    /**
     * 获取 tqrmon
     * @return tqrmon.
     */
    public BigDecimal getTqrmon() {
        return tqrmon;
    }

    
    /**
     * 设置 tqrmon
     * @param tqrmon tqrmon
     */
    public void setTqrmon(BigDecimal tqrmon) {
        this.tqrmon = tqrmon;
    }

    
    /**
     * 获取 aqrmon
     * @return aqrmon.
     */
    public BigDecimal getAqrmon() {
        return aqrmon;
    }

    
    /**
     * 设置 aqrmon
     * @param aqrmon aqrmon
     */
    public void setAqrmon(BigDecimal aqrmon) {
        this.aqrmon = aqrmon;
    }

    
    /**
     * 获取 israte
     * @return israte.
     */
    public String getIsrateFt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.是否, israte);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, israte))?
            SysDataUtil.getNameFromDataDict(DictCode.是否, israte):israte;
    }

    
    /**
     * 设置 israte
     * @param israte israte
     */
    public void setIsrate(String israte) {
        this.israte = israte;
    }

    
    /**
     * 获取 yjrte
     * @return yjrte.
     */
    public BigDecimal getYjrte() {
        return yjrte;
    }

    
    /**
     * 设置 yjrte
     * @param yjrte yjrte
     */
    public void setYjrte(BigDecimal yjrte) {
        this.yjrte = yjrte;
    }

    
    /**
     * 获取 assetTypeCode
     * @return assetTypeCode.
     */
    public String getAssetTypeCode() {
        return assetTypeCode;
    }

    
    /**
     * 设置 assetTypeCode
     * @param assetTypeCode assetTypeCode
     */
    public void setAssetTypeCode(String assetTypeCode) {
        this.assetTypeCode = assetTypeCode;
    }

    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */

	public String getAssetType() {
		return assetType;
	}

	public String getProfitType() {
		return profitType;
	}

	public String getCcyName() {
		return ccyName;
	}

	public String getBlnAreaName() {
		return blnAreaName;
	}

	public String getIsrate() {
		return israte;
	}

    public String getIsQtzq() {
        return isQtzq;
    }

    public void setIsQtzq(String isQtzq) {
        this.isQtzq = isQtzq;
    }

    public String getIsQtzqFmt() {
        if(StringUtil.equals(this.getProfitType(), DictCodeList.收益类型_收益型.getValue())) {
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsQtzq())) ?
                    SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsQtzq()) : DictCodeList.是否_否.getValue();
        }
        else {
            return "";
        }
    }

    public void setIsQtzqFmt(String isQtzqFmt) {
        this.isQtzqFmt = isQtzqFmt;
    }
  
}
