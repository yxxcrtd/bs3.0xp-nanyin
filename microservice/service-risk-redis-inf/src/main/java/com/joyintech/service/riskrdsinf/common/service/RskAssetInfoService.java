/*
 * 文件名：RskAssetInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.common.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.service.riskrdsinf.common.dao.RskAssetInfoMapper;
import com.joyintech.service.riskrdsinf.info.rsk099.entity.InterfAssetInfo;
import com.joyintech.redis.annotation.Listener;
import com.joyintech.redis.msg.consumer.IConsumer;
import com.joyintech.redis.msg.entity.MessageBody.Operation;
import com.joyintech.tams.invest.common.batchList.service.BatchListService;

/**
 * 批量插入资产基本信息Sevice
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月17日
 * @see RskAssetInfoService
 * @since 1.0
 */
@Service
@Listener
public class RskAssetInfoService implements IConsumer<InterfAssetInfo> {

    @Autowired
    private BatchListService<InterfAssetInfo> service;

    @Autowired
    private RskAssetInfoMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void consume(Operation opr, Collection<InterfAssetInfo> vList) {
        service.setBatchListMapper(mapper);
        service.consume(opr, vList);
    }

    /**
     * 批量插入
     * 
     * @param infoList 登录数据列表
     * @param offset 分批登录数量
     * @see
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void insertBatchList(List<InterfAssetInfo> infoList, int offset) {
        service.setBatchListMapper(mapper);
        service.insertBatchList(infoList, offset);
    }

    /**
     * 批量插入
     * 
     * @param infoList 登录数据列表
     * @see
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void insertBatchList(List<InterfAssetInfo> infoList) {
        service.setBatchListMapper(mapper);
        // 每500条数据登陆一次数据库
        service.insertBatchList(infoList, 500);
    }

}
