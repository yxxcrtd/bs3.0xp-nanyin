package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class InterfStockInfo extends BaseEntity{
	
	/**
     * 资产代码
     */
	private String assetcode;
	
	/**
     * 资产名称
     */
	private String assetname;
	
	/**
     * 公司名称
     */
	private String companyname;
	
	/**
     * 公司客户代码
     */
	private String companycode;
	
	/**
     * 交易场所
     */
	private String tradeplace;
	
	/**
     * 上市板
     */
	private String listingboard;
	
	/**
     * 上市日期
     */
	private Date listingdate;
	
	/**
     * 退市日期
     */
	private Date delistingdate;
	
	/**
     * 是否沪港通或深港通
     */
	private String ishorsexpress;
	
	/**
     * 是否融资融券标的
     */
	private String ismargintrading;
	
	/**
     * 币种
     */
	private String ccy;
	
	/**
     * 交易日历
     */
	private String tradecalender;
	
	/**
     * 股票类型
     */
	private String stocktype;
	
	/**
     * 行业（国标）
     */
	private String industryn;
	
	/**
     * 行业二级分类
     */
	private String industrynSecond;
	
	/**
     * 行业（证监会）
     */
	private String industrys;
	
	/**
     * 投资企业类型（按技术领域）
     */
	private String enterprisetype3;
	
	/**
     * 投资企业类型（按规模）
     */
	private String enterprisetype1;
	
	/**
     * 投资企业类型（按经济类型）
     */
	private String enterprisetype2;
	
	/**
     * 创建人
     */
	private String createuser;
	
	/**
     * 创建部门
     */
	private String createdept;
	
	/**
     * 更新人
     */
	private String updateuser;
	
	/**
     * 分类1
     */
	private String type1;
	
	/**
     * 分类2
     */
	private String type2;
	
	/**
     * 分类3
     */
	private String type3;
	
	/**
     * 分类6
     */
	private String type6;
	
	/**
     * 分类8
     */
	private String type8;
	
	/**
     * 总账组 (JXS_ISSUE_INFO表用)
     */
	private BigDecimal glgroup;
	
	/**
	 *自定义证券类型编号(JXS_ISSUE_INFO表用)
	 */
	private BigDecimal rpsectype;

	public String getAssetcode() {
		return assetcode;
	}

	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode==null?null:assetcode.trim();
	}

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname==null?null:assetname.trim();
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname==null?null:companyname.trim();
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode==null?null:companycode.trim();
	}

	public String getTradeplace() {
		return tradeplace;
	}

	public void setTradeplace(String tradeplace) {
		this.tradeplace = tradeplace==null?null:tradeplace.trim();
	}

	public String getListingboard() {
		return listingboard;
	}

	public void setListingboard(String listingboard) {
		this.listingboard = listingboard;
	}

	public Date getListingdate() {
		return listingdate;
	}

	public void setListingdate(Date listingdate) {
		this.listingdate = listingdate;
	}

	public Date getDelistingdate() {
		return delistingdate;
	}

	public void setDelistingdate(Date delistingdate) {
		this.delistingdate = delistingdate;
	}

	public String getIshorsexpress() {
		return ishorsexpress;
	}

	public void setIshorsexpress(String ishorsexpress) {
		this.ishorsexpress = ishorsexpress==null?null:ishorsexpress.trim();
	}

	public String getIsmargintrading() {
		return ismargintrading;
	}

	public void setIsmargintrading(String ismargintrading) {
		this.ismargintrading = ismargintrading==null?null:ismargintrading.trim();
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy==null?null:ccy.trim();
	}

	public String getTradecalender() {
		return tradecalender;
	}

	public void setTradecalender(String tradecalender) {
		this.tradecalender = tradecalender==null?null:tradecalender.trim();
	}

	public String getStocktype() {
		return stocktype;
	}

	public void setStocktype(String stocktype) {
		this.stocktype = stocktype==null?null:stocktype.trim();
	}

	public String getIndustryn() {
		return industryn;
	}

	public void setIndustryn(String industryn) {
		this.industryn = industryn==null?null:industryn.trim();
	}

	public String getIndustrys() {
		return industrys;
	}

	public void setIndustrys(String industrys) {
		this.industrys = industrys==null?null:industrys.trim();
	}

	public String getEnterprisetype3() {
		return enterprisetype3;
	}

	public void setEnterprisetype3(String enterprisetype3) {
		this.enterprisetype3 = enterprisetype3==null?null:enterprisetype3.trim();
	}

	public String getEnterprisetype1() {
		return enterprisetype1;
	}

	public void setEnterprisetype1(String enterprisetype1) {
		this.enterprisetype1 = enterprisetype1==null?null:enterprisetype1.trim();
	}

	public String getEnterprisetype2() {
		return enterprisetype2;
	}

	public void setEnterprisetype2(String enterprisetype2) {
		this.enterprisetype2 = enterprisetype2==null?null:enterprisetype2.trim();
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser==null?null:createuser.trim();
	}

	public String getCreatedept() {
		return createdept;
	}

	public void setCreatedept(String createdept) {
		this.createdept = createdept==null?null:createdept.trim();
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser==null?null:updateuser.trim();
	}

	public String getIndustrynSecond() {
		return industrynSecond;
	}

	public void setIndustrynSecond(String industrynSecond) {
		this.industrynSecond = industrynSecond==null?null:industrynSecond.trim();
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	public String getType6() {
		return type6;
	}

	public void setType6(String type6) {
		this.type6 = type6;
	}

	public String getType8() {
		return type8;
	}

	public void setType8(String type8) {
		this.type8 = type8;
	}

	public BigDecimal getGlgroup() {
		return glgroup;
	}

	public void setGlgroup(BigDecimal glgroup) {
		this.glgroup = glgroup;
	}

	public BigDecimal getRpsectype() {
		return rpsectype;
	}

	public void setRpsectype(BigDecimal rpsectype) {
		this.rpsectype = rpsectype;
	}
	
	
	
	

}
