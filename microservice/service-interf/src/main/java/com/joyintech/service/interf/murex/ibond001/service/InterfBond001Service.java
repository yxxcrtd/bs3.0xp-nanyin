/*
 * 文件名：Interfibond001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.ibond001.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.murex.InterfMurexService;
import com.joyintech.service.interf.murex.ibond001.entity.InterfBond001InEntity;

/**
 * 向MUREX系统推送债券交易数据，获取发送数据
 * 
 * @author whm
 * @version 1.0 2018年11月23日
 * @see InterfBond001Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_BOND001)
public class InterfBond001Service extends InterfMurexService<InterfBond001InEntity, InterfBond001InEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfBond001InEntity newEntity(Map<String, Object> sendMurex) {
        return new InterfBond001InEntity(sendMurex);
    }
}
