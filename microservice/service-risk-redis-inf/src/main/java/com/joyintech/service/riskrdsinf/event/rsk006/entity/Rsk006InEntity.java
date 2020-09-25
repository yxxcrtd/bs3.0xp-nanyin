/*
 * 文件名：Rsk006InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk006.entity;

import java.io.Serializable;

/**
 * RSK006接口-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月29日
 * @see Rsk006InEntity
 * @since 1.0
 */
public class Rsk006InEntity implements Serializable {

    private Object rskCalcList;

    /**
     * 序列号
     */
    private static final long serialVersionUID = 6360519671026860033L;

    /**
     * 获取 rSK_CALC_LIST
     * 
     * @return rSK_CALC_LIST.
     */
    public Object getRskCalcList() {
        return rskCalcList;
    }

    /**
     * 设置 rSK_CALC_LIST
     * 
     * @param rSK_CALC_LIST rSK_CALC_LIST
     */
    public void setRskCalcList(Object rskCalcList) {
    	this.rskCalcList = rskCalcList;
    }
}
