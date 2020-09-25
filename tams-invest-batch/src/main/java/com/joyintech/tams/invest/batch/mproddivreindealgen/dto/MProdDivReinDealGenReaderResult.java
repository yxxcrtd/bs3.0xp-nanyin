/*
 * 文件名：ResetBaseRuleInfoReaderResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 *  reader结果
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see MProdDivReinDealGenReaderResult
 * @since 1.0
 */
public class MProdDivReinDealGenReaderResult extends NeedOperateEntity{
	/**
	 * 删除用DTo
	 */
	private List<MProdDivReinDealDelDto> delDtos;
	/**
	 * 产品代码
	 */
	private String finprodId;
	
	/**
	 * 币种
	 */
	private String ccy;
	/**
	 * 母产品代码
	 */
	private String pFinprodId;
	/**
	 * 投资组合代码
	 */
	private String portfolioId;
	
	/**
	 * 帐套代码
	 */
	private String booksetId;
	/**
	 * 当日未分配利润
	 */
	private BigDecimal tdyUnpaidIntincexp;
	
	/**
	 * 交易明细Dto
	 */
	private List<MProdDivReinDealGenDto> dealDtos;
	
	public List<MProdDivReinDealDelDto> getDelDtos() {
		return delDtos;
	}
	public void setDelDtos(List<MProdDivReinDealDelDto> delDtos) {
		this.delDtos = delDtos;
	}
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getBooksetId() {
		return booksetId;
	}
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}
	public BigDecimal getTdyUnpaidIntincexp() {
		return tdyUnpaidIntincexp;
	}
	public void setTdyUnpaidIntincexp(BigDecimal tdyUnpaidIntincexp) {
		this.tdyUnpaidIntincexp = tdyUnpaidIntincexp;
	}
	public List<MProdDivReinDealGenDto> getDealDtos() {
		return dealDtos;
	}
	public void setDealDtos(List<MProdDivReinDealGenDto> dealDtos) {
		this.dealDtos = dealDtos;
	}
	public String getpFinprodId() {
		return pFinprodId;
	}
	public void setpFinprodId(String pFinprodId) {
		this.pFinprodId = pFinprodId;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

   
    
}
