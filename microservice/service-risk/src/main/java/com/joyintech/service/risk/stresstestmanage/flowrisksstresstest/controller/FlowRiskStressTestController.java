/*
 * 文件名：FlowRiskStressTestController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto.FlowRiskStressTest001Dto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto.FlowRskSupervisionIndicatorDto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.service.FlowRiskStressTestService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年9月5日
 * @see FlowRiskStressTestController
 * @since 1.0
 */
@RestController("FlowRiskStressTest001PointCtl_dtx")
@RequestMapping("/FlowRiskStressTest001")
public class FlowRiskStressTestController {
    
    @Autowired
    private FlowRiskStressTestService flowRiskStressTestService;
    
    /**
     * 根据压力测试名称判断数量
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("stressTestNameCount")
    public int stressTestNameCount(@RequestBody Map<String, Object> map){
        return flowRiskStressTestService.stressTestNameCount(map);
    }
    
    /**
     * 
     * 情景名称下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("querySceneOption")
    public List<SelectOptionDto> querySceneOption(@RequestBody Map<String, Object> map){
        return flowRiskStressTestService.querySceneOption(map);
    }
    /**
     * 执行压力测试
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
//    @Transactional
//    @DTXTransaction
//    @DataSource(DataSourceType.STRESSTEST)
    @PostMapping("Asset")
    public FlowRiskStressTest001Dto Asset(@RequestBody FlowRiskStressTest001Dto dto) throws Exception {
        return flowRiskStressTestService.asset(dto);
    }
    
    /**
     * 压力测试存储过程
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @see
     * @since 1.0
     */
    @PostMapping("stressTestLoad")
    public void stressTestLoad(@RequestBody Map<String,Object> param) {
        flowRiskStressTestService.stressTestLoad(param);
    }
    
    /**
     * 判断日期是否法定节假日
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("judgeYesOrNoHoliday")
    public FlowRiskStressTest001Dto judgeYesOrNoHoliday(@RequestBody Map<String,Object> param){
        return flowRiskStressTestService.judgeYesOrNoHoliday(param);
    }
    
    /**
     * 
     * @param param
     * @return
     */
    @PostMapping("selectProdSelectOpt")
    public List<SelectOptionDto> selectProdSelectOpt(@RequestBody Map<String,Object> param){
        return flowRiskStressTestService.selectProdSelectOpt(param);
    }
    
    /**
     * 组合树
     *
     * @param param
     * @return
     */
    @GetMapping("/getAllPortfolioByProfit")
    public List<TreeNode<FlowRskSupervisionIndicatorDto>> getAllPortfolioByProfit(String indId, String portfName, String profitType, String issueMode, String raiseTypeCond, String investTypeCond) {
        return flowRiskStressTestService.getAllPortfolioByProfit(indId, portfName, profitType, issueMode, raiseTypeCond, investTypeCond);
    }

    @GetMapping("/getAllPortfolioByOther")
    public List<TreeNode<FlowRskSupervisionIndicatorDto>> getAllPortfolioByOther(String fields, String indId, String portfName, String profitType, String issueMode, String raiseTypeCond, String investTypeCond) {
        return flowRiskStressTestService.getAllPortfolioByOther(fields, indId, portfName, profitType, issueMode, raiseTypeCond, investTypeCond);
    }
    

}
