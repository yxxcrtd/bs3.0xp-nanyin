/*
 * 文件名：Interf449188InEntity2.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449188.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * N天产品成立接口449188输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月7日
 * @see Interf449188InEntity
 * @since 1.0
 */
public class Interf449188InEntity extends InterfAliveProdEntity<InterfProdLadderDetail> {

    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Required
    @EsbName(value = "ShortName")
    @Override
    public String getProdid() {
        return super.getProdid();
    }

    @EsbName(value = "StartDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getStartdate() {
        return super.getcDate();
    }
}
