/*
 * 文件名：SecuritiesAccountInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlmanagementinfo.dto;

import com.joyintech.tams.common.base.entity.StlSecAcctInfo;

/**
 * 证券账户
 * @author fanshuanghu
 * @version 1.0 2018年9月11日
 * @see SecuritiesAccountInfoDto
 * @since 1.0
 */
public class SecuritiesAccountInfoDto extends StlSecAcctInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6981663179653524334L;
    /**
     * 证券账号
     */
    private String acctName;
    /**
     * 组合账号
     */
    private String portfolioId;
    
    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 acctNo
     * @return acctNo.
     */
    public String getAcctName() {
        return acctName;
    }
    
    /**
     * 设置 acctNo
     * @param acctName acctName
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
    
    
}
