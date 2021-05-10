/*
 * 文件名：ValBondValuationInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.basicdata.marketdata.bondmarketinfo.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.MstBondValuationInfo;

/**
 * 每日债券行情 dto
 * 
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Dto
 * @since 1.0
 */
public class ComBondMarketInfo001Dto extends MstBondValuationInfo {


	/**
	 * 
	 */
	private static final long serialVersionUID = 395899940154269458L;
	/**
	 * 金融产品简称
	 */
	private String finprodAbbr;

	/**
	 * 债券简称
	 * 
	 * @return String
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	/**
	 * 债券简称
	 * 
	 * @param finprodAbbr
	 *            finprodAbbr
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}
	 /**
     * 导入日
     */
    private Date importDay;
    /**
     * 获取 importDay
     * @return importDay.
     */
    public Date getImportDay() {
        return importDay;
    }
    
    /**
     * 设置 importDay
     * @param importDay importDay
     */
    public void setImportDay(Date importDay) {
        this.importDay = importDay;
    }
}
