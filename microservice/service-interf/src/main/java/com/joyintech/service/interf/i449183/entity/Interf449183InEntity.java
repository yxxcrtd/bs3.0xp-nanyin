/*
 * 文件名：Interf449183InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449183.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * 日添利、货币型产品申购赎回接口（449183）-输入
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月7日
 * @see Interf449183InEntity
 * @since 1.0
 */
public class Interf449183InEntity extends InterfAliveProdEntity<Interf449183InLvlEntity> {

    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Required
    @Override
    public String getProdid() {
        return super.getProdid();
    }

    @EsbName(value = "TransDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getcDate() {
        return super.getcDate();
    }

    /**
     * 取得分层数量
     * 
     * @return int 分层数量
     * @see
     * @since 1.0
     */
    @EsbName(value = "RefAddRateNum")
    @EsbFieldType(value = EsbFieldType.INT)
    @EsbNumber(length = 4)
    @Required
    @Override
    public int getListCnt() {
        return super.getListCnt();
    }

    @EsbName(value = "RefAddRate")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    @Override
    public List<Interf449183InLvlEntity> getLvlList() {
        return super.getLvlList();
    }
}
