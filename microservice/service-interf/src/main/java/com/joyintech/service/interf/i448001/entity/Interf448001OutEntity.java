/*
 * 文件名：Inter448001OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i448001.entity;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口448001输出
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月18日
 * @see Interf448001OutEntity
 * @since 1.0
 */
public class Interf448001OutEntity extends Interf448001InEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 49081675283085640L;

    /**
     * 设置 产品简称
     * 
     * @param
     */
    @Override
    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setShortName(String shortName) {
        super.setShortName(shortName);
    }

}
