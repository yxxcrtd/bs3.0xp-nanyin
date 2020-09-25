/*
 * 文件名：Interf448103OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i448103.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 448103输出
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月13日
 * @see Interf448103OutEntity
 * @since 1.0
 */
public class Interf448103OutEntity extends Interf448103InEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1701421504803280683L;

    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Required
    @Override
    public void setShortName(String shortName) {
        super.setShortName(shortName);
    }
}
