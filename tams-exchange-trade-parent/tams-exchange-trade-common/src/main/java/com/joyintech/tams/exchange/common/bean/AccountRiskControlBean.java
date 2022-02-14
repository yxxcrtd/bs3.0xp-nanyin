package com.joyintech.tams.exchange.common.bean;


/**
 * 投管系统账户风控查询返回bean
 */
public class AccountRiskControlBean {

    /**
     * 所属券商ID
     */
    private String brockerId;

    /**
     * 资金账号
     */
    private String busiAcctNo;

    /**
     * 账户风控类型
     */
    private String acctRiskType;

    /**
     * 账户风控值
     */
    private String acctRiskValue;

    public String getBrockerId() {
        return brockerId;
    }

    public void setBrockerId(String brockerId) {
        this.brockerId = brockerId;
    }

    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    public String getAcctRiskType() {
        return acctRiskType;
    }

    public void setAcctRiskType(String acctRiskType) {
        this.acctRiskType = acctRiskType;
    }

    public String getAcctRiskValue() {
        return acctRiskValue;
    }

    public void setAcctRiskValue(String acctRiskValue) {
        this.acctRiskValue = acctRiskValue;
    }
}
