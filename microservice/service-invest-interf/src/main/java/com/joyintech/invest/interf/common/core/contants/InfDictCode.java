/*
 * 文件名：InfDictCode.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.invest.interf.common.core.contants;

import com.joyintech.framework.base.BaseDictCode;

/**
 * 接口数据字典
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月8日
 * @see InfDictCode
 * @since 1.0
 */
public enum InfDictCode implements BaseDictCode {

    /**
     * 信息类型
     */
    信息类型(
            "STD400MSGT"
    ),

    /**
     * 信息码
     */
    信息码(
        "STD400MGID"
    ),
    
    发送标识(
    		"SEND_FLG"
    		),
    
    接口流水号(
    		"interfTermtrc"
    		),
    接口ID(
    		"INF_ID"
    		),
    发送状态(
    		"INTERF_STS"
    		),
    参与人角色(
        "PARTY_ROLE"
        ),
    收付方向(
        "PS"
        ),
    /**
	  * 标的账户类型
	  */
	 标的账户类型("ASSET_ACCT_TYPE"),
	/**
	  * 利率类型
	*/
	利率类型("INT_TYPE"),

    /**
     * 接口名称
     */
    接口名称("INTERF_NAME"),
    ;

    private String value;

    private InfDictCode(String value) {
        setValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

}
