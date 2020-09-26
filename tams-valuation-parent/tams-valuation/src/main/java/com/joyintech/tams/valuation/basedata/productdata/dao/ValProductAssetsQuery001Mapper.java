/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.productdata.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.basedata.productdata.dto.ValProductAssetsQuery001Dto;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.dao.ValFinprodTradeQuery001Mapper;

/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Mapper
 * @since 1.0
 */
public interface ValProductAssetsQuery001Mapper {
    
    /**
     * 分页查询
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<ValProductAssetsQuery001Dto> queryPageList(Map<String, Object> params);
    
}
