/*
 * 文件名：AssetClassManagement001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productplanning.assetclassmanagement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.product.productplanning.assetclassmanagement.dto.AssetClassManagementDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2019年12月11日
 * @see AssetClassManagementMapper
 * @since 1.0
 */
@Repository
public interface AssetClassManagementMapper {

    /**
     * 界面主查询
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
    List<AssetClassManagementDto> selectForPage(Map<String, Object> parameters);
    /**
     * 插入
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
	void insertSelective(AssetClassManagementDto assetClassManagementDto);
	  /**
     * 更新
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
	int doupdate(AssetClassManagementDto dto);
	  /**
     * 根据主键查询
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
    List<AssetClassManagementDto> selectByPrimaryKey(Map<String, Object> param);
    /**
     * 删除
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
	int deleteByPrimaryKey(AssetClassManagementDto keyDto);
}
