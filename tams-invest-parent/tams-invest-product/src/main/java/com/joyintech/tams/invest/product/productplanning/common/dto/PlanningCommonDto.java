package com.joyintech.tams.invest.product.productplanning.common.dto;

import com.joyintech.framework.base.BaseEntity;

public class PlanningCommonDto extends BaseEntity {

	private static final long serialVersionUID = 5421279072772876529L;
	
	private String isInnovation; // 是否创新
	private String prdDesignId; // 产品规划系列编号
	private String prdDesignSerialName; // 产品规划系列名称
	private String tdyShareAmt; // 规划初始值
	private String actmdate; // 实际到期日
	private String productDueShares; // 产品到期份额
	
	public String getIsInnovation() {
		return isInnovation;
	}
	public void setIsInnovation(String isInnovation) {
		this.isInnovation = isInnovation;
	}
	public String getPrdDesignId() {
		return prdDesignId;
	}
	public void setPrdDesignId(String prdDesignId) {
		this.prdDesignId = prdDesignId;
	}
	public String getPrdDesignSerialName() {
		return prdDesignSerialName;
	}
	public void setPrdDesignSerialName(String prdDesignSerialName) {
		this.prdDesignSerialName = prdDesignSerialName;
	}
	public String getTdyShareAmt() {
		return tdyShareAmt;
	}
	public void setTdyShareAmt(String tdyShareAmt) {
		this.tdyShareAmt = tdyShareAmt;
	}
	public String getActmdate() {
		return actmdate;
	}
	public void setActmdate(String actmdate) {
		this.actmdate = actmdate;
	}
	public String getProductDueShares() {
		return productDueShares;
	}
	public void setProductDueShares(String productDueShares) {
		this.productDueShares = productDueShares;
	}
}
