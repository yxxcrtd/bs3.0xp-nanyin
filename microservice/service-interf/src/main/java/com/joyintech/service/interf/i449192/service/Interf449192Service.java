package com.joyintech.service.interf.i449192.service;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449192.entity.Interf449192InEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * 接口449192详细处理
 * 
 * @author whm
 * @version 1.0 2018年11月15日
 * @see Interf449192Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_449192_01)
public class Interf449192Service extends ProdAliveService<Interf449192InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449192InEntity getReturnEntity() {
        return new Interf449192InEntity();
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
