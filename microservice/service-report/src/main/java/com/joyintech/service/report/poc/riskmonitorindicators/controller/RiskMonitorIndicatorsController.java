/*
 * 文件名：ProductLedgerController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liupeilin
 * 修改时间：2019年9月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.riskmonitorindicators.controller;

import java.util.List;
import java.util.Map; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.riskmonitorindicators.dto.RiskMonitorIndicatorsDto;
import com.joyintech.service.report.poc.riskmonitorindicators.service.RiskMonitorIndicatorsService; 
/**
 * 风险监测指标Controller
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月11日
 * @see RiskMonitorIndicatorsController
 * @since 1.0
 */
@RestController
@RequestMapping("/RiskMonitorIndicators")
public class RiskMonitorIndicatorsController{
    
    @Autowired
    private RiskMonitorIndicatorsService riskMonitorIndicatorsService;
    
    /**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<RiskMonitorIndicatorsDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return riskMonitorIndicatorsService.selectForPage(page, length, param);
    }
    
    /**
     * 产品代码的下拉框
     * 
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param) {
        return riskMonitorIndicatorsService.queryProdIdInfo(param);
    }
    
    /**
     * 理财经理的下拉框
     * 
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryManagerInfo")
    public List<SelectOptionDto> queryManagerInfo(@RequestBody Map<String, Object> param) {
        return riskMonitorIndicatorsService.queryManagerInfo(param);
    }
    
}
