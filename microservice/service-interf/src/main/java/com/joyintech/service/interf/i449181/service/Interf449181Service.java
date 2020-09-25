package com.joyintech.service.interf.i449181.service;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449181.entity.Interf449181InEntity;
import com.joyintech.service.interf.i449181.entity.Interf449181InLvlEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * 接口449181详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see Interf449181Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_449181_01)
public class Interf449181Service extends ProdAliveService<Interf449181InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449181InEntity getReturnEntity() {
        return new Interf449181InEntity();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Interf449181InLvlEntity getReturnLvlEntity() {
        return new Interf449181InLvlEntity();
    }
}
