/*
 * 文件名：ValBokBookdetailCurrentBatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年7月2日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;

@Repository
public interface ValBokBookdetailCurrentBatchMapper {
    int insert(List<BokBookdetailCurrent> record);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param listInsert
     * @see
     * @since 1.0
     */
    int deleteBatch(List<BokBookdetailCurrent> list);
}
