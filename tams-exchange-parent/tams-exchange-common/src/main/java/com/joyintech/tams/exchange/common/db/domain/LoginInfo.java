package com.joyintech.tams.exchange.common.db.domain;

import java.io.Serializable;
import java.util.List;

/**
 * create By MAXX on 2020/3/13
 */
public class LoginInfo implements Serializable {
    private Integer initDate;//交易日期
    private String sysStatus;//系统状态
    private String companyName;//公司名称
    private String contentType;//银行号、市场类别
    private String accountContent;//输入内容
    private Integer branchNo;//分支机构
    private String clientId;//客户编号
    private String clientName;//客户姓名
    private Integer corpClientGroup;//公司客户类型
    private Integer corpRiskLevel;//客户风险等级
    private Integer corpEndDate;//客户风险到期日
    private String validFlag;//有效标志
    private Integer fundaccountCount;//资产账号个数
    private String fundAccount;//资产账户
    private String clientRights;//客户权限
    private String lastOpEntrustWay;//上次登陆委托方式
    private String lastOpStation;//上次登陆站点/电话
    private Integer sysnodeId;//系统节点编号
    private String userToken;//用户口令
    private Integer errorNo;//错误代码
    private String errorInfo;//错误提示
    private String assetProp;//资产属性
    private String productFlag;//产品标志
    private String messageFlag;//消息标志
    private String tabconfirmFlag;//强制账单确认
    private Integer lastDate;//上次提成日期
    private Integer sessionNo;//会话编号
    private String foreignFlag;//境外标志
    private String passwordRiskTips;//密码风险提示
    private String inputContent;//客户标志类别
    private String terminalVersion;//终端版本信息
    private Integer uftSysnodeId;//UFT系统节点编号
    private List<ClientStkacct> clientStkacctList;//证券股东信息

    public Integer getInitDate() {
        return initDate;
    }

    public void setInitDate(Integer initDate) {
        this.initDate = initDate;
    }

    public String getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(String sysStatus) {
        this.sysStatus = sysStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAccountContent() {
        return accountContent;
    }

    public void setAccountContent(String accountContent) {
        this.accountContent = accountContent;
    }

    public Integer getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(Integer branchNo) {
        this.branchNo = branchNo;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getCorpClientGroup() {
        return corpClientGroup;
    }

    public void setCorpClientGroup(Integer corpClientGroup) {
        this.corpClientGroup = corpClientGroup;
    }

    public Integer getCorpRiskLevel() {
        return corpRiskLevel;
    }

    public void setCorpRiskLevel(Integer corpRiskLevel) {
        this.corpRiskLevel = corpRiskLevel;
    }

    public Integer getCorpEndDate() {
        return corpEndDate;
    }

    public void setCorpEndDate(Integer corpEndDate) {
        this.corpEndDate = corpEndDate;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public Integer getFundaccountCount() {
        return fundaccountCount;
    }

    public void setFundaccountCount(Integer fundaccountCount) {
        this.fundaccountCount = fundaccountCount;
    }

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public String getClientRights() {
        return clientRights;
    }

    public void setClientRights(String clientRights) {
        this.clientRights = clientRights;
    }

    public String getLastOpEntrustWay() {
        return lastOpEntrustWay;
    }

    public void setLastOpEntrustWay(String lastOpEntrustWay) {
        this.lastOpEntrustWay = lastOpEntrustWay;
    }

    public String getLastOpStation() {
        return lastOpStation;
    }

    public void setLastOpStation(String lastOpStation) {
        this.lastOpStation = lastOpStation;
    }

    public Integer getSysnodeId() {
        return sysnodeId;
    }

    public void setSysnodeId(Integer sysnodeId) {
        this.sysnodeId = sysnodeId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Integer getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(Integer errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getAssetProp() {
        return assetProp;
    }

    public void setAssetProp(String assetProp) {
        this.assetProp = assetProp;
    }

    public String getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag;
    }

    public String getMessageFlag() {
        return messageFlag;
    }

    public void setMessageFlag(String messageFlag) {
        this.messageFlag = messageFlag;
    }

    public String getTabconfirmFlag() {
        return tabconfirmFlag;
    }

    public void setTabconfirmFlag(String tabconfirmFlag) {
        this.tabconfirmFlag = tabconfirmFlag;
    }

    public Integer getLastDate() {
        return lastDate;
    }

    public void setLastDate(Integer lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getSessionNo() {
        return sessionNo;
    }

    public void setSessionNo(Integer sessionNo) {
        this.sessionNo = sessionNo;
    }

    public String getForeignFlag() {
        return foreignFlag;
    }

    public void setForeignFlag(String foreignFlag) {
        this.foreignFlag = foreignFlag;
    }

    public String getPasswordRiskTips() {
        return passwordRiskTips;
    }

    public void setPasswordRiskTips(String passwordRiskTips) {
        this.passwordRiskTips = passwordRiskTips;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }

    public String getTerminalVersion() {
        return terminalVersion;
    }

    public void setTerminalVersion(String terminalVersion) {
        this.terminalVersion = terminalVersion;
    }

    public Integer getUftSysnodeId() {
        return uftSysnodeId;
    }

    public void setUftSysnodeId(Integer uftSysnodeId) {
        this.uftSysnodeId = uftSysnodeId;
    }

    public List<ClientStkacct> getClientStkacctList() {
        return clientStkacctList;
    }

    public void setClientStkacctList(List<ClientStkacct> clientStkacctList) {
        this.clientStkacctList = clientStkacctList;
    }


}
