/*
 * 文件名：Interf150005OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodquote.entity;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月9日
 * @see Interf150005OutEntity
 * @since 1.0
 */
public class Interf150005OutEntity extends Interf150005InEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -1061354089203368476L;

    /**
     * 设置 中文简称
     * 
     * @param prodId 中文简称
     */
    @EsbName(value = "CHN_SHORT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProdId(String prodId) {
        super.setProdId(prodId);
    }

}
