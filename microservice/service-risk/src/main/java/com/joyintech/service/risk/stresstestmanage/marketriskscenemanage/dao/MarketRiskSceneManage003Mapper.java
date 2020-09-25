/*
 * 文件名：MarketRiskSceneManage003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage003Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月21日
 * @see MarketRiskSceneManage003Mapper
 * @since 1.0
 */
@Repository
public interface MarketRiskSceneManage003Mapper {
	
	/**
	 * 取序列值作为情景id
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	String getNewSceneId();
	
	/**
	 * 情景详细页面-因子变化信息列表
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
    List<MarketRiskSceneManage003Dto> queryFactorInfoList(Map<String, Object> pageParam);
    
    /**
     * 查询未准备的因子
     * <功能详细描述>
     * @param pageParam {sceneId:情景因子}
     * @return
     * @see
     * @since 1.0
     */
    List<MarketRiskSceneManage003Dto> queryUnreadyFactorList(Map<String, Object> pageParam);
    
    /**根据因子二级分类查询因子*/
    List<MarketRiskSceneFactorTypeDto> queryFactorList(Map<String, Object> pageParam);
    
    /**
     * 调用存储过程查询因子变化信息列表
     * <功能详细描述>
     * @param param
     * @see
     * @since 1.0
     */
    void callQueryFactorInfo(Map<String,Object> param);
}
