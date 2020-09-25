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

import com.joyintech.service.riskrdsinf.info.rsk099.entity.PrdCommonInfo;
import com.joyintech.tams.invest.common.batchList.dao.BatchListMapper;

/**
 * 产品基本信息批量存储Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月17日
 * @see RskProdInfoMapper
 * @since 1.0
 */
@Repository
public interface RskProdInfoMapper extends BatchListMapper<PrdCommonInfo> {

    /**
     * {@inheritDoc}
     */
    @Override
    default void insertBatchList(List<PrdCommonInfo> infoList) {
        // 删除金融产品基本信息
        deleteProdList(infoList);
        // 保存金融产品基本信息
        insertProdList(infoList);
        // 删除金融产品附属表
        deleteProdAddList(infoList);
        // 保存金融产品附属表
        insertProdAddList(infoList);
    }

    /**
     * 删除金融产品基本信息
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void deleteProdList(List<PrdCommonInfo> infoList);

    /**
     * 删除金融产品附属表
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void deleteProdAddList(List<PrdCommonInfo> infoList);

    /**
     * 保存金融产品基本信息
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void insertProdList(List<PrdCommonInfo> infoList);

    /**
     * 保存金融产品附属表
     * 
     * @param infoList 数据列表
     * @see
     * @since 1.0
     */
    void insertProdAddList(List<PrdCommonInfo> infoList);
}
