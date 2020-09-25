/*
 * 文件名：FlowRiskSceneManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2019年8月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dao.FlowRiskSceneManageMapper;
import com.joyintech.tams.common.base.entity.SttScene;

/**
 * 流动性情景管理通用服务-Service
 * @author Jiaoxujin
 * @version 1.0 2019年8月26日
 * @see FlowRiskSceneManageService
 * @since 1.0
 */
@Service
public class FlowRiskSceneManageService extends BaseService<SttScene> {

    @Autowired
    private FlowRiskSceneManageMapper flowRiskSceneManageMapper;

    /** 根据条件查询情景列表，无分页 */
    public List<SttScene> querycheck(Map<String, Object> paramMap) throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SttScene> dataList = flowRiskSceneManageMapper.querycheck(paramMap);
        DynamicDataSourceHolder.clearDataSource();
        return dataList;
    }
    
    /** 查询所有情景，无分页 */
    public List<SttScene> selectAll() throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SttScene> dataList = flowRiskSceneManageMapper.selectAll();
        DynamicDataSourceHolder.clearDataSource();
        return dataList;
    }
    /**  根据情景名称|编号 模糊查询情景基本信息 */
    public List<SttScene>  selectScene(String scene) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SttScene> dataList = flowRiskSceneManageMapper.selectScene(scene);
        DynamicDataSourceHolder.clearDataSource();
        return dataList;
    }
    
    /**  根据情景ID模糊查询情景基本信息 */
    public List<SttScene>  selectSceneById(String sceneId) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SttScene> dataList = flowRiskSceneManageMapper.fuzzyQueryBySceneId(sceneId);
        DynamicDataSourceHolder.clearDataSource();
        return dataList;
    }

    /**  根据情景名称模糊查询情景基本信息 */
    public List<SttScene>  selectSceneByName(String sceneName) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SttScene> dataList = flowRiskSceneManageMapper.fuzzyQueryBySceneName(sceneName);
        DynamicDataSourceHolder.clearDataSource();
        return dataList;
    }

    /**
     * 情景联想文本框service层实现
     *
     * @param code 情景代码
     * @param name 情景名称
     * @return List&lt;SuggestDto&gt;
     */
    //不看好在JAVA中过滤，若数据太多怎么办？查询是否有缓存？JIAOXUJIN
    public List<SuggestDto> getSceneSuggestList(String code, String name) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SuggestDto> dataList = queryForSuggest(flowRiskSceneManageMapper::selectSceneSuggestList, code, name);
        DynamicDataSourceHolder.clearDataSource();
        return dataList;
    }
}
