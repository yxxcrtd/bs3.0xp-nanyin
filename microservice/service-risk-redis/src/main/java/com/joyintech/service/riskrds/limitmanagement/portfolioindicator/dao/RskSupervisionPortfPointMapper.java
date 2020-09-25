/*
 * 文件名：RskSupervisionPortfPointMapper.java
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

import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskPortfolioIndicatorDto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicatorDto;
import com.joyintech.service.riskrds.limitmanagement.supervisionindividuation.dto.RskIndividUniteDto;

/**
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionPortfPointMapper
 * @since 1.0
 */
@Repository
public interface RskSupervisionPortfPointMapper {

    List<RskPortfolioIndicator002Dto> selectForPage(Map<String, Object> param);
    List<RskPortfolioIndicator002Dto> selectForBagPage(Map<String, Object> param);
    List<RskPortfolioIndicator002Dto> selectTableIndForPage(Map<String, Object> param);
    int insertSupervisionIndicator(RskSupervisionIndicatorDto rskSupervisionIndicatorDto);
    int insertPortIndicator(RskPortfolioIndicatorDto rskPortfolioIndicatorDto);
    List<TreeNode<RskSupervisionIndicatorDto>> getTreeIndicatorByIndType(@Param(value = "indType") String indType);
    
    // add by zhuzhen 20191128 组合关联指标删除时校验联合设置 start
    /**
     * 根据组合代码，指标代码，查询个性化联合设置数据
     * @param param 参数
     * @return list
     * @see
     * @since 1.0
     */
    List<RskIndividUniteDto> selectIndividUnite(Map<String, Object> param);
    
    /**
     * 根据组合代码，指标代码，删除个性化联合设置数据
     * @param dto
     * @see
     * @since 1.0
     */
    void deleteIndividUnite(RskPortfolioIndicator002Dto dto);
    // add by zhuzhen 20191128 组合关联指标删除时校验联合设置 end

    // add by peijiajun 20191209 组合关联指标删除时删除个性化 start
    void deleteIndividCondition(RskPortfolioIndicator002Dto dto);

    void deleteIndividAssetDel(RskPortfolioIndicator002Dto dto);

    void deleteSpecialStageMapper(RskPortfolioIndicator002Dto dto);
    // add by peijiajun 20191209 组合关联指标删除时删除个性化 end
}
