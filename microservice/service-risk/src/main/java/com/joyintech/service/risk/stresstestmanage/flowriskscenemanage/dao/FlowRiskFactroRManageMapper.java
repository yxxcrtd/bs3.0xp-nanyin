/*
 * 文件名：FlowRiskSceneManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskFactroRManageDto;


/**
 * 
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskFactroRManageMapper
 * @since 1.0
 */
@Repository
public interface FlowRiskFactroRManageMapper {
    List<FlowRiskFactroRManageDto> querycheck(Map<String, Object> pageParam);
}
