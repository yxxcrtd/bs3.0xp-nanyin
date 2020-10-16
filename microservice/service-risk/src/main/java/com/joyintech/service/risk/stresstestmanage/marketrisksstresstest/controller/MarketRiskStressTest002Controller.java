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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto.FlowRiskStressTest001Dto;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest001Dto;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest002Dto;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.service.MarketRiskStressTest002Service;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.service.MarketRiskStressTestService;
import com.joyintech.service.risk.stresstestmanage.riskfactor.controller.RiskFactor003Controller;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年9月5日
 * @see MarketRiskStressTest002Controller
 * @since 1.0
 */
@RestController("backMarketRiskStressTest002Controller")
@RequestMapping("/MarketRiskStressTest002")
public class MarketRiskStressTest002Controller {
    
    @Autowired
    private MarketRiskStressTest002Service marketRiskStressTest002Service;
    
    /**
     * 查询产品持仓
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("selectProdForPage/{page}/{length}")
    public Page<MarketRiskStressTest002Dto> selectProdForPage(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return marketRiskStressTest002Service.selectProdForPage(page, length, param);
    }
    
    /**
     * 查询未准备因子
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("selectFacForPage/{page}/{length}")
    public Page<MarketRiskStressTest002Dto> selectFacForPage(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return marketRiskStressTest002Service.selectFacForPage(page, length, param);
    }
    
}
