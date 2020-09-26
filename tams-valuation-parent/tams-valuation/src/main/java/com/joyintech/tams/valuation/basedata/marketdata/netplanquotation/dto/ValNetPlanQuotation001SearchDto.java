/*
 * 文件名：ValNetPlanQuotation001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.dto;

import java.util.Date;

/**
 * 计划类型净值信息查询Dto
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月11日
 * @see ValNetPlanQuotation001SearchDto
 * @since 1.0
 */
public class ValNetPlanQuotation001SearchDto {
	 /**
     * 资产代码
     */
    private String finprodId;
    
    /**
     * 资产名称
     */
    private String finprodAbbr;
    
    /**
     * 估值日期
     */
    private String valueDate;
    
    /**
     * 估值日期开始时间
     */
    private Date valueDateBegin;

    /**
     * 估值日期结束时间
     */
    private Date valueDateEnd;
    
 
    /**
     * 金融产品类型
     */
    private String finprodType;
    
    /**
     * 收益类型
     */
    private String profitType;
    
    /**
     * 资产代码集合
     */
    private String[] finprodIdArr;

	/**
	 * 获取 finprodId
	 * @return finprodId.
	 */
	public String getFinprodId() {
		return finprodId;
	}

	/**
	 * 设置 finprodId
	 * @param finprodId finprodId
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	/**
	 * 获取 finprodAbbr
	 * @return finprodAbbr.
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	/**
	 * 设置 finprodAbbr
	 * @param finprodAbbr finprodAbbr
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	/**
	 * 获取 valueDate
	 * @return valueDate.
	 */
	public String getValueDate() {
		return valueDate;
	}

	/**
	 * 设置 valueDate
	 * @param valueDate valueDate
	 */
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	/**
	 * 获取 valueDateBegin
	 * @return valueDateBegin.
	 */
	public Date getValueDateBegin() {
		return valueDateBegin;
	}

	/**
	 * 设置 valueDateBegin
	 * @param valueDateBegin valueDateBegin
	 */
	public void setValueDateBegin(Date valueDateBegin) {
		this.valueDateBegin = valueDateBegin;
	}

	/**
	 * 获取 valueDateEnd
	 * @return valueDateEnd.
	 */
	public Date getValueDateEnd() {
		return valueDateEnd;
	}

	/**
	 * 设置 valueDateEnd
	 * @param valueDateEnd valueDateEnd
	 */
	public void setValueDateEnd(Date valueDateEnd) {
		this.valueDateEnd = valueDateEnd;
	}

	/**
	 * 获取 finprodType
	 * @return finprodType.
	 */
	public String getFinprodType() {
		return finprodType;
	}

	/**
	 * 设置 finprodType
	 * @param finprodType finprodType
	 */
	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	/**
	 * 获取 profitType
	 * @return profitType.
	 */
	public String getProfitType() {
		return profitType;
	}

	/**
	 * 设置 profitType
	 * @param profitType profitType
	 */
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	/**
	 * 获取 finprodIdArr
	 * @return finprodIdArr.
	 */
	public String[] getFinprodIdArr() {
		return finprodIdArr;
	}

	/**
	 * 设置 finprodIdArr
	 * @param finprodIdArr finprodIdArr
	 */
	public void setFinprodIdArr(String[] finprodIdArr) {
		this.finprodIdArr = finprodIdArr;
	}      
    
}
