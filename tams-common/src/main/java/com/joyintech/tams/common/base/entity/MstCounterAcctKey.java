package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstCounterAcctKey extends BaseEntity {
    /**
     * 交易对手代码
     */
    private String counterId;

    /**
     * 账户账号，存网点账号
     */
    private String acctNo;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 取得 交易对手代码
     * @return 交易对手代码
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 交易对手代码
     * @param counterId 交易对手代码
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId == null ? null : counterId.trim();
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