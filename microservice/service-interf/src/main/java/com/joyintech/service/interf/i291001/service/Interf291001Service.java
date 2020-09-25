/*
 * 文件名：Interf291001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i291001.service;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i291001.entity.Interf291001InEntity;
import com.joyintech.service.interf.i291001.entity.Interf291001InLvlEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 天天成长当日销售数据统计查询（291001）-详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月19日
 * @see Interf291001Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_291001_00)
public class Interf291001Service extends ProdAliveService<Interf291001InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf291001InEntity getReturnEntity() {
        return new Interf291001InEntity();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Interf291001InLvlEntity getReturnLvlEntity() {
        return new Interf291001InLvlEntity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfOutEntity entity)
        throws Exception {
        setPinganBusiType(DictCodeListExt1.平安业务类型_产品申购.getValue());
        return super.saveInterfData(entity);
    }
}
