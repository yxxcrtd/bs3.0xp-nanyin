/*
 * 文件名：ValManualVoucher003CheckValidatorDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.validator.ValManualVoucherTemplete003CheckValidator;

import java.math.BigDecimal;

/**
 * 手工凭证模板添加到表格校验Dto
 * 
 * @author sunshuaishuai
 * @version 1.0 2021年02月22日
 * @see ValManualVoucherTemplete003CheckValidatorDto
 * @since 1.0
 */
@ValidLogic(targetClass = ValManualVoucherTemplete003CheckValidator.class)
public class ValManualVoucherTemplete003CheckValidatorDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 模板编号
     */
    private String tmpCode;

    /**
     * 模板名称
     */
    private String tmpName;

    /**
     * 科目号
     */
    @NotEmpty(fieldName = "ValManualVoucher.subjectNo")
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 借贷方向(余额方向)
     */
    private String cdFlag;

    /**
     * 币种
     */
    //@NotEmpty(fieldName = "ValManualVoucher.ccy")
    private String ccy;

    /**
     * 借方金额
     */
    private BigDecimal dAmt;

    /**
     * 贷方金额
     */
    private BigDecimal cAmt;

    /**
     * 数量
     */
    private BigDecimal happenNumber;

    /**
     * 业务类别
     */
    private String busiType;

    /**
     * 业务类别
     */
    private String busiTypeFmt;

    /**
     * 摘要
     */
    private String remark;

    /**
     * 操作人
     */
    private String createUser;

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

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCdFlag() {
        return cdFlag;
    }

    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getdAmt() {
        return dAmt;
    }

    public void setdAmt(BigDecimal dAmt) {
        this.dAmt = dAmt;
    }

    public BigDecimal getcAmt() {
        return cAmt;
    }

    public void setcAmt(BigDecimal cAmt) {
        this.cAmt = cAmt;
    }

    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getBusiTypeFmt() {
        return busiTypeFmt;
    }

    public void setBusiTypeFmt(String busiTypeFmt) {
        this.busiTypeFmt = busiTypeFmt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
