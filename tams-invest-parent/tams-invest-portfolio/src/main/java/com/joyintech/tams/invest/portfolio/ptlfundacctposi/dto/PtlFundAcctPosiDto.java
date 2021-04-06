/*
 * 文件名：PtlFundAcctPosiDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlfundacctposi.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.PtlCashPosition;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class PtlFundAcctPosiDto extends PtlCashPosition implements GridRowDto, ProcessEntity{

	/**
	 * 组合名称
	 */
	private String portfolioName;
	/**
	 * 开户行
	 */
	private String bankDep;
	/**
	 * 账户名称
	 */
	private String acctName;
	/**
	 * 大额网点号
	 */
	private String largePayno;
	/**
	 * swiftCode
	 */
	private String swiftCode;
	/**
	 * 父节点
	 */
	private String upperPortfolioCode;
	/**
	 * 子节点
	 */
	private String hasChildren;
	
	/**
	 * 
	 */
	private String portfolioId1;
	
	/**
	 * 
	 */
	private BigDecimal amount1;
	
	/**
	 * 业务编号
	 */
	private String busiNo;
	/**
	 * 产品/资产代码
	 */
	private String finprodMarketId;
	
	/**
	 * 产品/资产名称
	 */
	private String finprodAbbr;
	
	/**
	 * 业务类型
	 */
	private String busiType;
	/**
	 * 交易方向
	 */
	private String ps;
	
	private String isSummary;

	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getBankDep() {
		return bankDep;
	}
	public void setBankDep(String bankDep) {
		this.bankDep = bankDep;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public String getLargePayno() {
		return largePayno;
	}
	public void setLargePayno(String largePayno) {
		this.largePayno = largePayno;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public String getUpperPortfolioCode() {
		return upperPortfolioCode;
	}
	public void setUpperPortfolioCode(String upperPortfolioCode) {
		this.upperPortfolioCode = upperPortfolioCode;
	}
	public String getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}
	public String getPortfolioId1() {
		return portfolioId1;
	}
	public void setPortfolioId1(String portfolioId1) {
		this.portfolioId1 = portfolioId1;
	}
	
	public String getCdateStr(){
		if(StringUtil.equals(isSummary, DictCodeListExt1.是或否_是.getValue())) {
			return  SpringContextUtil.getTextValue("PtlFundAcctPosi001.isSummary");
		}
		return DateUtil.format(super.getCdate());
	}
	public String getCcyStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
	}
	@Override
	public String getRowId() {
		return this.getPortfolioId();
	}
	@Override
	public void setRowId(String rowId) {
		
	}
	@Override
	public int getOrder() {
		return 0;
	}
	@Override
	public void setOrder(int order) {
		
	}
	@Override
	public String getId() {
		return this.getPortfolioId();
	}
	@Override
	public void setId(String id) {
		
	}
	@Override
	public String getParentId() {
		return upperPortfolioCode;
	}
	@Override
	public void setParentId(String parentId) {
		
	}
	@Override
	public String getName() {
		return this.getPortfolioId1();
	}
	@Override
	public void setName(String name) {
		
	}
	@Override
	public boolean isLeaf() {
		return StringUtil.equals(hasChildren, DictCodeListExt1.是或否_否.getValue());
	}
	@Override
	public void setLeaf(boolean leaf) {
		
	}
	public BigDecimal getAmount1() {
		return amount1;
	}
	public void setAmount1(BigDecimal amount1) {
		this.amount1 = amount1;
	}
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	
	public String getBusiTypeStr(){
		return busiType;
	}
	/**
	 * @return the finprodMarketId
	 */
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	/**
	 * @param finprodMarketId the finprodMarketId to set
	 */
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
	/**
	 * @return the finprodAbbr
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}
	/**
	 * @param finprodAbbr the finprodAbbr to set
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}
	public String getIsSummary() {
		return isSummary;
	}
	public void setIsSummary(String isSummary) {
		this.isSummary = isSummary;
	}
}
