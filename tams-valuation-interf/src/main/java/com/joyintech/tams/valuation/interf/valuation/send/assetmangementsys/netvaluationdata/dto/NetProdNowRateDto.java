/*
 * 文件名：NetProdNowRateDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto;

import java.util.Date;

/**
 * 年化收益率Dto
 * @author chendong
 * @version 1.0 2018年11月20日
 * @see NetProdNowRateDto
 * @since 1.0
 */
public class NetProdNowRateDto {
	
	/**
     * 估值日
     */
    private Date accrStrDate;

	/**
	 * 获取 accrStrDate
	 * @return accrStrDate.
	 */
	public Date getAccrStrDate() {
		return accrStrDate;
	}

	/**
	 * 设置 accrStrDate
	 * @param accrStrDate accrStrDate
	 */
	public void setAccrStrDate(Date accrStrDate) {
		this.accrStrDate = accrStrDate;
	}
}
