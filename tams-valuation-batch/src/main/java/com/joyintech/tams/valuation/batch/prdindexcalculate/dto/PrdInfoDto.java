/*
 * 文件名：PrdInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.dto;

import java.util.Date;

/**
 * 金融产品数据
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdInfoDto
 * @since 1.0
 */
public class PrdInfoDto {
	/**
	 * 金融产品代码
	 */
	private String prdId;

	/**
	 * 金融产品简称
	 */
	private String prdAbbr;

	/**
	 * 金融产品名称
	 */
	private String prdName;

	/**
	 * 发行方式
	 */
	private String issueType;
	
	/**
	 * 收益类型
	 */
   private  String profitType;
   
   /**
    * 市场代码
    */
   private String finprodMarketId;
   
   
   /**
    * 起息日
    */
   private Date vDate;
   
   
   /**
    * 到期日
    */
   private Date mDate;
   /*
    * 产品类型
    */
   private String busiType;
   
   /**
    * 父产品编号
    */
   private String p_prdId;
   


   
	public String getP_prdId() {
	return p_prdId;
}

public void setP_prdId(String p_prdId) {
	this.p_prdId = p_prdId;
}

	public String getBusiType() {
	return busiType;
}

public void setBusiType(String busiType) {
	this.busiType = busiType;
}

	public Date getvDate() {
	return vDate;
}

public void setvDate(Date vDate) {
	this.vDate = vDate;
}

public Date getmDate() {
	return mDate;
}

public void setmDate(Date mDate) {
	this.mDate = mDate;
}

	public String getFinprodMarketId() {
	return finprodMarketId;
}

public void setFinprodMarketId(String finprodMarketId) {
	this.finprodMarketId = finprodMarketId;
}

	public String getPrdId() {
		return prdId;
	}

	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

	public String getPrdAbbr() {
		return prdAbbr;
	}

	public void setPrdAbbr(String prdAbbr) {
		this.prdAbbr = prdAbbr;
	}

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

}
