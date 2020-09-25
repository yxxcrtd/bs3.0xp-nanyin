/*
 * 文件名：SysParaDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.common.dto;

/**
 * 系统参数dto
 *
 * @author etliu
 * @version 1.0 2018年12月12日
 * @see SysParaDto
 * @since 1.0
 */
public class SysParaDto {

    /**
     * 系统编号
     */
    private String systemCode;
    
    /**
     * 参数代码
     */
    private String paraCode;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 获取 paraCode
     *
     * @return paraCode.
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 paraCode
     *
     * @param paraCode paraCode
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }

    /**
     * 获取 paramValue
     *
     * @return paramValue.
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 paramValue
     *
     * @param paramValue paramValue
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

}
