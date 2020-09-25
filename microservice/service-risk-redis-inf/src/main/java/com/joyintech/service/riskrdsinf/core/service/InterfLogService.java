/*
 * 文件名：InterfLogService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年7月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.core.service;

import java.util.Collection;
import java.util.Date;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.redis.annotation.Listener;
import com.joyintech.redis.msg.consumer.IConsumer;
import com.joyintech.redis.msg.entity.MessageBody.Operation;
import com.joyintech.tams.common.base.dao.InterfRiskLogMapper;
import com.joyintech.tams.common.base.entity.InterfRiskLogWithBLOBs;

/**
 * 日志表登录Service
 * 
 * @author zhangrui1
 * @version 1.0 2019年7月31日
 * @see InterfLogService
 * @since 1.0
 */
@Listener
@Service
public class InterfLogService implements IConsumer<InterfRiskLogWithBLOBs> {

    @Autowired
    private InterfRiskLogMapper logMapper;

    private static final String USER = "INF";

    /**
     * {@inheritDoc}
     */
    @Override
    public void consume(Operation opr, Collection<InterfRiskLogWithBLOBs> vList) {
        if (opr == Operation.W && !CollectionUtils.isEmpty(vList)) {
            vList.forEach(v -> {
                if (v != null) {
                    insert(v);
                }
            });
        }
    }

    /**
     * 登录日志表
     * 
     * @param v 日志内容
     * @see
     * @since 1.0
     */
    public void insert(InterfRiskLogWithBLOBs v) {
        if (StringUtil.isEmpty(v.getUuid())) {
            v.setUuid(IDGenerator.generate());
        }
        Date dt = new Date();
        v.setCreateUser(USER);
        v.setUpdateUser(USER);
        v.setCreateTime(dt);
        v.setUpdateTime(dt);
        logMapper.insertSelective(v);
    }
}
