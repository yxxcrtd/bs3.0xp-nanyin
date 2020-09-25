/*
 * 文件名：RskSupervisionPortfPoint002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicator002Dto;

/**
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionPortfPoint002Mapper
 * @since 1.0
 */
@Repository
public interface RskSupervisionPortfPoint002Mapper {

    List<RskSupervisionIndicator002Dto> queryRelatedIndList(Map<String, Object> param);
    
    String getPortfolioConst(@Param("portfolioCode")String portfolioCode);
    
    RskPortfolioIndicator002Dto queryPortfIndByPortfId(@Param("portfolioCode")String portfolioCode,@Param("indId")String indId);
    
    int searchRelatedIndByPortf(@Param("indId")String indId,@Param("portfolioId")String portfolioId);
    int searchRelatedIndByPortfBag(@Param("indId")String indId,@Param("portfolioId")String portfolioId);
    int searchRelatedIndByPortfBagSingle(@Param("indId")String indId,@Param("portfolioId")String portfolioId);
}
