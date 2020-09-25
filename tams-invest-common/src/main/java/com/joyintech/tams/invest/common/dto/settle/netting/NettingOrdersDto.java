package com.joyintech.tams.invest.common.dto.settle.netting;

import com.joyintech.tams.common.base.entity.StlActOrder;

/**
 * @author 姚轶文
 * @create 2019- 11-08 16:18
 */
public class NettingOrdersDto extends StlActOrder {
    /**
     * 付款账号
     */
    private String payAcctNo;

    /**
     * 付款账号名称
     */
    private String payAcctName;

    /**
     * 付款方开户银行
     */
    private String bankDep;

    /**
     * 收款方开户银行
     */
    private String recBankDep;

    /**
     * 经办人
     */
    private String operatorUser;
    /**
     * 接收时间
     */
    private String  operatorTime;
    /**
     * 复核人
     */
    private String checkUser;
    /**
     * 复核时间
     */
    private String  checkTime;

    /**
     * 收款账号
     */
    private String recAcctNo;

    /**
     * 收款账号名称
     */
    private String recAcctName;

    /**
     * 收款账号网点
     */
    private String largePayNo;

    /**
     * 收款账号网点
     */
    private String inOutBank;

    /**
     * 组合名称
     */
    private String portfolioName;

    public String getPayAcctNo() {
        return payAcctNo;
    }

    public void setPayAcctNo(String payAcctNo) {
        this.payAcctNo = payAcctNo;
    }


    /**
     * 获取 operatorUser
     *
     * @return operatorUser
     */
    public String getOperatorUser() {
        if (operatorUser == null || "".equals(operatorUser))
            return "";
        return operatorUser;
    }

    /**
     * 获取 checkUser
     *
     * @return checkUser
     */
    public String getCheckUser() {
        if (checkUser == null || "".equals(checkUser))
            return "";
        return checkUser;
    }

    public String getPayAcctName() {
        return payAcctName;
    }

    public void setPayAcctName(String payAcctName) {
        this.payAcctName = payAcctName;
    }

    public String getBankDep() {
        return bankDep;
    }

    public void setBankDep(String bankDep) {
        this.bankDep = bankDep;
    }

    public String getRecBankDep() {
        return recBankDep;
    }

    public void setRecBankDep(String recBankDep) {
        this.recBankDep = recBankDep;
    }

    public void setOperatorUser(String operatorUser) {
        this.operatorUser = operatorUser;
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getRecAcctNo() {
        return recAcctNo;
    }

    public void setRecAcctNo(String recAcctNo) {
        this.recAcctNo = recAcctNo;
    }

    public String getRecAcctName() {
        return recAcctName;
    }

    public void setRecAcctName(String recAcctName) {
        this.recAcctName = recAcctName;
    }

    public String getLargePayNo() {
        return largePayNo;
    }

    public void setLargePayNo(String largePayNo) {
        this.largePayNo = largePayNo;
    }

    public String getInOutBank() {
        return inOutBank;
    }

    public void setInOutBank(String inOutBank) {
        this.inOutBank = inOutBank;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
}
