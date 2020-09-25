/*
 * 文件名：BussFinProductLayeredInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod;

import java.util.List;

import com.joyintech.tams.component.business.prod.dto.BussFinProductLayeredInfoDto;


/**
 * 产品投向组件接口
 * @author 张亮
 * @version 1.0 2018年9月29日
 * @see BussFinProductLayeredInfoService
 * @since 1.0
 */
public interface BussFinProductLayeredInfoService {
	
	/**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductLayeredInfoDto";
    
    /**
     * 根据金融产品代码查询分层信息
     * 
     * @param finprodId 金融产品代码
     * @return 产品分层信息
     * @see
     * @since 1.0
     */
    List<BussFinProductLayeredInfoDto> productLayeredInfoQuery(String finprodId);
    
    /**
     * 根据金融产品代码查询产品投向信息
     * 
     * @param finprodId 金融产品代码
     * @return 产品投向信息
     * @see
     * @since 1.0
     */
    List<BussFinProductLayeredInfoDto> productLayeredInfoQueryByWflId(String workflowId, String beanName);
    
    /**
     * 保存产品投向信息
     * 
     * @param bussFinProductLayeredInfoDto 产品投向信息
     * @see
     * @since 1.0
     */
    void insert(BussFinProductLayeredInfoDto bussFinProductLayeredInfoDto);
    
    /**
     * 根据金融产品代码删除产品投向信息
     * 
     * @param finprodId 金融产品代码
     * @see
     * @since 1.0
     */
    void delete(String finprodId);
    
    /**
     * 更新产品投向信息
     * 
     * @param bussFinProductLayeredInfoDto 产品投向信息
     * @see
     * @since 1.0
     */
    void update(BussFinProductLayeredInfoDto bussFinProductLayeredInfoDto);

}
