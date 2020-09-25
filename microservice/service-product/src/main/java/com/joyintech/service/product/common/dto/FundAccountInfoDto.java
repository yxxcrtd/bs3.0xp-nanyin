/*
 * 文件名：FundAccountInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.dto;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;

/**
 * 资金账户实体类
 * @author fanshuanghu
 * @version 1.0 2018年9月11日
 * @see FundAccountInfoDto
 * @since 1.0
 */
public class FundAccountInfoDto extends StlCapacctInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6028360717726083021L;
    /**
     * 组合
     */
    private String portfolioId;
    /**
     * 账户类型
     */
    private String acctType;


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
     * 获取 acctType
     * @return acctType.
     */
    public String getAcctType() {
        return acctType;
    }

    /**
     * 设置 acctType
     * @param acctType acctType
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

}
