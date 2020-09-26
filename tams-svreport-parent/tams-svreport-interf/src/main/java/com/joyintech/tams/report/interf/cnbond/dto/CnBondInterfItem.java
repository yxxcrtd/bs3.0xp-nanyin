/*
 * 文件名：Item.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年5月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.cnbond.dto;

import java.util.List;
import java.util.Map;

/**
 * 中债接口参数项
 * 
 * @author lihuadong
 * @version 1.0 2017年5月21日
 * @see Item
 * @since 1.0
 */
public class CnBondInterfItem {

    /**
     * 项目值
     */
    private String value;

    /**
     * 项目精度
     */
    private String decimals;

    /**
     * 币种或者单位
     */
    private String unitRef;

    /**
     * 子 map
     */
    private List<Map<String, CnBondInterfItem>> maps;

    /**
     * 获取 value
     * 
     * @return value.
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     * 
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取 decimals
     * 
     * @return decimals.
     */
    public String getDecimals() {
        return decimals;
    }

    /**
     * 设置 decimals
     * 
     * @param decimals decimals
     */
    public void setDecimals(String decimals) {
        this.decimals = decimals;
    }

    /**
     * 获取 unitRef
     * 
     * @return unitRef.
     */
    public String getUnitRef() {
        return unitRef;
    }

    /**
     * 设置 unitRef
     * 
     * @param unitRef unitRef
     */
    public void setUnitRef(String unitRef) {
        this.unitRef = unitRef;
    }

    /**
     * 获取 maps
     * @return maps.
     */
    public List<Map<String, CnBondInterfItem>> getMaps() {
        return maps;
    }

    /**
     * 设置 maps
     * @param maps maps
     */
    public void setMaps(List<Map<String, CnBondInterfItem>> maps) {
        this.maps = maps;
    }

}
