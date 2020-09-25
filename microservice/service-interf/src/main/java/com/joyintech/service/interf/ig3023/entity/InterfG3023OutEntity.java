/*
 * 文件名：InterfG3023OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig3023.entity;

import java.util.List;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口G3023-输出
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月3日
 * @see InterfG3023OutEntity
 * @since 1.0
 */
public class InterfG3023OutEntity {

    /**
     * 交易明细数组
     */
    private List<TranListItem> tranList;

    /**
     * 获取 交易明细数组
     * 
     * @return 交易明细数组.
     */
    public List<TranListItem> getTranList() {
        return tranList;
    }

    /**
     * 设置 交易明细数组
     * 
     * @param tranList 交易明细数组
     */
    @EsbName(value = "TRAN_LIST_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setTranList(List<TranListItem> tranList) {
        this.tranList = tranList;
    }
}
