/*
 * 文件名：IbuQuoteCrepDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.business.dto;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteCrep;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Jiaoxujin
 * @version 1.0 2020年5月14日
 * @see IbuQuoteCrepDto
 * @since 1.0
 */
public class IbuQuoteCrepDto extends IbuQuoteCrep {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7233085794818500868L;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 获取 组合名称
     * @return
     */
    public String getPortfolioName() {
		return portfolioName;
	}

    /**
     * 设置 组合名称
     * @param portfolioName
     */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

}