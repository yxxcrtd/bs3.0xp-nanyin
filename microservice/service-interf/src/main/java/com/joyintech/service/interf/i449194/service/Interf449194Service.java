package com.joyintech.service.interf.i449194.service;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449194.entity.Interf449194InEntity;
import com.joyintech.service.interf.i449194.entity.Interf449194InLvlEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * 滚动N天产品成立接口(449194)-处理详细
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月7日
 * @see Interf449194Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_449194_01)
public class Interf449194Service extends ProdAliveService<Interf449194InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449194InEntity getReturnEntity() {
        return new Interf449194InEntity();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Interf449194InLvlEntity getReturnLvlEntity() {
        return new Interf449194InLvlEntity();
    }

}
