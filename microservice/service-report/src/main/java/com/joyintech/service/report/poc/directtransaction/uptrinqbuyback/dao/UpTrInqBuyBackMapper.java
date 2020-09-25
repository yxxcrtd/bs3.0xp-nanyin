/*
 * 文件名：TrdBondInvestTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.dto.UpTrInqBuyBackDto;

/**
 * 询价交易-现券买卖交易Mapper
 *
 * @author pcz
 * @version 1.0 2018年9月7日
 * @see TrdBondInvestTradeMapper
 * @since 1.0
 */
@Repository
public interface UpTrInqBuyBackMapper {

    /**
     * 检索债券交易列表
     *
     * @param param 参数
     * @return 债券交易列表
     * @see
     * @since 1.0
     */

    List<SelectOptionDto> getPortfolioId(Map<String, Object> param);

    /**
     * 分页查询 市场代码
     *
     * @return
     */
    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);
    
    /**
     * 
     * 查询交易对手
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCounterId(Map<String, Object> map);
    
    /**
     * 通过主键查询委托信息
     */
    UpTrInqBuyBackDto selectByPrimryKey(String entrustId);
    
}
