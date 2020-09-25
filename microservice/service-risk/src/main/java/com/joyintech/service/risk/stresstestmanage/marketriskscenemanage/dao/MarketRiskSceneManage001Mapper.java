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

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage001Dto;
import com.joyintech.tams.common.base.dao.SttSceneMapper;
import com.joyintech.tams.common.base.entity.SttScene;


/**
 * 市场性风险情景管理通用数据库操作接口
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月3日
 * @see MarketRiskSceneManage001Mapper
 * @since 1.0
 */
@Repository
public interface MarketRiskSceneManage001Mapper extends SttSceneMapper {
    /** 查询情景列表 */
    List<MarketRiskSceneManage001Dto> queryForPage(Map<String, Object> pageParam);
    
    /**  根据情景名称模糊查询情景基本信息 */
    List<SttScene>  fuzzyQueryBySceneNameOrId(String sceneStr);
    
    /**  批量or单个情景启用停用 */
    int updateScenesEffStatus(@Param("sceneIdList") List<String> sceneIdList, @Param("effStatus")String effStatus);
    
    /**  单个情景删除 */
    int deleteScene(String sceneId);
}
