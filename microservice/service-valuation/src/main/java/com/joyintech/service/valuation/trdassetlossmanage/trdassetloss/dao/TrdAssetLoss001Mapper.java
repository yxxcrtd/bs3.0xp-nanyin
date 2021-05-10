/*
 * 文件名：EtrInvestBondInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trdassetlossmanage.trdassetloss.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetloss.dto.TrdAssetLoss001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 资产减值管理mapper
 * 〈功能详细描述〉
 *
 * @author lby
 * @version 1.0 2020年10月10日
 * @see TrdAssetLoss001Mapper
 * @since 1.0
 */
@Repository
public interface TrdAssetLoss001Mapper {
    /**
     * 债券投资委托查询
     * <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<TrdAssetLoss001Dto> selectForPage(Map<String, Object> param);

    /**
     * 资产代码联想下拉框框
     * <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param);

    List<TrdAssetLoss001Dto> getAssetInfo(Map<String, Object> param);

    int insertSelective(TrdAssetLoss001Dto record);

    int updateByPrimaryKeySelective(TrdAssetLoss001Dto record);

    int updateByTradeIdSelective(TrdAssetLoss001Dto record);

    List<TrdAssetLoss001Dto> selectByKey(Map<String, Object> param);

    List<TrdAssetLoss001Dto> selectForPagePreView(Map<String, Object> param);

    List<TrdAssetLoss001Dto> selectByFinprodId(Map<String, Object> param);

    List<TrdAssetLoss001Dto> selectByFinprodIdAndValDate(Map<String, Object> param);

    List<TrdAssetLoss001Dto> selectPreByFinprodIdAndLossDate(Map<String, Object> param);

    int updateByTradeId(TrdAssetLoss001Dto tmp);

    List<TrdAssetLoss001Dto> selectNextByFinprodIdAndLossDate(Map<String, Object> param);

    TrdAssetLoss001Dto selectAssetExerciseDate(Map<String, Object> map);

    List<TrdAssetLoss001Dto> getBondAssetInfo(Map<String, Object> param);

    List<TrdAssetLoss001Dto> selectRepeatByFinprodIdAndValDate(Map<String, Object> param);
}
