/*
 * 文件名：Interf449189InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449189.entity;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * N天产品到期接口449189输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月7日
 * @see Interf449189InEntity
 * @since 1.0
 */
public class Interf449189InEntity extends InterfAliveProdEntity<InterfProdLadderDetail> {

    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Override
    public String getProdid() {
        return super.getProdid();
    }

    @EsbName(value = "EndDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Override
    public String getEnddate() {
        return super.getcDate();
    }

}
