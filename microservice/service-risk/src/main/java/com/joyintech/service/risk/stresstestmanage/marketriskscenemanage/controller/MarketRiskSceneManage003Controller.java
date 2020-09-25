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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage003Dto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage003Service;
import com.joyintech.tams.common.base.entity.SttSceneFactor;

/**
 * 市场性风险-情景详情
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月4日
 * @see MarketRiskSceneManage003Controller
 * @since 1.0
 */
@RestController("marketRiskSceneManage003Ctl_dtx")
@RequestMapping("/MarketRiskSceneManage003")
public class MarketRiskSceneManage003Controller {
    
    @Autowired
    private MarketRiskSceneManage003Service marketRiskSceneManage003Service;
    
    /**
     * 取情景id序列值
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getNewSceneId")
    public String getNewSceneId(){
    	return marketRiskSceneManage003Service.getNewSceneId();
    }
    
    /**
     * 查询未准备的因子
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryUnreadyFactorList")
    public List<MarketRiskSceneManage003Dto> queryUnreadyFactorList(@RequestBody Map<String, Object> pageParam){
    	return marketRiskSceneManage003Service.queryUnreadyFactorList(pageParam);
    }
    
	/**
	 * 执行存储过程
	 * <功能详细描述>
	 * @param ftrList
	 * @param startDat
	 * @param endDate
	 * @return
	 * @see
	 * @since 1.0
	 */
    @PostMapping("/executeProcedure")
    public List<MarketRiskSceneManage003Dto> executeProcedure(@RequestBody Map<String, Object> param){
    	String ftrIdStr = (String) param.get("ftrIdStr");
    	String startDat = (String) param.get("startDate");
    	String endDate = (String) param.get("endDate");
    	return marketRiskSceneManage003Service.executeProcedure(ftrIdStr, startDat, endDate);
    }
    
    /**
     * 更新情景因子的变化值
     * <功能详细描述>
     * @param scFactorsList
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @DataSource(DataSourceType.STRESSTEST)
    @PostMapping("/updateSceneFactor")
    public int updateSceneFactor(@RequestBody Map<String, Object> param){
    	String listJsonStr = (String) param.get("updateListJsonStr");
    	List<SttSceneFactor> scFactorsList = JSONObject.parseArray(listJsonStr, SttSceneFactor.class);
    	return marketRiskSceneManage003Service.updateSceneFactor(scFactorsList);
    }
    
    /**
     * 情景详细页面-因子变化信息列表
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFactorInfoList")
    public List<MarketRiskSceneManage003Dto> queryFactorInfoList(@RequestBody Map<String, Object> pageParam){
    	return marketRiskSceneManage003Service.queryFactorInfoList(pageParam);
    }
    
    /**
     * 情景详细页面-因子变化信息列表
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFactorList")
    public List<MarketRiskSceneFactorTypeDto> queryFactorList(@RequestBody Map<String, Object> pageParam){
    	return marketRiskSceneManage003Service.queryFactorList(pageParam);
    }
    
//    /**
//     * 查询因子变化信息
//     * <功能详细描述>
//     * @param origin
//     * @param sceneId
//     * @param dto
//     * @return
//     * @throws Exception
//     * @see
//     * @since 1.0
//     */
//    @Transactional
//    @DataSource(DataSourceType.STRESSTEST)
//    @PostMapping("/saveAndqueryFactorInfoList")
//    public List<MarketRiskSceneManage003Dto> saveAndqueryFactorInfoList(@RequestBody Map<String, Object> param) throws Exception {
//    	String origin = (String) param.get("origin");
//    	String sceneId = null;
//    	String dtoStr = JSONObject.toJSONString(param.get("entity"));
//    	MarketRiskSceneManage002Dto dto = null;
//    	if("manage".equals(origin)){
//    		sceneId = (String) param.get("sceneId");
//    	}else{
//    		dto = JSONObject.parseObject(dtoStr, new TypeReference<MarketRiskSceneManage002Dto>(){});
//    	}
//    	List<MarketRiskSceneManage003Dto> ss = marketRiskSceneManage003Service.queryFactorInfoList2(origin, sceneId, dto);
//        return ss;
//    }
}
