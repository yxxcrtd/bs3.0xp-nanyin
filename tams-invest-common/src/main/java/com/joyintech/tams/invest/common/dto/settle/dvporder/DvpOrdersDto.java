package com.joyintech.tams.invest.common.dto.settle.dvporder;

import com.joyintech.tams.common.base.entity.StlDvpOrders;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2019-01-13 16:37
 **/
public class DvpOrdersDto extends StlDvpOrders {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8417551225285857092L;

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

    /**
     * 获取 inOutBank
     *
     * @return inOutBank
     */
    public String getInOutBank() {
        if (inOutBank == null || "".equals(inOutBank)) {
            return "";
        }
        return inOutBank;
    }

    /**
     * 设置 inOutBank
     *
     * @param inOutBank inOutBank
     */
    public void setInOutBank(String inOutBank) {
        this.inOutBank = inOutBank;
    }

    /**
     * 获取 payAcctNo
     *
     * @return payAcctNo
     */
    public String getPayAcctNo() {
        if (payAcctNo == null || "".equals(payAcctNo))
            return "";
        return payAcctNo;
    }

    /**
     * 设置 payAcctNo
     *
     * @param payAcctNo payAcctNo
     */
    public void setPayAcctNo(String payAcctNo) {
        this.payAcctNo = payAcctNo;
    }

    /**
     * 获取 payAcctName
     *
     * @return payAcctName
     */
    public String getPayAcctName() {
        if (payAcctName == null || "".equals(payAcctName))
            return "";
        return payAcctName;
    }

    /**
     * 设置 payAcctName
     *
     * @param payAcctName payAcctName
     */
    public void setPayAcctName(String payAcctName) {
        this.payAcctName = payAcctName;
    }

    /**
     * 获取 bankDep
     *
     * @return bankDep
     */
    public String getBankDep() {
        if (bankDep == null || "".equals(bankDep))
            return "";
        return bankDep;
    }

    /**
     * 设置 bankDep
     *
     * @param bankDep bankDep
     */
    public void setBankDep(String bankDep) {
        this.bankDep = bankDep;
    }

    /**
     * 获取 recBankDep
     *
     * @return recBankDep
     */
    public String getRecBankDep() {
        if (recBankDep == null || "".equals(recBankDep))
            return "";
        return recBankDep;
    }

    /**
     * 设置 recBankDep
     *
     * @param recBankDep recBankDep
     */
    public void setRecBankDep(String recBankDep) {
        this.recBankDep = recBankDep;
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
     * 设置 operatorUser
     *
     * @param operatorUser operatorUser
     */
    public void setOperatorUser(String operatorUser) {
        this.operatorUser = operatorUser;
    }

    /**
     * 获取 operatorTime
     *
     * @return operatorTime
     */
    public String getOperatorTime() {
        if (operatorTime == null || "".equals(operatorTime))
            return "";
        return operatorTime;
    }

    /**
     * 设置 operatorTime
     *
     * @param operatorTime operatorTime
     */
    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
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

    /**
     * 设置 checkUser
     *
     * @param checkUser checkUser
     */
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    /**
     * 获取 checkTime
     *
     * @return checkTime
     */
    public String getCheckTime() {
        if (checkTime == null || "".equals(checkTime))
            return "";
        return checkTime;
    }

    /**
     * 设置 checkTime
     *
     * @param checkTime checkTime
     */
    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * 获取 recAcctNo
     *
     * @return recAcctNo
     */
    public String getRecAcctNo() {
        if (recAcctNo == null || "".equals(recAcctNo))
            return "";
        return recAcctNo;
    }

    /**
     * 设置 recAcctNo
     *
     * @param recAcctNo recAcctNo
     */
    public void setRecAcctNo(String recAcctNo) {
        this.recAcctNo = recAcctNo;
    }

    /**
     * 获取 recAcctName
     *
     * @return recAcctName
     */
    public String getRecAcctName() {
        if (recAcctName == null || "".equals(recAcctName))
            return "";
        return recAcctName;
    }

    /**
     * 设置 recAcctName
     *
     * @param recAcctName recAcctName
     */
    public void setRecAcctName(String recAcctName) {
        this.recAcctName = recAcctName;
    }

    /**
     * 获取 largePayNo
     *
     * @return largePayNo
     */
    public String getLargePayNo() {
        if (largePayNo == null || "".equals(largePayNo))
            return "";
        return largePayNo;
    }

    /**
     * 设置 largePayNo
     *
     * @param largePayNo largePayNo
     */
    public void setLargePayNo(String largePayNo) {
        this.largePayNo = largePayNo;
    }

    /**
     * 获取 portfolioName
     *
     * @return portfolioName
     */
    public String getPortfolioName() {
        if (portfolioName == null || "".equals(portfolioName))
            return "";
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     *
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
}
