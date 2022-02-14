package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.header;

/**
 * 客户登录返回头-61
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespLoginHeader implements IRespHeader {
    /**
     * 姓名
     */
    private String name;
    /**
     * 市场代码
     */
    private char market;
    /**
     * 账号diam
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
     * 市场个数/股东账号个数
     */
    private int marketOrAccountSize;
    /**
     * 营业部名称
     */
    private String yybName;
    /**
     * 系统工作日期
     */
    private String sysDate;
    /**
     * 系统工作状态
     */
    private char sysStatus;
    /**
     * 系统工作状态名称
     */
    private String sysStatusName;
    /**
     * 开户营业部代码
     */
    private String yybCode;
    /**
     * 柜台版本号
     */
    private String versionNum;
    /**
     * 各主帐号代码信息
     */
    private String mainAccountCodeInfo;
    /**
     * 大集中备份历史日期
     */
    private String backupHistoryDate;
    /**
     * 客户报警信息
     */
    private String alarmInfo;
    /**
     * 报警代码
     */
    private String alarmCode;
    /**
     * 客户融资融券权限
     */
    private char marginAuth;
    /**
     * 上次登入信息
     */
    private String loginInfo;
    /**
     * 周边登录校验信息
     */
    private String loginVerifyInfo;
    /**
     * 开放式基金权限
     */
    private char openEndRight;
    /**
     * 客户权限
     */
    private String clientRights;
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
     * 客户警示信息标志
     */
    private String warnInfoTag;
    /**
     * 客户所属交易中心
     */
    private String tradeCenter;
    /**
     * 股权激励白名单标志
     */
    private int whitelistMark;
    /**
     * 客户警示信息
     */
    private String warnInfo;


    @Override
    public int getResponseCount() {
        return marketOrAccountSize;
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

    public int getMarketOrAccountSize() {
        return marketOrAccountSize;
    }

    public void setMarketOrAccountSize(int marketOrAccountSize) {
        this.marketOrAccountSize = marketOrAccountSize;
    }

    public String getYybName() {
        return yybName;
    }

    public void setYybName(String yybName) {
        this.yybName = yybName;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public char getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(char sysStatus) {
        this.sysStatus = sysStatus;
    }

    public String getSysStatusName() {
        return sysStatusName;
    }

    public void setSysStatusName(String sysStatusName) {
        this.sysStatusName = sysStatusName;
    }

    public String getYybCode() {
        return yybCode;
    }

    public void setYybCode(String yybCode) {
        this.yybCode = yybCode;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getMainAccountCodeInfo() {
        return mainAccountCodeInfo;
    }

    public void setMainAccountCodeInfo(String mainAccountCodeInfo) {
        this.mainAccountCodeInfo = mainAccountCodeInfo;
    }

    public String getBackupHistoryDate() {
        return backupHistoryDate;
    }

    public void setBackupHistoryDate(String backupHistoryDate) {
        this.backupHistoryDate = backupHistoryDate;
    }

    public String getAlarmInfo() {
        return alarmInfo;
    }

    public void setAlarmInfo(String alarmInfo) {
        this.alarmInfo = alarmInfo;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public char getMarginAuth() {
        return marginAuth;
    }

    public void setMarginAuth(char marginAuth) {
        this.marginAuth = marginAuth;
    }

    public String getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(String loginInfo) {
        this.loginInfo = loginInfo;
    }

    public String getLoginVerifyInfo() {
        return loginVerifyInfo;
    }

    public void setLoginVerifyInfo(String loginVerifyInfo) {
        this.loginVerifyInfo = loginVerifyInfo;
    }

    public char getOpenEndRight() {
        return openEndRight;
    }

    public void setOpenEndRight(char openEndRight) {
        this.openEndRight = openEndRight;
    }

    public String getClientRights() {
        return clientRights;
    }

    public void setClientRights(String clientRights) {
        this.clientRights = clientRights;
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

    public String getWarnInfoTag() {
        return warnInfoTag;
    }

    public void setWarnInfoTag(String warnInfoTag) {
        this.warnInfoTag = warnInfoTag;
    }

    public String getTradeCenter() {
        return tradeCenter;
    }

    public void setTradeCenter(String tradeCenter) {
        this.tradeCenter = tradeCenter;
    }

    public int getWhitelistMark() {
        return whitelistMark;
    }

    public void setWhitelistMark(int whitelistMark) {
        this.whitelistMark = whitelistMark;
    }

    public String getWarnInfo() {
        return warnInfo;
    }

    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo;
    }
}
