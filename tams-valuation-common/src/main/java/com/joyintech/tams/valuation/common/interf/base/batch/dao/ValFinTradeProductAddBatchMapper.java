/*
 * 文件名：ValFinTradeProductAddBatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2020年01月09日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinTradeProductAdd;

@Repository
public interface ValFinTradeProductAddBatchMapper {

    int insertBatch(List<FinTradeProductAdd> list);

    int deleteBatch(List<FinTradeProductAdd> list);
}
