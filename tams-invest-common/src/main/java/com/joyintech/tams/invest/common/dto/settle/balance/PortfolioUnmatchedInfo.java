package com.joyintech.tams.invest.common.dto.settle.balance;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-10 15:42
 **/
public class PortfolioUnmatchedInfo {

    /**
     * 账户代码
     */
    private String acctNo;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 账户
     */
    private String busiAcctNo;

    /**
     * 未匹配数量
     */
    private Long unmatchedNum;

    /**
     * 获取 acctNo
     *
     * @return acctNo
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 acctNo
     *
     * @param acctNo acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 portfolioName
     *
     * @return portfolioName
     */
    public String getPortfolioName() {
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

    /**
     * 获取 busiAcctNo
     *
     * @return busiAcctNo
     */
    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    /**
     * 设置 busiAcctNo
     *
     * @param busiAcctNo busiAcctNo
     */
    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    /**
     * 获取 unmatchedNum
     *
     * @return unmatchedNum
     */
    public Long getUnmatchedNum() {
        return unmatchedNum;
    }

    /**
     * 设置 unmatchedNum
     *
     * @param unmatchedNum unmatchedNum
     */
    public void setUnmatchedNum(Long unmatchedNum) {
        this.unmatchedNum = unmatchedNum;
    }
}
