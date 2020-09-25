/*
 * 文件名：FlowRiskSceneManageController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2019年8月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.service.FlowRiskSceneManageService;
import com.joyintech.tams.common.base.entity.SttScene;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 流动性情景管理通用服务-Controller
 * @author Jiaoxujin
 * @version 1.0 2019年8月26日
 * @see FlowRiskSceneManageController
 * @since 1.0
 */
@RestController
@RequestMapping("/FlowRiskSceneManage")
public class FlowRiskSceneManageController {

    /**
     * FlowRiskSceneManageService
     */
    @Autowired
    private FlowRiskSceneManageService flowRiskSceneManageService;

    /** 根据条件查询情景列表，无分页 */
    @PostMapping("querycheck")
    public List<SttScene> querycheck(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManageService.querycheck(param);
    }

    /**
     * 情景联想文本框service层实现
     *
     * @param searchText 情景代码或情景名称
     * @return &lt;SuggestDto&gt;
     */
    @PostMapping("getSceneSuggestList")
    public List<SuggestDto> getSceneSuggestList(@RequestBody Map<String, Object> paramMap) throws Exception {
        // 收不到参数
        //@RequestParam("fuzzyText")String fuzzyText
        Object fuzzyTextObj = paramMap.get("fuzzyText");
        
        if (fuzzyTextObj != null) {
            if (CommonUtil.hasChinese(fuzzyTextObj.toString())) {
                return flowRiskSceneManageService.getSceneSuggestList(null, fuzzyTextObj.toString());
            }
            else {
                return flowRiskSceneManageService.getSceneSuggestList(fuzzyTextObj.toString(), null);
            }
        }

        return new ArrayList<SuggestDto>();
    }

    /**
     * 情景搜索页面中的情景编码/名称输入框的自动匹配搜索
     */
    //@GetMapping("/fuzzySelectScene")
    //public List<SelectOptionDto> fuzzySelectScene(@RequestParam("fuzzyText")String fuzzyText) throws Exception {
    @PostMapping("fuzzySelectScene")
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param) throws Exception {
        List<SelectOptionDto> resultList = new ArrayList<>();
        List<SttScene> dataList = null;
        Object sceneId = null;
        Object sceneName = null;

        Object fuzzyObject = param.get("scenes");
        if (fuzzyObject != null) {
            if (CommonUtil.hasChinese(fuzzyObject.toString())) {
                sceneName = fuzzyObject;
            }
            else {
                sceneId = fuzzyObject;
            }
        }

        dataList = flowRiskSceneManageService.selectScene(fuzzyObject.toString());
        /*if (sceneId != null && StringUtils.isNotBlank(sceneId.toString())) {
            dataList = flowRiskSceneManageService.selectSceneById(sceneId.toString());
        }
        if (sceneName != null && StringUtils.isNotBlank(sceneName.toString())) {
            dataList = flowRiskSceneManageService.selectSceneByName(sceneName.toString());
        }
        if (sceneId == null && sceneName == null) {
            dataList = flowRiskSceneManageService.selectSceneById(null);
        }*/

        if (CollectionUtils.isNotEmpty(dataList)) {
            dataList.forEach(p -> {
                SelectOptionDto option = new SelectOptionDto();
                option.setId(p.getSceneId());
                option.setText(p.getSceneName());
                //option.setObject(p);
                //option.setCol(ChineseCharToEnUtil.getFirstSpell(p.getSceneName()));
                
                resultList.add(option);
            });
        }

        return resultList;
    }

    /** 
     * 查询所有情景，无分页 
     * @throws Exception 
     */
    @GetMapping("getAllSceneList")
    public List<SttScene> getAllSceneList() throws Exception {
        return flowRiskSceneManageService.selectAll();
    }
}
