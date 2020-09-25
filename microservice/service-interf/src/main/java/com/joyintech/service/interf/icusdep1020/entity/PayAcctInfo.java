/*
 * 文件名：PayAcctInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icusdep1020.entity;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;

/**
 * 资金账户信息
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月23日
 * @see PayAcctInfo
 * @since 1.0
 */
public class PayAcctInfo {

    /**
     * 账户信息
     */
    private StlCapacctInfo acctInfo;

    /**
     * 账户类型
     */
    private String acctType;

    /**
     * 获取 账户类型
     * 
     * @return 账户类型.
     */
    public String getAcctType() {
        return acctType;
    }

    /**
     * 设置 账户类型
     * 
     * @param acctType 账户类型
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    /**
     * 获取 账户信息
     * 
     * @return 账户信息.
     */
    public StlCapacctInfo getAcctInfo() {
        return acctInfo;
    }

    /**
     * 设置 账户信息
     * 
     * @param acctInfo 账户信息
     */
    public void setAcctInfo(StlCapacctInfo acctInfo) {
        this.acctInfo = acctInfo;
    }
}
