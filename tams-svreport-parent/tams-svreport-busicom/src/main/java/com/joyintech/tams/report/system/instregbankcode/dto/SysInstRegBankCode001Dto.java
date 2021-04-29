/*
 * 文件名：SysInstRegBankCode001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.system.instregbankcode.dto;

import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;

/**
 * 机构登记银行代码管理画面用dto
 *
 * @author 刘二通
 * @version 1.0 2018年5月18日
 * @see SysInstRegBankCode001Dto
 * @since 1.0
 */
public class SysInstRegBankCode001Dto extends ChbOrgRegistBankCode {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4315492347446263945L;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 母行登记银行代码
     */
    private String fregistBankCode;

    /**
     * 获取 orgName
     *
     * @return orgName.
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置 orgName
     *
     * @param orgName orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getFregistBankCode() {
        return fregistBankCode;
    }

    public void setFregistBankCode(String fregistBankCode) {
        this.fregistBankCode = fregistBankCode;
    }
}
