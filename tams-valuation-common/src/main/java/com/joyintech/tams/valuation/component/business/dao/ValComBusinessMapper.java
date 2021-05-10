/*
 * 文件名：DateCalcMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.component.business.dao;

import java.util.Date;

/**
 * 组件共通服务
 * 
 * @author yanya
 * @version 1.0 2020-03-04
 * @see ValComBusinessMapper
 * @since 1.0
 */
public interface ValComBusinessMapper {

    Date getMaxTradeDate(String finprodId);


    /**
     * 资产是否存在交易
     *
     * @param finprodId 金融产品代码
     * @return 交易笔数
     * @see
     * @since 1.0
     */
    int isTransInAsset(String finprodId);

}
