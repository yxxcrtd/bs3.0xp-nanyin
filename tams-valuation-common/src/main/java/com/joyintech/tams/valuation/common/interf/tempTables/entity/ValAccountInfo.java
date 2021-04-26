package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValAccountInfo extends BaseEntity {
    /**
     * 账户
     */
    private String acctId;

    /**
     * 户名
     */
    private String acctName;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 开户行
     */
    private String bankDep;

    /**
     * 联行行号
     */
    private String corrNo;

    /**
     * 大额支付系统号
     */
    private String largePayno;

    /**
     * 境内外
     */
    private String blnArea;

    /**
     * 所属机构代码
     */
    private String belongId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 托管活期利息计提
     */
    private String isCalc;

    /**
     * 取得 账户
     * @return 账户
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * 设置 账户
     * @param acctId 账户
     */
    public void setAcctId(String acctId) {
        this.acctId = acctId == null ? null : acctId.trim();
    }

    /**
     * 取得 户名
     * @return 户名
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 户名
     * @param acctName 户名
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
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
     * 取得 开户行
     * @return 开户行
     */
    public String getBankDep() {
        return bankDep;
    }

    /**
     * 设置 开户行
     * @param bankDep 开户行
     */
    public void setBankDep(String bankDep) {
        this.bankDep = bankDep == null ? null : bankDep.trim();
    }

    /**
     * 取得 联行行号
     * @return 联行行号
     */
    public String getCorrNo() {
        return corrNo;
    }

    /**
     * 设置 联行行号
     * @param corrNo 联行行号
     */
    public void setCorrNo(String corrNo) {
        this.corrNo = corrNo == null ? null : corrNo.trim();
    }

    /**
     * 取得 大额支付系统号
     * @return 大额支付系统号
     */
    public String getLargePayno() {
        return largePayno;
    }

    /**
     * 设置 大额支付系统号
     * @param largePayno 大额支付系统号
     */
    public void setLargePayno(String largePayno) {
        this.largePayno = largePayno == null ? null : largePayno.trim();
    }

    /**
     * 取得 境内外
     * @return 境内外
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 境内外
     * @param blnArea 境内外
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea == null ? null : blnArea.trim();
    }

    /**
     * 取得 所属机构代码
     * @return 所属机构代码
     */
    public String getBelongId() {
        return belongId;
    }

    /**
     * 设置 所属机构代码
     * @param belongId 所属机构代码
     */
    public void setBelongId(String belongId) {
        this.belongId = belongId == null ? null : belongId.trim();
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
    
    private String belongInstitutionCode;

    public String getBelongInstitutionCode() {
        return belongInstitutionCode;
    }

    public void setBelongInstitutionCode(String belongInstitutionCode) {
        this.belongInstitutionCode = belongInstitutionCode;
    }
    
}