package com.joyintech.interf.bankname.cfetsup.secrequestquote.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.outbound.Table;
import com.joyintech.interf.bankname.cfetsup.outbound.dto.ICfetsDto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.validator.FxSecRequestQuoteDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Table SA_SEC_REQUEST_QUOTE PK: Field id Column SEQNO
 * 
 * @hibernate.class table="SA_SEC_REQUEST_QUOTE"
 */
@Table("FX_SECREPO_QUOTE")
@PageId("FxSecRequestQuote002")
@ValidLogic(targetClass = FxSecRequestQuoteDtoValidator.class, groups = {ValidateGroupEdit.class,
		ValidateGroupDelete.class})
public class FxSecRequestQuoteDto extends BaseEntity implements Serializable, ProcessEntity , ICfetsDto {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@PrimaryKey(PrimaryKey.Field.PK_1)
	private String seqno;
	/**
	 * 内部报价编号
	 */
	private String nbquoteid;
	/**
	 * 报价编号
	 */
	private String quoteid;
	/**
	 * 委托编号
	 */
	private String trustid;
	/**
	 * 产品代码
	 */
	private String accountid;
	/**
	 * 产品名称
	 */
	private String accountname;

	private String cpbdm;
	/**
	 * 债券代码
	 */
	private String seccode;
	/**
	 * 债券名称
	 */
	private String secname;
	/*
	 * 交易方向 P:买入；S:卖出
	 */
	private String ps;
	/**
	 * 报价日期
	 */
	private Date quotedate;
	/**
	 * 生效时间
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
	 * 执行状态
	 */
	private String tradestatus;
	/**
	 * 券面总额
	 */
	private BigDecimal prinamt;
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
	 * 报价发送方 0:本方；1:对手方
	 */
	private String homerole;
	/**
	 * 本方CSTP会员ID
	 */
	private String homecstpid;
	/**
	 * 本方交易员id
	 */
	private String hometrader;
	/**
	 * 本方交易员名称
	 */
	private String hometradername;
	/**
	 * 交易对手类型
	 */
	private String countertype;
	/**
	 * 交易对手
	 */
	private String counterid;

	/**
	 * 交易对手名称
	 */
	private String countername;
	/**
	 * CSTP会员ID
	 */
	private String countercstpid;
	/**
	 * 本币交易成员
	 */
	private String bbjycy;
	/**
	 * 对手方交易员
	 */
	private String countertrader;
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
	/*
	 * 结算币种
	 */
	private String ccy;
	/**
	 * 汇率
	 */
	private BigDecimal exchangerate;
	/**
	 * 补充条款
	 */
	private String suppprovision;
	/**
	 * 备注
	 */
	private String remark;
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
	private Long procstamp;
	/**
	 * 新建人员
	 */
	private String creatorid;
	/**
	 * 新建时间
	 */
	private Date createdtime;
	/**
	 * 新建时间
	 */
	private Date createdat;
	/**
	 * 交易日
	 */
	private Date dealdate;
	/**
	 * 交易场所
	 */
	private String jycs;
	/**
	 * 最后修改编号
	 */
	private String lastmodifierid;
	/**
	 * 最后修改时间
	 */
	private Date lastmodifiedtime;
	/**
	 * 资产大分类
	 */
	private String nzcdfl;
	/**
	 * 资产小分类
	 */
	private String nzcxfl;
	/**
	 * 资金账户
	 */
	private String zjzh;
	/**
	 * 会计类型
	 */
	private String investtype;
	/**
	 * 行权收益率(%)
	 */
	private BigDecimal exerciseyield;
	/**
	 * 限价类型 0:净价；1:到期收益率；1:行权收益率,来源码表limitType'
	 */
	private String limittype;
	/**
	 * 源流水号
	 */
	private String pseqno;
	/**
	 * 询价次数
	 */
	private Integer quotenum;

	/**
	 * 询价类型 0 :自动询价；1:手动录单
	 */
	private String quotetype;
	/**
	 * 取自 SA_SEC_INVEST_TRUST 表 zlms字段
	 * 指令模式 QXZL：清晰指令，MHZL：模糊指令，来源码表ZLMS
	 */
	private String zlms;
	/**
	 * SAM调用发出接口业务的操作发生时间
	 */
	private String transactime;
	/**
	 * 接口发送错误信息
	 */
	private String errormsg;
	/** *************前台显示的值******************* */
	/**
	 * 委托报价要素 券面总额
	 */
	private BigDecimal prinamtWt;
	/**
	 * 委托报价要素 净价
	 */
	private BigDecimal cleanpriceWt;
	/**
	 * 委托报价要素 到期收益率
	 */
	private BigDecimal matureyieldWt;
	/**
	 * 委托报价要素 行权收益率
	 */
	private BigDecimal exerciseyieldWt;
	/**
	 * 债券类型
	 */
	private String secType;
	/**
	 * 客户参考编号
	 */
	private String clordid;
	/**
	 * businesstype 记录拼写报文是具体调用的业务类型
	 */
	private String businessType;
	/**
	 * 发送标志，记录发送次数
	 */
	private Integer sendmark;
	/**
	 * 委托交易成员id
	 */
	private String jycyid;
	/**
	 * 委托交易成员全称
	 */
	private String jycyqc;
	/**
	 * 委托交易成员简称
	 */
	private String zwjc;
	/**
	 * 交易员id
	 */
	private String jyyid;
	/**
	 * 交易员姓名
	 */
	private String jyyxm;
	/**
	 * 账户类型
	 */
	private String zhlx;
	/**
	 * 本币交易员21位机构代码
	 */
	private String jgdm;
	
	private Date enddate;
	/**
	 * 委托执行状态 执行状态：关联码表 TRUSTEXESTATUS
	 */
	private String usetag;
	/**
	 * 本币本方
	 */
	private String homecstpname;

	/**
	 *
	 * 操作类型
	 */
	private String operateType;
	/**
	 * 交易对手
	 */
	private List<FxSecRequestQuoteCounterDto> counterList;

	private BigDecimal avalibleCash;

	private BigDecimal avaliblePosition;
	/**
	 * 是否再次发送
	 */
	private String isResend;
	/**
	 * 画面元素
	 */
	private String elementId;

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public BigDecimal getAvalibleCash() {
		return avalibleCash;
	}

	public void setAvalibleCash(BigDecimal avalibleCash) {
		this.avalibleCash = avalibleCash;
	}

	public BigDecimal getAvaliblePosition() {
		return avaliblePosition;
	}

	public void setAvaliblePosition(BigDecimal avaliblePosition) {
		this.avaliblePosition = avaliblePosition;
	}

	public List<FxSecRequestQuoteCounterDto> getCounterList() {
		return counterList;
	}

	public void setCounterList(List<FxSecRequestQuoteCounterDto> counterList) {
		this.counterList = counterList;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getNbquoteid() {
		return nbquoteid;
	}

	public void setNbquoteid(String nbquoteid) {
		this.nbquoteid = nbquoteid;
	}

	public String getQuoteid() {
		return quoteid;
	}

	public void setQuoteid(String quoteid) {
		this.quoteid = quoteid;
	}

	public String getTrustid() {
		return trustid;
	}

	public void setTrustid(String trustid) {
		this.trustid = trustid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getCpbdm() {
		return cpbdm;
	}

	public void setCpbdm(String cpbdm) {
		this.cpbdm = cpbdm;
	}

	public String getSeccode() {
		return seccode;
	}

	public void setSeccode(String seccode) {
		this.seccode = seccode;
	}

	public String getSecname() {
		return secname;
	}

	public void setSecname(String secname) {
		this.secname = secname;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public Date getQuotedate() {
		return quotedate;
	}

	public void setQuotedate(Date quotedate) {
		this.quotedate = quotedate;
	}

	public Date getEffecttime() {
		return effecttime;
	}

	public void setEffecttime(Date effecttime) {
		this.effecttime = effecttime;
	}

	public String getEffectflag() {
		return effectflag;
	}

	public void setEffectflag(String effectflag) {
		this.effectflag = effectflag;
	}

	public String getSendstatus() {
		return sendstatus;
	}

	public void setSendstatus(String sendstatus) {
		this.sendstatus = sendstatus;
	}

	public String getTradestatus() {
		return tradestatus;
	}

	public void setTradestatus(String tradestatus) {
		this.tradestatus = tradestatus;
	}

	public BigDecimal getPrinamt() {
		return prinamt;
	}

	public void setPrinamt(BigDecimal prinamt) {
		this.prinamt = prinamt;
	}

	public BigDecimal getCleanprice() {
		return cleanprice;
	}

	public void setCleanprice(BigDecimal cleanprice) {
		this.cleanprice = cleanprice;
	}

	public BigDecimal getCpriceamt() {
		return cpriceamt;
	}

	public void setCpriceamt(BigDecimal cpriceamt) {
		this.cpriceamt = cpriceamt;
	}

	public BigDecimal getDirtyprice() {
		return dirtyprice;
	}

	public void setDirtyprice(BigDecimal dirtyprice) {
		this.dirtyprice = dirtyprice;
	}

	public BigDecimal getDpriceamt() {
		return dpriceamt;
	}

	public void setDpriceamt(BigDecimal dpriceamt) {
		this.dpriceamt = dpriceamt;
	}

	public BigDecimal getAccuir() {
		return accuir;
	}

	public void setAccuir(BigDecimal accuir) {
		this.accuir = accuir;
	}

	public BigDecimal getAccuiramt() {
		return accuiramt;
	}

	public void setAccuiramt(BigDecimal accuiramt) {
		this.accuiramt = accuiramt;
	}

	public BigDecimal getSettleamt() {
		return settleamt;
	}

	public void setSettleamt(BigDecimal settleamt) {
		this.settleamt = settleamt;
	}

	public BigDecimal getDealamt() {
		return dealamt;
	}

	public void setDealamt(BigDecimal dealamt) {
		this.dealamt = dealamt;
	}

	public BigDecimal getHunprincipalamt() {
		return hunprincipalamt;
	}

	public void setHunprincipalamt(BigDecimal hunprincipalamt) {
		this.hunprincipalamt = hunprincipalamt;
	}

	public BigDecimal getPrincipalamt() {
		return principalamt;
	}

	public void setPrincipalamt(BigDecimal principalamt) {
		this.principalamt = principalamt;
	}

	public BigDecimal getMatureyield() {
		return matureyield;
	}

	public void setMatureyield(BigDecimal matureyield) {
		this.matureyield = matureyield;
	}

	public String getHomerole() {
		return homerole;
	}

	public void setHomerole(String homerole) {
		this.homerole = homerole;
	}

	public String getHomecstpid() {
		return homecstpid;
	}

	public void setHomecstpid(String homecstpid) {
		this.homecstpid = homecstpid;
	}

	public String getHometrader() {
		return hometrader;
	}

	public void setHometrader(String hometrader) {
		this.hometrader = hometrader;
	}

	public String getCountertype() {
		return countertype;
	}

	public void setCountertype(String countertype) {
		this.countertype = countertype;
	}

	public String getCounterid() {
		return counterid;
	}

	public void setCounterid(String counterid) {
		this.counterid = counterid;
	}

	public String getCountername() {
		return countername;
	}

	public void setCountername(String countername) {
		this.countername = countername;
	}

	public String getCountercstpid() {
		return countercstpid;
	}

	public void setCountercstpid(String countercstpid) {
		this.countercstpid = countercstpid;
	}

	public String getBbjycy() {
		return bbjycy;
	}

	public void setBbjycy(String bbjycy) {
		this.bbjycy = bbjycy;
	}

	public String getCountertrader() {
		return countertrader;
	}

	public void setCountertrader(String countertrader) {
		this.countertrader = countertrader;
	}

	public String getSettletype() {
		return settletype;
	}

	public void setSettletype(String settletype) {
		this.settletype = settletype;
	}

	public String getSettlespeed() {
		return settlespeed;
	}

	public void setSettlespeed(String settlespeed) {
		this.settlespeed = settlespeed;
	}

	public String getSettlemode() {
		return settlemode;
	}

	public void setSettlemode(String settlemode) {
		this.settlemode = settlemode;
	}

	public Date getSettledate() {
		return settledate;
	}

	public void setSettledate(Date settledate) {
		this.settledate = settledate;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getExchangerate() {
		return exchangerate;
	}

	public void setExchangerate(BigDecimal exchangerate) {
		this.exchangerate = exchangerate;
	}

	public String getSuppprovision() {
		return suppprovision;
	}

	public void setSuppprovision(String suppprovision) {
		this.suppprovision = suppprovision;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPreprocinstid() {
		return preprocinstid;
	}

	public void setPreprocinstid(String preprocinstid) {
		this.preprocinstid = preprocinstid;
	}

	public String getProcinstid() {
		return procinstid;
	}

	public void setProcinstid(String procinstid) {
		this.procinstid = procinstid;
	}

	public String getProcstatus() {
		return procstatus;
	}

	public void setProcstatus(String procstatus) {
		this.procstatus = procstatus;
	}

	public Long getProcstamp() {
		return procstamp;
	}

	public void setProcstamp(Long procstamp) {
		this.procstamp = procstamp;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	public Date getDealdate() {
		return dealdate;
	}

	public void setDealdate(Date dealdate) {
		this.dealdate = dealdate;
	}

	public String getJycs() {
		return jycs;
	}

	public void setJycs(String jycs) {
		this.jycs = jycs;
	}

	public String getLastmodifierid() {
		return lastmodifierid;
	}

	public void setLastmodifierid(String lastmodifierid) {
		this.lastmodifierid = lastmodifierid;
	}

	public Date getLastmodifiedtime() {
		return lastmodifiedtime;
	}

	public void setLastmodifiedtime(Date lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}

	public String getNzcdfl() {
		return nzcdfl;
	}

	public void setNzcdfl(String nzcdfl) {
		this.nzcdfl = nzcdfl;
	}

	public String getNzcxfl() {
		return nzcxfl;
	}

	public void setNzcxfl(String nzcxfl) {
		this.nzcxfl = nzcxfl;
	}

	public String getZjzh() {
		return zjzh;
	}

	public void setZjzh(String zjzh) {
		this.zjzh = zjzh;
	}

	public String getInvesttype() {
		return investtype;
	}

	public void setInvesttype(String investtype) {
		this.investtype = investtype;
	}

	public BigDecimal getExerciseyield() {
		return exerciseyield;
	}

	public void setExerciseyield(BigDecimal exerciseyield) {
		this.exerciseyield = exerciseyield;
	}

	public String getLimittype() {
		return limittype;
	}

	public void setLimittype(String limittype) {
		this.limittype = limittype;
	}

	public String getPseqno() {
		return pseqno;
	}

	public void setPseqno(String pseqno) {
		this.pseqno = pseqno;
	}

	public Integer getQuotenum() {
		return quotenum;
	}

	public void setQuotenum(Integer quotenum) {
		this.quotenum = quotenum;
	}

	public String getQuotetype() {
		return quotetype;
	}

	public void setQuotetype(String quotetype) {
		this.quotetype = quotetype;
	}

	public String getZlms() {
		return zlms;
	}

	public void setZlms(String zlms) {
		this.zlms = zlms;
	}

	public String getTransactime() {
		return transactime;
	}

	public void setTransactime(String transactime) {
		this.transactime = transactime;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public BigDecimal getPrinamtWt() {
		return prinamtWt;
	}

	public void setPrinamtWt(BigDecimal prinamtWt) {
		this.prinamtWt = prinamtWt;
	}

	public BigDecimal getCleanpriceWt() {
		return cleanpriceWt;
	}

	public void setCleanpriceWt(BigDecimal cleanpriceWt) {
		this.cleanpriceWt = cleanpriceWt;
	}

	public BigDecimal getMatureyieldWt() {
		return matureyieldWt;
	}

	public void setMatureyieldWt(BigDecimal matureyieldWt) {
		this.matureyieldWt = matureyieldWt;
	}

	public BigDecimal getExerciseyieldWt() {
		return exerciseyieldWt;
	}

	public void setExerciseyieldWt(BigDecimal exerciseyieldWt) {
		this.exerciseyieldWt = exerciseyieldWt;
	}

	public String getSecType() {
		return secType;
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

	public String getClordid() {
		return clordid;
	}

	public void setClordid(String clordid) {
		this.clordid = clordid;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getSendmark() {
		return sendmark;
	}

	public void setSendmark(Integer sendmark) {
		this.sendmark = sendmark;
	}

	public String getJycyid() {
		return jycyid;
	}

	public void setJycyid(String jycyid) {
		this.jycyid = jycyid;
	}

	public String getJycyqc() {
		return jycyqc;
	}

	public void setJycyqc(String jycyqc) {
		this.jycyqc = jycyqc;
	}

	public String getZwjc() {
		return zwjc;
	}

	public void setZwjc(String zwjc) {
		this.zwjc = zwjc;
	}

	public String getJyyid() {
		return jyyid;
	}

	public void setJyyid(String jyyid) {
		this.jyyid = jyyid;
	}

	public String getJyyxm() {
		return jyyxm;
	}

	public void setJyyxm(String jyyxm) {
		this.jyyxm = jyyxm;
	}

	public String getZhlx() {
		return zhlx;
	}

	public void setZhlx(String zhlx) {
		this.zhlx = zhlx;
	}

	public String getJgdm() {
		return jgdm;
	}

	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getUsetag() {
		return usetag;
	}

	public void setUsetag(String usetag) {
		this.usetag = usetag;
	}

	public String getHomecstpname() {
		return homecstpname;
	}

	public void setHomecstpname(String homecstpname) {
		this.homecstpname = homecstpname;
	}

	public String getHometradername() {
		return hometradername;
	}

	public void setHometradername(String hometradername) {
		this.hometradername = hometradername;
	}

	@Override
	public String getIsResend() {
		return isResend;
	}

	public void setIsResend(String isResend) {
		this.isResend = isResend;
	}
}