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

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage001Dto;
import com.joyintech.tams.common.base.dao.SttSceneMapper;
import com.joyintech.tams.common.base.entity.SttScene;

/**
 * 情景管理通用数据库操作接口
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskSceneManageMapper
 * @since 1.0
 */
@Repository
public interface FlowRiskSceneManageMapper extends SttSceneMapper {
    /** 查询情景列表 */
    List<FlowRiskSceneManage001Dto> queryForPage(Map<String, Object> pageParam);

    /**  根据情景名称|编号 模糊查询情景基本信息 */
    List<SttScene>  selectScene(@Param("scene") String scene);
    
    /**  根据情景ID模糊查询情景基本信息 */
    List<SttScene>  fuzzyQueryBySceneId(String sceneId);
    
    /**  根据情景名称模糊查询情景基本信息 */
    List<SttScene>  fuzzyQueryBySceneName(String sceneName);
    
    /** 查询所有情景，无分页 */
    List<SttScene> selectAll();
    
    /**  批量or单个情景启用停用 */
    int updateScenesEffStatus(@Param("sceneIdList") List<String> sceneIdList, @Param("effStatus")String effStatus);

    /**  单个情景删除 */
    int deleteScene(String sceneId);
    int deleteScene1(String sceneId);
    int deleteScene2(String sceneId);
    int deleteScene3(String sceneId);
    int deleteScene4(String sceneId);
    int deleteScene5(String sceneId);
    int deleteScene6(String sceneId);

    /** 根据条件查询情景列表，无分页 */
    List<SttScene> querycheck(Map<String, Object> paramMap);
    
    /** 情景联想文本框所有情景列表查询，无分页 */
    List<SuggestDto> selectSceneSuggestList();
}
