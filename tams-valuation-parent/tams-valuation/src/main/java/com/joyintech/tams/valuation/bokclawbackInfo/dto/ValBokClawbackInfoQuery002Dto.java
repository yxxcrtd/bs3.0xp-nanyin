/*
 * 文件名：ValBokClawbackInfoQuery002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.bokclawbackInfo.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 记账凭证
 * 
 * @author zhangwei
 * @version 1.0 2019年2月27日
 * @see ValBokClawbackInfoQuery002Dto
 * @since 1.0
 */
public class ValBokClawbackInfoQuery002Dto extends BaseEntity {

    /**
     * 凭证编号
     */
    private String vouchNum;

    /**
     * 业务凭证编号
     */
    private String busVouchnum;

    public String getVouchNum() {
        return vouchNum;
    }

    public void setVouchNum(String vouchNum) {
        this.vouchNum = vouchNum;
    }

    public String getBusVouchnum() {
        return busVouchnum;
    }

    public void setBusVouchnum(String busVouchnum) {
        this.busVouchnum = busVouchnum;
    }

}
