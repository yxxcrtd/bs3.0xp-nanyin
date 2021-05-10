/*
 * 文件名：ValManualVoucher003SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * 手工凭证添加到表格校验Dto
 * 
 * @author sunshuaishuai
 * @version 1.0 2021年2月9日
 * @see ValManualVoucherTemplete003AddDto
 * @since 1.0
 */
public class ValManualVoucherTemplete003AddDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 模板代码
     */
    private String tmpCode;

    /**
     * 模板名称
     */
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

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 金额
     */
    private BigDecimal oHappenAmt;

    /**
     * 借方金额
     */
    private BigDecimal dAmt;

    /**
     * 贷方金额
     */
    private BigDecimal cAmt;

    /**
     * 发生数量
     */
    private BigDecimal happenNumber;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 数据行
     */
    private String index;

    /**
     * 获取 科目号
     *
     * @return 科目号
     */
    public String getSubjectNo() {
        return StringUtils.isEmpty(subjectNo)?null:subjectNo.trim();
    }

    /**
     * 设置 科目号
     *
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 获取 科目名称
     *
     * @return 科目名称.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 科目名称
     *
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 获取 ccy
     *
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 cdFlag
     *
     * @return cdFlag.
     */
    public String getCdFlag() {
        return cdFlag;
    }

    /**
     * 设置 cdFlag
     *
     * @param cdFlag cdFlag
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    /**
     * 获取 happenNumber
     *
     * @return happenNumber.
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    /**
     * 设置 happenNumber
     *
     * @param happenNumber happenNumber
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

    public String getCdFlagFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag))?
                SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag):cdFlag;
//        return SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag);
    }

    public String getCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
                SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
//        return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
    }

    /**
     * 获取 dAmt
     * @return dAmt.
     */
    public BigDecimal getdAmt() {
        return dAmt;
    }

    /**
     * 设置 dAmt
     * @param dAmt dAmt
     */
    public void setdAmt(BigDecimal dAmt) {
        this.dAmt = dAmt;
    }

    /**
     * 获取 cAmt
     * @return cAmt.
     */
    public BigDecimal getcAmt() {
        return cAmt;
    }

    /**
     * 设置 cAmt
     * @param cAmt cAmt
     */
    public void setcAmt(BigDecimal cAmt) {
        this.cAmt = cAmt;
    }

    /**
     * 获取 status
     * @return status.
     */
    public String getStatus() {
        return status;
    }


    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * 获取 index
     * @return index.
     */
    public String getIndex() {
        return index;
    }

    /**
     * 设置 index
     * @param index index
     */
    public void setIndex(String index) {
        this.index = index;
    }

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

    public BigDecimal getoHappenAmt() {
        return oHappenAmt;
    }

    public void setoHappenAmt(BigDecimal oHappenAmt) {
        this.oHappenAmt = oHappenAmt;
    }
}
