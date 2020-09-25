/*
 * 文件名：IPtlProd.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.prodinfo.dto;

import java.util.Date;

/**
 * 组合信息接口
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年10月11日
 * @see IPtlProd
 * @since 1.0
 */
public interface IPtlProd{

    public String getPortfolioId();

    /**
     * 取得 组合名称
     * @return 组合名称
     */
    public String getPortfolioName();

    /**
     * 取得 组合类型
     * @return 组合类型
     */
    public String getPortfolioType() ;

    /**
     * 取得 组合开始日
     * @return 组合开始日
     */
    public Date getVdate();

    /**
     * 取得 组合到期日
     * @return 组合到期日
     */
    public Date getMdate();

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getProfitType();

    /**
     * 取得 是否保本
     * @return 是否保本
     */
    public String getIsGuarantee();

    /**
     * 取得 开放类型
     * @return 开放类型
     */
    public String getOpenType();

    /**
     * 取得 募集方式
     * @return 募集方式
     */
    public String getRaiseType();

    /**
     * 取得 投资性质
     * @return 投资性质
     */
    public String getInvestNature();
}
