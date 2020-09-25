/*
 * 文件名：TrdBondInvestTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.pretrdbondtrade.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade001Dto;
import com.joyintech.service.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade002Dto;

/**
 * 债券交易Mapper
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see PreTrdBondInvestTradeMapper
 * @since 1.0
 */
@Repository
public interface PreTrdBondInvestTradeMapper {
	
    /**
     * 资产代码：下拉框
     * @param param
     * @return
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> param);
    /**
     * 检索债券交易列表
     *
     * @param paraMap 参数
     * @return 债券交易列表
     * @see
     * @since 1.0
     */
    List<PreTrdBondInvestTrade001Dto> selectDeal(Map<String, Object> paraMap);
    
    PreTrdBondInvestTrade002Dto getPreTrdBondInvestTradeDto(Map<String, Object> params);
    /**
     * 资金账户下拉框
     * 
     * @param param
     * @return
     */
    List<SelectOptionDto> getacctNo(Map<String, Object> param);
    /**
     * 证券账户下拉框
     * 
     * @param param
     * @return
     */
    List<SelectOptionDto> getsecAcctId(Map<String, Object> param);
    /**
     * 结算方式下拉框
     * 
     * @param param
     * @return
     */
    List<SelectOptionDto> getdeliveryType(Map<String, Object> param);
    /**
     * 清算类型下拉框
     * 
     * @param param
     * @return
     */
    List<SelectOptionDto> getclearType(Map<String, Object> param);
}
