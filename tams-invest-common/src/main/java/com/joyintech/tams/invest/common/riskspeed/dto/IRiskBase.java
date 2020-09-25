/*
 * 文件名：IPtlProd.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.riskspeed.dto;

import java.io.Serializable;

/**
 * 风险基类接口
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年10月11日
 * @see IRiskBase
 * @since 1.0
 */
public interface IRiskBase extends Serializable {

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId();

    /**
     * 取得 试算日期
     * @return 试算日期
     */
    public String getValDate();

    /**
     * 取得 资产大类
     * @return 资产大类
     */
    public String getAssetType() ;




}
