/*
 * 文件名：SubjectBalanceCollectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.valscheme.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeDelResult;

/**
 * 净值确认删除数据Mapper
 * 
 * @author 魏鹏
 * @version 1.0 2018年10月18日
 * @see ValSchemeDelMapper
 * @since 1.0
 */
@Repository
public interface ValSchemeDelMapper {
    /**
     * 删除估值余额表数据
     * @param result 参数    
     * @see
     * @since 1.0
     */
    void deleteValbalTableStatus (ValSchemeDelResult result);
    
    /**
     * 删除估值日志表数据
     * @param result 参数    
     * @see
     * @since 1.0
     */
    void deleteValbalTableLog (ValSchemeDelResult result);
    
}
