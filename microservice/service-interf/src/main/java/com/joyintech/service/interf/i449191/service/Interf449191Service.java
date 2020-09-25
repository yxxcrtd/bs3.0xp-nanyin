package com.joyintech.service.interf.i449191.service;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449191.entity.Interf449191InEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * 接口449191详细处理
 * 
 * @author whm
 * @version 1.0 2018年11月06日
 * @see Interf449191Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_449191_01)
public class Interf449191Service extends ProdAliveService<Interf449191InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449191InEntity getReturnEntity() {
        return new Interf449191InEntity();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public InterfProdLadderDetail getReturnLvlEntity() {
        return new InterfProdLadderDetail();
    }

}
