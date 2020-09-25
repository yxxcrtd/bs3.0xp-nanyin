/*
 * 文件名：InterfOPDL03InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.iopdl03.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.tams.common.base.entity.InterfSendOpics;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * opdl03接口输入
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月19日
 * @see InterfOPDL03InEntity
 * @since 1.0
 */
public class InterfOPDL03InEntity {

    /**
     * 发送OPICS信息
     */
    private InterfSendOpics sendOpicsInfo;

    public InterfOPDL03InEntity(InterfSendOpics sendOpicsInfo) {
        this.sendOpicsInfo = sendOpicsInfo;
    }

    @EsbName(value = "SEQ_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    public String getSn() {
        return sendOpicsInfo.getSn();
    }

    @EsbName(value = "TRAN_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getDealtype() {
        return sendOpicsInfo.getDealtype();
    }

    @EsbName(value = "DEALER_ROLE_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    public String getTrad() {
        return sendOpicsInfo.getTrad();
    }

    @EsbName(value = "VALUE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getVdate() {
        return sendOpicsInfo.getVdate();
    }

    @EsbName(value = "EXPIRY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getMdate() {
        return sendOpicsInfo.getMdate();
    }

    @EsbName(value = "COUNTER_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getCno() {
        return sendOpicsInfo.getCno();
    }

    @EsbName(value = "CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    public String getCcy() {
        return sendOpicsInfo.getCcy();
    }

    @EsbName(value = "AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    public BigDecimal getAmt() {
        return sendOpicsInfo.getAmt();
    }

    @EsbName(value = "INTEST_RATE_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getRatecode() {
        return sendOpicsInfo.getRatecode();
    }

    @EsbName(value = "INTEREST_PERIOD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    public String getBasis() {
        return sendOpicsInfo.getBasis();
    }

    @EsbName(value = "INT_RATE_PRE8")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 8)
    @Required
    public BigDecimal getIntrate() {
        return sendOpicsInfo.getIntrate();
    }

    @EsbName(value = "SPREAD_PRE8")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 8)
    @Required
    public BigDecimal getSpread() {
        return sendOpicsInfo.getSpread();
    }

    @EsbName(value = "FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    public String getBrok() {
        return sendOpicsInfo.getBrok();
    }

    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getPort() {
        return sendOpicsInfo.getPort();
    }

    @EsbName(value = "COST_CENTER")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getCost() {
        return sendOpicsInfo.getCost();
    }

    @EsbName(value = "TRAN_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getDealdate() {
        return sendOpicsInfo.getDealdate();
    }

    @EsbName(value = "TRAN_TIME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 6)
    public String getDealtime() {
        return sendOpicsInfo.getDealtime();
    }

    @EsbName(value = "PERIOD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getTenor() {
        return sendOpicsInfo.getTenor();
    }

    @EsbName(value = "CHECK_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    public String getVerind() {
        return sendOpicsInfo.getVerind();
    }

    @EsbName(value = "TRANS_MSG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getDealtext() {
        return sendOpicsInfo.getDealtext();
    }

    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemark() {
        return sendOpicsInfo.getRemark();
    }

    @EsbName(value = "SEC_DEPT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    public String getBranch() {
        return sendOpicsInfo.getBranch();
    }

}
