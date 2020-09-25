/*
 * 文件名：ProductValuationReconciliation001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valuareconcy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.valuareconcy.dto.ProductValuationReconciliation001Dto;

/**
 * 产品估值对账 - 数据获取
 * 
 * @author yangjian
 * @version 1.0 2018年11月13日
 * @see ProductValuationReconciliation001Mapper
 * @since 1.0
 */
@Repository(value="serviceProductValuationReconciliation001Mapper")
public interface ProductValuationReconciliation001Mapper {

    /**
     * 分页查询
     * 
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<ProductValuationReconciliation001Dto> queryPageList(Map<String, Object> params);

}
