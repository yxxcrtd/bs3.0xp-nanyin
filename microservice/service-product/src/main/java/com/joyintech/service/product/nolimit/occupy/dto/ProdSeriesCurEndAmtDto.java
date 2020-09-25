package com.joyintech.service.product.nolimit.occupy.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 非标占比查询
 */
public class ProdSeriesCurEndAmtDto extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 产品系列ID
	 */
	private String prdDesignId;
	
	/**
	 * 月份
	 */
	private String assetMonth;
	
	/**
	 * 金额
	 */
	private BigDecimal curEndAmt;

	public String getPrdDesignId() {
		return prdDesignId;
	}

	public void setPrdDesignId(String prdDesignId) {
		this.prdDesignId = prdDesignId;
	}

	public String getAssetMonth() {
		return assetMonth;
	}

	public void setAssetMonth(String assetMonth) {
		this.assetMonth = assetMonth;
	}

	public BigDecimal getCurEndAmt() {
		return curEndAmt;
	}

	public void setCurEndAmt(BigDecimal curEndAmt) {
		this.curEndAmt = curEndAmt;
	}
	
	

}
