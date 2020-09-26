/*
 * 文件名：ValBokClawbackInfoQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.bokclawbackInfo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 回补信息查询
 * 
 * @author zhangwei
 * @version 1.0 2019年2月27日
 * @see ValBokClawbackInfoQuery001Dto
 * @since 1.0
 */
public class ValBokClawbackInfoQuery001Dto extends BaseEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 分层代码，子产品存分层代码，母产品存核算主体代码
     */
    private String layeringId;

    /**
     * 发生日期
     */
    private String happenDate;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 回补金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal clawbackAmt;

    /**
     * 回补方式
     */
    private String clawbackType;

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getLayeringId() {
        return layeringId;
    }

    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId;
    }

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    public String getFeeType() {
        // return feeType;
        return SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, this.feeType);
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getClawbackAmt() {
        return clawbackAmt;
    }

    public void setClawbackAmt(BigDecimal clawbackAmt) {
        this.clawbackAmt = clawbackAmt;
    }

    public String getClawbackType() {
        return SysDataUtil.getNameFromDataDict(DictCode.回补方式, this.clawbackType);
    }

    public void setClawbackType(String clawbackType) {
        this.clawbackType = clawbackType;
    }

}
