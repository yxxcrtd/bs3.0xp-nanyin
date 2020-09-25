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
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView002Dto;

/**
 * 底层资产映射
 * 〈功能详细描述〉
 * @author chenyunlong
 * @version 1.0 2019年8月23日
 * @see FlowRiskStressTestView002Mapper
 * @since 1.0
 */
@Repository
public interface FlowRiskStressTestView002Mapper {

    List<FlowRiskStressTestView002Dto> queryForPage(Map<String, Object> pageParam);
    /**
     * 根据测试代码得到测试明细信息
     * @param pageParam
     * @return
     */
    FlowRiskStressTestView002Dto queryDetByTestId(Map<String, Object> pageParam);
    
   
}
