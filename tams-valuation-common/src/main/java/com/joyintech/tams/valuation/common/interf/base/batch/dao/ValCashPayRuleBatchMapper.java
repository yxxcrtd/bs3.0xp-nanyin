/*
 * 文件名：ValFinProductBatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：魏鹏
 * 修改时间：2018年6月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashPayRule;

@Repository
public interface ValCashPayRuleBatchMapper {
    int insertBatch(List<FinCashPayRule> list);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param rstValCashPayRule
     * @see
     * @since 1.0
     */
    int deleteBatch(List<FinCashPayRule> list);
    
    
    /**
     * 根据金融产品代码、金融产品类型进行批量删除
     * @param list
     * @return
     * add by wujie on 20180909
     */
    int deleteFeeFinCashPayRule(List<FinCashPayRule> list);
}
