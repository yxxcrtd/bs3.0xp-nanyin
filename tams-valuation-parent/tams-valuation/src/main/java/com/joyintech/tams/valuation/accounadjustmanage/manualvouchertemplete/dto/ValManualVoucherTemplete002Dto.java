/*
 * 文件名：ValVoucherQuery002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;

/**
 * 手工凭证模板详情查看dto
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete002Dto
 * @since 2.0
 */
public class ValManualVoucherTemplete002Dto extends BaseEntity {

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
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    private BigDecimal oHappenAmt;

    /**
     * 数量
     */
    private BigDecimal happenNumber;

    /**
     * 取得 模板编号
     *
     * @return 模板编号
     */
    public String getTmpCode() {
        return tmpCode;
    }

    /**
     * 设置 模板编号
     *
     * @param tmpCode 模板编号
     */
    public void setTmpCode(String tmpCode) {
        this.tmpCode = tmpCode;
    }

    /**
     * 取得 模板名称
     *
     * @return 模板名称
     */
    public String getTmpName() {
        return tmpName;
    }

    /**
     * 设置 模板名称
     *
     * @param tmpName 模板名称
     */
    public void setTmpName(String tmpName) {
        this.tmpName = tmpName;
    }

    /**
     * 取得 科目号
     * 
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
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
     * 取得 科目名称
     * 
     * @return 科目名称
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
     * 取得 借贷方向
     * 
     * @return 借贷方向
     */
    public String getCdFlag() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag))?
            SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag):cdFlag;
//        return SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag);
    }

    /**
     * 设置 借贷方向
     * 
     * @param cdFlag 借贷方向
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    /**
     * 取得 币种
     * 
     * @return 币种
     */
    public String getCcy() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
            SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
//        return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
    }

    /**
     * 设置 币种
     * 
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 取得 金额
     * 
     * @return 金额
     */
    public BigDecimal getoHappenAmt() {
        return oHappenAmt;
    }

    /**
     * 设置 金额
     * 
     * @param oHappenAmt 金额
     */
    public void setoHappenAmt(BigDecimal oHappenAmt) {
        this.oHappenAmt = oHappenAmt;
    }

    /**
     * 取得 数量
     * 
     * @return 数量
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    /**
     * 设置 数量
     * 
     * @param happenNumber 数量
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

}
