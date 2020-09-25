package com.joyintech.interf.bankname.cfetsup.secdialogquote.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.outbound.Table;
import com.joyintech.interf.bankname.cfetsup.outbound.dto.ICfetsDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.math.BigDecimal;
import java.util.Date;
@Table("FX_SEC_DIALOG_QUOTE")
public class FxSecdialogQuote extends BaseEntity implements ICfetsDto {

    /**
     * 流水号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String seqno;

    /**源流水号*/
    private String pseqno;

    /**
     * 报价编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    private String quoteid;

    /**
     * 内部报价编号
     */
    private String nbquoteid;

    /**
     * 委托编号
     */
    private String trustid;

    /**
     * 产品代码
     */
    private String accountid;

    /**
     * 债券代码
     */
    private String seccode;

    /**
     * 交易方向 P:买入；S:卖出
     */
    private String ps;

    /**
     * 报价日期
     */
    private Date quotedate;

    /**
     * 交易日期
     */
    private Date dealdate;

    /**
     * 业务发生时间
     */
    private String transacttime;

    /**
     * 报价有效时间
     */
    private Date effecttime;

    /**
     * 有效状态
     */
    private String effectflag;

    /**
     * 发送状态
     */
    private String sendstatus;

    /**
     * 交易状态
     */
    private String tradestatus;

    /**
     * 券面总额
     */
    @NotEmpty(fieldName = "FxSecdialogQuote002.prinamt", groups = {ValidateGroupEdit.class})
    private BigDecimal prinamt;

    /**
     * 限价类型 0:净价；1:到期收益率；2:行权收益率,来源码表limitType
     */
    private String limittype;

    /**
     * 净价
     */
    private BigDecimal cleanprice;

    /**
     * 净价总额
     */
    private BigDecimal cpriceamt;

    /**
     * 全价
     */
    private BigDecimal dirtyprice;

    /**
     * 全价总额
     */
    private BigDecimal dpriceamt;

    /**
     * 应计利息
     */
    private BigDecimal accuir;

    /**
     * 应计利息总额
     */
    private BigDecimal accuiramt;

    /**
     * 结算金额
     */
    private BigDecimal settleamt;

    /**
     * 交易金额
     */
    private BigDecimal dealamt;

    /**
     * 每百元本金额
     */
    private BigDecimal hunprincipalamt;

    /**
     * 本金额
     */
    private BigDecimal principalamt;

    /**
     * 到期收益率(%)
     */
    private BigDecimal matureyield;

    /**
     * 行权收益率(%)
     */
    private BigDecimal exerciseyield;

    /**
     * 报价发送方 0:本方；1:对手方
     */
    private String homerole;

    /**
     * 本方21位机构码
     */
    private String homecstpid;

    /**
     * 本方交易员
     */
    private String hometrader;

    /**
     * 交易对手类型
     */
    private String countertype;

    /**
     * 交易对手
     */
    private String counterid;

    /**
     * 对手方本币交易成员ID
     */
    private String jycyid;

    /**
     * 对手方本币21位位机构码
     */
    private String countercstpid;

    /**
     * 对手方交易员
     */
    private String countertrader;

    /**
     * 询价类型 0 :自动询价；1:手动录单
     */
    private String quotetype;

    /**
     * 账户类型 0：本级账户，1：SPV账户，2：兴业银行资管，来源码表WTZHLX
     */
    private String zhlx;

    /**
     * 资金账户,来源FX_FX_jyzh表
     */
    private String zjzh;

    /**
     * 清算类型
     */
    private String settletype;

    /**
     * 清算速度:清算速度,来源码表SETTLESPEED_JYS
     */
    private String settlespeed;

    /**
     * 结算方式
     */
    private String settlemode;

    /**
     * 结算日
     */
    private Date settledate;

    /**
     * 结算币种
     */
    private String ccy;

    /**
     * 汇率
     */
    private BigDecimal exchangerate;

    /**
     * 询价次数
     */
    private BigDecimal quotenum;

    /**
     * 补充条款
     */
    private Object suppprovision;

    /**
     * 备注
     */
    private Object remark;

    /**
     * 客户参考编号
     */
    private String clordid;

    /**
     * 原客户参考编号
     */
    private String pclordid;

    /**
     * 接口业务类型
     */
    private String businesstype;

    /**
     * 发送标记
     */
    private Integer sendmark;

    /**
     * 前流程编号
     */
    private String preprocinstid;

    /**
     * 流程编号
     */
    private String procinstid;

    /**
     * 流程状态
     */
    private String procstatus;

    /**
     * 流程戳记
     */
    private BigDecimal procstamp;

    /**
     * 新建人员
     */
    private String creatorid;

    /**
     * 新建时间
     */
    private Date createdtime;

    /**
     * 修改人员
     */
    private String lastmodifierid;

    /**
     * 修改时间
     */
    private Date lastmodifiedtime;

    /**
     * 接口错误描述
     */
    private String errormsg;

    /**
     * 债券简称
     */
    private String accountname;


    /**
     * 债券币种
     */
    private String seccodeccy;

    /**
     * null
     */
    private String homeid;

    /**
     * null
     */
    private String secname;

    /**
     * null
     */
    private BigDecimal avaliblecash;

    /**
     * null
     */
    private BigDecimal avalibleholdposition;

    /**
     * null
     */
    private BigDecimal tradeamt;
    /**
     * null
     */
    private String isResend;

    /**
     * 流水号
     * @return SEQNO 流水号
     */
    public String getSeqno() {
        return seqno;
    }


    /**
     * 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 源流水号
     * @return PSEQNO 源流水号
     */
    public String getPseqno() {
        return pseqno;
    }

    /**
     * 源流水号
     * @param pseqno 源流水号
     */
    public void setPseqno(String pseqno) {
        this.pseqno = pseqno == null ? null : pseqno.trim();
    }

    /**
     * 报价编号
     * @return QUOTEID 报价编号
     */
    public String getQuoteid() {
        return quoteid;
    }

    /**
     * 报价编号
     * @param quoteid 报价编号
     */
    public void setQuoteid(String quoteid) {
        this.quoteid = quoteid == null ? null : quoteid.trim();
    }

    /**
     * 内部报价编号
     * @return NBQUOTEID 内部报价编号
     */
    public String getNbquoteid() {
        return nbquoteid;
    }

    /**
     * 内部报价编号
     * @param nbquoteid 内部报价编号
     */
    public void setNbquoteid(String nbquoteid) {
        this.nbquoteid = nbquoteid == null ? null : nbquoteid.trim();
    }

    /**
     * 委托编号
     * @return TRUSTID 委托编号
     */
    public String getTrustid() {
        return trustid;
    }

    /**
     * 委托编号
     * @param trustid 委托编号
     */
    public void setTrustid(String trustid) {
        this.trustid = trustid == null ? null : trustid.trim();
    }

    /**
     * 产品代码
     * @return ACCOUNTID 产品代码
     */
    public String getAccountid() {
        return accountid;
    }

    /**
     * 产品代码
     * @param accountid 产品代码
     */
    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    /**
     * 债券代码
     * @return SECCODE 债券代码
     */
    public String getSeccode() {
        return seccode;
    }

    /**
     * 债券代码
     * @param seccode 债券代码
     */
    public void setSeccode(String seccode) {
        this.seccode = seccode == null ? null : seccode.trim();
    }

    /**
     * 交易方向 P:买入；S:卖出
     * @return PS 交易方向 P:买入；S:卖出
     */
    public String getPs() {
        return ps;
    }

    /**
     * 交易方向 P:买入；S:卖出
     * @param ps 交易方向 P:买入；S:卖出
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 报价日期
     * @return QUOTEDATE 报价日期
     */
    public Date getQuotedate() {
        return quotedate;
    }

    /**
     * 报价日期
     * @param quotedate 报价日期
     */
    public void setQuotedate(Date quotedate) {
        this.quotedate = quotedate;
    }

    /**
     * 交易日期
     * @return DEALDATE 交易日期
     */
    public Date getDealdate() {
        return dealdate;
    }

    /**
     * 交易日期
     * @param dealdate 交易日期
     */
    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate;
    }

    /**
     * 业务发生时间
     * @return TRANSACTTIME 业务发生时间
     */
    public String getTransacttime() {
        return transacttime;
    }

    /**
     * 业务发生时间
     * @param transacttime 业务发生时间
     */
    public void setTransacttime(String transacttime) {
        this.transacttime = transacttime == null ? null : transacttime.trim();
    }

    /**
     * 报价有效时间
     * @return EFFECTTIME 报价有效时间
     */
    public Date getEffecttime() {
        return effecttime;
    }

    /**
     * 报价有效时间
     * @param effecttime 报价有效时间
     */
    public void setEffecttime(Date effecttime) {
        this.effecttime = effecttime;
    }

    /**
     * 有效状态
     * @return EFFECTFLAG 有效状态
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 有效状态
     * @param effectflag 有效状态
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 发送状态
     * @return SENDSTATUS 发送状态
     */
    public String getSendstatus() {
        return sendstatus;
    }

    /**
     * 发送状态
     * @param sendstatus 发送状态
     */
    public void setSendstatus(String sendstatus) {
        this.sendstatus = sendstatus == null ? null : sendstatus.trim();
    }

    /**
     * 交易状态
     * @return TRADESTATUS 交易状态
     */
    public String getTradestatus() {
        return tradestatus;
    }

    /**
     * 交易状态
     * @param tradestatus 交易状态
     */
    public void setTradestatus(String tradestatus) {
        this.tradestatus = tradestatus == null ? null : tradestatus.trim();
    }

    /**
     * 券面总额
     * @return PRINAMT 券面总额
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * 券面总额
     * @param prinamt 券面总额
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    /**
     * 限价类型 0:净价；1:到期收益率；2:行权收益率,来源码表limitType
     * @return LIMITTYPE 限价类型 0:净价；1:到期收益率；2:行权收益率,来源码表limitType
     */
    public String getLimittype() {
        return limittype;
    }

    /**
     * 限价类型 0:净价；1:到期收益率；2:行权收益率,来源码表limitType
     * @param limittype 限价类型 0:净价；1:到期收益率；2:行权收益率,来源码表limitType
     */
    public void setLimittype(String limittype) {
        this.limittype = limittype == null ? null : limittype.trim();
    }

    /**
     * 净价
     * @return CLEANPRICE 净价
     */
    public BigDecimal getCleanprice() {
        return cleanprice;
    }

    /**
     * 净价
     * @param cleanprice 净价
     */
    public void setCleanprice(BigDecimal cleanprice) {
        this.cleanprice = cleanprice;
    }

    /**
     * 净价总额
     * @return CPRICEAMT 净价总额
     */
    public BigDecimal getCpriceamt() {
        return cpriceamt;
    }

    /**
     * 净价总额
     * @param cpriceamt 净价总额
     */
    public void setCpriceamt(BigDecimal cpriceamt) {
        this.cpriceamt = cpriceamt;
    }

    /**
     * 全价
     * @return DIRTYPRICE 全价
     */
    public BigDecimal getDirtyprice() {
        return dirtyprice;
    }

    /**
     * 全价
     * @param dirtyprice 全价
     */
    public void setDirtyprice(BigDecimal dirtyprice) {
        this.dirtyprice = dirtyprice;
    }

    /**
     * 全价总额
     * @return DPRICEAMT 全价总额
     */
    public BigDecimal getDpriceamt() {
        return dpriceamt;
    }

    /**
     * 全价总额
     * @param dpriceamt 全价总额
     */
    public void setDpriceamt(BigDecimal dpriceamt) {
        this.dpriceamt = dpriceamt;
    }

    /**
     * 应计利息
     * @return ACCUIR 应计利息
     */
    public BigDecimal getAccuir() {
        return accuir;
    }

    /**
     * 应计利息
     * @param accuir 应计利息
     */
    public void setAccuir(BigDecimal accuir) {
        this.accuir = accuir;
    }

    /**
     * 应计利息总额
     * @return ACCUIRAMT 应计利息总额
     */
    public BigDecimal getAccuiramt() {
        return accuiramt;
    }

    /**
     * 应计利息总额
     * @param accuiramt 应计利息总额
     */
    public void setAccuiramt(BigDecimal accuiramt) {
        this.accuiramt = accuiramt;
    }

    /**
     * 结算金额
     * @return SETTLEAMT 结算金额
     */
    public BigDecimal getSettleamt() {
        return settleamt;
    }

    /**
     * 结算金额
     * @param settleamt 结算金额
     */
    public void setSettleamt(BigDecimal settleamt) {
        this.settleamt = settleamt;
    }

    /**
     * 交易金额
     * @return DEALAMT 交易金额
     */
    public BigDecimal getDealamt() {
        return dealamt;
    }

    /**
     * 交易金额
     * @param dealamt 交易金额
     */
    public void setDealamt(BigDecimal dealamt) {
        this.dealamt = dealamt;
    }

    /**
     * 每百元本金额
     * @return HUNPRINCIPALAMT 每百元本金额
     */
    public BigDecimal getHunprincipalamt() {
        return hunprincipalamt;
    }

    /**
     * 每百元本金额
     * @param hunprincipalamt 每百元本金额
     */
    public void setHunprincipalamt(BigDecimal hunprincipalamt) {
        this.hunprincipalamt = hunprincipalamt;
    }

    /**
     * 本金额
     * @return PRINCIPALAMT 本金额
     */
    public BigDecimal getPrincipalamt() {
        return principalamt;
    }

    /**
     * 本金额
     * @param principalamt 本金额
     */
    public void setPrincipalamt(BigDecimal principalamt) {
        this.principalamt = principalamt;
    }

    /**
     * 到期收益率(%)
     * @return MATUREYIELD 到期收益率(%)
     */
    public BigDecimal getMatureyield() {
        return matureyield;
    }

    /**
     * 到期收益率(%)
     * @param matureyield 到期收益率(%)
     */
    public void setMatureyield(BigDecimal matureyield) {
        this.matureyield = matureyield;
    }

    /**
     * 行权收益率(%)
     * @return EXERCISEYIELD 行权收益率(%)
     */
    public BigDecimal getExerciseyield() {
        return exerciseyield;
    }

    /**
     * 行权收益率(%)
     * @param exerciseyield 行权收益率(%)
     */
    public void setExerciseyield(BigDecimal exerciseyield) {
        this.exerciseyield = exerciseyield;
    }

    /**
     * 报价发送方 0:本方；1:对手方
     * @return HOMEROLE 报价发送方 0:本方；1:对手方
     */
    public String getHomerole() {
        return homerole;
    }

    /**
     * 报价发送方 0:本方；1:对手方
     * @param homerole 报价发送方 0:本方；1:对手方
     */
    public void setHomerole(String homerole) {
        this.homerole = homerole == null ? null : homerole.trim();
    }

    /**
     * 本方21位机构码
     * @return HOMECSTPID 本方21位机构码
     */
    public String getHomecstpid() {
        return homecstpid;
    }

    /**
     * 本方21位机构码
     * @param homecstpid 本方21位机构码
     */
    public void setHomecstpid(String homecstpid) {
        this.homecstpid = homecstpid == null ? null : homecstpid.trim();
    }

    /**
     * 本方交易员
     * @return HOMETRADER 本方交易员
     */
    public String getHometrader() {
        return hometrader;
    }

    /**
     * 本方交易员
     * @param hometrader 本方交易员
     */
    public void setHometrader(String hometrader) {
        this.hometrader = hometrader == null ? null : hometrader.trim();
    }

    /**
     * 交易对手类型
     * @return COUNTERTYPE 交易对手类型
     */
    public String getCountertype() {
        return countertype;
    }

    /**
     * 交易对手类型
     * @param countertype 交易对手类型
     */
    public void setCountertype(String countertype) {
        this.countertype = countertype == null ? null : countertype.trim();
    }

    /**
     * 交易对手
     * @return COUNTERID 交易对手
     */
    public String getCounterid() {
        return counterid;
    }

    /**
     * 交易对手
     * @param counterid 交易对手
     */
    public void setCounterid(String counterid) {
        this.counterid = counterid == null ? null : counterid.trim();
    }

    /**
     * 对手方本币交易成员ID
     * @return JYCYID 对手方本币交易成员ID
     */
    public String getJycyid() {
        return jycyid;
    }

    /**
     * 对手方本币交易成员ID
     * @param jycyid 对手方本币交易成员ID
     */
    public void setJycyid(String jycyid) {
        this.jycyid = jycyid == null ? null : jycyid.trim();
    }

    /**
     * 对手方本币21位位机构码
     * @return COUNTERCSTPID 对手方本币21位位机构码
     */
    public String getCountercstpid() {
        return countercstpid;
    }

    /**
     * 对手方本币21位位机构码
     * @param countercstpid 对手方本币21位位机构码
     */
    public void setCountercstpid(String countercstpid) {
        this.countercstpid = countercstpid == null ? null : countercstpid.trim();
    }

    /**
     * 对手方交易员
     * @return COUNTERTRADER 对手方交易员
     */
    public String getCountertrader() {
        return countertrader;
    }

    /**
     * 对手方交易员
     * @param countertrader 对手方交易员
     */
    public void setCountertrader(String countertrader) {
        this.countertrader = countertrader == null ? null : countertrader.trim();
    }

    /**
     * 询价类型 0 :自动询价；1:手动录单
     * @return QUOTETYPE 询价类型 0 :自动询价；1:手动录单
     */
    public String getQuotetype() {
        return quotetype;
    }

    /**
     * 询价类型 0 :自动询价；1:手动录单
     * @param quotetype 询价类型 0 :自动询价；1:手动录单
     */
    public void setQuotetype(String quotetype) {
        this.quotetype = quotetype == null ? null : quotetype.trim();
    }

    /**
     * 账户类型 0：本级账户，1：SPV账户，2：兴业银行资管，来源码表WTZHLX
     * @return ZHLX 账户类型 0：本级账户，1：SPV账户，2：兴业银行资管，来源码表WTZHLX
     */
    public String getZhlx() {
        return zhlx;
    }

    /**
     * 账户类型 0：本级账户，1：SPV账户，2：兴业银行资管，来源码表WTZHLX
     * @param zhlx 账户类型 0：本级账户，1：SPV账户，2：兴业银行资管，来源码表WTZHLX
     */
    public void setZhlx(String zhlx) {
        this.zhlx = zhlx == null ? null : zhlx.trim();
    }

    /**
     * 资金账户,来源FX_FX_jyzh表
     * @return ZJZH 资金账户,来源FX_FX_jyzh表
     */
    public String getZjzh() {
        return zjzh;
    }

    /**
     * 资金账户,来源FX_FX_jyzh表
     * @param zjzh 资金账户,来源FX_FX_jyzh表
     */
    public void setZjzh(String zjzh) {
        this.zjzh = zjzh == null ? null : zjzh.trim();
    }

    /**
     * 清算类型
     * @return SETTLETYPE 清算类型
     */
    public String getSettletype() {
        return settletype;
    }

    /**
     * 清算类型
     * @param settletype 清算类型
     */
    public void setSettletype(String settletype) {
        this.settletype = settletype == null ? null : settletype.trim();
    }

    /**
     * 清算速度:清算速度,来源码表SETTLESPEED_JYS
     * @return SETTLESPEED 清算速度:清算速度,来源码表SETTLESPEED_JYS
     */
    public String getSettlespeed() {
        return settlespeed;
    }

    /**
     * 清算速度:清算速度,来源码表SETTLESPEED_JYS
     * @param settlespeed 清算速度:清算速度,来源码表SETTLESPEED_JYS
     */
    public void setSettlespeed(String settlespeed) {
        this.settlespeed = settlespeed == null ? null : settlespeed.trim();
    }

    /**
     * 结算方式
     * @return SETTLEMODE 结算方式
     */
    public String getSettlemode() {
        return settlemode;
    }

    /**
     * 结算方式
     * @param settlemode 结算方式
     */
    public void setSettlemode(String settlemode) {
        this.settlemode = settlemode == null ? null : settlemode.trim();
    }

    /**
     * 结算日
     * @return SETTLEDATE 结算日
     */
    public Date getSettledate() {
        return settledate;
    }

    /**
     * 结算日
     * @param settledate 结算日
     */
    public void setSettledate(Date settledate) {
        this.settledate = settledate;
    }

    /**
     * 结算币种
     * @return CCY 结算币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 结算币种
     * @param ccy 结算币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 汇率
     * @return EXCHANGERATE 汇率
     */
    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    /**
     * 汇率
     * @param exchangerate 汇率
     */
    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    /**
     * 询价次数
     * @return QUOTENUM 询价次数
     */
    public BigDecimal getQuotenum() {
        return quotenum;
    }

    /**
     * 询价次数
     * @param quotenum 询价次数
     */
    public void setQuotenum(BigDecimal quotenum) {
        this.quotenum = quotenum;
    }

    /**
     * 补充条款
     * @return SUPPPROVISION 补充条款
     */
    public Object getSuppprovision() {
        return suppprovision;
    }

    /**
     * 补充条款
     * @param suppprovision 补充条款
     */
    public void setSuppprovision(Object suppprovision) {
        this.suppprovision = suppprovision;
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public Object getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(Object remark) {
        this.remark = remark;
    }

    /**
     * 客户参考编号
     * @return CLORDID 客户参考编号
     */
    public String getClordid() {
        return clordid;
    }

    /**
     * 客户参考编号
     * @param clordid 客户参考编号
     */
    public void setClordid(String clordid) {
        this.clordid = clordid == null ? null : clordid.trim();
    }

    /**
     * 原客户参考编号
     * @return PCLORDID 原客户参考编号
     */
    public String getPclordid() {
        return pclordid;
    }

    /**
     * 原客户参考编号
     * @param pclordid 原客户参考编号
     */
    public void setPclordid(String pclordid) {
        this.pclordid = pclordid == null ? null : pclordid.trim();
    }

    /**
     * 接口业务类型
     * @return BUSINESSTYPE 接口业务类型
     */
    public String getBusinesstype() {
        return businesstype;
    }

    /**
     * 接口业务类型
     * @param businesstype 接口业务类型
     */
    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype == null ? null : businesstype.trim();
    }

    @Override
    public String getBusinessType() {
        return businesstype;
    }

    /**
     * 发送标记
     * @return SENDMARK 发送标记
     */
    public Integer getSendmark() {
        return sendmark;
    }

    /**
     * 发送标记
     * @param sendmark 发送标记
     */
    public void setSendmark(Integer sendmark) {
        this.sendmark = sendmark;
    }

    /**
     * 前流程编号
     * @return PREPROCINSTID 前流程编号
     */
    public String getPreprocinstid() {
        return preprocinstid;
    }

    /**
     * 前流程编号
     * @param preprocinstid 前流程编号
     */
    public void setPreprocinstid(String preprocinstid) {
        this.preprocinstid = preprocinstid == null ? null : preprocinstid.trim();
    }

    /**
     * 流程编号
     * @return PROCINSTID 流程编号
     */
    public String getProcinstid() {
        return procinstid;
    }

    /**
     * 流程编号
     * @param procinstid 流程编号
     */
    public void setProcinstid(String procinstid) {
        this.procinstid = procinstid == null ? null : procinstid.trim();
    }

    /**
     * 流程状态
     * @return PROCSTATUS 流程状态
     */
    public String getProcstatus() {
        return procstatus;
    }

    /**
     * 流程状态
     * @param procstatus 流程状态
     */
    public void setProcstatus(String procstatus) {
        this.procstatus = procstatus == null ? null : procstatus.trim();
    }

    /**
     * 流程戳记
     * @return PROCSTAMP 流程戳记
     */
    public BigDecimal getProcstamp() {
        return procstamp;
    }

    /**
     * 流程戳记
     * @param procstamp 流程戳记
     */
    public void setProcstamp(BigDecimal procstamp) {
        this.procstamp = procstamp;
    }

    /**
     * 新建人员
     * @return CREATORID 新建人员
     */
    public String getCreatorid() {
        return creatorid;
    }

    /**
     * 新建人员
     * @param creatorid 新建人员
     */
    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid == null ? null : creatorid.trim();
    }

    /**
     * 新建时间
     * @return CREATEDTIME 新建时间
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * 新建时间
     * @param createdtime 新建时间
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * 修改人员
     * @return LASTMODIFIERID 修改人员
     */
    public String getLastmodifierid() {
        return lastmodifierid;
    }

    /**
     * 修改人员
     * @param lastmodifierid 修改人员
     */
    public void setLastmodifierid(String lastmodifierid) {
        this.lastmodifierid = lastmodifierid == null ? null : lastmodifierid.trim();
    }

    /**
     * 修改时间
     * @return LASTMODIFIEDTIME 修改时间
     */
    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    /**
     * 修改时间
     * @param lastmodifiedtime 修改时间
     */
    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }

    /**
     * 接口错误描述
     * @return ERRORMSG 接口错误描述
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * 接口错误描述
     * @param errormsg 接口错误描述
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg == null ? null : errormsg.trim();
    }

    /**
     * 债券简称
     * @return ACCOUNTNAME 债券简称
     */
    public String getAccountname() {
        return accountname;
    }

    /**
     * 债券简称
     * @param accountname 债券简称
     */
    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    /**
     * 债券币种
     * @return SECCODECCY 债券币种
     */
    public String getSeccodeccy() {
        return seccodeccy;
    }

    /**
     * 债券币种
     * @param seccodeccy 债券币种
     */
    public void setSeccodeccy(String seccodeccy) {
        this.seccodeccy = seccodeccy == null ? null : seccodeccy.trim();
    }

    /**
     * null
     * @return HOMEID null
     */
    public String getHomeid() {
        return homeid;
    }

    /**
     * null
     * @param homeid null
     */
    public void setHomeid(String homeid) {
        this.homeid = homeid == null ? null : homeid.trim();
    }

    /**
     * null
     * @return SECNAME null
     */
    public String getSecname() {
        return secname;
    }

    /**
     * null
     * @param secname null
     */
    public void setSecname(String secname) {
        this.secname = secname == null ? null : secname.trim();
    }

    /**
     * null
     * @return AVALIBLECASH null
     */
    public BigDecimal getAvaliblecash() {
        return avaliblecash;
    }

    /**
     * null
     * @param avaliblecash null
     */
    public void setAvaliblecash(BigDecimal avaliblecash) {
        this.avaliblecash = avaliblecash;
    }

    /**
     * null
     * @return AVALIBLEHOLDPOSITION null
     */
    public BigDecimal getAvalibleholdposition() {
        return avalibleholdposition;
    }

    /**
     * null
     * @param avalibleholdposition null
     */
    public void setAvalibleholdposition(BigDecimal avalibleholdposition) {
        this.avalibleholdposition = avalibleholdposition;
    }

    /**
     * null
     * @return TRADEAMT null
     */
    public BigDecimal getTradeamt() {
        return tradeamt;
    }

    /**
     * null
     * @param tradeamt null
     */
    public void setTradeamt(BigDecimal tradeamt) {
        this.tradeamt = tradeamt;
    }

    @Override
    public String getIsResend() {
        return isResend;
    }

    public void setIsResend(String isResend) {
        this.isResend = isResend;
    }
}
