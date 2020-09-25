/*
 * 文件名：FlowRiskSceneManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssetsDto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto.FlowRiskStressTest001Dto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto.FlowRskSupervisionIndicatorDto;



/**
 * 
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskStressTestMapper
 * @since 1.0
 */
@Repository
public interface FlowRiskStressTestMapper {
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
     * 根据情景代码查找是否有申赎净流出基础选择
     * @param param
     * @return
     */
    int queryCountNetOutFlowBySce(Map<String,Object> param);
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
     * 根据情景代码查找模板id
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FlowRiskStressTest001Dto queryTmplBySceneId(Map<String,Object> param);
    
    /**
     * 判断日期是否节假日
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FlowRiskStressTest001Dto judgeYesOrNoHoliday(Map<String,Object> param);
    
    /**
     * 组合名称下拉框
     * @param param
     * @return
     */
    List<SelectOptionDto> selectProdSelectOpt(Map<String,Object> param);
    
    /**
     * 组合tree
     * @param indId
     * @param portfLevel
     * @param portfName
     * @param profitType
     * @param issueMode
     * @param raiseType
     * @param investType
     * @param portfolioType
     * @return
     */
    List<TreeNode<FlowRskSupervisionIndicatorDto>> getAllPortfolioByProfit(@Param(value = "indId") String indId,@Param(value = "portfLevel") String portfLevel,@Param(value = "portfName") List<String> portfName,@Param(value = "profitType") String profitType,@Param(value = "issueMode") String issueMode,@Param(value = "raiseType") String raiseType,@Param(value = "investType") String investType,@Param("portfolioType") String portfolioType);
    List<TreeNode<FlowRskSupervisionIndicatorDto>> getAllPortfolioByOther(@Param(value = "fields") String fields,@Param(value = "portfLevel") String portfLevel,@Param(value = "indId") String indId,@Param(value = "portfName") List<String> portfName,@Param(value = "profitType") String profitType,@Param(value = "issueMode") String issueMode,@Param(value = "raiseType") String raiseType,@Param(value = "investType") String investType,@Param("portfolioType") String portfolioType);

}
