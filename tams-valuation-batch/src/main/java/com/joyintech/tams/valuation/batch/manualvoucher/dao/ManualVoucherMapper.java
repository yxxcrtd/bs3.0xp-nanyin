/*
 * 文件名：ManualVoucherMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chenggaoling
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.manualvoucher.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.manualvoucher.dto.ManualVoucherReaderResult;
import com.joyintech.tams.valuation.batch.manualvoucher.dto.TradVouchDealDetailDto;


/**
 * 
 * 手工凭证交易批处理Dao
 * 
 * @author chenggaoling
 * @version 1.0 2018年8月15日
 * @see ManualVoucherMapper
 * @since 1.0
 */
@Repository
public interface ManualVoucherMapper {

    /**
     * 
     * 手工凭证交易信息取得
     * 
     * @param taskId 任务代码
     * @return 手工凭证交易信息
     * @see
     * @since 1.0
     */
    List<ManualVoucherReaderResult> getTradVouchDealList(Map<String, Object> params);
    
    /**
     * 
     * 手工凭证交易明细信息取得
     * 
     * @param tradIdList 交易编号
     * @return 手工凭证交易明细信息
     * @see
     * @since 1.0
     */
    List<TradVouchDealDetailDto> getTradVouchDealDetailList(@Param("tradIdList") List<String> tradIdList);
    
}
