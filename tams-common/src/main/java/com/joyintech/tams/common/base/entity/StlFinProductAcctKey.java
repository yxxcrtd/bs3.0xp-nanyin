package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlFinProductAcctKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 账户账号，存网点账号
     */
    private String acctNo;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 账户账号，存网点账号
     * @return 账户账号，存网点账号
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 账户账号，存网点账号
     * @param acctNo 账户账号，存网点账号
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
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