/*
 * 文件名：ValVoucherQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;

/**
 * 手工凭证模板新建校验dto
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete003AddValidatorDto
 * @since 2.0
 */
public class ValManualVoucherTemplete003AddValidatorDto extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 模板代码
     */
    private String tmpCode;

    /**
     * 模板名称
     */
    @NotEmpty(fieldName = "ValManualVoucherTemplete.tmpName")
    private String tmpName;

    /**
     * 业务类别
     */
    private String busiType;

    /**
     * 摘要
     */
    private String remark;

    /**
     * 分录类型
     */
    private String recordType;

    public String getTmpCode() {
        return tmpCode;
    }

    public void setTmpCode(String tmpCode) {
        this.tmpCode = tmpCode;
    }

    public String getTmpName() {
        return tmpName;
    }

    public void setTmpName(String tmpName) {
        this.tmpName = tmpName;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
