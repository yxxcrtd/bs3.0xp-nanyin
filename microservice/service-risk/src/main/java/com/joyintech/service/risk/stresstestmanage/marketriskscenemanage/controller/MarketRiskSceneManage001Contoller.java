/*
 * 文件名：MarketRiskSceneManage001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage001Dto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage001Service;
import com.joyintech.tams.common.base.entity.SttScene;

/**
 * 市场性风险情景管理通用服务
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月3日
 * @see MarketRiskSceneManage001Contoller
 * @since 1.0
 */
@RestController("marketRiskSceneManage001Ctl_dtx")
@RequestMapping("/MarketRiskSceneManage001")
public class MarketRiskSceneManage001Contoller {

    @Autowired
    private MarketRiskSceneManage001Service marketRiskSceneManage001Service;
    
    /**
     * 根据多个查询条件查询情景列表
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<MarketRiskSceneManage001Dto> selectForPage(@RequestBody Map<String, Object> param,
                                                         @PathVariable("page") int page,
                                                         @PathVariable("length") int length)
        throws Exception {
        return marketRiskSceneManage001Service.selectForPage(page, length, param);
    }
    
    /**
     * 批量or单个情景启用停用 
     */
    @Transactional
    @DTXTransaction
    @DataSource(DataSourceType.STRESSTEST)
    @PostMapping("/updateScenesEffStatus")
    public Integer updateScenesEffStatus(@RequestBody Map<String, Object> param) throws Exception {
        List<String> sceneIdList = null;
        String effStatus = null;
        
        Object effStatusObj = param.get("effStatus");
        Object sceneIds = param.get("sceneIds");
        if (effStatusObj != null) {
            effStatus = effStatusObj.toString();
        }
        if (sceneIds != null) {
            sceneIdList = Arrays.asList(sceneIds.toString().split(","));
        }

        return marketRiskSceneManage001Service.updateScenesEffStatus(sceneIdList, effStatus);
    }
    
    /**
     * 单个情景删除 
     */
    @Transactional
    @DTXTransaction
    @DataSource(DataSourceType.STRESSTEST)
    @PostMapping("/deleteScene")
    public void deleteScene(@RequestBody Map<String, Object> param) throws Exception {
        Object sceneIdObj = param.get("sceneId");

        if (sceneIdObj != null && StringUtils.isNotBlank(sceneIdObj.toString())) {
            marketRiskSceneManage001Service.deleteScene(sceneIdObj.toString());
        }
    }
    
    @PostMapping("fuzzySelectScene")
    public List<SelectOptionDto> fuzzySelectScene(@RequestBody Map<String, Object> param) throws Exception {
        //CommonUtil.hasChinese(fuzzyText)
        List<SelectOptionDto> resultList = new ArrayList<>();
        List<SttScene> dataList = null;
        
        Object sceneStr = param.get("sceneStr");
        if(sceneStr==null){
        	sceneStr = "";
        }
        
        dataList = marketRiskSceneManage001Service.fuzzyQueryBySceneNameOrId(sceneStr.toString());

        if (CollectionUtils.isNotEmpty(dataList)) {
            dataList.forEach(p -> {
                SelectOptionDto option = new SelectOptionDto();
                option.setId(p.getSceneId());
                option.setText(p.getSceneName());
                
                resultList.add(option);
            });
        }

        return resultList;
    }
}
