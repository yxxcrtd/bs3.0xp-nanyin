package com.joyintech.tams.valuation.batch.positionmake.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

public class PositionMakeInterfWriterResult extends NeedToWriteEntity{
	
	/**
	 * 组合代码集合
	 */
	private List<String> portfolioIdList = new ArrayList<String>();
	
	/**
	 * 产品代码集合
	 */
	private List<String> prodList = new ArrayList<String>();
	
	
	/**
	 * 头寸日期
	 */
	private Date cdate;
	
	private String seqNo;
	

	public List<String> getPortfolioIdList() {
		return portfolioIdList;
	}

	public void setPortfolioIdList(List<String> portfolioIdList) {
		this.portfolioIdList = portfolioIdList;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public List<String> getProdList() {
		return prodList;
	}

	public void setProdList(List<String> prodList) {
		this.prodList = prodList;
	}
	
	
	
}
