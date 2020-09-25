/*
 * 文件名：RskPortfolioPackageDtlImp.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto;

import com.joyintech.tams.common.base.entity.RskPortfolioPackageDtl;

/**
 * 
 * 组合包明细基本信息
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskPortfolioPackageDtlImp
 * @since 1.0
 */
public class RskPortfolioPackageDtlImp  extends RskPortfolioPackageDtl {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    private String portfolioName;

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
    
   
    
}
