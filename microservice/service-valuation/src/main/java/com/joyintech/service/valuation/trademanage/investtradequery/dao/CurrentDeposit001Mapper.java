/*
 * 文件名：CurrentDeposit001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.investtradequery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.trademanage.investtradequery.dto.CurrentDepositDto;

/**
 * @author Administrator
 * @version 1.0 2018年8月3日
 * @see CurrentDeposit001Mapper
 * @since 1.0
 */
@Repository(value="serviceCurrentDeposit001Mapper")
public interface CurrentDeposit001Mapper {

    /**
     * 双击查看
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    CurrentDepositDto selectByTradeId(Map<String, Object> params);

    /**
     * 对应投资主体收息信息列表
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    List<CurrentDepositDto> selectByTradeId2(@Param("tradeId") String tradeId);

    /**
     * 查看计提明细列表
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    List<CurrentDepositDto> selectByTradeId3(@Param("tradeId") String tradeId);

}
