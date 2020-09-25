/*
 * 文件名：RskProdInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.service.riskrdsinf.info.rsk099.entity.InterfAssetInfo;
import com.joyintech.tams.invest.common.batchList.dao.BatchListMapper;

/**
 * 资产基本信息批量存储Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月17日
 * @see RskAssetInfoMapper
 * @since 1.0
 */
@Repository
public interface RskAssetInfoMapper extends BatchListMapper<InterfAssetInfo> {

    /**
     * {@inheritDoc}
     */
    @Override
    default void insertBatchList(List<InterfAssetInfo> infoList) {
        // 删除金融产品基本信息
        deleteAssetList(infoList);
        // 保存金融产品基本信息
        insertAssetList(infoList);
        // 删除金融产品类型信息
        deleteAssetTypeList(infoList);
        // 保存金融产品类型信息
        insertAssetTypeList(infoList);
        // 删除金融产品附属表
        deleteAssetAddList(infoList);
        // 保存金融产品附属表
        insertAssetAddList(infoList);
        // 删除金融产品附属表
        deleteAssetDeptList(infoList);
        // 保存金融产品附属表
        insertAssetDeptList(infoList);
    }

    /**
     * 保存金融产品基本信息
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void insertAssetList(List<InterfAssetInfo> infoList);

    /**
     * 保存金融产品类型信息
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void insertAssetTypeList(List<InterfAssetInfo> infoList);

    /**
     * 保存金融产品附属表
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void insertAssetAddList(List<InterfAssetInfo> infoList);

    /**
     * 保存金融产品附属表
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void insertAssetDeptList(List<InterfAssetInfo> infoList);

    /**
     * 删除金融产品基本信息
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void deleteAssetList(List<InterfAssetInfo> infoList);

    /**
     * 删除金融产品类型信息
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void deleteAssetTypeList(List<InterfAssetInfo> infoList);

    /**
     * 删除金融产品附属表
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void deleteAssetAddList(List<InterfAssetInfo> infoList);

    /**
     * 删除金融产品附属表
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void deleteAssetDeptList(List<InterfAssetInfo> infoList);
}
