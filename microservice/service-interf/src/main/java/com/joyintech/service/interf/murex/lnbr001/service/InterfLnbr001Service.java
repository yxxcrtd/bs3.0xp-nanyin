/*
 * 文件名：LNBR001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.lnbr001.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.murex.InterfMurexService;
import com.joyintech.service.interf.murex.lnbr001.entity.InterfLnbr001InEntity;

/**
 * 拆借交易详细处理
 * 
 * @author whm
 * @version 1.0 2018年11月21日
 * @see InterfLnbr001Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LNBR001)
public class InterfLnbr001Service extends InterfMurexService<InterfLnbr001InEntity, InterfLnbr001InEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfLnbr001InEntity newEntity(Map<String, Object> sendMurex) {
        sendMurex.put("REQUEST_SEQ_NO", getBizSeqNo());
        return new InterfLnbr001InEntity(sendMurex);
    }

}
