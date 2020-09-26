/*
 * 文件名：ValBondassetquery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.bondasset.dto;

import java.math.BigDecimal;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.assets.bondasset.validator.ValBondassetquery002Validator;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import com.joyintech.framework.util.StringUtil;

/**
 * 债券类资产查询dto
 * 
 * @author qujianzhong
 * @version 1.0 2018年10月26日
 * @see ValBondassetquery002Dto
 * @since 1.0
 */
@PageId("ValBondassetquery002")
@ValidLogic(targetClass = ValBondassetquery002Validator.class)
public class ValBondassetquery002Dto extends FinProduct implements ProcessEntity {
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 债券列表类属性Dto,包括行权计划、付息计划等
     */
    private ValBondassdetComponentDto componentDto;;
    
    /**
     * 债券代码
     */
    @PrimaryKey(value=Field.PK_1)
    @NotEmpty(fieldName="ValBondassetquery002Dto.bondid")
    private String bondid;
    /**
     * 起息日
     */
    private String vdate1; 
    /**
     * 到期日
     */
    private String mdate1;
    /**
     * 永续
     */ 
    private String issus;

    /**
     * 含权债摊销日设置
     */
    private String exeDate;

    /**
     * 息票品种
     */
    private String couponType;
    /**
     * 债券分类
     */
    private String bondtype;
    /**
     * 债券分类code
     */
    private String bondtypeCode;
    /**
     * 当前利率
     */
    private BigDecimal rate;
    //private String rate;
    /**
     * 计息基础   
     */
    private String basis;
    /**
     * 发行价
     */
    private BigDecimal price;
    /**
     * 发行人
     */
    private String issuer;
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
//    /**
//     * 到期赎回价
//     * 到期赎回价字段删除  by huangjian 20190103
//     */
//    private BigDecimal dqshj;
    
//    /**
//     * 营业日准则
//     */
//    private String yyrzz;
    
    /**
     * 截止日营业日准则
     */
    private String endTdayRule;

    /**
     * 支付日营业日准则
     */
    private String payTdayRule;
    
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
     * 息票品种
     */
    private String couponTypeCode;
    /**
     * 行权状态
     */
    private String xqstate;
    /**
     * 行权申请开始日
     */
    private String applydatestr;
    /**
     * 行权申请结束日
     */
    private String applydateend;
    /**
     * 行权方式
     */
    private String xqmode;
    /**
     * 行权日
     */
    private String exerciseDate;
    
    /**
     * 获取 exerciseDate
     * @return exerciseDate.
     */
    public String getExerciseDate() {
        return exerciseDate;
    }

    
    /**
     * 设置 exerciseDate
     * @param exerciseDate exerciseDate
     */
    public void setExerciseDate(String exerciseDate) {
        this.exerciseDate = exerciseDate;
    }


    /**
     * 行权净价
     */
    private BigDecimal xqnetprice;
    /**
     * 行权净价
     */
    private String xqclause;
    /**
     * 生效日
     */
    private String efdate;
    /**
     * 利率
     */
    private BigDecimal rate2;
    /**
     * 理论开始日
     */
    private String theorydatestr;
    /**
     * 理论结束日
     */
    private String theorydateend;
    /**
     * 实际计息开始日
     */
    private String actdatestr;
    /**
     * 实际计息结束日
     */
    private String actdateend;
    /**
     * 计划支付日
     */
    private String plandate;
    
    /**
     * 计划支付利息
     */
    private BigDecimal planintamt;
    /**
     * 计息开始日
     */
    private String jsdatestr;
    /**
     * 计息截止日
     */
    private String jsdateend;
    /**
     * 支付日
     */
    private String paydate;
    /**
     * 还本面额/百元
     */
    private BigDecimal remoney;
    /**
     * 区间利率
     */
    private String qjrate;
    /**
     * 计息本金
     */
    private BigDecimal jxamt;
    /**
     * 应计利息/百元
     */
    private BigDecimal yjint;
    /**
     * 现金流
     */
    private BigDecimal cashflow;
    
    private String yesOrNo;
    
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
     * 获取 bondid
     * @return bondid.
     */
    public String getBondid() {
        return bondid;
    }
    
    /**
     * 设置 bondid
     * @param bondid bondid
     */
    public void setBondid(String bondid) {
        this.bondid = bondid;
    }
    
    /**
     * 获取 couponType
     * @return couponType.
     */
    public String getCouponType() {
        return couponType;
    }
    
    /**
     * 获取 息票品种转换数据字典
     * 
     * @return 息票品种转换数据字典
     */
    public String getcouponTypeName() {
//        return SysDataUtil.getNameFromDataDict(DictCode.息票品种, couponType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.息票品种, this.getCouponType()))?
        		SysDataUtil.getNameFromDataDict(DictCode.息票品种, this.getCouponType()):this.getCouponType();
    }

    /**
     * 设置 couponType
     * @param couponType couponType
     */
    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
    
    
    /**
     * 获取 bondtype
     * @return bondtype.
     */
    public String getBondtype() {
        return this.bondtype;
    }
    
    /**
     * 设置 bondtype
     * @param bondtype bondtype
     */
    public void setBondtype(String bondtype) {
        this.bondtype = bondtype;
    }
    
    
    
     
    
    /**
     * 获取 bondtypeCode
     * @return bondtypeCode.
     */
    public String getBondtypeCode() {
        return bondtypeCode;
    }

    
    /**
     * 设置 bondtypeCode
     * @param bondtypeCode bondtypeCode
     */
    public void setBondtypeCode(String bondtypeCode) {
        this.bondtypeCode = bondtypeCode;
    }

    
    /**
     * 获取 rate
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }

    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
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
     * 获取 issus
     * @return issus.
     */
    public String getIssus() {
        return issus;
    }


    
    /**
     * 设置 issus
     * @param issus issus
     */
    public void setIssus(String issus) {
        this.issus = issus;
    }


    
    /**
     * 获取 issuer
     * @return issuer.
     */
    public String getIssuer() {
        return issuer;
    }


    
    /**
     * 设置 issuer
     * @param issuer issuer
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * 获取 exeDate
     * @return exeDate.
     */
    public String getExeDate() {
        return exeDate;
    }


    
    /**
     * 设置 exeDate
     * @param exeDate exeDate
     */
    public void setExeDate(String exeDate) {
        this.exeDate = exeDate;
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
//    /**
//     * 获取 dqshj
//     * @return dqshj.
//     */
//    public BigDecimal getDqshj() {
//        return dqshj;
//    }
//
//    到期赎回价字段删除  by huangjian 20190103
    
//    /**
//     * 设置 dqshj
//     * @param dqshj dqshj
//     */
//    public void setDqshj(BigDecimal dqshj) {
//        this.dqshj = dqshj;
//    }

    
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

    
    public String getBasis() {
        return basis;
    }

    
    public void setBasis(String basis) {
        this.basis = basis;
    }

    
    /**
     * 获取 blnAreaName
     * @return blnAreaName.
     */
    public String getBlnAreaName() {
//        return SysDataUtil.getNameFromDataDict(DictCode.境内外, this.getBlnArea());
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.境内外, this.getBlnArea()))?
        		SysDataUtil.getNameFromDataDict(DictCode.境内外, this.getBlnArea()):this.getBlnArea();
    }
    
    /**
     * 获取 tradingplaceName
     * @return tradingplaceName.
     */
    public String getTradingplaceName() {
//        return SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, this.getTradingplace());
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, this.getTradingplace()))?
        		SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, this.getTradingplace()):this.getTradingplace();
    }
    
    /**
     * 获取 couponTypeCode
     * @return couponTypeCode.
     */
    public String getCouponTypeCode() {
        return couponTypeCode;
    }

    
    /**
     * 设置 couponTypeCode
     * @param couponTypeCode couponTypeCode
     */
    public void setCouponTypeCode(String couponTypeCode) {
        this.couponTypeCode = couponTypeCode;
    }

    
    /**
     * 获取 xqstate
     * @return xqstate.
     */
    public String getXqstate() {
        return xqstate;
    }

    
    /**
     * 设置 xqstate
     * @param xqstate xqstate
     */
    public void setXqstate(String xqstate) {
        this.xqstate = xqstate;
    }

    
    /**
     * 获取 applydatestr
     * @return applydatestr.
     */
    public String getApplydatestr() {
        return applydatestr;
    }

    
    /**
     * 设置 applydatestr
     * @param applydatestr applydatestr
     */
    public void setApplydatestr(String applydatestr) {
        this.applydatestr = applydatestr;
    }

    
    /**
     * 获取 applydateend
     * @return applydateend.
     */
    public String getApplydateend() {
        return applydateend;
    }

    
    /**
     * 设置 applydateend
     * @param applydateend applydateend
     */
    public void setApplydateend(String applydateend) {
        this.applydateend = applydateend;
    }

    
    /**
     * 获取 xqmode
     * @return xqmode.
     */
    public String getXqmode() {
        return xqmode;
    }

    
    /**
     * 设置 xqmode
     * @param xqmode xqmode
     */
    public void setXqmode(String xqmode) {
        this.xqmode = xqmode;
    }

    
    /**
     * 获取 xqnetprice
     * @return xqnetprice.
     */
    public BigDecimal getXqnetprice() {
        return xqnetprice;
    }

    
    /**
     * 设置 xqnetprice
     * @param xqnetprice xqnetprice
     */
    public void setXqnetprice(BigDecimal xqnetprice) {
        this.xqnetprice = xqnetprice;
    }

    
    /**
     * 获取 xqclause
     * @return xqclause.
     */
    public String getXqclause() {
        return xqclause;
    }

    
    /**
     * 设置 xqclause
     * @param xqclause xqclause
     */
    public void setXqclause(String xqclause) {
        this.xqclause = xqclause;
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
     * 获取 theorydatestr
     * @return theorydatestr.
     */
    public String getTheorydatestr() {
        return theorydatestr;
    }

    
    /**
     * 设置 theorydatestr
     * @param theorydatestr theorydatestr
     */
    public void setTheorydatestr(String theorydatestr) {
        this.theorydatestr = theorydatestr;
    }

    
    /**
     * 获取 theorydateend
     * @return theorydateend.
     */
    public String getTheorydateend() {
        return theorydateend;
    }

    
    /**
     * 设置 theorydateend
     * @param theorydateend theorydateend
     */
    public void setTheorydateend(String theorydateend) {
        this.theorydateend = theorydateend;
    }

    
    /**
     * 获取 actdatestr
     * @return actdatestr.
     */
    public String getActdatestr() {
        return actdatestr;
    }

    
    /**
     * 设置 actdatestr
     * @param actdatestr actdatestr
     */
    public void setActdatestr(String actdatestr) {
        this.actdatestr = actdatestr;
    }

    
    /**
     * 获取 actdateend
     * @return actdateend.
     */
    public String getActdateend() {
        return actdateend;
    }

    
    /**
     * 设置 actdateend
     * @param actdateend actdateend
     */
    public void setActdateend(String actdateend) {
        this.actdateend = actdateend;
    }

    
    /**
     * 获取 plandate
     * @return plandate.
     */
    public String getPlandate() {
        return plandate;
    }

    
    /**
     * 设置 plandate
     * @param plandate plandate
     */
    public void setPlandate(String plandate) {
        this.plandate = plandate;
    }
    
    /**
     * 获取 planintamt
     * @return planintamt.
     */
    public BigDecimal getPlanintamt() {
        return planintamt;
    }
    
    /**
     * 设置 planintamt
     * @param planintamt planintamt
     */
    public void setPlanintamt(BigDecimal planintamt) {
        this.planintamt = planintamt;
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
     * 获取 remoney
     * @return remoney.
     */
    public BigDecimal getRemoney() {
        return remoney;
    }

    
    /**
     * 设置 remoney
     * @param remoney remoney
     */
    public void setRemoney(BigDecimal remoney) {
        this.remoney = remoney;
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
     * 获取 qjrate
     * @return qjrate.
     */
    public String getQjrate() {
        return qjrate;
    }

    
    /**
     * 设置 qjrate
     * @param qjrate qjrate
     */
    public void setQjrate(String qjrate) {
        this.qjrate = qjrate;
    }

    
    /**
     * 获取 jxamt
     * @return jxamt.
     */
    public BigDecimal getJxamt() {
        return jxamt;
    }

    
    /**
     * 设置 jxamt
     * @param jxamt jxamt
     */
    public void setJxamt(BigDecimal jxamt) {
        this.jxamt = jxamt;
    }

    
    /**
     * 获取 yjint
     * @return yjint.
     */
    public BigDecimal getYjint() {
        return yjint;
    }

    
    /**
     * 设置 yjint
     * @param yjint yjint
     */
    public void setYjint(BigDecimal yjint) {
        this.yjint = yjint;
    }

    
    /**
     * 获取 cashflow
     * @return cashflow.
     */
    public BigDecimal getCashflow() {
        return cashflow;
    }

    
    /**
     * 设置 cashflow
     * @param cashflow cashflow
     */
    public void setCashflow(BigDecimal cashflow) {
        this.cashflow = cashflow;
    }
    
    /**
     * 获取 componentDto
     * @return componentDto.
     */
    public ValBondassdetComponentDto getComponentDto() {
        if (componentDto == null) {
            componentDto = new ValBondassdetComponentDto();
        }
        return componentDto;
    }
    
    /**
     * 设置 componentDto
     * @param componentDto componentDto
     */
    public void setComponentDto(ValBondassdetComponentDto componentDto) {
        this.componentDto = componentDto;
    }


    
    /**
     * 获取 yesOrNo
     * @return yesOrNo.
     */
    public String getYesOrNo() {
        return yesOrNo;
    }


    
    /**
     * 设置 yesOrNo
     * @param yesOrNo yesOrNo
     */
    public void setYesOrNo(String yesOrNo) {
        this.yesOrNo = yesOrNo;
    }
}
