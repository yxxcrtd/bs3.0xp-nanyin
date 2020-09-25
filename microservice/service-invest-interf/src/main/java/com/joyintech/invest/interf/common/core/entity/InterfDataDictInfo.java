/*
 * 文件名：DataDictInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.invest.interf.common.core.entity;

import java.io.Serializable;

/**
 * 接口数据字典处理Entity
 * 
 * @author zhangrui1
 * @version 1.0 2019年3月13日
 * @see InterfDataDictInfo
 * @since 1.0
 */
public class InterfDataDictInfo implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 8089206631081411595L;

    /**
     * 数据字典代码
     */
    private String dctCode;

    /**
     * 系统代码
     */
    private String sysCode;

    /**
     * 数据字典项目代码
     */
    private String itmCode;

    /**
     * 数据字典项目值
     */
    private String itemVal;

    /**
     * 关联数据字典代码
     */
    private String lnkDict;

    /**
     * 关联数据字典项目代码
     */
    private String lnkItem;

    /**
     * 关联数据字典项目值
     */
    private String lnkdVal;

    /**
     * 获取 数据字典代码
     * 
     * @return 数据字典代码.
     */
    public String getDctCode() {
        return dctCode;
    }

    /**
     * 设置 数据字典代码
     * 
     * @param dctCode 数据字典代码
     */
    public void setDctCode(String dctCode) {
        this.dctCode = dctCode;
    }

    /**
     * 获取 系统代码
     * 
     * @return 系统代码.
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * 设置 系统代码
     * 
     * @param sysCode 系统代码
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    /**
     * 获取 数据字典项目代码
     * 
     * @return 数据字典项目代码.
     */
    public String getItmCode() {
        return itmCode;
    }

    /**
     * 设置 数据字典项目代码
     * 
     * @param itmCode 数据字典项目代码
     */
    public void setItmCode(String itmCode) {
        this.itmCode = itmCode;
    }

    /**
     * 获取 数据字典项目值
     * 
     * @return 数据字典项目值.
     */
    public String getItemVal() {
        return itemVal;
    }

    /**
     * 设置 数据字典项目值
     * 
     * @param itemVal 数据字典项目值
     */
    public void setItemVal(String itemVal) {
        this.itemVal = itemVal;
    }

    /**
     * 获取 关联数据字典代码
     * 
     * @return 关联数据字典代码.
     */
    public String getLnkDict() {
        return lnkDict;
    }

    /**
     * 设置 关联数据字典代码
     * 
     * @param lnkDict 关联数据字典代码
     */
    public void setLnkDict(String lnkDict) {
        this.lnkDict = lnkDict;
    }

    /**
     * 获取 关联数据字典项目代码
     * 
     * @return 关联数据字典项目代码.
     */
    public String getLnkItem() {
        return lnkItem;
    }

    /**
     * 设置 关联数据字典项目代码
     * 
     * @param lnkItem 关联数据字典项目代码
     */
    public void setLnkItem(String lnkItem) {
        this.lnkItem = lnkItem;
    }

    /**
     * 获取 关联数据字典项目值
     * 
     * @return 关联数据字典项目值.
     */
    public String getLnkdVal() {
        return lnkdVal;
    }

    /**
     * 设置 关联数据字典项目值
     * 
     * @param lnkdVal 关联数据字典项目值
     */
    public void setLnkdVal(String lnkdVal) {
        this.lnkdVal = lnkdVal;
    }
}
