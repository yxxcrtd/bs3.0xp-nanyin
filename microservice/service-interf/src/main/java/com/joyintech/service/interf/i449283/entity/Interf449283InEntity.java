/*
 * 文件名：Interf449283InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449283.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * 449283接口输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月7日
 * @see Interf449283InEntity
 * @since 1.0
 */
public class Interf449283InEntity extends InterfAliveProdEntity<Interf449283InLvlEntity> {

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Required
    @Override
    public String getProdid() {
        return super.getProdid();
    }

    /**
     * 取得449283接口.交易日期
     * 
     * @return transDate 交易日期
     * @see
     * @since 1.0
     */
    @EsbName(value = "TransDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getTransDate() {
        return super.getcDate();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "RefAddRateNum")
    @EsbFieldType(value = EsbFieldType.INT)
    @EsbNumber(length = 8)
    @Required
    @Override
    public int getListCnt() {
        return super.getListCnt();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "RefAddRate")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    @Override
    public List<Interf449283InLvlEntity> getLvlList() {
        return super.getLvlList();
    }
}
