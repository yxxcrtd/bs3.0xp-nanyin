/*
 * 文件名：SysPara.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年2月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.common.cache.service.entity;

import com.joyintech.framework.base.intf.Para;


/**
 * 画面
 * @author 何阳阳
 * @version 1.0 2018年2月3日
 * @see SysPara
 * @since 1.0
 */
public class SysPara implements Para {

    private static final long serialVersionUID = -3350576936978012710L;

    private String paraCode;

    private String paramValue;

    public SysPara() {
        super();
    }

    public SysPara(String paraCode, String paramValue) {
        super();
        this.paraCode = paraCode;
        this.paramValue = paramValue;
    }

    @Override
    public String getParaCode() {
        return paraCode;
    }

    @Override
    public String getParamValue() {
        return paramValue;
    }
}
