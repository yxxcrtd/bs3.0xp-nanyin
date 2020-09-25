/*
 * 文件名：FinDepositTargetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.stock.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.stock.dto.FinStockInfoDto;


/**
 * 存款类标的信息mapper
 * @author zhangnian
 * @version 1.0 2018年9月14日
 * @see FinStockInfoMapper
 * @since 1.0
 */
@Repository
public interface FinStockInfoMapper {

   // List<FinStockInfoDto> queryForPage(Map<String, Object> pageParam);
   List<FinStockInfoDto> queryForPage(Map<String,Object> pageParam);
 FinStockInfoDto getShares(Map<String, Object> Param);

    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinProdMarketIdList(Map<String, Object> param);

    FinStockInfoDto getBasicInfo(@Param("finprodId") String finprodId, @Param("finprodType") String finprodType, @Param("cashType") String cashType);
    
    List<SuggestDto> getTradeOpponent(@Param("counterType") String counterType);
    
    List<SuggestDto> getCalendarId(@Param("tradeMarket") String tradeMarket);
    
    List<SuggestDto> getCustomerName();
    /**
     * 根据金融产品代码查询dto
     * <功能详细描述>
     * @param //finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    FinStockInfoDto selectByFinprodId(Map<String, Object> param);
    /**
     * 根据市场代码查询dto
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FinStockInfoDto findEntity(Map<String, Object> param);
    /**
     * 获取当前流程用户
     * @param param
     * @return
     */
	String getBusinessUser(Map<String, Object> param);
	/**
     * 获取用户真实姓名
     * @param param
     * @return
     */
	String getRealName(String user);

}
