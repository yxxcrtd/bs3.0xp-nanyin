/*
 * 文件名：InterfRepo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.irepo002.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.murex.InterfMurexService;
import com.joyintech.service.interf.murex.irepo002.entity.InterfRepo002InEntity;

/**
 * 质押式回购交易详细处理
 * 
 * @author whm
 * @version 1.0 2018年11月22日
 * @see InterfRepo002Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_REPO002)
public class InterfRepo002Service extends InterfMurexService<InterfRepo002InEntity, InterfRepo002InEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfRepo002InEntity newEntity(Map<String, Object> sendMurex) {
        return new InterfRepo002InEntity(sendMurex);
    }
}
