/*
 * 文件名：MarketRiskSceneManage001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.tams.common.base.entity.SttSceneFactor;
import com.joyintech.tams.common.base.entity.SttSceneFtrtips;


/**
 * 市场性风险情景管理通用数据库操作接口
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月3日
 * @see MarketRiskSceneManage002Mapper
 * @since 1.0
 */
@Repository
public interface MarketRiskSceneManage002Mapper {
    /** 查询情景模板 */
    MarketRiskSceneManage002Dto selectSceneTmpl(Map<String, Object> pageParam);
    
    /**  查找模板关联指标 */
    List<MarketRiskSceneManage002Dto> selectTmplIndByType(Map<String, Object> pageParam);
    
    /**  根据指标类型查找指标 */
    List<MarketRiskSceneManage002Dto> queryIndexByType(Map<String, Object> pageParam);
    
    /**  根据情景名称查找数据 */
    List<MarketRiskSceneManage002Dto> queryScentNameCount(Map<String, Object> pageParam);
    
    /**  根据情景代码得到情景信息 */
    MarketRiskSceneManage002Dto querySceneById(Map<String, Object> pageParam);
    
    /**删除情景关联的风险因子*/
    int deleteSceneFactor(String sceneId);
    
    /**查询因子二级分类*/
    List<MarketRiskSceneFactorTypeDto> queryFactorTypeList(Map<String, Object> pageParam);
    
    /**根据情景因子查询因子二级分类*/
    List<MarketRiskSceneFactorTypeDto> queryFactorTypeListById(Map<String, Object> pageParam);
       
    /**向曲线因子值表中插入数据*/
    int insertSceneCurveList(Map<String, Object> pageParam);
    
    /**插入情景因子集合*/
    int insertSceneFactorList(List<SttSceneFactor> sceneFacs);
    
    /**插入情景因子分类集合*/
    int insertSceneFtrtipsList(SttSceneFtrtips sceneFtrtip);
}
