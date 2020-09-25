/*
 * 文件名：FlowRiskSceneManage001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：jiaoxujin
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dao.FlowRiskSceneManageMapper;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage001Dto;

/**
 * 流动性风险-情景管理数据服务层-Service
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskSceneManage001Service
 * @since 1.0
 */
@Service
public class FlowRiskSceneManage001Service extends BaseService<FlowRiskSceneManage001Dto> {
	
	@Autowired
	private FlowRiskSceneManageMapper flowRiskSceneManageMapper;

    /**
     * 根据多个查询条件查询情景列表
     */
//	@Transactional
//	@DataSource(DataSourceType.STRESSTEST)
    public Page<FlowRiskSceneManage001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        Page<FlowRiskSceneManage001Dto> pageData = super.queryForPage(page, length, () -> flowRiskSceneManageMapper.queryForPage(pageParam));
        DynamicDataSourceHolder.clearDataSource();
        return pageData;
    }

    /**  批量or单个情景启用停用 */
    @Transactional
    @DataSource(DataSourceType.STRESSTEST)
    public int updateScenesEffStatus(List<String> sceneIdList, String effStatus){
        return flowRiskSceneManageMapper.updateScenesEffStatus(sceneIdList, effStatus);
    }
    
    /**  单个情景删除 */
    @Transactional
    @DataSource(DataSourceType.STRESSTEST)
    public int deleteScene(String sceneId) {
    	flowRiskSceneManageMapper.deleteScene1(sceneId);
    	flowRiskSceneManageMapper.deleteScene2(sceneId);
    	flowRiskSceneManageMapper.deleteScene3(sceneId);
    	flowRiskSceneManageMapper.deleteScene4(sceneId);
    	flowRiskSceneManageMapper.deleteScene5(sceneId);
    	flowRiskSceneManageMapper.deleteScene6(sceneId);
        return flowRiskSceneManageMapper.deleteScene(sceneId);
    }
}
