/*
 * 文件名：BondAutoDurDealGenerateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.cfetsbond.dao;

import com.joyintech.tams.common.base.entity.RegFinProductDeal;
import com.joyintech.tams.common.base.entity.RegFinProductDealAdd;
import com.joyintech.tams.common.base.entity.RegFinProductDealTotal;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurationDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Mapper
 *
 * @author etliu
 * @version 1.0 2018年12月11日
 * @see CfetsBondAutoDurDealGenerateMapper
 * @since 1.0
 */
@Repository
public interface CfetsBondAutoDurDealGenerateMapper {

    List<TrdBondDurationDto> findAllData(Map<String, Object> paramMap);

    List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params);

    TrdBondDurationDto findEffectiveData(Map<String, Object> paramMap);

    //查询行权赎回待处理数据
    List<RegFinProductDealTotal> findShData(Map<String, Object> paramMap);

    //查询金融产品交易明细数据
    List<RegFinProductDeal> queryRegDeallnfo(Map<String, Object> map);

}
