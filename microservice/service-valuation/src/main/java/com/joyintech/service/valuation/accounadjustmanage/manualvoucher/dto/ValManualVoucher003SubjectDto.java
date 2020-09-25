/*
 * 文件名：ValVoucherQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 用于科目联动检索
 * 
 * @author weipeng
 * @version 1.0 2018年5月8日
 * @see ValManualVoucher003SubjectDto
 * @since 1.0
 */
public class ValManualVoucher003SubjectDto extends BokSubject {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3474889922622081614L;

    /**
     * 现金流二级类型
     */
    private String cashType2;

    /**
     * 现金流二级类型名称
     */
    private String cashType2Name;

    /**
     * 资产代码
     */
    private String busiId;

    /**
     * 页面的业务类型
     */
    private String finprodType;

    /**
     * 资产代码对应的期限品种
     */
    private String termType;

    /**
     * 是否查询期限品种(Y:是，N:否)
     */
    private String termTypeFlag;
    
    /** modify by dongjun on 20181211 start**/
    /**
     * 资金帐户
     */
    private String acctCode;
    
    /**
     * 币种
     */
    private String ccy;
     
    /**
     * 获取 acctCode
     * @return acctCode.
     */
    public String getAcctCode() {
        return acctCode;
    }
 
    /**
     * 设置 acctCode
     * @param acctCode acctCode
     */
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }
 
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    /** modify by dongjun on 20181211 end**/
    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 cashType2
     * 
     * @return cashType2.
     */
    public String getCashType2() {
        return cashType2;
    }

    /**
     * 设置 cashType2
     * 
     * @param cashType2 cashType2
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    /**
     * 获取 cashType2Name
     * 
     * @return cashType2Name.
     */
    public String getCashType2Name() {
        return cashType2Name;
    }

    /**
     * 设置 cashType2Name
     * 
     * @param cashType2Name cashType2Name
     */
    public void setCashType2Name(String cashType2Name) {
        this.cashType2Name = cashType2Name;
    }

    /**
     * 获取 busiId
     * 
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 busiId
     * 
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 获取 业务类型
     * 
     * @return 业务类型.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 业务类型
     * 
     * @param finprodType 业务类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 期限品种
     * 
     * @return 期限品种.
     */
    public String getTermType() {
        return termType;
    }

    /**
     * 设置 期限品种
     * 
     * @param termType 期限品种
     */
    public void setTermType(String termType) {
        this.termType = termType;
    }

    /**
     * 获取 是否查询期限品种
     * 
     * @return 是否查询期限品种
     */
    public String getTermTypeFlag() {
        return termTypeFlag;
    }

    /**
     * 设置 是否查询期限品种
     * 
     * @param termTypeFlag 是否查询期限品种
     */
    public void setTermTypeFlag(String termTypeFlag) {
        this.termTypeFlag = termTypeFlag;
    }
}
