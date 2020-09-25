package com.joyintech.interf.bankname.cfetsup.secrepoquote.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 质押债券
 * @author wot_chenzhenling_zy
 *
 */
public class FxSecrepoQuoteDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1908398112322170659L;
	/**
	 * 流水号
	 */
	private String seqno;
	/**
	 * 报价流水号
	 */
	private String pseqno;
	/**
	 * 报价编号
	 */
	private String quoteid;
	/**
	 * 券面总额
	 */
	private BigDecimal facetotalamt;
	/**
	 * 债券代码
	 */
	private String seccode;
	/**
	 * 折算比例
	 */
	private BigDecimal convrate;
	/**
	 * 成交金额
	 */
	private BigDecimal dealamt;
	/**
	 * 创建人
	 */
	private String creatorid;
	/**
	 * 创建时间
	 */
	private Date createdtime;
	/**
	 * 有效状态
	 */
	private String effectflag;
	/**
	 * 最后更新人
	 */
	private String lastmodifierid;
	/**
	 * 最后更新时间
	 */
	private Date lastmodifiedtime;
	//主体评级		取债券资料维护中主体评级
	private String ztpj;
	//债项评级		去债券资料维护中债项外部评级
	private String zxpj;
	//中债资信评级		取主体评级，评级机构为中债资产评估有限责任公司对债券的发行主体的评级信息；
	private String zzzxpj;
	//剩余期限		计算公式：	有行权日：未来最近行权日（回售日或赎回日）-交易日	;无行权日：到期日-交易日
	private BigDecimal syqx;
	//是否城投债		列表文本		Y 是 N 否
	private String isctz;
	//行业		列表文本		外部机构信息维护中国标行业分类；
	private String gbhycode;
	//企业性质		列表文本		外部机构信息维护中企业性质；
	private String qyxz;
	
	public String getSeccode() {
		return seccode;
	}
	public void setSeccode(String seccode) {
		this.seccode = seccode;
	}
	
	public String getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}
	
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	public String getPseqno() {
		return pseqno;
	}
	public void setPseqno(String pseqno) {
		this.pseqno = pseqno;
	}
	
	public String getEffectflag() {
		return effectflag;
	}
	public void setEffectflag(String effectflag) {
		this.effectflag = effectflag;
	}
	public String getLastmodifierid() {
		return lastmodifierid;
	}
	public void setLastmodifierid(String lastmodifierid) {
		this.lastmodifierid = lastmodifierid;
	}

	public Date getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}
	public Date getLastmodifiedtime() {
		return lastmodifiedtime;
	}
	public void setLastmodifiedtime(Date lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}

	/**
	 * 判断外汇接口调用的对应的api 
	 */
	private String businessType;

	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getQuoteid() {
		return quoteid;
	}
	public void setQuoteid(String quoteid) {
		this.quoteid = quoteid;
	}
	public BigDecimal getFacetotalamt() {
		return facetotalamt;
	}
	public void setFacetotalamt(BigDecimal facetotalamt) {
		this.facetotalamt = facetotalamt;
	}
	public BigDecimal getConvrate() {
		return convrate;
	}
	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}
	public BigDecimal getDealamt() {
		return dealamt;
	}
	public void setDealamt(BigDecimal dealamt) {
		this.dealamt = dealamt;
	}
	public String getZtpj() {
		return ztpj;
	}
	public void setZtpj(String ztpj) {
		this.ztpj = ztpj;
	}
	public String getZxpj() {
		return zxpj;
	}
	public void setZxpj(String zxpj) {
		this.zxpj = zxpj;
	}
	public String getZzzxpj() {
		return zzzxpj;
	}
	public void setZzzxpj(String zzzxpj) {
		this.zzzxpj = zzzxpj;
	}
	
	public String getIsctz() {
		return isctz;
	}
	public void setIsctz(String isctz) {
		this.isctz = isctz;
	}
	public String getGbhycode() {
		return gbhycode;
	}
	public void setGbhycode(String gbhycode) {
		this.gbhycode = gbhycode;
	}
	
	public String getQyxz() {
		return qyxz;
	}
	public void setQyxz(String qyxz) {
		this.qyxz = qyxz;
	}
	public BigDecimal getSyqx() {
		return syqx;
	}
	public void setSyqx(BigDecimal syqx) {
		this.syqx = syqx;
	}
	
	
	
}