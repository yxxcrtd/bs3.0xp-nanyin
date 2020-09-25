/*
 * 文件名：FlowRiskStressTestViewMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView003Dto;
import com.joyintech.tams.common.base.dao.SttSceneMapper;
import com.joyintech.tams.common.base.entity.SttScene;


/**
 * 〈现金流详细〉
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年8月30日
 * @see FlowRiskStressTestView003Mapper
 * @since 1.0
 */
@Repository
public interface FlowRiskStressTestView003Mapper extends SttSceneMapper {

    /** 根据条件查询现金流详细 */
    List<FlowRiskStressTestView003Dto> getCashFlowDetail(Map<String, Object> paramMap);
    
}
