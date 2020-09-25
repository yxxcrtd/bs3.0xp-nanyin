/*
 * 文件名：CeAssetPositionManagementService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetpositionmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceassetpositionmanagement.dao.CeAssetPositionManagementMapper;
import com.joyintech.service.risk.creditmanagement.ceassetpositionmanagement.dto.CeAssetPositionManagement001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月16日
 * @see CeAssetPositionManagementService
 * @since 1.0
 */
@Service
public class CeAssetPositionManagementService extends BaseService<CeAssetPositionManagement001Dto>{
    
    /*
     * CeAssetPositionManagementMapper
     */
    @Autowired
    private CeAssetPositionManagementMapper ceAssetPositionManagementMapper;

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<CeAssetPositionManagement001Dto> selectForPage(int page, int length,
                                                               Map<String, Object> param) throws Exception {
        return super.queryForPage(page, length, () ->ceAssetPositionManagementMapper.selectForPage(param));
    }

    /**
     * <一句话功能简述>
     * 校验资产代码是否已存在
     * @param assetCode 资产代码
     * @return
     * @see
     * @since 1.0
     */
    public int check1(String assetCode) {
        return ceAssetPositionManagementMapper.check1(assetCode);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryGroupIdInfo(Map<String, Object> param) {
        return ceAssetPositionManagementMapper.queryGroupIdInfo(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryAssetCodeInfo(Map<String, Object> param) {
        return ceAssetPositionManagementMapper.queryAssetCodeInfo(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    public int updateCeAssetPositionManagement(List<CeAssetPositionManagement001Dto> ceAssetPositionManagement) {
        return ceAssetPositionManagementMapper.updateCeAssetPositionManagement(ceAssetPositionManagement);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param assetType
     * @param assetCode
     * @return
     * @see
     * @since 1.0
     */
    public int check0(String assetType, String assetCode) {
        return ceAssetPositionManagementMapper.check0(assetType, assetCode);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param marketId
     * @return
     * @see
     * @since 1.0
     */
    public String getAssetIdByMarketId(String marketId) {
        return ceAssetPositionManagementMapper.getAssetIdByMarketId(marketId);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param ptlid
     * @param valdate
     * @return
     * @see
     * @since 1.0
     */
    public int deleteByPtlid(Map<String, Object> map) {
        return ceAssetPositionManagementMapper.deleteByPtlid(map);
    }

    /**
     * 更新占用授信额度
     * @param ptlid
     * @return
     * @see
     * @since 1.0
     */
    public int updateLinesByPtlId(String ptlid) {
        return ceAssetPositionManagementMapper.updateLinesByPtlId(ptlid);
    }
}
