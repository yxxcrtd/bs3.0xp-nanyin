/*
 * 文件名：Interf449185InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449185.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * 概要设计-449185-净值型当日申购金额、赎回份额、净份额查询 --输入
 * 
 * @author whm
 * @version 1.0 2018年11月19日
 * @see Interf449185InEntity
 * @since 1.0
 */
public class Interf449185InEntity extends InterfAliveProdEntity<InterfProdLadderDetail> {

    /**
     * 取得金融产品代码
     * 
     * @return String 金融产品代码
     * @see
     * @since 1.0
     */
    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Required
    @Override
    public String getProdid() {
        return super.getProdid();
    }
}
