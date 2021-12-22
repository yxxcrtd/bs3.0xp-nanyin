package com.joyintech.tams.exchange.common.bean.investment;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.db.domain.Account;
import com.joyintech.tams.exchange.common.utils.AccountUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 投管系统资金账户查询返回bean
 */
@ApiModel(value = "查询账户资金返回报文", description = "查询账户资金返回报文")
public class CapitalAccountBean implements Serializable {
    private static final long serialVersionUID = -7758117880214238801L;

    @ApiModelProperty(name = "acctNo", value = "网点账号")
    private String acctNo;

    @ApiModelProperty(name = "busiAcctNo", value = "资金账号")
    private String busiAcctNo;

    @ApiModelProperty(name = "accountName", value = "账户姓名")
    private String accountName;

    @ApiModelProperty(name = "CCY", value = "币种")
    private String CCY;

    @ApiModelProperty(name = "acctType", value = "资金账户类型")
    private String acctType;

    @ApiModelProperty(name = "stockExchangeNo", value = "券商营业部代码")
    private String stockExchangeNo;

    @ApiModelProperty(name = "status", value = "启用状态")
    private String status;

    @ApiModelProperty(name = "acctPwd", value = "账户密码")
    private String acctPwd;

    @ApiModelProperty(name = "tradePwd", value = "交易密码")
    private String tradePwd;

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    public String getCCY() {
        return CCY;
    }

    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getStockExchangeNo() {
        return stockExchangeNo;
    }

    public void setStockExchangeNo(String stockExchangeNo) {
        this.stockExchangeNo = stockExchangeNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAcctPwd() {
        return acctPwd;
    }

    public void setAcctPwd(String acctPwd) {
        this.acctPwd = acctPwd;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Account createAccount() {
        try {
            Account account = new Account();
            if (Strings.isNullOrEmpty(this.acctNo)
                    || Strings.isNullOrEmpty(this.busiAcctNo)
                    || Strings.isNullOrEmpty(this.CCY)
                    || Strings.isNullOrEmpty(this.acctType)
                    || Strings.isNullOrEmpty(this.stockExchangeNo)
                    || Strings.isNullOrEmpty(this.status)
                    || Strings.isNullOrEmpty(this.acctPwd)
                    || Strings.isNullOrEmpty(this.tradePwd)) {
                return null;
            }
            account.setId(this.acctNo);
            account.setFinanceAccount(this.busiAcctNo);
            account.setAccountName(this.accountName);
            account.setCurrencyType(this.CCY);
            account.setAccountType(AccountUtils.convertAccountTypeAndAgentType(this.acctType));
            account.setBrokerId(this.stockExchangeNo);
            account.setUsingStatus(Short.valueOf(this.status));
            account.setPwd(this.acctPwd);
            account.setTradePwd(this.tradePwd);
            return account;
        } catch (Exception e) {
            return null;
        }
    }
}
