/*
 * 文件名：ValOtcOpt001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年03月05日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valotcopt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valotcopt.dto.ValOtcOpt001Dto;
import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 场外期权交易
 * 
 * @author tanxuan
 * @version 1.0 2020年03月05日
 * @see ValOtcOpt001Mapper
 * @since 1.0
 */
@Repository(value="serviceValOtcOpt001Mapper")
public interface ValOtcOpt001Mapper {

    /**
     * 分页查询
     * 
     * @param params 参数
     * @return List<ValOtcOpt001Dto>
     * @see
     * @since 1.0
     */
    List<ValOtcOpt001Dto> findPage(Map<String, Object> params);
    
    /**
     * 账套下拉框数据选择
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBooksetId(Map<String, Object> map);
    
    /**
     * 交易编号下拉框数据选择
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getTotleTradeId(Map<String, Object> map);
    
    /**
     * 合约标的下拉框数据选择
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBenchmarkId(Map<String, Object> map);
    
    /**
    *
    * 金融产品交易查询
    * <功能详细描述>
    * @param totalTradeId
    * @return
    * @see
    * @since 1.0
    */
   List<TrdProductDeal> selectTrdProductDealsByTotalTradeId(@Param("totalTradeId") String totalTradeId);

}
