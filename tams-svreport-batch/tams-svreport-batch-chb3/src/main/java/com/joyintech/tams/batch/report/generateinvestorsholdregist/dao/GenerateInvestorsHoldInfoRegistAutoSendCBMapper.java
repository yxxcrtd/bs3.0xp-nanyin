/*
 * 文件名：GetInvestorsHoldInfoRegistAutoSendCBMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.report.generateinvestorsholdregist.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;

/**
 * 投资者持有信息登记数据自动生成批处理mapper
 * @author zuoyinyi
 * @version 1.0 2018年5月21日
 * @see GenerateInvestorsHoldInfoRegistAutoSendCBMapper
 * @since 1.0
 */
@Repository
public interface GenerateInvestorsHoldInfoRegistAutoSendCBMapper {
    /**
     * 
     * 查询业务数据
     * @param batchDate 批处理日
     * @return 结果集
     * @see
     * @since 1.0
     */
    List<InvestorsHoldInfoRegist001Dto> selectBusiData(Map<String,Object> map);
    
    /**
     * 查询最新的汇率
     * <功能详细描述>
     * @param temp 参数
     * @return 最新的结果
     * @see
     * @since 1.0
     */
    BigDecimal getRateNearByDate(Map<String, Object> temp);

    /**
     * 查询主键数据是否存在
     * @param dto 查询条件
     * @return 结果条数
     * @see
     * @since 1.0
     */
    Map<String,Object> selectIsExist(@Param("dto")InvestorsHoldInfoRegist001Dto dto);

    /**
     * 批量更新
     * @param updateDataList 数据
     * @see
     * @since 1.0
     */
    void update(@Param("updateDataList")List<InvestorsHoldInfoRegist001Dto> updateDataList);

    /**
     * 批量插入
     * @param insertDataList 数据
     * @see
     * @since 1.0
     */
    void insert(@Param("insertDataList")List<InvestorsHoldInfoRegist001Dto> insertDataList);
}
