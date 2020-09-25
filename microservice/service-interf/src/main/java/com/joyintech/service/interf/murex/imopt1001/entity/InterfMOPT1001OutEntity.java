/*
 * 文件名：InterfMOPT1001OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.imopt1001.entity;

import java.util.List;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 查询交易导入结果信息 接口输出
 * 
 * @author changwenbin
 * @version 1.0 2018年11月21日
 * @see InterfMOPT1001OutEntity
 * @since 1.0
 */
public class InterfMOPT1001OutEntity {

    /**
     * 明细总数
     */
    private List<InterfMOPT1001TranOutEntity> tranlist;

    /**
     * 获取 明细总数
     * 
     * @return 明细总数
     */
    public List<InterfMOPT1001TranOutEntity> getTranlist() {
        return tranlist;
    }

    /**
     * 设置 明细总数
     * 
     * @param tranlist 明细总数
     */
    @EsbName(value = "TRAN_LIST_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setTranlist(List<InterfMOPT1001TranOutEntity> tranlist) {
        this.tranlist = tranlist;
    }

}
