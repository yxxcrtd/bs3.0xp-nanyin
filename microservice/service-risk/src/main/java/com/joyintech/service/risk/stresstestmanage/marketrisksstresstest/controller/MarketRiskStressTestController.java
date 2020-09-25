/*
 * 文件名：FlowRiskStressTestController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto.FlowRiskStressTest001Dto;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest001Dto;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.service.MarketRiskStressTestService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年9月5日
 * @see MarketRiskStressTestController
 * @since 1.0
 */
@RestController("MarketRiskStressTest001PointCtl_dtx")
@RequestMapping("/MarketRiskStressTest001")
public class MarketRiskStressTestController {
    
    @Autowired
    private MarketRiskStressTestService marketRiskStressTestService;
    
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
        return marketRiskStressTestService.stressTestNameCount(map);
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
        return marketRiskStressTestService.querySceneOption(map);
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
    public MarketRiskStressTest001Dto Asset(@RequestBody MarketRiskStressTest001Dto dto) throws Exception {
        return marketRiskStressTestService.asset(dto);
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
    	marketRiskStressTestService.stressTestLoad(param);
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
    public MarketRiskStressTest001Dto judgeYesOrNoHoliday(@RequestBody Map<String,Object> param){
        return marketRiskStressTestService.judgeYesOrNoHoliday(param);
    }
    
    /**
     * 判断情景下有无为准备的因子
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("countFacType")
    public int countFacType(@RequestBody Map<String,Object> param){
        return marketRiskStressTestService.countFacType(param);
    }
}
