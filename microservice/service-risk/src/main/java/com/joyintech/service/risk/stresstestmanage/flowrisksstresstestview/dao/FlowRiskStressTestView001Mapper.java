/*
 * 文件名：RskValtblPrtBotassetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView001Dto;
import com.joyintech.tams.common.base.entity.SttScene;

/**
 * 底层资产映射
 * 〈功能详细描述〉
 * @author chenyunlong
 * @version 1.0 2019年8月23日
 * @see FlowRiskStressTestView001Mapper
 * @since 1.0
 */
@Repository
public interface FlowRiskStressTestView001Mapper {

    List<FlowRiskStressTestView001Dto> queryForPage(Map<String, Object> pageParam);
    
    List<SelectOptionDto> queryTestList(Map<String, Object> pageParam);
    
    List<FlowRiskStressTestView001Dto> initPage(Map<String, Object> pageParam);
    /**  根据情景名称模糊查询情景基本信息 */
    List<SttScene>  fuzzyQueryBySceneNameOrId(String sceneStr);
}
