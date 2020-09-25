package com.joyintech.tams.invest.trade.pretrdrepotrade.dto;

import com.joyintech.framework.validation.annotation.NotEmpty;

/*
*
* 人员信息
* */
public class TrdInvestPersonInfoDto {

    /**
     * 本方交易员
     */

    private String trader;
    /**
     * 交易对手
     */

    private String counterId;
    /**
     * 对方交易员
     */

    private String counterTrader;

    /**
     * 资金账户
     */

    private String acctNo;
    /**
     * 资金账户名
     */
    private String acctName;
    /**
     * 资金开户行
     */
    private String acctBankDep;
    /**
     * 资金开户行支付系统行号
     */
    private String acctBankDepoPayno;
    /**
     * 托管账号
     */

    private String secAcctId;
    /**
     * 托管账户户名
     */
    private String secAcctName;
    /**
     * 托管机构名称
     */
    private String registOrg;

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getAcctBankDep() {
        return acctBankDep;
    }

    public void setAcctBankDep(String acctBankDep) {
        this.acctBankDep = acctBankDep;
    }

    public String getAcctBankDepoPayno() {
        return acctBankDepoPayno;
    }

    public void setAcctBankDepoPayno(String acctBankDepoPayno) {
        this.acctBankDepoPayno = acctBankDepoPayno;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getSecAcctName() {
        return secAcctName;
    }

    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    public String getRegistOrg() {
        return registOrg;
    }

    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }
}
