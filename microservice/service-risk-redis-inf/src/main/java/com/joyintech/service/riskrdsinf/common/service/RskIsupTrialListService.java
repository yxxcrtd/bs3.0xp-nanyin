/*
 * 文件名：RskIsupTrialListService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.common.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.redis.annotation.Listener;
import com.joyintech.redis.msg.consumer.IConsumer;
import com.joyintech.redis.msg.entity.MessageBody.Operation;
import com.joyintech.tams.common.base.entity.RskIsupTrial;
import com.joyintech.tams.invest.common.batchList.dao.RskIsupTrialListMapper;
import com.joyintech.tams.invest.common.batchList.service.BatchListService;

/**
 * 指标计算结果批量保存Service
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月5日
 * @see RskIsupTrialListService
 * @since 1.0
 */
@Service
@Listener
public class RskIsupTrialListService implements IConsumer<RskIsupTrial> {

    @Autowired
    private BatchListService<RskIsupTrial> service;

    @Autowired
    private RskIsupTrialListMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void consume(Operation opr, Collection<RskIsupTrial> vList) {
        service.setBatchListMapper(mapper);
        service.consume(opr, vList);
    }

}
