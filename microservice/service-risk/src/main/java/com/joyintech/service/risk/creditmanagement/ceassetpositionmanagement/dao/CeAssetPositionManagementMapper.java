/*
 * 文件名：CeAssetPositionManagementMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetpositionmanagement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceassetpositionmanagement.dto.CeAssetPositionManagement001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月16日
 * @see CeAssetPositionManagementMapper
 * @since 1.0
 */
@Repository
public interface CeAssetPositionManagementMapper {

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<CeAssetPositionManagement001Dto> selectForPage(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param assetCode
     * @return
     * @see
     * @since 1.0
     */
    int check1(@Param("assetCode") String assetCode);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryGroupIdInfo(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryAssetCodeInfo(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param ceAssetPositionManagement
     * @return
     * @see
     * @since 1.0
     */
    int updateCeAssetPositionManagement(List<CeAssetPositionManagement001Dto> ceAssetPositionManagement);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param assetType
     * @param assetCode
     * @return
     * @see
     * @since 1.0
     */
    int check0(@Param("assetType") String assetType, @Param("assetCode") String assetCode);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param marketId
     * @return
     * @see
     * @since 1.0
     */
    String getAssetIdByMarketId(@Param("marketId") String marketId);
    
    /**
     * 
     * <一句话功能简述>
     * 根据日期和组合代码删除上一次导入的数据
     * @param ptlid
     * @param valdate
     * @return
     * @see
     * @since 1.0
     */
    int deleteByPtlid(Map<String, Object> map);

    /**
     * 
     * <一句话功能简述>
     * 更新占用授信额度
     * @param ptlid
     * @return
     * @see
     * @since 1.0
     */
	int updateLinesByPtlId(@Param("ptlid") String ptlid);


}
