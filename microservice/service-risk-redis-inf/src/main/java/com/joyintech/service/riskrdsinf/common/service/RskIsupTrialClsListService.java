/*
 * 文件名：RskIsupTrialClsListService.java
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
import com.joyintech.tams.common.base.entity.RskIsupTrialCls;
import com.joyintech.tams.invest.common.batchList.dao.RskIsupTrialClsListMapper;
import com.joyintech.tams.invest.common.batchList.service.BatchListService;

/**
 * 风险计算结论批量保存Service
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月5日
 * @see RskIsupTrialClsListService
 * @since 1.0
 */
@Service
@Listener
public class RskIsupTrialClsListService implements IConsumer<RskIsupTrialCls> {

    @Autowired
    private BatchListService<RskIsupTrialCls> service;

    @Autowired
    private RskIsupTrialClsListMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void consume(Operation opr, Collection<RskIsupTrialCls> vList) {
        service.setBatchListMapper(mapper);
        service.consume(opr, vList);
    }

}
