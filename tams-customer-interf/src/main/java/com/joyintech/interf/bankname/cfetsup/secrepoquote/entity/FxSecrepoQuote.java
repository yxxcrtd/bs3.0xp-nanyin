package com.joyintech.interf.bankname.cfetsup.secrepoquote.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.interf.bankname.cfetsup.outbound.Table;
import com.joyintech.interf.bankname.cfetsup.outbound.dto.ICfetsDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:FX_SECREPO_QUOTE表的实体类
 * @version
 * @author:  zcf
 * @创建时间: 2020-01-08
 */
@Table("FX_SECREPO_QUOTE")
public class FxSecrepoQuote extends BaseEntity  implements ICfetsDto {
    /**
     * 流水号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String seqno;

    /**
     * 源流水号
     */
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
     * 交易方向 RS:正回购 RB:逆回购
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
    private Date transactime;

    /**
     * 报价有效时间
     */
    private Date effecttime;

    /**
     * 有效状态 A:新建 E:有效 D:删除
     */
    private String effectflag;

    /**
     * 发送状态 0:成功 1:失败 2：再次发送成功
     */
    private String sendstatus;

    /**
     * 交易状态 0:待回复 1:已成交 2:已过期；3:已拒绝
     */
    private String tradestatus;

    /**
     * 券面总额合计
     */
    private BigDecimal prinamt;

    /**
     * 应计利息
     */
    private BigDecimal accuir;

    /**
     * 应计利息总额
     */
    private BigDecimal accuiramt;

    /**
     * 首次结算金额
     */
    private BigDecimal vsettleamt;

    /**
     * 到期结算金额
     */
    private BigDecimal msettleamt;

    /**
     * 交易金额
     */
    private BigDecimal dealamt;

    /**
     * 交易品种
     */
    private String dealvar;

    /**
     * 回购期限
     */
    private BigDecimal term;

    /**
     * 实际占款天数
     */
    private BigDecimal actpaydays;

    /**
     * 回购利率(%)
     */
    private BigDecimal rate;

    /**
     * 本方角色 0:询价方 1:确认方
     */
    private String homerole;

    /**
     * 本方CSTP会员ID
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
     * 本币交易成员
     */
    private String jycyid;

    /**
     * 交易对手CSTP会员ID
     */
    private String countercstpid;

    /**
     * 对手方交易员
     */
    private String countertrader;

    /**
     * 清算类型 码表SETTLETYPE
     */
    private String settletype;

    /**
     * 清算速度,码表SETTLESPEED_JYS
     */
    private String settlespeed;

    /**
     * 首次结算方式 码表BUYCLEARINGFORM
     */
    private String vsettlemode;

    /**
     * 到期结算方式 码表BUYCLEARINGFORM
     */
    private String msettlemode;

    /**
     * 首次结算日
     */
    private Date vsettledate;

    /**
     * 到期结算日
     */
    private Date msettledate;

    /**
     * 结算币种 CNY/USD
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
     * 交易场所 码表WTJYCS
     */
    private String jycs;

    /**
     * 账户类型
     */
    private String zhlx;

    /**
     * 交易账户
     */
    private String capacc;

    /**
     * 计息基础 码表RATEBASIC_REPO 
     */
    private String ratebasic;

    /**
     * 是否手动录单（0否1是）
     */
    private String manual;

    /**
     * 客户参考编号
     */
    private String clordid;

    /**
     * 补充条款
     */
    private Object suppprovision;

    /**
     * 备注
     */
    private Object remark;

    /**
     * 风险审批通知书编号的主体机构
     */
    private String ztjgcode;

    /**
     * 风险审批通知书编号
     */
    private String sptzsbh;

    /**
     * 业务发生时间字符串
     */
    private String transactimestr;

    /**
     * 报价有效时间字符串
     */
    private String effecttimestr;
    /**
     * 发送标记 
     */
    private Integer sendmark;

    /**
     * 成交编号
     */
    private String execid;

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
     * 计算金额
     */
    private BigDecimal jsje;

    /**
     * 下限比例 默认80%
     */
    private BigDecimal xxbl;
    /**
     * 下限比例 默认80%
     */
    private String businesstype;
    /**
     * 汇总券面总额
     */
    private BigDecimal totalamount;

    /**
     * 是否纳入全行统一授信
     */
    private String sfnrqhtysx;

    /**
     * 下柜流水号
     */
    private String xgtradeid;

    /**
     * 原客户参考编号
     */
    private String origclordid;

    /**
     * 中债登交易金额
     */
    private BigDecimal zzdamt;

    /**
     * 上清所交易金额
     */
    private BigDecimal sqsamt;
    /**
     * 是否再次发送
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
     * 交易方向 RS:正回购 RB:逆回购
     * @return PS 交易方向 RS:正回购 RB:逆回购
     */
    public String getPs() {
        return ps;
    }

    /**
     * 交易方向 RS:正回购 RB:逆回购
     * @param ps 交易方向 RS:正回购 RB:逆回购
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
     * @return TRANSACTIME 业务发生时间
     */
    public Date getTransactime() {
        return transactime;
    }

    /**
     * 业务发生时间
     * @param transactime 业务发生时间
     */
    public void setTransactime(Date transactime) {
        this.transactime = transactime;
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
     * 有效状态 A:新建 E:有效 D:删除
     * @return EFFECTFLAG 有效状态 A:新建 E:有效 D:删除
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 有效状态 A:新建 E:有效 D:删除
     * @param effectflag 有效状态 A:新建 E:有效 D:删除
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 发送状态 0:成功 1:失败 2：再次发送成功
     * @return SENDSTATUS 发送状态 0:成功 1:失败 2：再次发送成功
     */
    public String getSendstatus() {
        return sendstatus;
    }

    /**
     * 发送状态 0:成功 1:失败 2：再次发送成功
     * @param sendstatus 发送状态 0:成功 1:失败 2：再次发送成功
     */
    public void setSendstatus(String sendstatus) {
        this.sendstatus = sendstatus == null ? null : sendstatus.trim();
    }

    /**
     * 交易状态 0:待回复 1:已成交 2:已过期；3:已拒绝
     * @return TRADESTATUS 交易状态 0:待回复 1:已成交 2:已过期；3:已拒绝
     */
    public String getTradestatus() {
        return tradestatus;
    }

    /**
     * 交易状态 0:待回复 1:已成交 2:已过期；3:已拒绝
     * @param tradestatus 交易状态 0:待回复 1:已成交 2:已过期；3:已拒绝
     */
    public void setTradestatus(String tradestatus) {
        this.tradestatus = tradestatus == null ? null : tradestatus.trim();
    }

    /**
     * 券面总额合计
     * @return PRINAMT 券面总额合计
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * 券面总额合计
     * @param prinamt 券面总额合计
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
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
     * 首次结算金额
     * @return VSETTLEAMT 首次结算金额
     */
    public BigDecimal getVsettleamt() {
        return vsettleamt;
    }

    /**
     * 首次结算金额
     * @param vsettleamt 首次结算金额
     */
    public void setVsettleamt(BigDecimal vsettleamt) {
        this.vsettleamt = vsettleamt;
    }

    /**
     * 到期结算金额
     * @return MSETTLEAMT 到期结算金额
     */
    public BigDecimal getMsettleamt() {
        return msettleamt;
    }

    /**
     * 到期结算金额
     * @param msettleamt 到期结算金额
     */
    public void setMsettleamt(BigDecimal msettleamt) {
        this.msettleamt = msettleamt;
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
     * 交易品种
     * @return DEALVAR 交易品种
     */
    public String getDealvar() {
        return dealvar;
    }

    /**
     * 交易品种
     * @param dealvar 交易品种
     */
    public void setDealvar(String dealvar) {
        this.dealvar = dealvar == null ? null : dealvar.trim();
    }

    /**
     * 回购期限
     * @return TERM 回购期限
     */
    public BigDecimal getTerm() {
        return term;
    }

    /**
     * 回购期限
     * @param term 回购期限
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 实际占款天数
     * @return ACTPAYDAYS 实际占款天数
     */
    public BigDecimal getActpaydays() {
        return actpaydays;
    }

    /**
     * 实际占款天数
     * @param actpaydays 实际占款天数
     */
    public void setActpaydays(BigDecimal actpaydays) {
        this.actpaydays = actpaydays;
    }

    /**
     * 回购利率(%)
     * @return RATE 回购利率(%)
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 回购利率(%)
     * @param rate 回购利率(%)
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 本方角色 0:询价方 1:确认方
     * @return HOMEROLE 本方角色 0:询价方 1:确认方
     */
    public String getHomerole() {
        return homerole;
    }

    /**
     * 本方角色 0:询价方 1:确认方
     * @param homerole 本方角色 0:询价方 1:确认方
     */
    public void setHomerole(String homerole) {
        this.homerole = homerole == null ? null : homerole.trim();
    }

    /**
     * 本方CSTP会员ID
     * @return HOMECSTPID 本方CSTP会员ID
     */
    public String getHomecstpid() {
        return homecstpid;
    }

    /**
     * 本方CSTP会员ID
     * @param homecstpid 本方CSTP会员ID
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
     * 本币交易成员
     * @return JYCYID 本币交易成员
     */
    public String getJycyid() {
        return jycyid;
    }

    /**
     * 本币交易成员
     * @param jycyid 本币交易成员
     */
    public void setJycyid(String jycyid) {
        this.jycyid = jycyid == null ? null : jycyid.trim();
    }

    /**
     * 交易对手CSTP会员ID
     * @return COUNTERCSTPID 交易对手CSTP会员ID
     */
    public String getCountercstpid() {
        return countercstpid;
    }

    /**
     * 交易对手CSTP会员ID
     * @param countercstpid 交易对手CSTP会员ID
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
     * 清算类型 码表SETTLETYPE
     * @return SETTLETYPE 清算类型 码表SETTLETYPE
     */
    public String getSettletype() {
        return settletype;
    }

    /**
     * 清算类型 码表SETTLETYPE
     * @param settletype 清算类型 码表SETTLETYPE
     */
    public void setSettletype(String settletype) {
        this.settletype = settletype == null ? null : settletype.trim();
    }

    /**
     * 清算速度,码表SETTLESPEED_JYS
     * @return SETTLESPEED 清算速度,码表SETTLESPEED_JYS
     */
    public String getSettlespeed() {
        return settlespeed;
    }

    /**
     * 清算速度,码表SETTLESPEED_JYS
     * @param settlespeed 清算速度,码表SETTLESPEED_JYS
     */
    public void setSettlespeed(String settlespeed) {
        this.settlespeed = settlespeed == null ? null : settlespeed.trim();
    }

    /**
     * 首次结算方式 码表BUYCLEARINGFORM
     * @return VSETTLEMODE 首次结算方式 码表BUYCLEARINGFORM
     */
    public String getVsettlemode() {
        return vsettlemode;
    }

    /**
     * 首次结算方式 码表BUYCLEARINGFORM
     * @param vsettlemode 首次结算方式 码表BUYCLEARINGFORM
     */
    public void setVsettlemode(String vsettlemode) {
        this.vsettlemode = vsettlemode == null ? null : vsettlemode.trim();
    }

    /**
     * 到期结算方式 码表BUYCLEARINGFORM
     * @return MSETTLEMODE 到期结算方式 码表BUYCLEARINGFORM
     */
    public String getMsettlemode() {
        return msettlemode;
    }

    /**
     * 到期结算方式 码表BUYCLEARINGFORM
     * @param msettlemode 到期结算方式 码表BUYCLEARINGFORM
     */
    public void setMsettlemode(String msettlemode) {
        this.msettlemode = msettlemode == null ? null : msettlemode.trim();
    }

    /**
     * 首次结算日
     * @return VSETTLEDATE 首次结算日
     */
    public Date getVsettledate() {
        return vsettledate;
    }

    /**
     * 首次结算日
     * @param vsettledate 首次结算日
     */
    public void setVsettledate(Date vsettledate) {
        this.vsettledate = vsettledate;
    }

    /**
     * 到期结算日
     * @return MSETTLEDATE 到期结算日
     */
    public Date getMsettledate() {
        return msettledate;
    }

    /**
     * 到期结算日
     * @param msettledate 到期结算日
     */
    public void setMsettledate(Date msettledate) {
        this.msettledate = msettledate;
    }

    /**
     * 结算币种 CNY/USD
     * @return CCY 结算币种 CNY/USD
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 结算币种 CNY/USD
     * @param ccy 结算币种 CNY/USD
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
     * 交易场所 码表WTJYCS
     * @return JYCS 交易场所 码表WTJYCS
     */
    public String getJycs() {
        return jycs;
    }

    /**
     * 交易场所 码表WTJYCS
     * @param jycs 交易场所 码表WTJYCS
     */
    public void setJycs(String jycs) {
        this.jycs = jycs == null ? null : jycs.trim();
    }

    /**
     * 账户类型
     * @return ZHLX 账户类型
     */
    public String getZhlx() {
        return zhlx;
    }

    /**
     * 账户类型
     * @param zhlx 账户类型
     */
    public void setZhlx(String zhlx) {
        this.zhlx = zhlx == null ? null : zhlx.trim();
    }

    /**
     * 交易账户
     * @return CAPACC 交易账户
     */
    public String getCapacc() {
        return capacc;
    }

    /**
     * 交易账户
     * @param capacc 交易账户
     */
    public void setCapacc(String capacc) {
        this.capacc = capacc == null ? null : capacc.trim();
    }

    /**
     * 计息基础 码表RATEBASIC_REPO 
     * @return RATEBASIC 计息基础 码表RATEBASIC_REPO 
     */
    public String getRatebasic() {
        return ratebasic;
    }

    /**
     * 计息基础 码表RATEBASIC_REPO 
     * @param ratebasic 计息基础 码表RATEBASIC_REPO 
     */
    public void setRatebasic(String ratebasic) {
        this.ratebasic = ratebasic == null ? null : ratebasic.trim();
    }

    /**
     * 是否手动录单（0否1是）
     * @return MANUAL 是否手动录单（0否1是）
     */
    public String getManual() {
        return manual;
    }

    /**
     * 是否手动录单（0否1是）
     * @param manual 是否手动录单（0否1是）
     */
    public void setManual(String manual) {
        this.manual = manual == null ? null : manual.trim();
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
     * 风险审批通知书编号的主体机构
     * @return ZTJGCODE 风险审批通知书编号的主体机构
     */
    public String getZtjgcode() {
        return ztjgcode;
    }

    /**
     * 风险审批通知书编号的主体机构
     * @param ztjgcode 风险审批通知书编号的主体机构
     */
    public void setZtjgcode(String ztjgcode) {
        this.ztjgcode = ztjgcode == null ? null : ztjgcode.trim();
    }

    /**
     * 风险审批通知书编号
     * @return SPTZSBH 风险审批通知书编号
     */
    public String getSptzsbh() {
        return sptzsbh;
    }

    /**
     * 风险审批通知书编号
     * @param sptzsbh 风险审批通知书编号
     */
    public void setSptzsbh(String sptzsbh) {
        this.sptzsbh = sptzsbh == null ? null : sptzsbh.trim();
    }

    /**
     * 业务发生时间字符串
     * @return TRANSACTIMESTR 业务发生时间字符串
     */
    public String getTransactimestr() {
        return transactimestr;
    }

    /**
     * 业务发生时间字符串
     * @param transactimestr 业务发生时间字符串
     */
    public void setTransactimestr(String transactimestr) {
        this.transactimestr = transactimestr == null ? null : transactimestr.trim();
    }

    /**
     * 报价有效时间字符串
     * @return EFFECTTIMESTR 报价有效时间字符串
     */
    public String getEffecttimestr() {
        return effecttimestr;
    }

    /**
     * 报价有效时间字符串
     * @param effecttimestr 报价有效时间字符串
     */
    public void setEffecttimestr(String effecttimestr) {
        this.effecttimestr = effecttimestr == null ? null : effecttimestr.trim();
    }
    @Override
    public Integer getSendmark() {
        return sendmark;
    }

    public void setSendmark(Integer sendmark) {
        this.sendmark = sendmark;
    }

    /**
     * 成交编号
     * @return EXECID 成交编号
     */
    public String getExecid() {
        return execid;
    }

    /**
     * 成交编号
     * @param execid 成交编号
     */
    public void setExecid(String execid) {
        this.execid = execid == null ? null : execid.trim();
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
     * 计算金额
     * @return JSJE 计算金额
     */
    public BigDecimal getJsje() {
        return jsje;
    }

    /**
     * 计算金额
     * @param jsje 计算金额
     */
    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    /**
     * 下限比例 默认80%
     * @return XXBL 下限比例 默认80%
     */
    public BigDecimal getXxbl() {
        return xxbl;
    }

    /**
     * 下限比例 默认80%
     * @param xxbl 下限比例 默认80%
     */
    public void setXxbl(BigDecimal xxbl) {
        this.xxbl = xxbl;
    }

    /**
     * 汇总券面总额
     * @return TOTALAMOUNT 汇总券面总额
     */
    public BigDecimal getTotalamount() {
        return totalamount;
    }

    /**
     * 汇总券面总额
     * @param totalamount 汇总券面总额
     */
    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    /**
     * 是否纳入全行统一授信
     * @return SFNRQHTYSX 是否纳入全行统一授信
     */
    public String getSfnrqhtysx() {
        return sfnrqhtysx;
    }

    /**
     * 是否纳入全行统一授信
     * @param sfnrqhtysx 是否纳入全行统一授信
     */
    public void setSfnrqhtysx(String sfnrqhtysx) {
        this.sfnrqhtysx = sfnrqhtysx == null ? null : sfnrqhtysx.trim();
    }

    /**
     * 下柜流水号
     * @return XGTRADEID 下柜流水号
     */
    public String getXgtradeid() {
        return xgtradeid;
    }

    /**
     * 下柜流水号
     * @param xgtradeid 下柜流水号
     */
    public void setXgtradeid(String xgtradeid) {
        this.xgtradeid = xgtradeid == null ? null : xgtradeid.trim();
    }

    /**
     * 原客户参考编号
     * @return ORIGCLORDID 原客户参考编号
     */
    public String getOrigclordid() {
        return origclordid;
    }

    /**
     * 原客户参考编号
     * @param origclordid 原客户参考编号
     */
    public void setOrigclordid(String origclordid) {
        this.origclordid = origclordid == null ? null : origclordid.trim();
    }

    /**
     * 中债登交易金额
     * @return ZZDAMT 中债登交易金额
     */
    public BigDecimal getZzdamt() {
        return zzdamt;
    }

    /**
     * 中债登交易金额
     * @param zzdamt 中债登交易金额
     */
    public void setZzdamt(BigDecimal zzdamt) {
        this.zzdamt = zzdamt;
    }

    /**
     * 上清所交易金额
     * @return SQSAMT 上清所交易金额
     */
    public BigDecimal getSqsamt() {
        return sqsamt;
    }

    /**
     * 上清所交易金额
     * @param sqsamt 上清所交易金额
     */
    public void setSqsamt(BigDecimal sqsamt) {
        this.sqsamt = sqsamt;
    }

    public String getBusinessType() {
        return businesstype;
    }

    public void setBusinessType(String businessType) {
        this.businesstype = businessType;
    }
    @Override
    public String getIsResend() {
        return isResend;
    }

    public void setIsResend(String isResend) {
        this.isResend = isResend;
    }
}