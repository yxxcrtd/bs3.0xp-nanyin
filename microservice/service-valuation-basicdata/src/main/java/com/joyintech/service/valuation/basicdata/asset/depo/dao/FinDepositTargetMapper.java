/*
 * 文件名：FinDepositTargetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basicdata.asset.depo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basicdata.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.tams.common.base.entity.FinProductClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;



/**
 * 存款类标的信息mapper
 * @author zhangnian
 * @version 1.0 2018年9月14日
 * @see FinDepositTargetMapper
 * @since 1.0
 */
@Repository
public interface FinDepositTargetMapper {

    List<FinDepositTargetDto> queryForPage(Map<String, Object> pageParam);
    
    FinDepositTargetDto getBasicInfo(@Param("finprodId") String finprodId,@Param("finprodType") String finprodType,@Param("cashType") String cashType,@Param("finprodMarketId") String finprodMarketId,@Param("finprodType2") String finprodType2);
    
    List<SuggestDto> getTradeOpponent(@Param("counterType") String counterType);
    
    List<SuggestDto> getTradeCalendarName(@Param("isTradeCalendar") String isTradeCalendar);
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 start*/
    List<SelectOptionDto> getCustomerName(Map<String, Object> map);
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 end*/


     void deleteFinCashCalcRule(@Param("finprodId") String finprodId);

    int selectCountByfinprodId(@Param("finprodId") String finprodId);

    /**
     * 获取资产id联想文本框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAssetId(Map<String, Object> param);

    /**
     * 查询核算分类
     * <功能详细描述>
     * @param finProductClass
     * @see
     * @since 1.0
     */
     FinProductClass getFinanceProductClass(FinProductClass finProductClass);
}
