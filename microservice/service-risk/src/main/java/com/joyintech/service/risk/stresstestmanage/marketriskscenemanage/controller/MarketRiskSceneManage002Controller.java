/*
 * 文件名：MarketRiskSceneManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.controller;

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
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage002Service;

/**
 * 市场性风险-情景新增
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月4日
 * @see MarketRiskSceneManage002Controller
 * @since 1.0
 */
@RestController("marketRiskSceneManage002Ctl_dtx")
@RequestMapping("/MarketRiskSceneManage002")
public class MarketRiskSceneManage002Controller {
    
    @Autowired
    private MarketRiskSceneManage002Service marketRiskSceneManage002Service;
    
    /**
     * 流动性情景模板
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectSceneTmpl")
    public MarketRiskSceneManage002Dto selectSceneTmpl(@RequestBody Map<String, Object> param) throws Exception {
        return marketRiskSceneManage002Service.selectSceneTmpl(param);
    }
    
    /**
     * 查找模板关联指标
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectTmplIndByType")
    public List<MarketRiskSceneManage002Dto> selectTmplIndByType(@RequestBody Map<String, Object> param) throws Exception {
        return marketRiskSceneManage002Service.selectTmplIndByType(param);
    }
    
    /**
     * 根据指标类型获取指标
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/queryIndexByType")
    public List<MarketRiskSceneManage002Dto> queryIndexByType(@RequestBody Map<String, Object> param) throws Exception {
        return marketRiskSceneManage002Service.queryIndexByType(param);
    }
    
    /**
     * 根据指标类型获取指标
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/querySceneById")
    public MarketRiskSceneManage002Dto querySceneById(@RequestBody Map<String, Object> param) throws Exception {
    	return marketRiskSceneManage002Service.querySceneById(param);
    }
    
    /**
     * 根据情景名称查找数据
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryScentNameCount")
    public List<MarketRiskSceneManage002Dto> queryScentNameCount(@RequestBody Map<String, Object> param) throws Exception {
        return marketRiskSceneManage002Service.queryScentNameCount(param);
    }
    
    /**
     * 查询所有因子二级分类
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFactorTypeList")
    public List<MarketRiskSceneFactorTypeDto> queryFactorTypeList(@RequestBody Map<String, Object> param){
        return marketRiskSceneManage002Service.queryFactorTypeList(param);
    }
    
    /**
     * 情景新建
     * <功能详细描述>
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @DataSource(DataSourceType.STRESSTEST)
    @PostMapping("/insert")
    public void insert(@RequestBody MarketRiskSceneManage002Dto dto) throws Exception {
        marketRiskSceneManage002Service.insert(dto);
    }
    
    /**
     * 情景更新
     * <功能详细描述>
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @DataSource(DataSourceType.STRESSTEST)
    @PostMapping("/update")
    public void update(@RequestBody MarketRiskSceneManage002Dto dto) throws Exception {
        marketRiskSceneManage002Service.update(dto);
    }
}
