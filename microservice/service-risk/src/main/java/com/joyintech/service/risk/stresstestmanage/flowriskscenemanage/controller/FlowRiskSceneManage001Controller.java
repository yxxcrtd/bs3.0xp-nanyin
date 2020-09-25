/*
 * 文件名：FlowRiskSceneManage001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage001Dto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.service.FlowRiskSceneManage001Service;

/**
 * 流动性风险-情景管理
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskSceneManage001Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/FlowRiskSceneManage001")
public class FlowRiskSceneManage001Controller {

    /**
     * FlowRiskSceneManage001Service
     */
    @Autowired
    private FlowRiskSceneManage001Service flowRiskSceneManage001Service;
    
    /**
     * 根据多个查询条件查询情景列表
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FlowRiskSceneManage001Dto> selectForPage(@RequestBody Map<String, Object> param,
                                                         @PathVariable("page") int page,
                                                         @PathVariable("length") int length)
        throws Exception {
        param.put("isEnabled", MultilevelDataAuthorityUtil.isEnabled());
        return flowRiskSceneManage001Service.selectForPage(page, length, param);
    }

    /**
     * 批量or单个情景启用停用 
     */
    @PostMapping("/updateScenesEffStatus")
    public Integer updateScenesEffStatus(@RequestBody Map<String, Object> param) throws Exception {
        // 收不到参数
        //@ModelAttribute(value="inputBean")  SceneBatchOperationPojo inputBean
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

        return flowRiskSceneManage001Service.updateScenesEffStatus(sceneIdList, effStatus);
    }

    /**
     * 单个情景删除 
     */
    @PostMapping("/deleteScene")
    public void deleteScene(@RequestBody Map<String, Object> param) throws Exception {
        // 收不到参数
        //@RequestParam("sceneId")String sceneId
        //@RequestBody FlowRiskSceneManage001Dto ?
        Object sceneIdObj = param.get("sceneId");

        if (sceneIdObj != null && StringUtils.isNotBlank(sceneIdObj.toString())) {
            flowRiskSceneManage001Service.deleteScene(sceneIdObj.toString());
        }
    }
}
