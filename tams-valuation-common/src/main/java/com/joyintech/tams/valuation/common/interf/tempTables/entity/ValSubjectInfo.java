package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValSubjectInfo extends BaseEntity {
    /**
     * 标的代码
     */
    private String subjectId;

    /**
     * 标的类型
     */
    private String subjectType;

    /**
     * 账号
     */
    private String actNo;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 账户类型
     */
    private String actType;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 托管活期利息计提
     */
    private String isCalc;

    /**
     * 取得 标的代码
     * @return 标的代码
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 设置 标的代码
     * @param subjectId 标的代码
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    /**
     * 取得 标的类型
     * @return 标的类型
     */
    public String getSubjectType() {
        return subjectType;
    }

    /**
     * 设置 标的类型
     * @param subjectType 标的类型
     */
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType == null ? null : subjectType.trim();
    }

    /**
     * 取得 账号
     * @return 账号
     */
    public String getActNo() {
        return actNo;
    }

    /**
     * 设置 账号
     * @param actNo 账号
     */
    public void setActNo(String actNo) {
        this.actNo = actNo == null ? null : actNo.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 账户类型
     * @return 账户类型
     */
    public String getActType() {
        return actType;
    }

    /**
     * 设置 账户类型
     * @param actType 账户类型
     */
    public void setActType(String actType) {
        this.actType = actType == null ? null : actType.trim();
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 托管活期利息计提
     * @return 托管活期利息计提
     */
    public String getIsCalc() {
        return isCalc;
    }

    /**
     * 设置 托管活期利息计提
     * @param isCalc 托管活期利息计提
     */
    public void setIsCalc(String isCalc) {
        this.isCalc = isCalc == null ? null : isCalc.trim();
    }
}