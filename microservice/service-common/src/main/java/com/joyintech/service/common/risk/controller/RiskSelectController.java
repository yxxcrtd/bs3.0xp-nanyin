/*
 * 文件名：RiskSelectController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.common.risk.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.PtlPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.service.common.risk.dto.RiskSelectDto;
import com.joyintech.service.common.risk.service.RiskSelectService;

/**
 * 获取风险试算的结果
 * @author fanshuanghu
 * @version 1.0 2019年1月17日
 * @see RiskSelectController
 * @since 1.0
 */
@RestController
@RequestMapping("/RiskSelect")
public class RiskSelectController {
    
    @Autowired
    private RiskSelectService riskSelectService;
    /**
     * 风险结果查询
     * @param param 查询的交易编号
     * @return 风险试算结果
     */
    @RequestMapping("select")
    public List<RiskSelectDto> select(@RequestBody Map<String, Object> param) {
        return riskSelectService.select(param);
    }
    /**
     * 获取单位净值
     * @param map 请求参数
     * @return 金融产品估值信息
     * @see
     * @since 1.0
     */
    @RequestMapping("getValue")
    public FinProductValuation getValue(@RequestBody Map<String,Object> map){
        return riskSelectService.getValue(map);
    }

    @RequestMapping("getPtlPortfolioInfo")
    public PtlPortfolio getPtlPortfolioInfo(@RequestBody Map<String,Object> map){
        return riskSelectService.getPtlPortfolioInfo(map);
    }
}
