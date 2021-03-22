/*
 * 文件名：FinProductModule002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdbondduration.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.period.trdbondduration.SecPositionExerciseInfoDto;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurationDto;

/**
 * 债券存续期管理
 *
 * @author 强成西
 * @version 1.0 2018年8月31日
 * @see TrdBondDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdBondDurationMapper {

    List<SelectOptionDto> getPortfolioId(Map<String, Object> param);

    List<SelectOptionDto> getSecManageAcctId(Map<String, Object> param);

    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);

    List<TrdBondDurationDto> findPage(Map<String, Object> param);

    TrdBondDurationDto findEffectiveData(Map<String, Object> param);

    int selectMatchedData(String bizCode);

    List<SelectOptionDto> getBusiCode(Map<String, Object> param);

    long checkIfMaxDate(Map<String, String> params);

    long checkIfMinDate(Map<String, String> params);

    List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params);

    List<String> getFlowDeal(Map<String, Object> map);

    List<SecPositionExerciseInfoDto> findSecPosition(Map<String, Object> param);
    
    /**
     * 撤销时根据汇总表的主键查询授信交易编号
     *findFinprodIdByBusiCode
     */
    List<String> findCeTradeIdByTotleTradeId(String totleTradeId);

    List<String> findProcessDtos(Map<String, Object> param);
    
    Map<String,Object> getPkByBizCode(String bizCode);

}
