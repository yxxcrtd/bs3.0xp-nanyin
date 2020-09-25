/*
 * 文件名：Interf291001InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i291001.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * 天天成长当日销售数据统计查询（291001）-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月19日
 * @see Interf291001InEntity
 * @since 1.0
 */
public class Interf291001InEntity extends InterfAliveProdEntity<Interf291001InLvlEntity> {

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LOT_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    @Override
    public List<Interf291001InLvlEntity> getLvlList() {
        return super.getLvlList();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CHN_SHORT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    @Override
    public String getProdid() {
        return super.getProdid();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TRAN_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getcDate() {
        return super.getcDate();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LOOP_COUNT")
    @EsbFieldType(value = EsbFieldType.INT)
    @EsbLength(value = 20)
    @Required
    @Override
    public int getListCnt() {
        return super.getListCnt();
    }
}
