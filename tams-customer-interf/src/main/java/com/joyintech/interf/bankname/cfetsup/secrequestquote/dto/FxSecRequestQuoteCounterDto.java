package com.joyintech.interf.bankname.cfetsup.secrequestquote.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import com.joyintech.framework.base.BaseEntity;


public class FxSecRequestQuoteCounterDto extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	/**
	 * 流水号
	 */
	private String seqno;
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
	 * 备注
	 */
	private String remark;
	
	/**
	 * 行权收益率(%)
	 */
	private BigDecimal exerciseyield;
	/*******************原来的字段**************************/
	/**
	 * 报价流水号
	 */
	private String pseqno;
	/**
	 * 报价编号
	 */
	private String quoteid;
    /**
	 * 有效状态 A:新建；E:有效；D:删除
	 */
	private String effectflag;
	/**
     * 交易对手类型（默认外部）
     */
	private String countertype;
	/**
	 * 交易对手（机构代码）
	 */
	private String ztjgcode;
	/**
	 * 对手方交易员（机构名称）
	 */
	private String ztjgname;
	/**
	 * 新建人员
	 */
	private String creatorid;
	/**
	 * 新建时间
	 */
	private java.util.Date createdtime;
	/**
	 * 修改人员
	 */
	private String lastmodifierid;
	
	
	/**
	 * 修改时间
	 */
	private java.util.Date lastmodifiedtime;
	/**
	 * 业务发生时间
	 */
	private String transactime;
	/**
	 * 接口错误信息
	 */
	private String errormsg;
	/*********************新增字段*********************/
	/**
	 * 交易对手
	 */
	private String counterid;
	/**
	 * 交易对手名称
	 */
	private String countername;
	/**
	 * 交易成员ID 本币交易成员ID
	 */
	private String jycyid;
	/**
	 * 本币交易成员全称
	 */
	private String jycyfullname;
	/**
	 * 本币交易成员简称
	 */
	private String jycyname;
	/**
	 * 交易账户21位码 本币交易成员21码
	 */
	private String jgdm;
	/**
	 * 交易员ID 对手方交易员Id
	 */
	private String jyyid;
	/**
	 * 对手方交易员的名称
	 */
	private String jyyname;
	/**
	 * 请求回复id
	 */
	private String quoteresponseid;

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getExerciseyield() {
		return exerciseyield;
	}

	public void setExerciseyield(BigDecimal exerciseyield) {
		this.exerciseyield = exerciseyield;
	}

	public String getPseqno() {
		return pseqno;
	}

	public void setPseqno(String pseqno) {
		this.pseqno = pseqno;
	}

	public String getQuoteid() {
		return quoteid;
	}

	public void setQuoteid(String quoteid) {
		this.quoteid = quoteid;
	}

	public String getEffectflag() {
		return effectflag;
	}

	public void setEffectflag(String effectflag) {
		this.effectflag = effectflag;
	}

	public String getCountertype() {
		return countertype;
	}

	public void setCountertype(String countertype) {
		this.countertype = countertype;
	}

	public String getZtjgcode() {
		return ztjgcode;
	}

	public void setZtjgcode(String ztjgcode) {
		this.ztjgcode = ztjgcode;
	}

	public String getZtjgname() {
		return ztjgname;
	}

	public void setZtjgname(String ztjgname) {
		this.ztjgname = ztjgname;
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

	public String getJycyid() {
		return jycyid;
	}

	public void setJycyid(String jycyid) {
		this.jycyid = jycyid;
	}

	public String getJycyfullname() {
		return jycyfullname;
	}

	public void setJycyfullname(String jycyfullname) {
		this.jycyfullname = jycyfullname;
	}

	public String getJycyname() {
		return jycyname;
	}

	public void setJycyname(String jycyname) {
		this.jycyname = jycyname;
	}

	public String getJgdm() {
		return jgdm;
	}

	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}

	public String getJyyid() {
		return jyyid;
	}

	public void setJyyid(String jyyid) {
		this.jyyid = jyyid;
	}

	public String getJyyname() {
		return jyyname;
	}

	public void setJyyname(String jyyname) {
		this.jyyname = jyyname;
	}

	public String getQuoteresponseid() {
		return quoteresponseid;
	}

	public void setQuoteresponseid(String quoteresponseid) {
		this.quoteresponseid = quoteresponseid;
	}
}
