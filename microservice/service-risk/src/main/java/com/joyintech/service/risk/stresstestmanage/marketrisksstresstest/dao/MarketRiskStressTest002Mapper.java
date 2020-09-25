/*
 * 文件名：FlowRiskSceneManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest002Dto;



/**
 * 
 * @author lifangchen
 * @version 1.0 2019年8月21日
 * @see MarketRiskStressTest002Mapper
 * @since 1.0
 */
@Repository
public interface MarketRiskStressTest002Mapper {
    /**
     * 根据情景代码查找起止日期
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    MarketRiskStressTest002Dto selectDateBySceId(Map<String,Object> param);
    
    /**
     * 查找产品持仓
     * @param pageParam
     * @return
     */
    List<MarketRiskStressTest002Dto> selectProdForPage(Map<String, Object> pageParam);
    
    /**
     * 未准备因子
     * @param pageParam
     * @return
     */
    List<MarketRiskStressTest002Dto> selectFacForPage(Map<String, Object> pageParam);

    
}
