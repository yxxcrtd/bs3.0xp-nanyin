/*
 * 文件名：MarketRiskSceneManage001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dao.MarketRiskSceneManage001Mapper;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage001Dto;
import com.joyintech.tams.common.base.entity.SttScene;


/**
 * 市场性风险-情景管理数据服务层
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月3日
 * @see MarketRiskSceneManage001Service
 * @since 1.0
 */
@Service("backMarketRiskSceneManage001Service")
public class MarketRiskSceneManage001Service extends BaseService<MarketRiskSceneManage001Dto> {
    
    @Autowired
    private MarketRiskSceneManage001Mapper marketRiskSceneManage001Mapper;
    
    /**
     * 分页按条件查询情景列表
     * <功能详细描述>
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.STRESSTEST)
    @Transactional
    public Page<MarketRiskSceneManage001Dto> selectForPage(int page, int length, Map<String, Object> pageParam) 
        throws Exception{
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        pageParam.put("isTmpl", DictCodeList.是否_否.getValue());
        Page<MarketRiskSceneManage001Dto> pageData = super.queryForPage(page, length, () -> marketRiskSceneManage001Mapper.queryForPage(pageParam));
        DynamicDataSourceHolder.clearDataSource();
        return pageData;
    }

    /**  根据情景名称模糊查询情景基本信息 */
    @DataSource(DataSourceType.STRESSTEST)
    @Transactional
    public List<SttScene>  fuzzyQueryBySceneNameOrId(String sceneStr) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SttScene> dataList = marketRiskSceneManage001Mapper.fuzzyQueryBySceneNameOrId(sceneStr);
        DynamicDataSourceHolder.clearDataSource();
        return dataList;
    }
    
    /**  批量or单个情景启用停用 */
    public int updateScenesEffStatus(List<String> sceneIdList, String effStatus){
        return marketRiskSceneManage001Mapper.updateScenesEffStatus(sceneIdList, effStatus);
    }

    /**  单个情景删除 */
    public int deleteScene(String sceneId) {
        return marketRiskSceneManage001Mapper.deleteScene(sceneId);
    }
    
}
