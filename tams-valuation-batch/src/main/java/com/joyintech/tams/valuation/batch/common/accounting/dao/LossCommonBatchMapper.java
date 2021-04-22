/*
 * 文件名：AccountingCommonMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.accounting.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokStatLossPosiiton;
import com.joyintech.tams.common.base.entity.SysBatchTask;

/**
 * 减值后台共通Mapper
 *
 * @author caoguo
 * @version 1.0 2018年5月31日
 * @see LossCommonBatchMapper
 * @since 1.0
 */
@Repository
public interface LossCommonBatchMapper {

    /**
     * 最新统计分析明细表（资产减值）查询
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    BokStatLossPosiiton queryLossDetailInfo(Map<String, Object> param);
    
    /**
     * 最新统计分析明细表（资产减值）查询(不支持倒起息)
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    BokStatLossPosiiton queryLossDetailInfoNoR(Map<String, Object> param);
    
    /**
     * 最新统计分析明细表（资产减值）查询(不支持倒起息)
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<BigDecimal> getStage3Vaule(Map<String, Object> param);
    
    /**
     * 查询批处理任务信息
     *
     * @param beanName
     * @see
     * @since 1.0
     */
    SysBatchTask getTaskInfo(@Param("beanName") String beanName);

}
