/*
 * 文件名：CnBondInterfItemDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年9月23日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.component.svrreport.chb.dto;

import java.util.List;
import java.util.Map;

/**
 * 中债接口参数项
 * @description 
 * @author 王波
 * @version 1.0 2020年9月23日
 * @see CnBondInterfItemDto
 * @since 1.0
 */
public class CnBondInterfItemDto {
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
     * id
     */
    private String id;


    /**
     * 子 map
     */
    private List<Map<String, CnBondInterfItemDto>> maps;

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

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
     * 获取 maps
     * @return maps.
     */
    public List<Map<String, CnBondInterfItemDto>> getMaps() {
        return maps;
    }

    /**
     * 设置 maps
     * @param maps maps
     */
    public void setMaps(List<Map<String, CnBondInterfItemDto>> maps) {
        this.maps = maps;
    }
}
