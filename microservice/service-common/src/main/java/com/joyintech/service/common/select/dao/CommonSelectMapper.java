/*
 * 文件名：CommonSelectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.select.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommonSelectMapper {

    List<SuggestDto> getAssetIdCommon(Map<String, Object> param);
    
    List<SuggestDto> getFinprodAbbr(Map<String, Object> param);
    
    List<SuggestDto> getBrachBankId(Map<String, Object> param);

    List<SuggestDto> getCustomerId(Map<String, Object> param);

    List<SuggestDto> getEntrustId(Map<String, Object> param);

    List<SuggestDto> getMarketValuationId(Map<String, Object> param);

    List<SuggestDto> getPortfolioId(Map<String, Object> param);

    List<SelectOptionDto> selectPortfolioId(Map<String, Object> param);

    List<SelectOptionDto> selectCounterId(Map<String, Object> param);

    List<SuggestDto> getCashOutNo(Map<String, Object> param);

    List<SelectOptionDto> selectAsset(Map<String, Object> param);

    List<SelectOptionDto> selectWindBondAsset(Map<String, Object> param);

    List<SelectOptionDto> getCustomer(Map<String, Object> param);

    List<SelectOptionDto> getGroupIdInfo(Map<String, Object> param);

    List<SelectOptionDto> getAcctNo(Map<String, Object> param);

    List<SelectOptionDto> selectPtlCapAcctNo(Map<String, Object> param);
    
    List<SelectOptionDto> selectMstCapAcctNo(Map<String, Object> param);

    List<SelectOptionDto> getChannelIdForAsset(Map<String, Object> params);

	List<SelectOptionDto> getChannelIdAndCollectTypeForAsset(Map<String, Object> params);
    /**
     * 资产代码查询（可投金额试算使用）
     * Add by nixiukuan 20190927
     *债券、基金、股票
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    List<SuggestDto> getFinprodByType(Map<String, Object> param);

    List<SelectOptionDto> getSecManageAcctId(Map<String, Object> param);

    List<SelectOptionDto> selectCalendarId(Map<String, Object> param);

    List<SelectOptionDto> selectCashOutNo(Map<String, Object> param);

    List<SelectOptionDto> selectFinprodMarketId(Map<String, Object> param);
    
    List<SelectOptionDto> selectWindFundAsset(Map<String, Object> param);
}
