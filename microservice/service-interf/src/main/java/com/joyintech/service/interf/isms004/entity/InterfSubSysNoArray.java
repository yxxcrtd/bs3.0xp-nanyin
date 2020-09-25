/*
 * 文件名：InterfSubSysNoArray.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.isms004.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 子系统列表
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月21日
 * @see InterfSubSysNoArray
 * @since 1.0
 */
public class InterfSubSysNoArray {

    /**
     * 子系统ID
     */
    private String subSysNo;

    /**
     * 取得 子系统ID
     * 
     * @return 子系统ID
     */
    @EsbName(value = "SUB_SYSTEM_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 6)
    @Required
    public String getSubSysNo() {
        return subSysNo;
    }

    /**
     * 设置 子系统ID
     * 
     * @param subSysNo 子系统ID
     */
    @EsbName(value = "SUB_SYSTEM_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setSubSysNo(String subSysNo) {
        this.subSysNo = subSysNo;
    }
}
