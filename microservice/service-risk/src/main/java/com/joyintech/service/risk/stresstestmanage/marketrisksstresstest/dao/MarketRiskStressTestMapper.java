/*
 * 文件名：FlowRiskSceneManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest001Dto;



/**
 * 
 * @author lifangchen
 * @version 1.0 2019年8月21日
 * @see MarketRiskStressTestMapper
 * @since 1.0
 */
@Repository
public interface MarketRiskStressTestMapper {
    /**
     * 根据情景名称判断数量
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    int stressTestNameCount(Map<String,Object> param);
    /**
     * 判断压力测试表数量
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    int queryStrTesCount();
    /**
     * 调存储过程
     * <一句话功能简述>
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    void stressTestLoad(Map<String,Object> param);
    /**
     * 情景下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> querySceneOption(Map<String,Object> param);
    /**
     * 查找模板下关联的指标
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<MarketRiskStressTest001Dto> selectTmplIndByType(Map<String,Object> param);
    /**
     * 根据情景代码查找模板id
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    MarketRiskStressTest001Dto queryTmplBySceneId(Map<String,Object> param);
    
    /**
     * 判断日期是否节假日
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    MarketRiskStressTest001Dto judgeYesOrNoHoliday(Map<String,Object> param);
    
    /**
     * 判断情景下有无为准备的因子
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    int countFacType(Map<String,Object> param);
    
    /**
     * 更新执行状态
     * @param param
     * @return
     */
    @Update("update STT_STRESS_TEST set exe_status =  #{exeStatus,jdbcType=TIMESTAMP} where test_id = #{in_test_id,jdbcType=VARCHAR}")
    int updateStatus(Map<String,Object> param);
}
