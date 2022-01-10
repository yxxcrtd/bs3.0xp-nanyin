package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.header;

/**
 * 账号检验响应-1
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespAccountCheckHeader implements IRespHeader {
    /**
     * 姓名
     */
    private String name;
    /**
     * 市场代码
     */
    private char market;
    /**
     * 账号代码
     */
    private String account;
    /**
     * 货币代码
     */
    private char moneyType;
    /**
     * 客户号
     */
    private String accountNum;
    /**
     * 柜台版本号
     */
    private char versionNum;
    /**
     * 营业部代码
     */
    private String yybId;
    /**
     * 报警信息
     */
    private String warnMsg;
    /**
     * 报警信息代码
     */
    private String warnCode;
    /**
     * 融资融券客户权限
     */
    private char marginTradingRights;
    /**
     * 开放式基金权限
     */
    private char openEndRight;
    /**
     * 客户权限
     */
    private String customerRights;
    /**
     * 报价回购权限
     */
    private char buybackRights;
    /**
     * 客户是否开通强身份认证
     */
    private char strongIDAuth;
    /**
     * 该委托方式是否启用强身份认证平台
     */
    private char strongIDAuthPlatform;
    /**
     * 客户所属交易中心
     */
    private String tradeCenter;

    @Override
    public int getResponseCount() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getMarket() {
        return market;
    }

    public void setMarket(char market) {
        this.market = market;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public char getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(char moneyType) {
        this.moneyType = moneyType;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public char getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(char versionNum) {
        this.versionNum = versionNum;
    }

    public String getYybId() {
        return yybId;
    }

    public void setYybId(String yybId) {
        this.yybId = yybId;
    }

    public String getWarnMsg() {
        return warnMsg;
    }

    public void setWarnMsg(String warnMsg) {
        this.warnMsg = warnMsg;
    }

    public String getWarnCode() {
        return warnCode;
    }

    public void setWarnCode(String warnCode) {
        this.warnCode = warnCode;
    }

    public char getMarginTradingRights() {
        return marginTradingRights;
    }

    public void setMarginTradingRights(char marginTradingRights) {
        this.marginTradingRights = marginTradingRights;
    }

    public char getOpenEndRight() {
        return openEndRight;
    }

    public void setOpenEndRight(char openEndRight) {
        this.openEndRight = openEndRight;
    }

    public String getCustomerRights() {
        return customerRights;
    }

    public void setCustomerRights(String customerRights) {
        this.customerRights = customerRights;
    }

    public char getBuybackRights() {
        return buybackRights;
    }

    public void setBuybackRights(char buybackRights) {
        this.buybackRights = buybackRights;
    }

    public char getStrongIDAuth() {
        return strongIDAuth;
    }

    public void setStrongIDAuth(char strongIDAuth) {
        this.strongIDAuth = strongIDAuth;
    }

    public char getStrongIDAuthPlatform() {
        return strongIDAuthPlatform;
    }

    public void setStrongIDAuthPlatform(char strongIDAuthPlatform) {
        this.strongIDAuthPlatform = strongIDAuthPlatform;
    }

    public String getTradeCenter() {
        return tradeCenter;
    }

    public void setTradeCenter(String tradeCenter) {
        this.tradeCenter = tradeCenter;
    }
}
