package com.joyintech.service.interf.cache.entity;

import com.joyintech.framework.base.BaseEntity;

/**
 * 系统参数主键
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月6日
 * @see SysParaKey
 * @since 1.0
 */
public class SysParaKey extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 979932162201805279L;

    /**
     * 系统代码
     */
    private String systemCode;

    /**
     * 参数代码
     */
    private String paraCode;

    /**
     * 取得 系统代码
     * 
     * @return 系统代码
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * 设置 系统代码
     * 
     * @param systemCode 系统代码
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    /**
     * 取得 参数代码
     * 
     * @return 参数代码
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 参数代码
     * 
     * @param paraCode 参数代码
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }
}