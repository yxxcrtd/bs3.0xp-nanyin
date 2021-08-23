package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class JxsIssueInfo extends BaseEntity{
	/**
	 * 主键
	 */
	private Long id;
	
	/**
     * 唯一主键
     */
	private String cusip;

	/**
     * 激活日期
     */
	private Date activedate;
	
	/**
     * 总账组 | 关联CoaaGroup表的groupNum字段 
     */
	private BigDecimal glgroup;
	
	/**
     * 票面价值，在期货中则为合约乘数
     */
	private BigDecimal contractsize;
	
	/**
     * 到期价
     */
	private BigDecimal endprice;
	
	/**
     * 证券过期日期,主要用在债券的收益类产品的到期时间 
     */
	private Date expiredate;
	
	/**
     * 固收类型
     */
	private BigDecimal fixincometype;
	
	/**
     * 证券类型 | 数据字典--证券类型 
     */
	private String issuetype;
	
	/**
     * 期权类型 | 0:认购期权 1:认沽期权 
     */
	private BigDecimal optiontype;
	
	/**
     * 证券代码
     */
	private String securityid;
	
	/**
     * 证券名称 
     */
	private String securityname;

	/**
     * 发行价
     */
	private BigDecimal startprice;
	
	/**
     * 在swap交易中，判定前后端是否结转现金的标记;无/不结转, 前端结转，后端结转，前后和后端结转
     */
	private BigDecimal swapexchangeind;
	
	/**
     * 关联其它证券，用于判断该证券是否被抵押出去,导致不能交易 | 关联IssueInfo表的主键 
     */
	private String undercusip;
	
	/**
     * 该证券所属交易所编号 | 关联ExchangeCfg表的主键 
     */
	private String exchangecode;
	
	/**
     * 发行商公司编号,该证券属于哪个公司发行 | 关联Issuer表的issuerID字段 
     */
	private String issuer;
	
	/**
     * 自定义证券类型编号, 普通股、优先股等 | 关联RPSecType表的id字段 
     */
	private BigDecimal rpsectype;
	
	/**
     * 数据源,接口编号,该证券从哪个数据源导入创建 | 关联InterfaceSource表的sourceID字段
     */
	private BigDecimal sourceid;
	
	
	/**
     * 货币
     */
	private String pricur;
	
	/**
     * 收入货币
     */
	private String incomecur;

	/**
     * 空方货币
     */
	private String pricur2;
	
	/**
     * 空方收入货币
     */
	private String incomecur2;
	
	/**
     * 该证券所属国家编号
     */
	private String countrycode;
	
	/**
     * 创建部门
     */
	private String createdept;
	
	/**
     * 更新用户
     */
	private String updateuser;
	
	/**
     * 创建用户
     */
	private String createuser;
	
	/**
     * 现金流配置
     */
	private String cashflowcfg;
	
	
	/**
     * 空方现金流配置
     */
	private String cashflowcfg2;


	public String getCusip() {
		return cusip;
	}


	public void setCusip(String cusip) {
		this.cusip = cusip;
	}


	public Date getActivedate() {
		return activedate;
	}


	public void setActivedate(Date activedate) {
		this.activedate = activedate;
	}


	public BigDecimal getGlgroup() {
		return glgroup;
	}


	public void setGlgroup(BigDecimal glgroup) {
		this.glgroup = glgroup;
	}


	public BigDecimal getContractsize() {
		return contractsize;
	}


	public void setContractsize(BigDecimal contractsize) {
		this.contractsize = contractsize;
	}


	public BigDecimal getEndprice() {
		return endprice;
	}


	public void setEndprice(BigDecimal endprice) {
		this.endprice = endprice;
	}


	public Date getExpiredate() {
		return expiredate;
	}


	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}


	public BigDecimal getFixincometype() {
		return fixincometype;
	}


	public void setFixincometype(BigDecimal fixincometype) {
		this.fixincometype = fixincometype;
	}


	public String getIssuetype() {
		return issuetype;
	}


	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}


	public BigDecimal getOptiontype() {
		return optiontype;
	}


	public void setOptiontype(BigDecimal optiontype) {
		this.optiontype = optiontype;
	}


	public String getSecurityid() {
		return securityid;
	}


	public void setSecurityid(String securityid) {
		this.securityid = securityid;
	}


	public String getSecurityname() {
		return securityname;
	}


	public void setSecurityname(String securityname) {
		this.securityname = securityname;
	}


	public BigDecimal getStartprice() {
		return startprice;
	}


	public void setStartprice(BigDecimal startprice) {
		this.startprice = startprice;
	}


	public BigDecimal getSwapexchangeind() {
		return swapexchangeind;
	}


	public void setSwapexchangeind(BigDecimal swapexchangeind) {
		this.swapexchangeind = swapexchangeind;
	}


	public String getUndercusip() {
		return undercusip;
	}


	public void setUndercusip(String undercusip) {
		this.undercusip = undercusip;
	}


	public String getExchangecode() {
		return exchangecode;
	}


	public void setExchangecode(String exchangecode) {
		this.exchangecode = exchangecode;
	}


	public String getIssuer() {
		return issuer;
	}


	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}


	public BigDecimal getRpsectype() {
		return rpsectype;
	}


	public void setRpsectype(BigDecimal rpsectype) {
		this.rpsectype = rpsectype;
	}


	public BigDecimal getSourceid() {
		return sourceid;
	}


	public void setSourceid(BigDecimal sourceid) {
		this.sourceid = sourceid;
	}


	public String getPricur() {
		return pricur;
	}


	public void setPricur(String pricur) {
		this.pricur = pricur;
	}


	public String getIncomecur() {
		return incomecur;
	}


	public void setIncomecur(String incomecur) {
		this.incomecur = incomecur;
	}


	public String getPricur2() {
		return pricur2;
	}


	public void setPricur2(String pricur2) {
		this.pricur2 = pricur2;
	}


	public String getIncomecur2() {
		return incomecur2;
	}


	public void setIncomecur2(String incomecur2) {
		this.incomecur2 = incomecur2;
	}


	public String getCountrycode() {
		return countrycode;
	}


	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}


	public String getCreatedept() {
		return createdept;
	}


	public void setCreatedept(String createdept) {
		this.createdept = createdept;
	}


	public String getUpdateuser() {
		return updateuser;
	}


	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}


	public String getCreateuser() {
		return createuser;
	}


	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}


	public String getCashflowcfg() {
		return cashflowcfg;
	}


	public void setCashflowcfg(String cashflowcfg) {
		this.cashflowcfg = cashflowcfg;
	}


	public String getCashflowcfg2() {
		return cashflowcfg2;
	}


	public void setCashflowcfg2(String cashflowcfg2) {
		this.cashflowcfg2 = cashflowcfg2;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
