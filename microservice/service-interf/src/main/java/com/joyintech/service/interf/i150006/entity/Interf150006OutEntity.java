/*
 * 文件名：Interf150006OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i150006.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 15006接口输出
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月9日
 * @see Interf150006OutEntity
 * @since 1.0
 */
public class Interf150006OutEntity {

    /**
     * 中文简称
     */
    private String chnShortName;

    /**
     * 获取 中文简称
     * 
     * @return 中文简称
     */
    public String getChnShortName() {
        return chnShortName;
    }

    /**
     * 设置 中文简称
     * 
     * @param chnShortName 中文简称
     */
    @EsbName(value = "CHN_SHORT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Required
    public void setChnShortName(String chnShortName) {
        this.chnShortName = chnShortName;
    }
}
