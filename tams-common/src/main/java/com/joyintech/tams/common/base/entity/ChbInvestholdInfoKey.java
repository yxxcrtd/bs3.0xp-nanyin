package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbInvestholdInfoKey extends BaseEntity {
    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * null
     */
    private String prodid;

    /**
     * 持有日期
     */
    private Date holdDate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 取得 登记银行代码
     * @return 登记银行代码
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 登记银行代码
     * @param registBankCode 登记银行代码
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
    }

    /**
     * 取得 识别标识
     * @return 识别标识
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 识别标识
     * @param indentifyMark 识别标识
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 null
     * @param prodid null
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 持有日期
     * @return 持有日期
     */
    public Date getHoldDate() {
        return holdDate;
    }

    /**
     * 设置 持有日期
     * @param holdDate 持有日期
     */
    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
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
}