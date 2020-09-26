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

import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;

@Repository
public interface ValPerfRewardAccrscheBatchMapper {
    int insertBatch(List<FinFloatAccrualSche> list);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param rstValPerfRewardAccrsche
     * @see
     * @since 1.0
     */
    int deleteBatch(List<FinFloatAccrualSche> list);
    
    /**
     * 根据现金流代码进行删除
     * @param list
     * @return
     */
    int deleteByCashId(List<FinFloatAccrualSche> list);
    
}
